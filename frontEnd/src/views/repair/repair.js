import axios from "axios";



export default {

  name: "Repair",
  data() {
    return {
      hasSaved: false,
      model: null,
      states: [],
      device : [],
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/cc/getAllCC')
      .then(response => {
         this.states = response.data;  
  
      })

  },

  methods: {
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
      return date;
    },
    
    save() {
      this.isEditing = !this.isEditing
      this.hasSaved = true
    },
  },
}
