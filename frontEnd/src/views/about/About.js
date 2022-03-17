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
        { id: "1", name: "เฉพาะในเขต กฟฉ.2", value: "1" },
        { id: "2", name: "กฟจ.อบ.", value: "2" },
        { id: "3", name: "กฟจ.ศก.", value: "3" },
        { id: "4", name: "กฟจ.ยส.", value: "4" },
        { id: "5", name: "กฟจ.มค.", value: "5" },
        { id: "6", name: "กฟจ.กส.", value: "6" },
        { id: "7", name: "กฟจ.รอ.", value: "7" },
        { id: "8", name: "กฟจ.มห.", value: "8" },
        { id: "9", name: "กฟจ.อจ.", value: "9" },
        { id: "10", name: "กฟอ.สล.", value: "10" },
        { id: "11", name: "กฟอ.สดจ.", value: "11" },
        { id: "12", name: "กฟอ.กล.", value: "12" },
        { id: "13", name: "กฟอ.ดอ.", value: "13" },
        { id: "14", name: "กฟอ.วรช.", value: "14" },
        { id: "15", name: "กฟอ.ตผ.", value: "15" },
      ],
      typeSearch: [
        { id: "1", name: "เลขทรัพย์สิน", value: "1" },
        { id: "2", name: "คำอธิบายของสินทรัพย์", value: "2" },
        { id: "3", name: "หมายเลขผลิตภัณฑ์", value: "3" },
        { id: "4", name: "วันที่โอนเข้าเป็นทุน", value: "4" },
        { id: "5", name: "มูลค่าการได้มา", value: "5" },
        { id: "6", name: "มูลค่าตามบัญชี", value: "6" },
        { id: "7", name: "รหัสพนักงานผู้ครอบครอง", value: "7" },
        { id: "8", name: "ศูนย์ต้นทุน", value: "8" },
      ],
      selectedFruits: [],
      selectedTypeSearch: [],
      appendBranch: [],
      appendType: [],
      appendText: [],
      jsonObj: [],
      jsonStrBranch: '{"branch":["all"]}',
      jsonStrType: '{"type":["all"]}',
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
    toggleBranch() {
      this.$nextTick(() => {
        if (this.likesAllFruit) {
          this.selectedFruits = [];
          this.appendBranch = [];
          console.log("b-");
        } else {
          this.selectedFruits = this.fruits.slice();
          this.jsonObj = JSON.parse(this.jsonStrBranch);
          this.jsonObj["branch"] = [];
          this.jsonObj["branch"].push("all");
          this.appendBranch = JSON.stringify(this.jsonObj);
          console.log("b- " + this.appendBranch);
          // console.log("fruits" + this.fruits[0]["name"]);
        }
      });
    },
    toggleBranch2(Fruits) {
      this.jsonObj = JSON.parse(this.jsonStrBranch);
      this.jsonObj["branch"] = [];
      this.jsonObj["branch"] = Fruits;
      this.appendBranch = JSON.stringify(this.jsonObj);
      console.log("b-" + this.appendBranch);
    },
    toggleType() {
      this.$nextTick(() => {
        if (this.likesAllTypeSearch) {
          this.selectedTypeSearch = [];
          this.appendType = [];
          console.log("t-");
        } else {
          this.selectedTypeSearch = this.typeSearch.slice();
          this.jsonObj = JSON.parse(this.jsonStrType);
          this.jsonObj["type"] = [];
          this.jsonObj["type"].push("all");
          this.appendType = JSON.stringify(this.jsonObj);
          console.log("t-" + this.appendType);
          // console.log("fruits" + this.fruits[0]["name"]);
        }
      });
    },
    toggleType2(TypeSearch) {
      this.jsonObj = JSON.parse(this.jsonStrType);
      this.jsonObj["type"] = [];
      this.jsonObj["type"] = TypeSearch;
      this.appendType = JSON.stringify(this.jsonObj);
      console.log("t-" + this.appendType);
    },

    searchFunction() {
      this.appendText = JSON.parse(
        '{"data":[{"branch":["all"]},{"type":["all"]}]}'
      );
      this.appendText["data"][0] = JSON.parse(this.appendBranch);
      this.appendText["data"][1] = JSON.parse(this.appendType);
      console.log("start");
      console.log(JSON.stringify(this.appendText["data"]));
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
    likesAllTypeSearch() {
      return this.selectedTypeSearch.length === this.typeSearch.length;
    },
    likesSomeTypeSearch() {
      return this.selectedTypeSearch.length > 0 && !this.likesAllTypeSearch;
    },
    icon2() {
      if (this.likesAllTypeSearch) return "mdi-close-box";
      if (this.likesSomeTypeSearch) return "mdi-minus-box";
      return "mdi-checkbox-blank-outline";
    },
  },
};
