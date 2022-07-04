const defTemp = {
    subject: "ขอให้จัดซ่อมคอมพิวเตอร์ และอุปกรณ์ประกอบ",
    substance: "ขอแจ้งเครื่องชำรุจเพื่อส่งซ่อมตามรายการดังนี้"

}
import jsPDF from 'jspdf'
// import html2canvas from "html2canvas"

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
        download() {
            const doc = new jsPDF();
            const contentHtml = this.$refs.content.innerHTML;
            doc.fromHTML(contentHtml, 15, 15, {
                width: 170
            });
            doc.save("sample.pdf");
        },
        download2() {
            alert('555')
        }
    }


}