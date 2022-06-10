const defTemp = {
    subject: "ขอให้จัดซ่อมคอมพิวเตอร์ และอุปกรณ์ประกอบ",
    substance: "ขอแจ้งเครื่องชำรุจเพื่อส่งซ่อมตามรายการดังนี้"

}

export default {
    name: "repairForm",
    data() {
        return {
            defTemp,
        }
    },
    methods: {
        currentDate() {
            const current = new Date();
            const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
            return date;
        },
    }


}