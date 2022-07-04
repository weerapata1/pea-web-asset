import axios from "axios";

let url = "http://localhost:8080";
export default {
    name: "listRepair",
    data() {
        return {
            items: [],
            value: null,
            state: null,
            data1: [],
            headers: [
                { text: 'เลขทรัพย์สิน', align: 'start', sortable: false, value: 'device.devPeaNo', },
                { text: 'การไฟฟ้า', value: 'device.tbCostCenterTest.ccShortName' },
                { text: 'คำอธิบายทรัพย์สิน', value: 'device.devDescription' },
                // { text: 'เลขที่สัญญา', value: 'damageDetail' },
                { text: 'หมายเลขผลิตภัณฑ์', value: 'device.devSerialNo' },
                { text: 'วันที่ส่งซ่อม', value: 'recivedIn' },
                // { text: 'ผู้ส่งซ่อม', value: 'damageDetail' },
                { text: 'สถานะ', value: 'tbRepairStatus.statusName' },

            ],
        }
    },
    mounted() {
        axios.get(url + "/cc/getAllCC").then((res => {
            this.items = res.data;
        })).catch(error => {
            console.log(error);
        });

        axios.get(url + "/repair/getAllRepair").then((res => {
            this.data1 = res.data;
        })).catch(error => {
            console.log(error);
        });
    },
    methods: {
        lookFor(value) {
            if (this.state == 1) {
                this.find(value, 1);
            }
            else if (this.state == 2) {
                this.find(value, 2);
            }
            else if (this.state == 3) {
                this.find(value, 3);
            }
        },
        find(value, state) {
            let yy = value.ccLongCode
            axios.get(url + "/repair/getByLocAndSta", { params: { location: yy, status: state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
            console.log(yy + " " + state)
        },

        reseveInState() {
            this.state = 1;
            axios.get(url + "/repair/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
            console.log("reseveIn", this.state)
        },
        inProgressState() {
            this.state = 2;
            axios.get(url + "/repair/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
            console.log("inProgress", this.state)
        },
        doneState() {
            this.state = 3;
            axios.get(url + "/repair/getByStatusId", { params: { status: this.state } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });
            console.log("done", this.state)
        },
        recived(item) {
            console.log(item.repairId);
        },
        inProgress(item) { console.log(item.repairId); },
        done(item) { console.log(item.repairId); },

    }
}