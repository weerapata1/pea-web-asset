
import axios from "axios";

export default {
    name: "EventsList",
    data() {
      return {
        valid : null,
        model: null,
        items: [],
        getDeviceResult:[],
        getEmployeeResult:[],
        deviceheaders:[ {
            text: "เลขทรัพย์สิน",
            align: "start",
            value: "devPeaNo",
            // width: "10%",
          },
          {
            text: "คำอธิบายของสินทรัพย์",
            value: "devDescription",
            // width: "6%"
          },
          {
            text: "ชื่อผู้ครอบครอง",
            value: "tbEmployee.empName",
            // width: "25%"
          },
          {
            text: "วันที่ได้รับ",
            value: "devReceivedDate",
            // width: "25%"
          },
        ],
        employeeheaders:[
            {
                text: "รหัสพนักงาน",
                align: "start",
                value: "empId",
                // width: "10%",
              },
              {
                text: "ชื่อ-นามสกุล",
                value: "empName",
                // width: "6%"
              },
              {
                text: "ตำแหน่ง",
                value: "empRole",
                // width: "25%"
              },
        ],
        checkQuotaResult:'',
        totalDeviceResult: 0,
        totalEmployeeResult: 0,
        showVRow: false,
        totalEmployee:'',
        totalDevice:'',
      }
    },

    mounted() {
        axios
          .get('http://localhost:8080/cc/getAllCC')
          .then(response => {
             this.items = response.data;  
          })
      },

      methods:{
        async checkQuota() {
            console.log(this.model['ccLongCode']);
            let params = [];
            
            params = {
                region: this.model['ccLongCode'],
                dt_id: 1
              };
              await axios
              .get("http://localhost:8080/api/dev/getDevice53unpageByccId", { params })
              .then((resp2) => {
                // this.getAllResult = resp.data;
                // console.log(
                //   "getAllByPattern2unpage",
                //   JSON.stringify(this.getAllResult)
                // );

                this.getDeviceResult = resp2.data.dataDevice;
                this.totalDeviceResult = resp2.data.totalItems;
                // this.myloadingvariable = false;
              })
              .catch((error) => {
                console.log(error.resp);
              });

            // let params = [];
            let ccLong = this.model['ccLongCode'];
            console.log(ccLong);
              params = {
                region: ccLong,
              };
              await axios
              .get("http://localhost:8080/getEmpByccLongCode", { params })
              .then((resp2) => {
                // this.getEmployeeResult = resp.data;
                // console.log(
                //   "getEmpByccLongCode",
                //   JSON.stringify(this.getEmployeeResult)
                // );

                this.getEmployeeResult = resp2.data.dataEmployee;
                this.totalEmployeeResult = resp2.data.totalItems;
                // this.myloadingvariable = false;

              })
              .catch((error) => {
                console.log(error.resp);
              });

              if(this.totalEmployeeResult > this.totalDeviceResult)
              {
                this.checkQuotaResult = 'คอมพิวเตอร์น้อยกว่าจำนวนคน ' + this.totalEmployeeResult + ' - ' +  this.totalDeviceResult
              }
              else if( this.totalEmployeeResult ==  this.totalDeviceResult ||  this.totalEmployeeResult <  this.totalDeviceResult)
              {
                this.checkQuotaResult = 'คอมพิวเตอร์เพียงพอกับจำนวนคน ' + this.totalEmployeeResult + ' - ' +  this.totalDeviceResult
              }
          this.showVRow = true;
        },
      },

}