import axios from "axios";

export default {
  name: "EventsList",
  data() {
    return {
      valid: null,
      model: null,
      items: [],
      getDeviceResult: [],
      getEmployeeResult: [],
      deviceheaders: [
        {
          text: "เลขทรัพย์สิน",
          align: "start",
          value: "devPeaNo",
          width: "15%",
        },
        {
          text: "คำอธิบายของสินทรัพย์",
          value: "devDescription",
          // width: "6%"
        },
        {
          text: "ชื่อผู้ครอบครอง",
          value: "tbEmployee.empName",
          // width: "25%"
        },
        {
          text: "วันที่ได้รับ",
          value: "devReceivedDate",
          // width: "25%"
        },
      ],
      employeeheaders: [
        {
          text: "รหัสพนักงาน",
          align: "start",
          value: "empId",
          // width: "10%",
        },
        {
          text: "ชื่อ-นามสกุล",
          value: "empName",
          // width: "6%"
        },
        {
          text: "ตำแหน่ง",
          value: "empRole",
          // width: "25%"
        },
      ],
      checkQuotaResult: "",
      totalDeviceResult: 0,
      totalEmployeeResult: 0,
      showVRow: false,
      totalEmployee: "",
      totalDevice: "",
      alert: false,
      itemName:"",
      assetComType: [
        { id: "1", name: "1.Computer or labtop or Tablet", value: "1" },
        {
          id: "2",
          name: "2.Monitor",
          value: "2",
        },
        { id: "3", name: "3.Printer", value: "3" },
        {
          id: "4",
          name: "4.UPS",
          value: "4",
        },
        {
          id: "5",
          name: "5.อุปกรณ์สื่อสาร",
          value: "5",
        },
        {
          id: "6",
          name: "6.อุปกรณ์ประกอบหรืออุปกรณ์อื่นๆ",
          value: "6",
        },
      ],
      selectedAssetComType: { id: "1", name: "1.Computer or labtop or Tablet", value: "1" },
      setAssetComType: 1,
      jsonObj: [],
      jsonAssetComType: '{"assetComType":["1"]}',
    };
  },

  mounted() {
    axios.get("http://localhost:8080/cc/getAllCCOnlyUse").then((response) => {
      this.items = response.data.costCenter;
    });
  },

  methods: {
    async checkQuota() {
      if (this.model == null) {
        this.alert = true;
        window.setInterval(() => {
          this.alert = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        console.log("param dt_id - ",this.setAssetComType.assetComType);
        let params = [];

        params = {
          region: this.model["ccLongCode"],
          dt_id: this.setAssetComType,
        };
        await axios
          .get("http://localhost:8080/api/dev/getDevice53unpageByccId", {
            params,
          })
          .then((resp2) => {
            // this.getAllResult = resp.data;
            // console.log(
            //   "getAllByPattern2unpage",
            //   JSON.stringify(this.getAllResult)
            // );

            this.getDeviceResult = resp2.data.dataDevice;
            this.totalDeviceResult = resp2.data.totalItems;
            // this.myloadingvariable = false;
          })
          .catch((error) => {
            console.log(error.resp);
          });

        // let params = [];
        let ccLong = this.model["ccLongCode"];
        console.log(ccLong);
        params = {
          region: ccLong,
        };
        await axios
          .get("http://localhost:8080/emp/getEmpByccLongCode", { params })
          .then((resp2) => {
            // this.getEmployeeResult = resp.data;
            // console.log(
            //   "getEmpByccLongCode",
            //   JSON.stringify(this.getEmployeeResult)
            // );

            this.getEmployeeResult = resp2.data.dataEmployee;
            this.totalEmployeeResult = resp2.data.totalItems;
            // this.myloadingvariable = false;
          })
          .catch((error) => {
            console.log(error.resp);
          });
        
          
        if (this.totalEmployeeResult > this.totalDeviceResult) {
          this.checkQuotaResult =
            "คอมพิวเตอร์น้อยกว่าจำนวนคน " +
            this.totalEmployeeResult +
            " - " +
            this.totalDeviceResult;
        } else if (
          this.totalEmployeeResult == this.totalDeviceResult ||
          this.totalEmployeeResult < this.totalDeviceResult
        ) {
          this.checkQuotaResult =
            "คอมพิวเตอร์เพียงพอกับจำนวนคน " +
            this.totalEmployeeResult +
            " - " +
            this.totalDeviceResult;
        }
        this.showVRow = true;
      }
    },

    genQuotaReport() {
      if (this.model == null) {
        this.alert = true;
        window.setInterval(() => {
          this.alert = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        this.itemName = this.model["ccFullName"];
        this.$refs.html2Pdf.generatePdf();
        // console.log("hide alert after 4 seconds");
      }
    },

    toggleAssetComType(assetComType) {
      this.jsonObj = JSON.parse(this.jsonAssetComType);
      this.jsonObj["assetComType"] = [];
      this.jsonObj["assetComType"] = assetComType;
      this.setAssetComType = assetComType;
      console.log("assetComType-" + this.setAssetComType);
    },
  },
};
