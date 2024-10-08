import Vue from "vue";
import axios from "axios";
// import DataService from "../../services/dataServices.js"; // NEW
import JsonExcel from "vue-json-excel";
Vue.component("downloadExcel", JsonExcel);

import QrcodeVue from "qrcode.vue";
Vue.component("qrcode-vue", QrcodeVue);

import { mdiQrcodeScan } from "@mdi/js";
Vue.component("mdiQrcode-Scan", mdiQrcodeScan);

import { mdiMicrosoftExcel } from "@mdi/js";
Vue.component("mdiMicrosoftExcel", mdiMicrosoftExcel);

import { mdiFileFindOutline } from "@mdi/js";
Vue.component("mdiFileFindOutline", mdiFileFindOutline);

import { mdiMagnify } from "@mdi/js";
Vue.component("mdiMagnify", mdiMagnify);

import { mdiQrcode } from "@mdi/js";
Vue.component("mdiQrcode", mdiQrcode);

// import the component
import Treeselect from "@riophae/vue-treeselect";
Vue.component("treeselect", Treeselect);
// import the styles
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

// import router from "../../router";

export default {
  name: "EventsList",
  data() {
    return {
      event: {},
      events: [],
      headers: [
        // {
        //   align: "start",
        //   value: "",
        //   width: "1%",
        //   text:"select-all"
        // },
        {
          text: "เลขทรัพย์สิน",
          align: "start",
          value: "devPeaNo",
          class: "primary--text font-size: 50%",
          // width: "10%",
        },
        {
          text: "คำอธิบายของสินทรัพย์",
          value: "devDescription",
          class: "primary--text",
          // width: "6%"
        },
        {
          text: "หมายเลขผลิตภัณฑ์",
          value: "devSerialNo",
          class: "primary--text",
          //  width: "3%"
        },
        {
          text: "วันที่โอนเข้าเป็นทุน",
          value: "devReceivedDate",
          class: "primary--text",
          // width: "7%"
        },
        {
          text: "มูลค่าการได้มา",
          value: "devReceivedPrice",
          class: "primary--text",
          //  width: "3%"
        },
        {
          text: "มูลค่าตามบัญชี",
          value: "devLeftPrice",
          class: "primary--text",
          // width: "3%"
        },
        {
          text: "ชื่อผู้ครอบครอง",
          value: "tbEmployee.empName",
          class: "primary--text",
          // width: "20%",
        },
        {
          text: "รหัสพนักงาน",
          value: "tbEmployee.empId",
          class: "primary--text",
          // width: "3%"
        },
        {
          text: "สังกัด",
          value: "tbCostCenterTest.ccShortName",
          class: "primary--text",
          // width: "5%",
        },
        {
          text: "ศูนย์ต้นทุน",
          value: "tbCostCenterTest.ccLongCode",
          class: "primary--text",
          // width: "5%",
        },
        {
          text: "Action",
          value: "actions",
          sortable: false,
          class: "primary--text",
          align: "center",
          // width: "3%"
        },
      ],
      excelHeaders: {
        เลขทรัพย์สิน: "devPeaNo",
        คำอธิบายของสินทรัพย์: "devDescription",
        หมายเลขผลิตภัณฑ์: "devSerialNo",
        วันที่โอนเข้าเป็นทุน: "devReceivedDate",
        มูลค่าการได้มา: "devReceivedPrice",
        มูลค่าตามบัญชี: "devLeftPrice",
        ชื่อผู้ครอบครอง: {
          field: "tbEmployee.empName",
          callback: (value) => {
            return `${value}`;
          },
        },
        รหัสพนักงาน: {
          field: "tbEmployee.empId",
          callback: (value) => {
            return `${value}`;
          },
        },
        ศูนย์ต้นทุน: {
          field: "tbCostCenterTest.ccLongCode",
          callback: (value) => {
            return `${value}`;
          },
        },
        สังกัด: {
          field: "tbCostCenterTest.ccShortName",
          callback: (value) => {
            return `${value}`;
          },
        },
        // "Telephone 2": {
        //   field: "phone.landline",
        //   callback: (value) => {
        //     return `Landline Phone - ${value}`;
        //   },
        // },
      },
      select: [],

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
      // searchResult: [
      //   {
      //     pea_no: "531009537-0",
      //     description: "ระบบสายสัญญาณ (FIBER OPTIC)",
      //     serial: "",
      //     user_id: "430962",
      //     user_name: "นาง มนัสนันท์ พรรักษมณีรัฐ",
      //     cc_short_name: "ผบห.กฟฉ.2-บห.",
      //     received_date: "2551.6.18",
      //     price_recieve: "108130.85",
      //     price_left: "1",
      //     cost_center: "E301000010",
      //   },
      //   {
      //     pea_no: "531011277-0",
      //     description: "ระบบเครือข่าย Switch HUB 1 ตัว",
      //     serial: "",
      //     user_id: "505338",
      //     user_name: "นาย นนทธรรม นนทเตรียมกิจ",
      //     cc_short_name: "กบห.กบล.-บห.",
      //     received_date: "2553.7.30",
      //     price_recieve: "32700",
      //     price_left: "1",
      //     cost_center: "E301011000",
      //   },
      // ],
      getAllResult: [],
      data1: [],
      itemsPerPage: 0,
      totalItems: 0,
      footerProps: {
        "items-per-page-options": [30, 50, 100, -1],
        page: 0,
        showFirstLastPage: true,
      },
      alert: false,
      alert2: false,
      myloadingvariable: false,

      assetType: [
        { id: "1", name: "1.ทรัพย์สินคอมพิวเตอร์", value: "53" },
        {
          id: "2",
          name: "2.ทรัพย์สินคอมพิวเตอร์ มูลค่าคงเหลือ 1 บาท",
          value: "153",
        },
        { id: "3", name: "3.ทรัพย์สินทุกประเภท", value: "all" },
        {
          id: "4",
          name: "4.ทรัพย์สินทุกประเภท มูลค่าคงเหลือ 1 บาท",
          value: "1all",
        },
      ],
      selectedAssetType: {
        id: "1",
        name: "1.ทรัพย์สินคอมพิวเตอร์",
        value: "53",
      },
      setAssetType: [],
      jsonStrAssetType: '{"assetType":["53"]}',
      dataExcel: [],

      qrcode_value:
        // JSON.parse([
        JSON.stringify({
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
        }),
      // ]),
      qrcode_size: 128,
      dialog: false,
      dialogFixForm: false,

      editedIndex: -1,
      editedItem: {
        name: "",
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0,
      },
      defaultItem: {
        name: "",
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0,
      },
      groupSelected: [],
      qrcode_value2: [],
      result: [],
      selected: [],

      fruits: [
        { header: "เขต" },
        { id: "1", name: "เฉพาะในเขต กฟฉ.2", value: "E3010" },
        // { divider: true },
        { header: "หน้างาน" },
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

      optionBranches: [
        {
          id: "E300",
          label: "สังกัด ผชก.(ฉ2)",
          value: "E300",
          icon: "mdi-account",
        },
        {
          id: "E3",
          label: "ทุกหน่วยงานในสังกัด กฟฉ.2",
          value: "E3",
          icon: "mdi-home-circle",
        },
        {
          id: "reg",
          label: "สำนักงาน กฟฉ.2",
          value: "E3010",
          icon: "mdi-home-circle",
          children: [
            {
              id: "reg1",
              label: "กอก.",
              value: "E30100",
              icon: "mdi-home-assistant",
              // isNew: true,
            },
            {
              id: "reg2",
              label: "ฝวบ.",
              value: "E30101",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg3",
              label: "กบล.",
              value: "E301011",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg4",
              label: "กวว.",
              value: "E301012",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg5",
              label: "กกค.",
              value: "E301013",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg6",
              label: "ฝบพ.",
              value: "E30102",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg7",
              label: "กบญ.",
              value: "E301021",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg8",
              label: "กซข.",
              value: "E301022",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg9",
              label: "กรท.",
              value: "E301023",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg10",
              label: "ฝปบ.",
              value: "E30103",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg11",
              label: "กปบ.",
              value: "E301031",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg12",
              label: "กบษ.",
              value: "E301032",
              icon: "mdi-home-assistant",
            },
            {
              id: "reg13",
              label: "กรส.",
              value: "E301033",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "ubn",
          label: "จุดรวมงาน กฟจ.อบ.",
          value: "E3011",
          icon: "mdi-home-circle",
          children: [
            {
              id: "ub1",
              label: "กฟจ.อบ.",
              value: "E3011",
              icon: "mdi-home-assistant",
            },
            {
              id: "ub2",
              label: "กฟส.มสส.",
              value: "E3015",
              icon: "mdi-home-assistant",
            },
            {
              id: "ub3",
              label: "กฟส.ขน.",
              value: "E3016",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "ssk",
          label: "จุดรวมงาน กฟจ.ศก.",
          value: "E302",
          icon: "mdi-home-circle",
          children: [
            {
              id: "ssk1",
              label: "กฟจ.ศก.",
              value: "E3021",
              icon: "mdi-home-assistant",
            },
            {
              id: "ssk2",
              label: "กฟส.อพ.",
              value: "E3022",
              icon: "mdi-home-assistant",
            },
            {
              id: "ssk3",
              label: "กฟอ.ขข.",
              value: "E3023",
              icon: "mdi-home-assistant",
            },
            {
              id: "ssk4",
              label: "กฟส.รศ.",
              value: "E3024",
              icon: "mdi-home-assistant",
            },
            {
              id: "ssk5",
              label: "กฟส.กร.",
              value: "E3025",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "yst",
          label: "จุดรวมงาน กฟจ.ยส.",
          value: "E303",
          icon: "mdi-home-circle",
          children: [
            {
              id: "yst1",
              label: "กฟจ.ยส.",
              value: "E3031",
              icon: "mdi-home-assistant",
            },
            {
              id: "yst2",
              label: "กฟส.พนพ.",
              value: "E3032",
              icon: "mdi-home-assistant",
            },
            {
              id: "yst3",
              label: "กฟส.มช.",
              value: "E3033",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "mhk",
          label: "จุดรวมงาน กฟจ.มค.",
          value: "E304",
          icon: "mdi-home-circle",
          children: [
            {
              id: "mhk1",
              label: "กฟจ.มค.",
              value: "E3041",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk2",
              label: "กฟส.พภ.",
              value: "E3042",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk3",
              label: "กฟส.วป.",
              value: "E3043",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk4",
              label: "กฟจ.กพส.",
              value: "E3044",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk5",
              label: "กฟส.บบ.",
              value: "E3045",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk6",
              label: "กฟส.ชย.",
              value: "E3046",
              icon: "mdi-home-assistant",
            },
            {
              id: "mhk7",
              label: "กฟส.กวช.",
              value: "E3047",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "kls",
          label: "จุดรวมงาน กฟจ.กส.",
          value: "E305",
          icon: "mdi-home-circle",
          children: [
            {
              id: "kls1",
              label: "กฟจ.กส.",
              value: "E3051",
              icon: "mdi-home-assistant",
            },
            {
              id: "kls2",
              label: "กฟส.นกศ.",
              value: "E3052",
              icon: "mdi-home-assistant",
            },
            {
              id: "kls3",
              label: "กฟส.ยต.",
              value: "E3053",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "roe",
          label: "จุดรวมงาน กฟจ.รอ.",
          value: "E306",
          icon: "mdi-home-circle",
          children: [
            {
              id: "roe1",
              label: "กฟจ.รอ.",
              value: "E3061",
              icon: "mdi-home-assistant",
            },
            {
              id: "roe2",
              label: "กฟส.สณ.",
              value: "E3062",
              icon: "mdi-home-assistant",
            },
            {
              id: "roe3",
              label: "กฟส.กษ.",
              value: "E3063",
              icon: "mdi-home-assistant",
            },
            {
              id: "roe4",
              label: "กฟส.อส.",
              value: "E3064",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "mdh",
          label: "จุดรวมงาน กฟจ.มห.",
          value: "E307",
          icon: "mdi-home-circle",
          children: [
            {
              id: "mdh1",
              label: "กฟจ.มห.",
              value: "E3071",
              icon: "mdi-home-assistant",
            },
            {
              id: "mdh2",
              label: "กฟส.คช.",
              value: "E3072",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "anc",
          label: "จุดรวมงาน กฟจ.อจ.",
          value: "E308",
          icon: "mdi-home-circle",
          children: [
            {
              id: "anc1",
              label: "กฟจ.อจ.",
              value: "E3081",
              icon: "mdi-home-assistant",
            },
            {
              id: "anc2",
              label: "กฟส.ลท..",
              value: "E3083",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "slp",
          label: "จุดรวมงาน กฟอ.สล.",
          value: "E309",
          icon: "mdi-home-circle",
          children: [
            {
              id: "slp1",
              label: "กฟอ.สล.",
              value: "E3091",
              icon: "mdi-home-assistant",
            },
            {
              id: "slp2",
              label: "กฟส.พทง.",
              value: "E3092",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "sdj",
          label: "จุดรวมงาน กฟอ.สดจ.",
          value: "E310",
          icon: "mdi-home-circle",
          children: [
            {
              id: "sdj1",
              label: "กฟอ.สดจ.",
              value: "E3101",
              icon: "mdi-home-assistant",
            },
            {
              id: "sdj2",
              label: "กฟส.กฉ.",
              value: "E3102",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "ktl",
          label: "จุดรวมงาน กฟอ.กล.",
          value: "E311",
          icon: "mdi-home-circle",
          children: [
            {
              id: "ktl1",
              label: "กฟอ.กล.",
              value: "E3111",
              icon: "mdi-home-assistant",
            },
            {
              id: "ktl2",
              label: "กฟส.ขห.",
              value: "E3112",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "dud",
          label: "จุดรวมงาน กฟอ.ดอ.",
          value: "E312",
          icon: "mdi-home-circle",
          children: [
            {
              id: "dud1",
              label: "กฟอ.ดอ.",
              value: "E3121",
              icon: "mdi-home-assistant",
            },
            {
              id: "dud2",
              label: "กฟส.นย.",
              value: "E3122",
              icon: "mdi-home-assistant",
            },
            {
              id: "dud3",
              label: "กฟส.บฑ.",
              value: "E3123",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "wch",
          label: "จุดรวมงาน กฟอ.วรช.",
          value: "E313",
          icon: "mdi-home-circle",
          children: [
            {
              id: "wch1",
              label: "กฟอ.วรช.",
              value: "E3131",
              icon: "mdi-home-assistant",
            },
            {
              id: "wch2",
              label: "กฟส.พมห.",
              value: "E3132",
              icon: "mdi-home-assistant",
            },
          ],
        },
        {
          id: "tpp",
          label: "จุดรวมงาน กฟอ.ตผ.",
          value: "E314",
          icon: "mdi-home-circle",
          children: [
            {
              id: "tpp1",
              label: "กฟอ.ตผ.",
              value: "E3141",
              icon: "mdi-home-assistant",
            },
            {
              id: "tpp2",
              label: "กฟส.ขร.",
              value: "E3142",
              icon: "mdi-home-assistant",
            },
          ],
        },
      ],

      value: ["reg"],
      fieldValid: false,
      formData: {
        cost_center_name: "กฟส.กทล.",
        date: "19 มิ.ย. 2567",
        type_other: "",
        brand: "HP",
        model: "ProDesk 600 G5",
        contract: "บ.75/2563",
        serial: "4CE03526C6",
        pea_no: "5330404643",
        problem: "ฮาร์ดิสชำรุด",
        emp_name: "นายอนุสรณ์ อมรรัตนศักดิ์",
        emp_role: "พบค.7",
        emp_id: "499857",
        tel: "(22)14890",
        inspector_name: "นายภาณุวิชญ์ ธานีวัฒน์",
        inspector_role: "นรค.7",
        inspector_date: "19 มิ.ย. 2567",
        dep_head_name: "นายสุเธียรพงศ์ ธนาอภิสิทธิ์โสภณ",
        dep_head_role: "หผ.คข.กดส.ฉ.2",
        dep_head_date: "19 มิ.ย. 2567",
      },
    };
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogFixForm(val) {
      val || this.closeFixForm();
    },
  },

  mounted() {
    this.myloadingvariable = true;

    let params = {
      region: "E301000000",
      setAssetType: 53,
    };

    axios
      .get("http://localhost:8080/api/dev/searchNoWordUnpage/", { params })
      .then((resp) => {
        this.getAllResult = resp;

        this.data1 = resp.data.dataExcel;

        this.itemsPerPage = resp.data.itemsPerPage;
        this.totalItems = resp.data.totalItems;
        console.log("at mounted ", this.getAllResult.data.totalItems);
        this.myloadingvariable = false;
      })

      .catch((error) => {
        console.log(error.resp);
      });

    // axios.get('http://localhost:8080/api/dev/test')
    // .then(response => {
    //     console.log(response.data);
    // })
    // .catch(error => {
    //     console.error("There was an error with the test request!", error);
    // });
  },

  created() {
    //this.myloadingvariable = true;
    // console.log("at created");
    // this.getEventsData(); // NEW - call getEventData() when the instance is created
    //this.myloadingvariable = false;
  },
  // NEW

  methods: {
    // async getEventsData() {
    //   // NEW - Use the eventService to call the getEvents() method
    //   DataService.getEvents().then(
    //     ((events) => {
    //       console.log("inside method dataservice", JSON.stringify(events));
    //       this.$set(this, "events", events);
    //     }).bind(this)

    //   );
    // },
    getItemPerPage(val) {
      this.itemsPerPage = val;
      console.log("setItemPerPage ", this.itemsPerPage);
      this.searchFunction();
    },
    // hide_alert: function() {
    //   console.log("at hide_alert");
    //   // `event` is the native DOM event
    // },
    toggleBranch() {
      this.$nextTick(() => {
        if (this.likesAllFruit) {
          this.selectedFruits = [];
          this.appendBranch = [];
          console.log("b-");
        } else {
          this.selectedFruits = this.fruits.slice();
          this.jsonObj = JSON.parse(this.jsonStrBranch);
          this.jsonObj["branch"] = "E3";
          // this.jsonObj["branch"].push("E3");
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
    // toggleType() {
    //   this.$nextTick(() => {
    //     if (this.likesAllTypeSearch) {
    //       this.selectedTypeSearch = [];
    //       this.appendType = [];
    //       console.log("t-");
    //     } else {
    //       this.selectedTypeSearch = this.typeSearch.slice();
    //       this.jsonObj = JSON.parse(this.jsonStrType);
    //       this.jsonObj["type"] = [];
    //       this.jsonObj["type"].push("*");
    //       this.appendType = JSON.stringify(this.jsonObj);
    //       console.log("t-" + this.appendType);
    //       // console.log("fruits" + this.fruits[0]["name"]);
    //     }
    //   });
    // },
    // toggleType2(TypeSearch) {
    //   this.jsonObj = JSON.parse(this.jsonStrType);
    //   this.jsonObj["type"] = [];
    //   this.jsonObj["type"] = TypeSearch;
    //   this.appendType = JSON.stringify(this.jsonObj);
    //   console.log("t-" + this.appendType);
    // },
    treeselectChange: function (node) {
      // alert("changed ", value);
      console.log(node.value);
      this.jsonObj = JSON.parse(this.jsonStrBranch);
      this.jsonObj["branch"] = [];
      this.jsonObj["branch"] = node.value;
      this.appendBranch = JSON.stringify(this.jsonObj);
      console.log("b-" + this.appendBranch);
    },

    toggleAssetType(assetType) {
      this.jsonObj = JSON.parse(this.jsonStrAssetType);
      this.jsonObj["assetType"] = [];
      this.jsonObj["assetType"] = assetType;
      this.setAssetType = JSON.stringify(this.jsonObj);
      console.log("assetType-" + JSON.stringify(this.jsonObj));
    },

    searchFunction() {
      this.qrcode_value2 = [];
      this.result = [];
      this.groupSelected = [];
      this.selected = [];
      if (this.appendBranch == "") {
        this.alert = true;
        window.setInterval(() => {
          this.alert = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        if (this.setAssetType.length == 0) {
          this.setAssetType = JSON.stringify({ assetType: 53 });
        }
        // this.setAssetType.length === 0
        //   ? (this.setAssetType2 = JSON.stringify({ assetType: 53 }))
        //   : (this.setAssetType2 = JSON.parse(this.setAssetType));

        this.myloadingvariable = true;
        let selectedBranch = JSON.parse(this.appendBranch);

        let setAssetType2 = JSON.parse(this.setAssetType);
        // console.log("setAssetType ",this.setAssetType);
        let params = [];
        console.log("itemsPerPage", this.itemsPerPage);
        //ถ้าไม่ใส่คำค้น
        if (this.textSearch.length == 0) {
          // if (this.itemsPerPage > 0) {
          //   params = {
          //     page: 0,
          //     size: this.itemsPerPage,
          //     region: selectedBranch.branch,
          //     setAssetType: setAssetType2.assetType,
          //   };
          //   // console.log("Pattern2 ", params);
          //   axios
          //     .get("http://localhost:8080/api/dev/getAllByPattern2", { params })
          //     .then((resp) => {
          //       this.getAllResult = resp.data;
          //       console.log(
          //         "getAllByPattern2",
          //         JSON.stringify(this.getAllResult),
          //         " resp.data.itemsPerPage ",
          //         resp.data.itemsPerPage
          //       );

          //       this.data1 = resp.data.data1;
          //       this.itemsPerPage = resp.data.itemsPerPage;
          //       this.totalItems = resp.data.totalItems;
          //       this.myloadingvariable = false;
          //     })
          //     .catch((error) => {
          //       console.log(error.resp);
          //     });
          // }
          // else if (this.itemsPerPage == -1) {
          params = {
            region: selectedBranch.branch,
            setAssetType: setAssetType2.assetType,
          };
          console.log("searchNoWordUnpage-", params);
          axios
            .get("http://localhost:8080/api/dev/searchNoWordUnpage", {
              params,
            })
            .then((resp) => {
              this.getAllResult = resp.data;
              console.log(
                "searchNoWordUnpage-" + params["region"],
                JSON.stringify(this.getAllResult)
              );

              this.data1 = resp.data.dataExcel;
              // this.itemsPerPage = resp.data.itemsPerPage;
              this.totalItems = resp.data.totalItems;
              this.myloadingvariable = false;
            })
            .catch((error) => {
              console.log(error.resp);
            });
          // }
        }
        //ถ้าใส่คำค้น
        else {
          params = {
            // page: 0,
            // size: this.itemsPerPage,
            region: selectedBranch.branch,
            textSearch: this.textSearch,
            setAssetType: setAssetType2.assetType,
          };
          console.log("searchFunction ", params);

          axios
            .get("http://localhost:8080/api/dev/searchWithWord", { params })
            .then((resp) => {
              this.getAllResult = resp.data;
              console.log("searchWithWord", JSON.stringify(this.getAllResult));

              this.data1 = resp.data.data1;
              this.itemsPerPage = resp.data.itemsPerPage;
              this.totalItems = resp.data.totalItems;
              this.myloadingvariable = false;
            })
            .catch((error) => {
              console.log(error.resp);
            });
        }
      }
    },

    async fetchData2() {
      if (this.appendBranch == "") {
        this.alert = true;
        window.setInterval(() => {
          this.alert = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        // if (this.setAssetType.length == 0) {
        //   this.setAssetType = JSON.stringify({ assetType: 53 });
        // }
        // this.myloadingvariable = true;
        // let selectedBranch = JSON.parse(this.appendBranch);
        // let setAssetType = JSON.parse(this.setAssetType);
        // // console.log("setAssetType ",this.setAssetType);
        // let params = [];
        // params = {
        //   region: selectedBranch.branch,
        //   setAssetType: setAssetType.assetType,
        // };
        // let response = await axios
        //   .get("http://localhost:8080/api/dev/getAllByPattern2unpage", {
        //     params,
        //   })
        //   .then((resp) => {
        //     this.getAllResult = resp.data;
        //     console.log(
        //       "getAllByPattern2unpage",
        //       JSON.stringify(this.getAllResult)
        //     );

        //     this.dataExcel = resp.data.dataExcel;
        //     this.itemsPerPage = resp.data.itemsPerPage;
        //     this.totalItems = resp.data.totalItems;
        //     this.myloadingvariable = false;
        //     return this.dataExcel;
        //   })
        //   .catch((error) => {
        //     console.log(error.resp);
        //   });

        this.dataExcel = this.data1;
        this.myloadingvariable = false;
        console.log("dataExcel : ", this.dataExcel);
        return this.dataExcel;
      }
    },

    startDownload() {
      alert("show loading");
    },
    finishDownload() {
      alert("hide loading");
    },

    editItem(item) {
      this.editedIndex = this.data1.indexOf(item);
      this.editedItem = Object.assign({}, item);
      console.log(this.editedItem);
      (this.qrcode_value =
        // JSON.parse([
        JSON.stringify({
          pea_no: this.editedItem["devPeaNo"],
          description: this.editedItem["devDescription"],
          serial: this.editedItem["devSerialNo"],
          user_id: this.editedItem["tbEmployee"]["empId"],
          user_name: this.editedItem["tbEmployee"]["empName"],
          received_date: this.editedItem["devReceivedDate"],
          price_recieve: this.editedItem["devReceivedPrice"],
          price_left: this.editedItem["devLeftPrice"],
          cc_short_name: this.editedItem["tbCostCenterTest"]["ccShortName"],
          cost_center: this.editedItem["tbCostCenterTest"]["ccLongCode"],
        })),
        (this.dialog = true);
    },

    showFixForm(item) {
      this.editedIndex = this.data1.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogFixForm = true;
    },

    sendFixPage() {
      let data = JSON.stringify({
        templateProjectPath: "sample/ams/506027-fixform.dito",
        templateName: "output",
        pdfVersion: "1.7",
      });

      let config = {
        method: "post",
        maxBodyLength: Infinity,
        url: "http://localhost:8080/api/proxy-pdf-producer",
        headers: {
          Accept: "application/pdf",
          "Content-Type": "application/json",
        },
        data: data,
      };

      axios
        .request(config)
        .then((response) => {
          console.log("PDF generated successfully.", response);
        })
        .catch((error) => {
          console.error("Error occurred while generating PDF:", error);
        });

      // axios
      //   .post("http://localhost:8080/api/dev/redirectPdfProducer", data, {
      //     responseType: "blob", // Important
      //   })
      //   .then((response) => {
      //     const blob = new Blob([response.data], { type: "application/pdf" });
      //     const link = document.createElement("a");
      //     link.href = window.URL.createObjectURL(blob);
      //     link.download = "generated.pdf";
      //     link.click();
      //   })
      //   .catch((error) => {
      //     console.error("There was an error redirecting!", error);
      //   });

      // axios
      //   .get("http://localhost:8080/api/dev/redirectgoogle")
      //   .then((response) => {
      //     // Assuming the API returns an object with a key 'redirectUrl'
      //     const redirectUrl = response.data.redirectUrl;
      //     if (redirectUrl) {
      //       // Redirect the browser to the URL
      //       window.location.href = redirectUrl;
      //     } else {
      //       console.error("Redirect URL not found in the response.");
      //     }
      //   })
      //   .catch((error) => {
      //     console.error("There was an error with the test request!", error);
      //   });
    },

    sendpostmanecho() {
      const data = {
        name: "ChatGPT",
        message: "Hello from JavaScript!",
      };

      axios
        .post("http://localhost:8080/api/postmanecho", data, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          console.log("Response from Postman Echo:", response.data);
        })
        .catch((error) => {
          console.error("Error occurred:", error);
        });
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeFixForm() {
      this.dialogFixForm = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem);
      } else {
        this.desserts.push(this.editedItem);
      }
      this.close();
    },
    enterSelect() {
      let e = this.selected.map((e) => e);
      // console.log(e.length); // logs all the selected items.
      this.qrcode_value2 = [];
      this.detail_value = [];
      this.groupSelected = e;
      console.log(this.groupSelected.length);
      // this.qrcode_value2 = JSON.stringify(this.groupSelected);
      // user_id: this.editedItem["tbEmployee"]["empId"],
      // user_name: this.editedItem["tbEmployee"]["empName"],
      let i = 0;
      this.result = this.groupSelected.map(({ devPeaNo }) => ({ devPeaNo }));
      this.result2 = this.groupSelected.map(({ devPeaNo }) => ({ devPeaNo }));
      // result.forEach((element) => {
      //   element.empId = this.groupSelected.tbEmployee.empId;
      // });
      for (i = 0; i < this.groupSelected.length; i++) {
        if (this.groupSelected[i].tbEmployee !== null) {
          this.result[i].empId = this.groupSelected[i].tbEmployee.empId;
          this.result[i].empName = this.groupSelected[i].tbEmployee.empName;
          // this.result2[i].empId = this.groupSelected[i].tbEmployee.empId;
          // this.result2[i].empName = this.groupSelected[i].tbEmployee.empName;
        } else {
          this.result[i].empId = "ไม่ระบุ";
          this.result[i].empName = "ไม่ระบุ";
          // this.result2[i].empId = "ไม่ระบุ";
          // this.result2[i].empName = "ไม่ระบุ";
        }
        // result[i].empId = this.groupSelected[i].tbEmployee.empId;
        // result[i].empId = this.groupSelected[i].tbEmployee.empId;
        this.result[i].devSerialNo = this.groupSelected[i].devSerialNo;
        // this.result2[i].devSerialNo =
        // this.groupSelected[i].devSerialNo;

        this.result[i].devReceivedDate = this.groupSelected[i].devReceivedDate;

        this.result[i].devReceivedPrice =
          this.groupSelected[i].devReceivedPrice;

        this.result[i].devLeftPrice = this.groupSelected[i].devLeftPrice;

        this.result[i].ccLongCode =
          this.groupSelected[i].tbCostCenterTest.ccLongCode;

        this.result[i].ccShortName =
          this.groupSelected[i].tbCostCenterTest.ccShortName;
        this.result2[i].ccShortName =
          this.groupSelected[i].tbCostCenterTest.ccShortName;

        this.result[i].devDescription = this.groupSelected[i].devDescription;
      }

      for (i = 0; i < this.result.length; i++) {
        console.log(JSON.stringify(this.result[i]));
        // this.qrcode_value2[i].push(JSON.stringify(this.groupSelected[i].devPeaNo)); ?region=ZC05020000
        this.detail_value.push(JSON.stringify(this.result[i]));
      }
      for (i = 0; i < this.result.length; i++) {
        console.log(JSON.stringify(this.result2[i]));
        // this.qrcode_value2[i].push(JSON.stringify(this.groupSelected[i].devPeaNo)); ?region=ZC05020000
        this.qrcode_value2.push(JSON.stringify(this.result2[i].devPeaNo));
      }
      if (this.selected.length == this.itemsPerPage) {
        alert("selected all");
      }
    },

    // genQR_Code() {},

    generateReport() {
      // var opt = {
      //   margin:       [30, 0, 30, 0], //top, left, buttom, right
      //   // filename:    name + '.pdf',
      //   // image:        { type: 'jpeg', quality: 0.98 },
      //   // html2canvas:  { dpi: 192, scale: 2, letterRendering: true},
      //   // jsPDF:        { unit: 'pt', format: 'a4', orientation: 'portrait'},
      //   // pageBreak: { mode: 'css', after:'.break-page'}
      //   };
      if (this.groupSelected.length == 0) {
        this.alert2 = true;
        window.setInterval(() => {
          this.alert2 = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        this.$refs.html2Pdf.generatePdf();
      }
    },

    genFixFormReport() {
     console.log("this.editedItem",this.editedItem);
      if (this.editedItem == null) {
        this.alert = true;
        window.setInterval(() => {
          this.alert = false;
          // console.log("hide alert after 3 seconds");
        }, 3000);
      } else {
        // Make a POST request to the Spring Boot endpoint
        axios
          .post("http://localhost:8080/api/dev/redirectPdfProducer", this.editedItem, {
            responseType: "blob", // Important: To handle the response as a binary blob (PDF)
            headers: {
              "Content-Type": "application/json",
              Accept: "application/pdf",
            },
          })
          .then((response) => {
            // Create a blob from the response
            const fileBlob = new Blob([response.data], {
              type: "application/pdf",
            });
            const fileURL = URL.createObjectURL(fileBlob);

            // Create a temporary anchor element to download the PDF
            const link = document.createElement("a");
            link.href = fileURL;
            link.setAttribute("download", "generated.pdf"); // Filename for the download
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
          })
          .catch((error) => {
            console.error("Error generating PDF:", error);
            this.alert = true;
            setTimeout(() => {
              this.alert = false;
            }, 3000);
          });
      }
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
    formTitle() {
      return this.editedIndex === -1 ? "New Item" : "QR Code";
    },
    formDevPeaNo() {
      return this.editedIndex === -1 ? "New Item" : this.editedItem["devPeaNo"];
    },
    treeselectClass() {
      return {
        "treeselect-invalid": !this.fieldValid,
      };
    },
  },
};
