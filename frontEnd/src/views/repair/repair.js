import axios from "axios";



export default {

  name: "Repair",
  data() {
    return {
      hasSaved: false,
      model: null,
      states: [
        
        ],
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/cc/getAllCC')
      .then(response => {this.states = response.data;
        // this.states = JSON.parse(response.data)
        console.log(this.states)
      })
  },

  methods: {
    currentDate() {
      const current = new Date();
      const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
      return date;
    },
    customFilter(item, queryText) {
      const textOne = item.name.toLowerCase()
      const textTwo = item.abbr.toLowerCase()
      const searchText = queryText.toLowerCase()

      return textOne.indexOf(searchText) > -1 ||
        textTwo.indexOf(searchText) > -1
    },
    save() {
      this.isEditing = !this.isEditing
      this.hasSaved = true
    },
  },
}
