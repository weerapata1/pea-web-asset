import axios from "axios";

export default {

    name: "mainRepair",
    data: () => ({
        checkbox: true,
        items: [],
        value: null,
        headers: [
            { text: 'เลขทรัพย์สิน', align: 'start', sortable: false, value: 'device.devPeaNo', },
            { text: 'การไฟฟ้า', value: 'device.tbCostCenterTest.ccFullName' },
            // { text: 'เลขที่สัญญา', value: 'damageDetail' },
            { text: 'หมายเลขผลิตภัณฑ์', value: 'device.devSerialNo' },
            { text: 'วันที่ส่งซ่อม', value: 'recivedIn' },
            // { text: 'ผู้ส่งซ่อม', value: 'damageDetail' },
            { text: 'สถานะ', value: 'tbRepairStatus.statusName' },

        ],
        data1: [],
    }),
    mounted() {

        axios.get("http://localhost:8080/cc/getAllCC").then((res => {
            this.items = res.data;
        })).catch(error => {
            console.log(error);
        });

        axios.get("http://localhost:8080/repair/getAllRepair").then((res => {
            this.data1 = res.data;
        })).catch(error => {
            console.log(error);
        });
    },

    computed: {

    },
    methods: {
        find(value) {
            let yy = value.ccLongCode
            let xx = "http://localhost:8080/repair/getByLocation"
            axios.get(xx,{ params : {location : yy}}).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });

        }
    },
}