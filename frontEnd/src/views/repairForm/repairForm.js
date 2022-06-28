const defTemp = {
    subject: "ขอให้จัดซ่อมคอมพิวเตอร์ และอุปกรณ์ประกอบ",
    substance: "ขอแจ้งเครื่องชำรุจเพื่อส่งซ่อมตามรายการดังนี้"

}
import  jspdf  from "jspdf";

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
        download(){
            console.log("555");
            const doc = new jspdf();
            
            // const html = this.$refs.content.innerHTML

            // doc.fromHTML(html ,15,15)
            doc.text("5555+" , 10 ,10)

            doc.save("a4.pdf");
        },
        download2(){
            alert('555')
        }
    }


}