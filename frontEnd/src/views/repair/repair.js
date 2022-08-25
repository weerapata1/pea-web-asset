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
    save() {
      this.isEditing = !this.isEditing;
      this.hasSaved = true;
      // if()
    },
    
  },
};
