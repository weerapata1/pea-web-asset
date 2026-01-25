import axios from "axios";
import TrackingStepFristComponent from "./TrackingStep1.vue"
import TrackingStepSecondComponent from "./TrackingStep2.vue"
import TrackingStepThirdComponent from "./TrackingStep3.vue"

let url = "http://localhost:8080";
// let urlRepair = "http://localhost:8080/repair";
// let url = `${process.env.VUE_APP_BASE_URL}`;


export default {
    name: "TrackingRepairComponent",
    components: {TrackingStepFristComponent ,TrackingStepSecondComponent ,TrackingStepThirdComponent},
    data() {

        return {
            singleSelect: true,
            pickOneDeviceItem: [],
            loading: false,

            adminLogin: false,
            dialog: false,
            // headers of table search
            headers: [
                {text: 'รหัสทรัพย์สิน', align: 'start', sortable: false, value: 'devPeaNo'},
                {text: 'คำอธิบาย', value: 'devDescription'},
                {text: 'หมายเลขผลิตภัณฑ์', value: 'devSerialNo'},
                {text: 'รหัสพนักงาน', value: 'tbEmployee.empId'},
                {text: 'ผู้ครอบครอง', value: 'tbEmployee.empName'},
                {text: 'สังกัด', value: 'tbCostCenter.ccShortName'},
                {text: 'วันที่โอนเข้าเป็นทุน', value: 'devReceivedDate'},

            ],
            // data of table search
            resultSearchDeviceItem: [],
            resultCreateRepairDeviceItem: [],


            step: 1,
            textSearch: '',
            devPeaNoSelected: '',
            devPeaNoRule: [],
            valid: false,
            checkRepair: false,

            inputDetailForm: {
                sendPhoneNum: "",
                defectDetail: "",
                adminReceive: "",
                empSend: "",
            }
            ,
            scrollInvoked: 0,
            // in final get from api tbEmployee where role eq. admin
            adminReceiveItems: [

                {"adminRecName": 'นายภาณุวิชญ์ ธานีวัฒน์', "adminRecId": "506027"},
                {"adminRecName": 'นายวีรภัทร ทวีศักดิ์', "adminRecId": "512099"},
                // {"adminRecName":'Item 3',"empId": "506027"},
                // {"adminRecName":'Item 4',"empId": "506027"},
                // {"adminRecName":'Item 5',"empId": "506027"},
                // {"adminRecName":'Item 6',"empId": "506027"},
                // {"adminRecName":'Item 7',"empId": "506027"},
            ],


        }
    },
    computed: {},

    mounted() {
        // use get empAdmin role
        // axios.get(url + "/empAdmin/getEmpAdmin").then((response) => {
        //     this.empAdmin = response.data;
        // });
        // use get role Name
        // axios.get(url + "/cc/getAllCCOnlyUse").then((response) => {
        //     this.itemCC = response.data.costCenter;
        // });

    },

    methods: {
        adminLoginCheck() {
            if (this.adminLogin == false) {
                this.dialog = true;
            } else {
                this.dialog = false;
            }
        },
        adminLoginX() {
            this.adminLogin = true;
            // document.cookie("adminLogin", "true", {
            //     expires: 7,
            //     path: "/",
            // })
            // console.log("adminLogin : ", this.adminLogin)
            // console.log("document.cookie : ", document.cookie)
        },
        adminLogOutX() {
            this.adminLogin = false;
            // console.log("adminLogin : ", this.adminLogin)
        },
        onScroll() {
            this.scrollInvoked++
        },
        searchDeviceByPeaNo() {
            this.loading = true;
            this.resultSearchDeviceItem = [];
            this.pickOneDeviceItem = [];

            let params = {
                "textSearch": this.textSearch
            }
            axios.get(url + "/api/dev/getDeviceLast4Digit", {params})
                .then((response) => {
                    this.resultSearchDeviceItem = response.data;

                }).finally(() => {
                    this.loading = false;
                }
            );
        },
        createRepair() {
            const params = {
                "sendPhoneNum": this.inputDetailForm.sendPhoneNum,
                "defectDetail": this.inputDetailForm.defectDetail,
                "peaNo": this.pickOneDeviceItem[0].devPeaNo,
                "adminReceive": this.inputDetailForm.adminReceive,
                "empSend": this.inputDetailForm.empSend,
            }
            // console.log("params : ", params)
            axios.post(url + "/repair/createRepair", params)
                .then((response) => {
                    this.resultCreateRepairDeviceItem = response.data;
                    console.log("createRepairDeviceItem : ", this.resultCreateRepairDeviceItem);
                }).finally(() => {
                this.reset()
                alert("บันทึกเรียบร้อย")
                setTimeout(() => {
                }, 50)
                this.step = 1;
            })

        },
        reset() {
            this.$refs.form.reset()
            this.pickOneDeviceItem = []
            this.resultSearchDeviceItem = [];
            this.textSearch = ''
        },
    },
};
