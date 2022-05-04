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
        { id: "1", name: "เฉพาะในเขต กฟฉ.2", value: "E3010" },
        { id: "2", name: "กฟจ.อบ.", value: "E3011" },
        { id: "3", name: "กฟจ.ศก.", value: "E302" },
        { id: "4", name: "กฟจ.ยส.", value: "E303" },
        { id: "5", name: "กฟจ.มค.", value: "E304" },
        { id: "6", name: "กฟจ.กส.", value: "E305" },
        { id: "7", name: "กฟจ.รอ.", value: "E306" },
        { id: "8", name: "กฟจ.มห.", value: "E307" },
        { id: "9", name: "กฟจ.อจ.", value: "E308" },
        { id: "10", name: "กฟอ.สล.", value: "E309" },
        { id: "11", name: "กฟอ.สดจ.", value: "E310" },
        { id: "12", name: "กฟอ.กล.", value: "E311" },
        { id: "13", name: "กฟอ.ดอ.", value: "E312" },
        { id: "14", name: "กฟอ.วรช.", value: "E313" },
        { id: "15", name: "กฟอ.ตผ.", value: "E314" },
      ],
      typeSearch: [
        { id: "1", name: "เลขทรัพย์สิน", value: "pea_no" },
        { id: "2", name: "คำอธิบายของสินทรัพย์", value: "description" },
        { id: "3", name: "หมายเลขผลิตภัณฑ์", value: "serial" },
        { id: "4", name: "วันที่โอนเข้าเป็นทุน", value: "recieve_date" },
        { id: "5", name: "มูลค่าการได้มา", value: "price_recieve" },
        { id: "6", name: "มูลค่าตามบัญชี", value: "price_left" },
        { id: "7", name: "รหัสพนักงานผู้ครอบครอง", value: "user_id" },
        { id: "8", name: "ศูนย์ต้นทุน", value: "cost_center" },
      ],
      selectedFruits: [],
      selectedTypeSearch: [],
      appendBranch: [],
      appendType: [],
      appendText: [],
      jsonObj: [],
      jsonStrBranch: '{"branch":["*"]}',
      jsonStrType: '{"type":["*"]}',
      jsonTextSearch: '{"text":["*"]}',
      appendSearch: [],
      textSearch: "",
      searchResult: [
        {
          pea_no: "531009537-0",
          description: "ระบบสายสัญญาณ (FIBER OPTIC)",
          serial: "",
          user_id: "430962",
          user_name: "นาง มนัสนันท์ พรรักษมณีรัฐ",
          cc_short_name: "ผบห.กฟฉ.2-บห.",
          received_date: "2551.6.18",
          price_recieve: "108130.85",
          price_left: "1",
          cost_center: "E301000010",
        },
        {
          pea_no: "531011277-0",
          description: "ระบบเครือข่าย Switch HUB 1 ตัว",
          serial: "",
          user_id: "505338",
          user_name: "นาย นนทธรรม นนทเตรียมกิจ",
          cc_short_name: "กบห.กบล.-บห.",
          received_date: "2553.7.30",
          price_recieve: "32700",
          price_left: "1",
          cost_center: "E301011000",
        }
      ],
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
          this.jsonObj["branch"].push("*");
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
          this.jsonObj["type"].push("*");
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
      //console.log(this.textSearch);
      this.appendText = JSON.parse(
        '{"data":[{"branch":["*"]},{"type":["*"]},{"text":["*"]}]}'
      );
      this.jsonObj = JSON.parse(this.jsonTextSearch);
      this.jsonObj["text"] = [];
      this.jsonObj["text"] = this.textSearch;
      this.appendSearch = JSON.stringify(this.jsonObj);

      this.appendText["data"][0] = JSON.parse(this.appendBranch);
      this.appendText["data"][1] = JSON.parse(this.appendType);
      this.appendText["data"][2] = JSON.parse(this.appendSearch);
      console.log("start");
      console.log(JSON.stringify(this.appendText["data"]));
      //DataService.getSearch(this.appendText["data"]);
      // this.searchResult = JSON.parse(
      //   '{"pea_no": "531009537-0","description": "ระบบสายสัญญาณ (FIBER OPTIC)","serial": "","user_id": "430962","user_name": "นาง มนัสนันท์ พรรักษมณีรัฐ","cc_short_name": "ผบห.กฟฉ.2-บห.","received_date": "2551.6.18","price_recieve": "108130.85","price_left": "1","cost_center": "E301000010"}'
      //   );
      this.searchResult = [        {
        pea_no: "531009537-0",
        description: "ระบบสายสัญญาณ (FIBER OPTIC)",
        serial: "",
        user_id: "430962",
        user_name: "นาง มนัสนันท์ พรรักษมณีรัฐ",
        cc_short_name: "ผบห.กฟฉ.2-บห.",
        received_date: "2551.6.18",
        price_recieve: "108130.85",
        price_left: "1",
        cost_center: "E301000010",
      }];

      
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
