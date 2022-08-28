import axios from "axios";

export default {

    name: "TrackingRepair",
    data: () => ({
        checkbox: true,
        items: [],
        value: null,
        headers: [
            { text: 'เลขทรัพย์สิน',          align: 'start', sortable: false, value: 'device.devPeaNo', },
            { text: 'การไฟฟ้า',            align: 'start', sortable: false, value: 'device.tbCostCenterTest.ccShortName' },
            { text: 'คำอธิบายทรัพย์สิน',     align: 'start', sortable: false, value: 'device.devDescription' },
            // { text: 'เลขที่สัญญา', value: 'damageDetail' },
            { text: 'หมายเลขผลิตภัณฑ์',    align: 'start', sortable: false, value: 'device.devSerialNo' },
            { text: 'วันที่ส่งซ่อม',          align: 'start', sortable: false, value: 'sendDate' },
            { text: 'ผู้ส่งซ่อม',           align: 'start', sortable: false, value: 'empSend.empName' },
            { text: 'สถานะ',            align: 'start', sortable: false, value: 'repairStatus.statusName' },

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
            console.log(res.data)
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
            axios.get(xx, { params: { location: yy } }).then((res => {
                this.data1 = res.data;
            })).catch(error => {
                console.log(error);
            });

        }
    },
}