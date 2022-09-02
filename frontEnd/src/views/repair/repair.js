import axios from "axios";

// E3010 ในเขต
const data = {
  ccNameSeclected: null,
  devPeaNO : null,
  empSend  : null,
};
export default {
  name: "Repair",
  data() {
    return {
      ccNameRules: [(v) => !!v || "โปรดระบุ การไฟฟ้า"],
      devPeaNoRules: [(v) => !!v || "โปรดระบุ รหัสทรัพย์สิน"],
      damageRules: [
        (v) => !!v || "โปรดระบุอาการเสีย",
        (v) => (v && v.length >= 5) || "Name must be less than 5 characters",
      ],
      empsendRules: [(v) => !!v || "โปรดระบุ รหัสพนักงาน"],
      valid: false,
      hasSaved: false,
      ccNameSeclected: null,
      devPeaNO: null,
      damage: null,
      empSend: null,
      empName: null,
      itemCC: [],
      itemDevice: [],
      empData: [],
    };
  },
  mounted() {
    axios.get("http://localhost:8080/cc/getAllCC").then((response) => {
      this.itemCC = response.data;
    });
  },

  methods: {
    async toggleBranch2(ccCode) {
      this.ccNameSeclected = ccCode;
      await axios
        .get("http://localhost:8080/api/dev/getAll53", {
          params: { ccLong: this.ccNameSeclected },
        })
        .then((response) => {
          this.itemDevice = response.data;
        
        });
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
        alert("โปรดระบุรหัสพนักงาน");
      } else {
        await axios
          .get("http://localhost:8080/emp/getEmpId", {
            params: { id: empSend },
          })
          .then((response) => {
            this.empData = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    save() {
      if (this.valid === false) {
        alert("โปรดตรวจสอบข้อมูล");
      } else {
        data.ccNameSeclected = this.ccNameSeclected;
        data.devPeaNO = this.devPeaNO;
        data.empSend = this.empSend;
        data.damage = this.damage;
      }
      console.log("data : ",data);
      // console.log(this.ccNameSeclected);
    },
  },
};
