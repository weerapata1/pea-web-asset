import axios from "axios";

// E3010 ในเขต
// var regx = "E3010(\\d{5})";
export default {
  name: "Repair",
  data() {
    return {
      valid : null,
      hasSaved: false,
      ccName: null,
      devPeaNO: null,
      damage : null,
      empsend : null,
      items: [],
      device: [],
    };
  },
  mounted() {
    axios.get("http://localhost:8080/cc/getAllCC").then((response) => {
      this.items = response.data;
    });
  },

  methods: {
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
      return date;
    },
    
    save() {
      this.isEditing = !this.isEditing;
      this.hasSaved = true;
      // if()
    },
    
  },
};
