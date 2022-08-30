import axios from "axios";

// E3010 ในเขต
var regx = "E3010(\\d{5})";
export default {
  name: "Repair",
  data() {
    return {
      valid: null,
      hasSaved: false,
      ccNameSeclected: null,
      devPeaNO: null,
      damage: null,
      empsend: null,
      itemCC: [],
      itemDevice: [],
    };
  },
  mounted() {
    axios.get("http://localhost:8080/cc/getAllCC").then((response) => {
      console.log("getAllCC : " + response.data);
      this.itemCC = response.data;
    });
    axios
      .get("http://localhost:8080/api/dev/getAll53", {
        params: { ccLong: "E301023030" },
      })
      .then((response) => {
        this.itemDevice = response.data;
      });
  },

  methods: {
    toggleBranch2(ccCode) {
      this.ccNameSeclected = ccCode;
      console.log("toggleBranch2 : " + this.ccNameSeclected);
    },
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${
        current.getMonth() + 1
      }/${current.getFullYear()}`;
      return date;
    },

    save() {
      // this.isEditing = !this.isEditing;
      // this.hasSaved = true;
      if (this.ccName == regx) {
        console.log("5555+");
      } else {
        console.log("fail");
      }
      console.log(this.ccNameSeclected);
    },
  },
};
