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
<<<<<<< Updated upstream
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
=======
        { id: "1", name: "เฉพาะในเขต กฟฉ.2", value: 'E3010' },
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    };
  },
=======
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
      getAllResult: [],
      oatTest : [],
    };
  },

  mounted(){
    axios.get('http://localhost:8080/api/dev/getAllDevice/')
    .then((resp) => {
      this.getAllResult = resp.data.data1;
//      console.log(this.getAllResult);
    })
    .catch((error) => {
      console.log(error.resp);
    })

  },

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
          this.jsonObj = JSON.parse(this.jsonStrBranch);
          this.jsonObj["branch"] = [];
          this.jsonObj["branch"].push("E3");
          this.appendBranch = JSON.stringify(this.jsonObj);
          console.log("b--:  " + this.appendBranch);
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
>>>>>>> Stashed changes
        }
      });
    },
    searchFunction() {
<<<<<<< Updated upstream
      console.log("start");
      alert("start");
=======
      let params = {
        page : '0',
        size : '40',
        region : '',

      }
      console.log(params);

      axios.get('http://localhost:8080/api/dev/getAllByPattern2',
       { params }).then((resp) => {
                        this.getAllResult = resp.data.data1;
                        console.log(this.getAllResult);
                      })
                      .catch((error) => {
                        console.log(error.resp);
                      })

>>>>>>> Stashed changes
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
