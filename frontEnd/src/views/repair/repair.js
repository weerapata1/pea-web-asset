import axios from "axios";
import { validationMixin } from "vuelidate";
import { required, maxLength, minLength , numeric } from "vuelidate/lib/validators";

// E3010 ในเขต
const StaticHeader = {
  No: "",
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
const fBody = {
  empSendName: "",
  empSendRole: "",
  empSendId: "",
  damage: "",
  warranty: "",
  location: "",
  deviceType: "",
  peaNo : "",
  discription : "",
  empPhoneNumb : "",
};
const StaticFoot = {
  dot1: ".....................................................................................................",
  dot2: "(.....................................................)",
  dot3: "........................................................",
  date1: "................./................./.................",
  to1: "อก.รท.ฉ.2",
  to2: "ผจก.",
  note1: "เพื่อโปรดพิจารณา อนุมัติ",
};
// const data = {
//   ccNameSeclected: '',
//   devPeaNoSelceted: '',
//   empSend: '',
//   damage: '',
// };
export default {
  name: "Repair",
  mixins: [validationMixin],
  validations: {
    ccNameSeclected: { required },
    devPeaNoSelceted: { required },
    damage: { required, minLength: minLength(5), maxLength: maxLength(100) },
    empSend: { required, minLength: minLength(6), maxLength: maxLength(9) , numeric },
    empPhoneNumb :{required, minLength: minLength(5), maxLength: maxLength(10) ,numeric},
  },
  data() {
    return {
      valid: false,
      dialog: false,
      first5char: "",
      fBody: fBody,
      StaticHeader: StaticHeader,
      StaticBoby: StaticBoby,
      StaticFoot: StaticFoot,
      hasSaved: false,
      ccNameSeclected: "",
      devPeaNoSelceted: "",
      damage: "",
      empSend: "",
      empPhoneNumb : "",

      resPost: "",
      empName: [],
      itemCC: [],
      itemDevice: [],
      devDesc: [],
      empData: [],
      locationSelected: [],
    };
  },
  computed: {
    ccNameSeclectedErrors() {
      const errors = [];
      if (!this.$v.ccNameSeclected.$dirty) return errors;
      !this.$v.ccNameSeclected.required && errors.push("โปรดระบุการไฟฟ้า");
      return errors;
    },
    devPeaNoSelcetedErrors() {
      const errors = [];
      if (!this.$v.devPeaNoSelceted.$dirty) return errors;
      !this.$v.devPeaNoSelceted.required &&
        errors.push("โปรดระบุรหัสทรัพย์สิน");
      return errors;
    },
    damageErrors() {
      const errors = [];
      if (!this.$v.damage.$dirty) return errors;
      !this.$v.damage.required && errors.push("โปรดระบุอาการที่ชำรุจ");
      !this.$v.damage.minLength &&
      errors.push("พิมพ์อย่างน้อย 5 ตัวอักษร โปรดตรวจสอบ");
      !this.$v.damage.maxLength &&
      errors.push("พิมพ์เกินที่กำหนด โปรดตรวจสอบ");
      return errors;
    },
    empSendErrors() {
      const errors = [];
      if (!this.$v.empSend.$dirty) return errors;
      !this.$v.empSend.required && errors.push("โปรดระบุรหัสพนักงาน");
      !this.$v.empSend.minLength &&
        errors.push("พิมพ์อย่างน้อย 6 ตัวอักษร โปรดตตรวจสอบ");
      !this.$v.empSend.maxLength &&
      errors.push("พิมพ์เกินที่กำหนด โปรดตรวจสอบ");
      !this.$v.empSend.numeric &&
      errors.push("โปรดกรอกข้อมูลเป็นตัวเลข");
      return errors;
    },
    empPhoneNumbErrors(){
      const errors = [];
      if (!this.$v.empPhoneNumb.$dirty) return errors;
      !this.$v.empPhoneNumb.required && errors.push("โปรดระบุเบอร์ติดต่อกลับ");
      !this.$v.empPhoneNumb.minLength &&
        errors.push("พิมพ์อย่างน้อย 5 ตัวอักษร โปรดตรวจสอบ");
      !this.$v.empPhoneNumb.maxLength &&
        errors.push("พิมพ์เกินที่กำหนด โปรดตรวจสอบ");
        !this.$v.empPhoneNumb.numeric &&
        errors.push("โปรดกรอกข้อมูลเป็นตัวเลข");
      return errors;
    }
  },
  mounted() {
    axios.get("http://172.21.1.51:8080/cc/getAllCCOnlyUse").then((response) => {
      this.itemCC = response.data.costCenter;
    });
  },

  methods: {
    readFile() {
      window.open("./resume.pdf", "_blank"); //to open in new tab
    },
    async findDiscDevice(peaNo) {
      this.devPeaNoSelceted = peaNo;
      await axios
        .get("http://172.21.1.51:8080/api/dev/getDeviceByPeaNo", {
          params: { PeaNo: peaNo },
        })
        .then((response) => {
          this.devDesc = response.data;
        });
    },
    async toggleBranch2(ccCode) {
      this.ccNameSeclected = ccCode;
      await axios
        .get("http://172.21.1.51:8080/api/dev/getAll53", {
          params: { ccLong: this.ccNameSeclected },
        })
        .then((response) => {
          this.itemDevice = response.data;
        });
      if (ccCode.substring(0, 5) == "E3010") {
        this.first5char = 1;
      } else {
        this.first5char = 2;
      }
    },
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${
        current.getMonth() + 1
      }/${current.getFullYear()}`;
      return date;
    },
    async findEmp(empSend) {
      if (empSend == null) {
        this.empSendErrors();
        alert("โปรดระบุรหัสพนักงาน");
      } else {
        await axios
          .get("http://172.21.1.51:8080/emp/getEmpId", {
            params: { id: empSend },
          })
          .then((response) => {
            // console.log(response.data)
            this.empData = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    async continues() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        alert("โปรดตรวจสอบข้อมูล");
      } else {
        (fBody.empSendName = this.empData.empName),
          (fBody.empSendRole = this.empData.empRole),
          (fBody.empSendId = this.empData.empId);
        (fBody.warranty = ""),
        (fBody.peaNo = this.devPeaNoSelceted),
        (fBody.discription = this.devDesc.devDescription),
          (fBody.location = this.devDesc.tbCostCenterTest.ccShortName),
          (fBody.deviceType = this.devDesc.tbDeviceType.deviceTypeName),
          (fBody.damage = this.damage),
          (fBody.empPhoneNumb = this.empPhoneNumb),
          
          await axios
            .post("http://172.21.1.51:8080/repair/repair", null, {
              params: {
                empSend: this.empData.empId ,
                damage: this.damage,
                devicePeaNO: this.devPeaNoSelceted,
                empPhoneNumb : this.empPhoneNumb
              },
            })
            .then((response) => {
              this.resPost = response.status;
              this.$refs.html2Pdf.generatePdf();
              alert("บันทึกสำเร็จ");
            })
            .catch((error) => {
              console.log(error);
              alert("บันทึกไม่สำเร็จ โปรดตรวจสอบข้อมูล");
            });
      }
      if (this.resPost == "201") {
        
        this.clear();
      }
    },
    clear() {
      this.$v.$reset();
      this.ccNameSeclected = "";
      this.devPeaNoSelceted = "";
      this.devDesc = "";
      this.damage = "";
      this.empSend = "";
      this.empPhoneNumb = "";
      this.empData = "";
    },
  },
};
