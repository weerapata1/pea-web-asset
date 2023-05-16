import axios from "axios";
import moment from "moment";

// let url = "http://localhost:8080";
// let urlRepair = "http://localhost:8080/repair";
let url = "http://localhost:172.21.1.51";
let urlRepair = "http://localhost:172.21.1.51/repair";
moment.locale("th");

const StaticHeader = {
  // E3010 ในเขต
  No: "...................................................................",
  FromDepartment: "",
  to1: "ผปค.กรท.ฉ.2",
  to2: "ศปค.",
};
const StaticBoby = {
  subject: "ขอให้จัดซ่อมคอมพิวเตอร์ และอุปกรณ์ประกอบ",
  substance: "ขอแจ้งเครื่องชำรุจเพื่อส่งซ่อมตามรายการดังนี้",
  to1: "หผ.ปค.",
  to2: "พคค.",
  note1:
    "ในกรณีที่ไม่อยู่ในสัญญาประกัน ขอให้กรท.ฉ.2 ดำเนินการซ่อมและตัดงบค่าใช้จ่ายตามบันทึกฉบับ ฉ.2 กรท(ก) 94/2564 ",
  note2: "จึงเรียนมาเพื่อโปรดแจ้งผู้ที่เกี่ยวข้องดำเนินการต่อไปด้วย",
};
const StaticFoot = {
  dot1: ".........................................................................",
  dot2: "(.....................................................)",
  dot3: "..........................................................",
  dot4: "ตำแหน่ง ................................................",
  date1: "............../............../..............",
  to1: "อก.รท.ฉ.2",
  to2: "ผจก.",
  note1: "เพื่อโปรดพิจารณา อนุมัติ",
};

export default {
  name: "listRepair",

  data() {
    return {
      rowIdValue: null,
      first5char : null,
      valid: true,
      dialogInfo: false,
      StaticHeader: StaticHeader,
      StaticBoby: StaticBoby,
      StaticFoot: StaticFoot,

      state: null,
      adminNames: [],
      caues: [
        { header: "CPU/Notebook" },
        { name: "เมนบอร์ดชำรุด/ไหม้", value: 1 },
        { name: "ฮาร์ดดิสก์ชำรุด ", value: 2 },
        { name: "ปุ่มเปิดชำรุดดับเอง", value: 3 },
        { name: "ระบบไฟลัดวงจร", value: 4 },
        { name: "พาวเวอร์ซัพพลายชำรุด", value: 5 },
        { name: "ประมวลผลช้า", value: 6 },
        { name: "ระบบภายในชำรุด", value: 7 },
        { divider: true },
        { header: "จอภาพ(Monitor)" },
        { name: "หลอดภาพชำรุด/CRT", value: 8 },
        { name: "การ์ดจอไหม้", value: 9 },
        { name: "จอภาพชำรุด/เสื่อมสภาพ", value: 10 },
        { name: "แผงวงจรไหม้/ชำรุด", value: 11 },
        { name: "จอสีเพี้ยน/มีเส้น", value: 12 },
        { name: 'หน้าจอแตก">หน้าจอแตก', value: 13 },
        { name: "จอเปิดไม่ติด/สวิทช์ชำรุด", value: 14 },
        { divider: true },
        { header: "เครื่องพิมพ์(Printer)" },
        { name: "หลอดสร้างภาพชำรุด", value: 15 },
        { name: "เมนบอร์ดชำรุด/ไหม้ ", value: 16 },
        { name: "ฟีดกระดาษชำรุด", value: 17 },
        { name: "พาวเวอร์ซัพพลายชำรุด", value: 18 },
        { name: "กระดาษติด/ยับ", value: 19 },
        { name: "ผงหมึกรั่ว", value: 20 },
        { name: "เปิดไม่ติด/สวิทช์ชำรุด", value: 21 },
        { name: "ไม่มีไฟสถานะทำงาน", value: 22 },
        { name: "ตัวหนังสือจาง", value: 23 },
        { name: "หัวเข็มชำรุด", value: 24 },
        { divider: true },
        { header: "UPS(เครื่องสำรองไฟ)" },
        { name: "แบตเตอรี่เสื่อม", value: 25 },
        { name: "แผงวงจรไหม้/ชำรุด", value: 26 },
        { name: "ไม่เก็บกระแสไฟ", value: 27 },
        { name: "ปุ่มเปิด/ปิดไม่ทำงาน", value: 28 },
      ],

      selectCCvalue: null,
      CcItems: [],

      dataTableItems: [],
      DataTableHeaders: [
        {
          text: "เลขทรัพย์สิน",
          align: "start",
          value: "device.devPeaNo",
        },
        { text: "การไฟฟ้า", value: "device.tbCostCenterTest.ccShortName" },
        { text: "คำอธิบายทรัพย์สิน", value: "device.devDescription" },

        { text: "หมายเลขผลิตภัณฑ์", value: "device.devSerialNo" },
        { text: "วันที่ส่งซ่อม", value: "sendDate" },
        { text: "ผู้ส่งซ่อม", value: "empSend.empId" },
        { text: "สถานะ", value: "tbRepairStatus.statusName" },
        { text: "", value: "" },
      ],
      dialog1: false, //รับเครื่อง
      dialog1Value: [{ adminName: null }, { caues: null }],
      adNameRules: [(v) => !!v || " required"],
      causeRules: [(v) => !!v || " required"],

      dialog2: false, // กำลังดำเนินการ
      dialog2Value: [{ treatment: null }],
      treatmentRules: [
        (v) => !!v || " required",
        (v) => (v && v.length >= 5) || "พิมพ์อย่างน้อย 5 ตัวอักษร",
      ],

      dialog3: false,
      dialog3Value: [{ returnEmp: null }],
      returnEnpRules: [
        (v) => !!v || " required",
        (v) => (v && v.length >= 6) || "พิมพ์อย่างน้อย 6 ตัวอักษร",
      ],

      dialogInfoValue: [
        {
          repairId : null,
          empSendRole: null,
          peaNo: null,
          location: null,
          ccFull: null,
          stage: null,
          empOwnerName: null,
          empOwnerId: null,
          damage: null,
          adminName: null,
          adminRole : null,
          admitDate: null,
          empSendName: null,
          empSendId: null,
          empRole :null,
          adminID: null,
          returnEmp: null,
          returnDate: null,
          treatment: null,
          treatComplete: null,
          discription: null,
          deviceType: null,
          empPhoneNumb: null,
        },
      ],
    };
  },
  mounted() {
    this.dialog1Value.adminName = sessionStorage.getItem("userName");
    axios
      .get(url + "/cc/getAllCCOnlyUse")
      .then((res) => {
        this.CcItems = res.data.costCenter;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(url + "/empAdmin/getAllAd")
      .then((res) => {
        this.adminNames = res.data;
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .get(urlRepair + "/getAllRepair")
      .then((res) => {
        this.dataTableItems = res.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {},
  methods: {
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${
        current.getMonth() + 1
      }/${current.getFullYear()}`;
      return date;
    },
    printPDF() {
      this.$refs.html2Pdf.generatePdf();
      console.log("PDF print");
    },
    formatDate(value) {
      return moment(value).format("DD MMMM YYYY HH:mm");
    },

    lookFor(selectCCvalue) {
      if (this.state == 1) {
        this.find(selectCCvalue, 1);
      } else if (this.state == 2) {
        this.find(selectCCvalue, 2);
      } else if (this.state == 3) {
        this.find(selectCCvalue, 3);
      } else if (this.state == 4) {
        this.find(selectCCvalue, 4);
      } else {
        this.find2(selectCCvalue);
      }
    },
    find5charCCid(ccCode) {
      // E3010 ในเขต
      if (ccCode.substring(0, 5) == "E3010") {
        return 2;
      } else {
        return 1;
      }
    },
    openDialogInfo(item) {
      
      this.dialogInfoValue.repairId = item.repairId;
      this.dialogInfoValue.peaNo = item.device.devPeaNo;
      this.dialogInfoValue.discription = item.device.devDescription;
      this.dialogInfoValue.deviceType =
        item.device.tbDeviceType.deviceTypeName;
      this.first5char = this.find5charCCid(item.device.devPeaNo);
      this.dialogInfoValue.location = item.device.tbCostCenterTest.ccFullName;
      this.dialogInfoValue.ccFull = item.device.tbCostCenterTest.ccLongCode;
      this.dialogInfoValue.stage = item.repairStatus.statusName;
      
      this.dialogInfoValue.empOwnerId =
        item.device.tbEmployee == null ? null : item.device.tbEmployee.empId;
      this.dialogInfoValue.empOwnerName =
        item.device.tbEmployee == null ? null : item.device.tbEmployee.empName;
      this.dialogInfoValue.damage =
        item.cause == null ? null : item.cause.causeName;

      this.dialogInfoValue.damageDetail =
        item.damageDetail == null ? null : item.damageDetail;

      this.dialogInfoValue.admitDate =
        item.admitDate == null
          ? null
          : moment(String(item.admitDate), "YYYY-MM-DD HH:mm").format(
              "DD MMMM YYYY HH:mm"
            );
      this.dialogInfoValue.adminName =
        item.adminReceive == null ? null : item.adminReceive.adName;
      this.dialogInfoValue.adminRole = item.adminReceive.empRole;
      this.dialogInfoValue.adminID =
        item.adminReceive == null ? null : item.adminReceive.adEmp;
      this.dialogInfoValue.empSendName =
        item.empSend == null ? null : item.empSend.empName;
      this.dialogInfoValue.empSendId =
        item.empSend == null ? null : item.empSend.empId;
      this.dialogInfoValue.empSendRole =
        item.empSend == null ? null : item.empSend.empRole;
      this.dialogInfoValue.empPhoneNumb = item.empPhoneNumb;
      this.dialogInfoValue.returnEmp =
        item.returnEmp == null ? null : item.returnEmp.empName;
      this.dialogInfoValue.sendDate =
        item.sendDate == null
          ? null
          : moment(String(item.sendDate), "YYYY-MM-DD HH:mm").format(
              "DD MMMM YYYY HH:mm"
            );
      this.dialogInfoValue.returnDate =
        item.returnDate == null
          ? null
          : moment(String(item.returnDate), "YYYY-MM-DD HH:mm").format(
              "DD MMMM YYYY HH:mm"
            );
      this.dialogInfoValue.treatment =
        item.treatment == null ? null : item.treatment;
      this.dialogInfoValue.treatComplete =
        item.treatComplete == null
          ? null
          : moment(String(item.treatComplete), "YYYY-MM-DD HH:mm").format(
              "DD MMMM YYYY HH:mm"
            );
    },

    find(value, state) {
      let locationFromCC = value.ccLongCode;

      axios
        .get(urlRepair + "/getByLocAndSta", {
          params: { location: locationFromCC, status: state },
        })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    find2(value) {
      let locationFromCC = value.ccLongCode;
      axios
        .get(urlRepair + "/getByLocation", {
          params: { location: locationFromCC },
        })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    allStste() {
      this.state = 0;
      axios
        .get(urlRepair + "/getAllRepair")
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    reseveInState() {
      //
      this.state = 1;
      axios
        .get(urlRepair + "/getByStatusId", { params: { status: this.state } })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    inProgressState() {
      this.state = 2;
      axios
        .get(urlRepair + "/getByStatusId", { params: { status: this.state } })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    doneState() {
      this.state = 3;
      axios
        .get(urlRepair + "/getByStatusId", { params: { status: this.state } })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    returnState() {
      this.state = 4;
      axios
        .get(urlRepair + "/getByStatusId", { params: { status: this.state } })
        .then((res) => {
          this.dataTableItems = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    reset() {
      this.$refs.form.reset();
    },

    openDialog(item) {
      this.rowIdValue = item.repairId;
    },

    async saveDialog1(dialog1Value) {
      //dialogรับเครื่อง
      if (dialog1Value.adminName == null || dialog1Value.caues == null) {
        alert("โปรดกรอกข้อมูล");
      } else {
        await axios
          .put(urlRepair + "/updateStatusSec/" + this.rowIdValue, null, {
            params: {
              adminName: dialog1Value.adminName,
              causeId: dialog1Value.caues,
            },
          })
          .then(
            ((Response) => {
              this.dataTableItems = Response.data;
            },
            (error) => {
              console.log(error);
            })
          );
        window.location.reload();
        this.dialog1 = false;
      }
      (this.dialog1Value.adminName = null), (this.dialog1Value.caues = null);
    },

    async openDialog2(dialog2Value) {
      if (dialog2Value.treatment == null) {
        alert("โปรดกรอกข้อมูล");
      } else {
        await axios
          .put(urlRepair + "/updateStatusThd/" + this.rowIdValue, null, {
            params: {
              treat: dialog2Value.treatment,
            },
          })
          .then(
            ((Response) => {
              this.dataTableItems = Response.data;
            },
            (error) => {
              console.log(error);
              // alert('เกิดข้อผิดพลาด')
            })
          );
        window.location.reload();
        this.dialog2Value.treatment = null;
      }
    },

    async openDialog3(dialog3Value) {
      if (dialog3Value.returnEmp == null) {
        alert("โปรดกรอกข้อมูล");
      } else {
        await axios
          .put(urlRepair + "/updateStatusFur/" + this.rowIdValue, null, {
            params: {
              returnEmp: dialog3Value.returnEmp,
            },
          })
          .then(
            ((Response) => {
              this.dataTableItems = Response.data;
            },
            (error) => {
              console.log(error);
              // alert('เกิดข้อผิดพลาด')
            })
          );
        this.$router.go();
        this.dialog3Value.returnEmp = null;
      }
    },
    
  },
};
