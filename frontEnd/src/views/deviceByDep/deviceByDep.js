import Vue from "vue";

import JsonExcel from "vue-json-excel";
Vue.component("downloadExcel", JsonExcel);

import { mdiMicrosoftExcel } from "@mdi/js";
Vue.component("mdiMicrosoftExcel", mdiMicrosoftExcel);

import { mdiFileFindOutline } from "@mdi/js";
Vue.component("mdiFileFindOutline", mdiFileFindOutline);

import { mdiMagnify } from "@mdi/js";
Vue.component("mdiMagnify", mdiMagnify);

import { mdiQrcode } from "@mdi/js";
Vue.component("mdiQrcode", mdiQrcode);

import Treeselect from "@riophae/vue-treeselect";
Vue.component("treeselect", Treeselect);

import "@riophae/vue-treeselect/dist/vue-treeselect.css";

import axios from "axios";
import BarChart from "@/components/BarChart.vue";

export default {
  name: "deviceByDep",
  components: { BarChart },
  data() {
    return {
      expandedRegions: [],
      expandedDivisions: [],
      expandedDepartments: {},
      rows: [],
      loading: false,
      deviceByDep: [],
      error: null,
    };
  },

  computed: {
    regions() {
      const regionMap = new Map();

      for (const r of this.rows) {
        // tag rows by year
        r._tag = this.tagRowByYear(r);

        // region
        const regionKey = this.getRegionKey(r.ccLongCode);
        if (!regionMap.has(regionKey)) {
          regionMap.set(regionKey, {
            regionKey,
            regionLabel: null,
            divisions: new Map(), // divisionCode -> division
            totalRecords: 0,
            newCount: 0,
            oldCount: 0,
            unknownCount: 0,
            firstDept: null,
          });
        }
        const reg = regionMap.get(regionKey);
        reg.totalRecords++;
        if (r._tag === "new") reg.newCount++;
        else if (r._tag === "old") reg.oldCount++;
        else reg.unknownCount++;

        // division inside region (use the divisionCode already provided)
        const divKey = r.divisionCode || "UNKNOWN_DIV";
        if (!reg.divisions.has(divKey)) {
          reg.divisions.set(divKey, {
            divisionCode: divKey,
            divisionCount: r.divisionCount ?? 0,
            departmentCount: r.departmentCount ?? 0,
            departments: new Map(), // ccLongCode -> dept
            totalRecords: 0,
            newCount: 0,
            oldCount: 0,
            unknownCount: 0,
            firstDept: null,
          });
        }
        const div = reg.divisions.get(divKey);
        div.totalRecords++;
        if (r._tag === "new") div.newCount++;
        else if (r._tag === "old") div.oldCount++;
        else div.unknownCount++;
        if (typeof r.divisionCount === "number")
          div.divisionCount = Math.max(div.divisionCount, r.divisionCount);
        if (typeof r.departmentCount === "number")
          div.departmentCount = Math.max(
            div.departmentCount,
            r.departmentCount
          );

        // keep max if counts vary
        if (typeof r.divisionCount === "number") {
          div.divisionCount = Math.max(div.divisionCount, r.divisionCount);
        }
        if (typeof r.departmentCount === "number") {
          div.departmentCount = Math.max(
            div.departmentCount,
            r.departmentCount
          );
        }

        const deptKey = r.ccLongCode || "UNKNOWN";
        if (!div.departments.has(deptKey)) {
          const deptObj = {
            ccLongCode: deptKey,
            ccShortName: r.ccShortName || null,
            items: [],
            newCount: 0,
            oldCount: 0,
            unknownCount: 0,
          };
          div.departments.set(deptKey, deptObj);
          if (!div.firstDept) div.firstDept = deptObj; // set first dept for division
          if (!reg.firstDept) reg.firstDept = deptObj; // set first dept for region
        }
        const dept = div.departments.get(deptKey);
        dept.items.push(r);
        if (r._tag === "new") dept.newCount++;
        else if (r._tag === "old") dept.oldCount++;
        else dept.unknownCount++;
      }

      return Array.from(regionMap.values())
        .map((reg) => {
          const divisionsArr = Array.from(reg.divisions.values())
            .map((div) => {
              const deptsArr = Array.from(div.departments.values())
                .map((d) => ({
                  ...d,
                  items: d.items.sort((a, b) =>
                    String(a.deviceId).localeCompare(String(b.deviceId))
                  ),
                }))
                .sort((a, b) =>
                  String(a.ccLongCode).localeCompare(String(b.ccLongCode))
                );
              // ensure firstDept follows sorted order if not set
              const firstDeptDiv = div.firstDept || deptsArr[0] || null;
              return { ...div, departments: deptsArr, firstDept: firstDeptDiv };
            })
            .sort((a, b) =>
              String(a.divisionCode).localeCompare(String(b.divisionCode))
            );

          const regionFirstDept =
            reg.firstDept || divisionsArr[0]?.departments?.[0] || null;
          const regionLabel = regionFirstDept
            ? regionFirstDept.ccLongCode
            : reg.regionKey;

          return {
            ...reg,
            divisions: divisionsArr,
            firstDept: regionFirstDept,
            regionLabel,
          };
        })
        .sort((a, b) => String(a.regionKey).localeCompare(String(b.regionKey)));
    },
  },

  watch: {},

  mounted() {
    this.getCountDeviceByDep();

    // setTimeout(() => {
    //   this.barData = {
    //     ...this.barData,
    //     datasets: [{ ...this.barData.datasets[0], data: [8, 14, 6, 10, 4, 7] }],
    //   };
    // }, 3000);
  },

  created() {},

  methods: {
    async getCountDeviceByDep() {
      this.loading = true;
      try {
        // const response = await axios.get("http://localhost:8080/emp/getEmpAll");
        const response = await axios.get(
          `${process.env.VUE_APP_BASE_URL}/api/dev/countDeviceByDep`
        );
        this.deviceByDep = response.data.data.data;

        // this.records60 = response.data.data.map((item) => ({
        //   recordsPerMonth: item[0],
        //   valuePerMonth: item[1],
        //   yearMonth: item[2],
        // }));
        this.rows = this.deviceByDep;
        console.log(this.rows);
      } catch (error) {
        console.error(error);
      } finally {
        this.loadingEmp = false;
      }

      this.loading = false;
    },

    beYear(dateStr) {
      if (!dateStr) return null;
      const parts = String(dateStr)
        .trim()
        .split(/[-./\s]+/);
      const y = Number(parts[0]);
      return Number.isFinite(y) ? y : null;
    },

    tagRowByYear(row) {
      const y = this.beYear(row.devReceivedDate);
      if (y == null) return "unknown";
      if (y >= 2561) return "new";
      if (y <= 2560) return "old";
      return "unknown";
    },

    getRegionKey(ccLongCode) {
      if (!ccLongCode) return "UNKNOWN";
      return String(ccLongCode).slice(0, 6);
    },
    // For display like "E30101xxxx"
    getRegionLabel(regionKey) {
      return `${regionKey}xxxx`;
    },
  },
};
