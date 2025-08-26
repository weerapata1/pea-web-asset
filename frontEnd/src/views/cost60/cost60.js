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
  name: "cost60viewer",
  components: { BarChart },
  data() {
    return {
      barData: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
        datasets: [
          {
            label: "Sales",
            backgroundColor: "rgba(128,0,128,0.8)",
            data: [12, 19, 3, 5, 2, 3],
          },
        ],
      },
      barOptions: {
        responsive: true,
        maintainAspectRatio: false,
        legend: { display: true },
        scales: {
          yAxes: [{ ticks: { beginAtZero: true } }],
          xAxes: [{ gridLines: { display: false } }],
        },
      },
      loading: false,
      records:[],
    };
  },

  computed: {},

  watch: {},

  mounted() {
    // Example: update data later (reactiveProp in BarChart will re-render)
    this.getAllCost60();

    // setTimeout(() => {
    //   this.barData = {
    //     ...this.barData,
    //     datasets: [{ ...this.barData.datasets[0], data: [8, 14, 6, 10, 4, 7] }],
    //   };
    // }, 3000);
  },

  created() {},

  methods: {
    async getAllCost60() {
      this.loading = true;
      try {
        // const response = await axios.get("http://localhost:8080/emp/getEmpAll");
        const response = await axios.get(
          `${process.env.VUE_APP_BASE_URL}/api/cost/getAllCost60`
        );
        this.records = response.data;
        console.log(this.records);
        // this.records60 = response.data.data1.map((item) => ({
        //   empId: item[0],
        //   empName: item[1],
        //   empDep_full: item[2],
        //   empRank: item[3],
        //   ccLongCode: item[4],
        
        // }));
      } catch (error) {
        console.error(error);
      } finally {
        this.loadingEmp = false;
      }

      this.loading = false;
    },
  },
};
