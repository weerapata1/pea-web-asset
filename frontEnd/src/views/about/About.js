import DataService from "../../services/dataServices.js"; // NEW
export default {
  name: "EventsList",

  data() {
    return {
      event: {},
      events: [],
      headers: [
        {
          text: "Dessert (100g serving)",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Calories", value: "calories" },
        { text: "Fat (g)", value: "fat" },
        { text: "Carbs (g)", value: "carbs" },
        { text: "Protein (g)", value: "protein" },
        { text: "Iron (%)", value: "iron" },
      ],
      select: [],
      fruits: [
        "เฉพาะในเขต กฟฉ.2",
        "กฟจ.อบ.",
        "กฟจ.ศก.",
        "กฟจ.ยส.",
        "กฟจ.มค.",
        "กฟจ.กส.",
        "กฟจ.รอ.",
        "กฟจ.มห.",
        "กฟจ.อจ.",
        "กฟอ.สล.",
        "กฟอ.สดจ.",
        "กฟอ.กล.",
        "กฟอ.ดอ.",
        "กฟอ.วรช.",
        "กฟอ.ตผ.",
      ],
      selectedFruits: [],
    };
  },
  created() {
    this.getEventsData(); // NEW - call getEventData() when the instance is created
  },
  // NEW
  methods: {
    async getEventsData() {
      // NEW - Use the eventService to call the getEvents() method
      DataService.getEvents().then(
        ((events) => {
          this.$set(this, "events", events);
        }).bind(this)
      );
    },
    toggle() {
      this.$nextTick(() => {
        if (this.likesAllFruit) {
          this.selectedFruits = [];
        } else {
          this.selectedFruits = this.fruits.slice();
        }
      });
    },
    searchFunction() {
      console.log("start");
      alert("start");
    },
  },

  computed: {
    likesAllFruit() {
      return this.selectedFruits.length === this.fruits.length;
    },
    likesSomeFruit() {
      return this.selectedFruits.length > 0 && !this.likesAllFruit;
    },
    icon() {
      if (this.likesAllFruit) return "mdi-close-box";
      if (this.likesSomeFruit) return "mdi-minus-box";
      return "mdi-checkbox-blank-outline";
    },
  },
};
