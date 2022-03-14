import DataService from "../../services/dataServices.js"; // NEW
export default {
  name: "EventsList",

  data() {
    return {
      event: {},
      events: [],
      headers: [
        {
          text: "เลขทรัพย์สิน",
          align: "start",
          sortable: false,
          value: "pea_no",
        },
        { text: "คำอธิบายของสินทรัพย์", value: "description" },
        { text: "หมายเลขผลิตภัณฑ์", value: "serial" },
        { text: "วันที่โอนเข้าเป็นทุน", value: "recieve_date" },
        { text: "มูลค่าการได้มา", value: "price_recieve" },
        { text: "มูลค่าตามบัญชี", value: "price_left" },
        { text: "รหัสพนักงานผู้ครอบครอง", value: "user_id" },
        { text: "ศูนย์ต้นทุน", value: "cost_center" },
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
      typeSearch: [
        "เลขทรัพย์สิน",
        "คำอธิบายของสินทรัพย์",
        "หมายเลขผลิตภัณฑ์",
        "วันที่โอนเข้าเป็นทุน",
        "มูลค่าการได้มา",
        "มูลค่าตามบัญชี",
        "รหัสพนักงานผู้ครอบครอง",
        "ศูนย์ต้นทุน",
      ],
      selectedFruits: [],
      selectedTypeSearch: [],
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
