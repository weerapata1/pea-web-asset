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

import * as XLSX from "xlsx";

import dateService from "../../services/dateService";

export default {
  name: "EventsList",
  data() {
    return {
      selectedFile: null,
      fileName: "",
      alert: false,
      expectedHeaders: [
        "สินทรัพย์",
        "SNo.",
        "InvNo.",
        "คำอธิบายของสินทรัพย์",
        "ศ.ต้นทุน",
        "ศูนย์กำไร",
        "Cap.date",
        "มูลค่าการได้มา",
        "ค่าเสื่อมสะสม",
        "มูลค่าตามบัญชี",
        "Pers.No.",
        "เลขที่ผลิตภัณฑ์",
      ],
      tableItems: [],
      tableHeaders: [
        { text: "devPeaNo", value: "devPeaNo" },
        { text: "Description", value: "dev_description" },
        { text: "Serial No.", value: "dev_serial_no" },
        { text: "Employee ID", value: "emp_id" },
        { text: "Cost Center", value: "cc_long_code" },
        { text: "Received Price", value: "dev_received_price" },
        { text: "Left Price", value: "dev_left_price" },
        { text: "Received Date", value: "dev_received_date" },
      ],
      loading: false,
      isReadFileValid: false,
      uploadItems: [],
      // headerMap: [
      //   { สินทรัพย์: "devPeaNo" },
      //   { คำอธิบายของสินทรัพย์: "devDescription" },
      //   { เลขที่ผลิตภัณฑ์: "devSerialNo" },
      //   { "Cap.date": "devReceivedDate" },
      //   { มูลค่าการได้มา: "devReceivedPrice" },
      //   { ค่าเสื่อมสะสม: "devLeftPrice" },
      //   { "ศ.ต้นทุน": "ccLongCode" },
      //   { "Pers.No.": "empId" },
      // ],
      headerMap: {
        สินทรัพย์: "devPeaNo",
        คำอธิบายของสินทรัพย์: "devDescription",
        เลขที่ผลิตภัณฑ์: "devSerialNo",
        "Cap.date": "devReceivedDate",
        มูลค่าการได้มา: "devReceivedPrice",
        มูลค่าตามบัญชี: "devLeftPrice",
        "ศ.ต้นทุน": "ccLongCode",
        "Pers.No.": "empId",
      },
      uploadFinish: false,
      uploadFinishtime: Date.now(),
      uploadSuccess: false,
    };
  },

  watch: {},

  mounted() {},

  created() {},

  methods: {
    triggerFileSelect() {
      console.log("triggerFileSelect called");
      this.$refs.fileInput.click();
    },
    onFileSelected(event) {
      const file = event.target.files[0];
      if (file) {
        const allowedTypes = [
          "application/vnd.ms-excel",
          "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        ];
        const fileExtension = file.name.split(".").pop().toLowerCase();

        if (
          !allowedTypes.includes(file.type) &&
          fileExtension !== "xls" &&
          fileExtension !== "xlsx"
        ) {
          this.alert = true;
          this.fileName = "";
          this.selectedFile = null;
          console.warn("Invalid file type");
          return;
        }

        this.selectedFile = file;
        this.fileName = file.name;
        this.alert = false;
        console.log("✅ Valid Excel file selected:", this.fileName);
        // Optional: auto upload or validate here
      }
    },
    processReadFile() {
      this.loading = true;
      this.uploadFinish = false;
      console.log("loading state:", this.loading);
      setTimeout(() => {
        console.log("loading done");
        this.loading = false;
      }, 2000);

      console.log("XLSX", XLSX);
      if (!this.selectedFile) {
        this.alert = true;
        setTimeout(() => {
          this.alert = false;
        }, 3000);
        return;
      }

      this.alert = false;

      const reader = new FileReader();

      reader.onload = (e) => {
        const data = new Uint8Array(e.target.result);
        console.log("check2 data", data);
        try {
          console.log("check5", XLSX.utils);
          const workbook = XLSX.read(data, { type: "array" });
          console.log("check3", workbook);

          const sheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[sheetName];

          console.log("check4", worksheet);

          const rows = XLSX.utils.sheet_to_json(worksheet, {
            header: 1,
            defval: "",
          });
          console.log("✅ rows", rows);

          const headerRowIndex = rows.findIndex((row) => {
            if (!Array.isArray(row)) return false;

            const nonEmptyCells = row.filter(
              (col) => String(col).trim() !== ""
            ).length;
            if (nonEmptyCells === 0) return false; // 🚫 Skip completely empty rows

            const matchCount = row.reduce((count, col) => {
              const trimmed = String(col).trim();
              return this.expectedHeaders.includes(trimmed) ? count + 1 : count;
            }, 0);

            return matchCount >= this.expectedHeaders.length / 2;
          });

          if (headerRowIndex !== -1) {
            const headerRow = rows[headerRowIndex];
            console.log(
              "✅ Found header row at index",
              headerRowIndex,
              headerRow
            );
            const HEADER_ROW_INDEX = headerRowIndex;
            const dataRows = rows.slice(headerRowIndex + 1);
            const size = dataRows.length;
            const DISPLAY_LIMIT = 1000;
            const DISPLAY_START = Math.max(
              0,
              Math.floor(size / 2 - DISPLAY_LIMIT / 2)
            );

            const headers = rows[HEADER_ROW_INDEX].map((h) => h.trim());

            const allValidRecords = dataRows
              .filter((row) => {
                const assetIndex = headers.findIndex((h) => h === "สินทรัพย์");
                if (assetIndex === -1) return false;
                const isEmptyRow = row.every(
                  (cell) => String(cell).trim() === ""
                );
                const hasAssetValue =
                  row[assetIndex] && String(row[assetIndex]).trim() !== "";
                return !isEmptyRow && hasAssetValue;
              })
              .map((row) => {
                const record = {};
                headers.forEach((header, index) => {
                  record[header] = row[index] ?? "";
                });
                return record;
              });

            const previewRows = allValidRecords.slice(
              DISPLAY_START,
              DISPLAY_START + DISPLAY_LIMIT
            );

            const formatCapDate = (rawDate) => {
              const parts = String(rawDate).trim().split(".");
              if (parts.length === 3) {
                const [day, month, year] = parts;
                return `${year}.${month.padStart(2, "0")}.${day.padStart(
                  2,
                  "0"
                )}`;
              }
              return ""; // or keep rawDate if you prefer
            };

            this.tableItems = previewRows.map((item) => ({
              devPeaNo: `${item["สินทรัพย์"] ?? ""}-${item["SNo."] ?? ""}`,
              dev_description: item["คำอธิบายของสินทรัพย์"] ?? "",
              dev_serial_no: item["เลขที่ผลิตภัณฑ์"] ?? "",
              emp_id: item["Pers.No."] ?? "",
              cc_long_code: item["ศ.ต้นทุน"] ?? "",
              dev_received_price: item["มูลค่าการได้มา"] ?? "",
              dev_left_price: item["มูลค่าตามบัญชี"] ?? "",
              dev_received_date: formatCapDate(item["Cap.date"]),
            }));

            // const headerMap = {
            //   สินทรัพย์: "devPeaNo",
            //   คำอธิบายของสินทรัพย์: "devDescription",
            //   เลขที่ผลิตภัณฑ์: "devSerialNo",
            //   "Cap.date": "devReceivedDate",
            //   มูลค่าการได้มา: "devReceivedPrice",
            //   ค่าเสื่อมสะสม: "devLeftPrice",
            //   "ศ.ต้นทุน": "ccLongCode",
            //   "Pers.No.": "empId",
            //   ศูนย์กำไร: "ccLongCodeString",
            // };

            const mappedRecords = allValidRecords.map((row) => {
              const mapped = {};
              for (const [thaiKey, backendKey] of Object.entries(
                this.headerMap
              )) {
                mapped[backendKey] = row[thaiKey] ?? "";
              }
              return mapped;
            });

            this.uploadItems = mappedRecords;

            console.log(
              "uploadItems ",
              this.uploadItems[Math.floor(mappedRecords.length / 2)]
            );
            this.isReadFileValid = allValidRecords.length > 0;
            this.loading = false;
          } else {
            console.warn("❌ Header row not found.");
            this.alert = true;
          }
        } catch (error) {
          console.error("❌ Failed to read Excel file", error);
          this.alert = true;
        }
      };

      reader.onerror = (err) => {
        console.error("Failed to read file", err);
        this.alert = true;
      };

      reader.readAsArrayBuffer(this.selectedFile);
    },

    uploadData() {
      this.loading = true;
      this.uploadFinish = false;
      console.log("uploadItems.length ", this.uploadItems.length);
      axios
        .post(
          `${process.env.VUE_APP_BASE_URL}/api/dev/temp_upload`,
          this.uploadItems
        )
        .then((resp) => {
          console.log("📦 Response:", resp.data);
          this.loading = false;
          const { success, message } = resp.data;
          this.uploadFinish = true;
          this.uploadFinishtime = dateService.formatDateToThai(new Date());
          if (success) {
            this.uploadSuccess = true;
            console.log("📦 success result:", success);
          } else {
            this.uploadSuccess = false;
            console.log("📦 success result:", success);
            this.$toast.error(message);
          }
        })

        .catch((error) => {
          const message =
            error?.response?.data?.message ||
            error?.message ||
            "Unknown error occurred";

          console.error("❌ Upload failed:", error);
          this.$toast.error(message);
          this.loading = false;
        });
    },

    processDB(){

    },
  },

  computed: {
    isExcelFileValid() {
      if (!this.selectedFile) return false;

      const allowedTypes = [
        "application/vnd.ms-excel",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      ];
      const fileExtension = this.selectedFile.name
        .split(".")
        .pop()
        .toLowerCase();

      return (
        allowedTypes.includes(this.selectedFile.type) ||
        fileExtension === "xls" ||
        fileExtension === "xlsx"
      );
    },
  },
};
