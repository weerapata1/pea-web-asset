import axios from "axios";
// import { validationMixin, required, maxLength, email } from 'vuelidate'
import { required } from 'vuelidate'

let url = "http://localhost:8080";
let urlRepair = "http://localhost:8080/repair";
export default {
    name: "listRepair",
    validations: {
        adName: { required },
        examine: { required },
    },
    data() {
        return {
            recived2 : 0,
            examineSelect: {},
            examines: [
                { header: 'จอภาพ(Monitor)' },
                { name: 'หลอดภาพชำรุด/CRT' },
                { name: 'การ์ดจอไหม้' },
                { name: 'จอภาพชำรุด/เสื่อมสภาพ' },
                { name: 'แผงวงจรไหม้/ชำรุด' },
                { name: 'จอสีเพี้ยน/มีเส้น' },
                { name: 'หน้าจอแตก">หน้าจอแตก' },
                { name: 'จอเปิดไม่ติด/สวิทช์ชำรุด' },
                { divider: true },
                { header: 'CPU/Notebook' },
                { name: 'เมนบอร์ดชำรุด/ไหม้' },
                { name: 'ฮาร์ดดิสก์ชำรุด ' },
                { name: 'ปุ่มเปิดชำรุดดับเอง' },
                { name: 'ระบบไฟลัดวงจร' },
                { name: 'พาวเวอร์ซัพพลายชำรุด' },
                { name: 'ประมวลผลช้า' },
                { name: 'ระบบภายในชำรุด' },
                { divider: true },
                { header: 'เครื่องพิมพ์(Printer)' },
                { name: 'หลอดสร้างภาพชำรุด' },
                { name: 'เมนบอร์ดชำรุด/ไหม้ ' },
                { name: 'ฟีดกระดาษชำรุด' },
                { name: 'พาวเวอร์ซัพพลายชำรุด' },
                { name: 'กระดาษติด/ยับ' },
                { name: 'ผงหมึกรั่ว' },
                { name: 'เปิดไม่ติด/สวิทช์ชำรุด' },
                { name: 'ไม่มีไฟสถานะทำงาน' },
                { name: 'ตัวหนังสือจาง' },
                { name: 'หัวเข็มชำรุด' },
                { divider: true },
                { header: 'UPS(เครื่องสำรองไฟ)' },
                { name: 'แบตเตอรี่เสื่อม' },
                { name: 'แผงวงจรไหม้/ชำรุด' },
                { name: 'ไม่เก็บกระแสไฟ' },
                { name: 'ปุ่มเปิด/ปิดไม่ทำงาน' },
            ],
            adNameSelect: {},
            adName: null,
            
            value: null,
            state: null,
            data1: [],
            items: [],
            headers: [
                { text: 'เลขทรัพย์สิน', align: 'start', sortable: false, value: 'device.devPeaNo', },
                { text: 'การไฟฟ้า', value: 'device.tbCostCenterTest.ccShortName' },
                { text: 'คำอธิบายทรัพย์สิน', value: 'device.devDescription' },
                // { text: 'เลขที่สัญญา', value: 'damageDetail' },
                { text: 'หมายเลขผลิตภัณฑ์', value: 'device.devSerialNo' },
                { text: 'วันที่ส่งซ่อม', value: 'sendDate' },
                { text: 'ผู้ส่งซ่อม', value: 'empSend.empId' },
                { text: 'สถานะ', value: 'tbRepairStatus.statusName' },

            ],
            diaRec: false,
        }
    },
    mounted() {
        axios.get(url + "/cc/getAllCC").then((res => {
            this.items = res.data;
        })).catch(error => {
            console.log(error);
        });
        axios.get(url + "/empAdmin/getAllAd").then((res => {
            console.log(res.data)
            this.adName = res.data;
        })).catch(error => {
            console.log(error);
        });

        axios.get(urlRepair + "/getAllRepair").then((res => {
            this.data1 = res.data;
        })).catch(error => {
            console.log(error);
        });
    },
    computed: {

    },
    methods: {
        lookFor(value) {

            if (this.state == 1) {
                // console.log(value)
                this.find(value, 1);
            }
            else if (this.state == 2) {
                this.find(value, 2);
            }
            else if (this.state == 3) {
                this.find(value, 3);
            }
            else if (this.state == 4) {
                this.find(value, 4);
            }
            else{
                this.find2(value);
            }
        },
        find(value, state) {
            let yy = value.ccLongCode
            console.log(yy)
            axios.get(urlRepair + "/getByLocAndSta", { params: { location: yy, status: state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
            console.log(yy + " " + state)
        },
        find2(value) {
            let yy = value.ccLongCode
            console.log(yy)
            axios.get(urlRepair + "/getByLocation", { params: { location: yy } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },
        allStste() {
            this.state = 0;
            axios.get(urlRepair + "/getAllRepair").then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },

        reseveInState() {
            this.state = 1;
            axios.get(urlRepair + "/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },
        inProgressState() {
            this.state = 2;
            axios.get(urlRepair + "/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },
        doneState() {
            this.state = 3;
            axios.get(urlRepair + "/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },
        returnState() {
            this.state = 4;
            axios.get(urlRepair + "/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
        },
        recived(item) {  
            // this.diaRec = false;
            this.recived2 = item.repairId;
            console.log("recice : " + item.repairId+ " | " + this.recived2);
        },
        RecSave(ad,ex){
            
            console.log("value : "+ ad +" "+ ex + " " + this.recived2)
            this.examineSelect = [];
            this.adNameSelect = [];
            this.diaRec = false;
        },
        inProgress(item) { console.log(item.repairId); },
        
        done(item) { console.log(item.repairId); },

        
        close(){
            this.diaRec = false;
            // this.examineSelect = [];
            // this.adNameSelect = [];
        }
    }
}