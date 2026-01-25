<template>
  <v-container fluid style="max-width:100%" class="mb-2">
    <v-row>
      <v-card min-width="80%" max-width="100%" min-height="600px" max-height="90%" color="pink lighten-4">
        <v-col cols="12" sm="12" md="12">
          <v-form ref="form" class="mt-4 ">
            <v-text-field
                v-model="textSearch"
                :rules="devPeaNoRule"
                :counter="11"
                required
                @keyup.enter="searchDeviceByPeaNo"
                label="กรอกรหัสทรัพย์สิน, หมายเลขผลิตภัณฑ์หรือหมายเลขซ่อม"
                placeholder="โปรดกรอกอย่างน้อย 4 ตัวอักษร"
                solo
            ></v-text-field>
          </v-form>
        </v-col>

        <!-- button -->
        <v-card-actions class="justify-center mt-n10">
          <v-col cols="12" sm="6" md="6">
            <v-btn
                large
                block
                color="error lighten-1"
                @click="reset"
            >
              ล้างค่า
            </v-btn>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <v-btn
                large
                block
                color="primary lighten-1"
                @click="searchDeviceByPeaNo"
            >
              ค้นหา
            </v-btn>
          </v-col>
        </v-card-actions>

        <v-card-actions class="justify-center mt-n7">
          <v-col cols="12" sm="6" md="4">
            <v-btn
                large
                block
                color="red lighten-3"
                @click="getRepairByStepStatus(1)"
            >
              กำลังดำเนินการ
            </v-btn>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-btn
                large
                block
                color="yellow lighten-4"
                @click="getRepairByStepStatus(2)"
            >
              พร้อมส่งมอบ
            </v-btn>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-btn
                large
                block
                color="green lighten-4"
                @click="getRepairByStepStatus(3)"
            >
              เสร็จแล้ว
            </v-btn>
          </v-col>
        </v-card-actions>

        <v-col cols="12" sm="12" md="12">
          <v-data-table
              v-model="pickOneDeviceItem"
              :headers="headers"
              :items="repairLists"
              :loading="loading"
              :items-per-page="9"
              loading-text="กำลังดึงข้อมูล..."
              item-key="devPeaNo"
              class="elevation-1"
          >
            <!-- ช่อง actions -->
            <template v-slot:[`item.actions`]="{ item }">
              <v-btn icon small color="primary" @click="readItem(item) ; ">
                <v-icon>mdi-eye</v-icon>
              </v-btn>
            </template>
          </v-data-table>
        </v-col>
      </v-card>
    </v-row>

    <!--    #################-->
    <v-dialog
        v-model="readingDialog"
        max-width="70%"
        max-hight="80%"
        persistent
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">สถานะการซ่อม : {{ fields[11].value }}</span>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text>
          <v-container fluid>
            <v-row>
              <v-col
                  dense
                  v-for=" i in fields.length"
                  :key="i.label"
                  cols="12"
                  md="4"
              >
                <v-textarea
                    class="readonly-textarea"
                    dense
                    :label="fields[i-1].label"
                    :value="fields[i-1].value || 'No data'"
                    rows="2"
                    row-height="20"
                    readonly
                    auto-grow
                    disabled

                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>

          <v-row
              persistent-hint>
            <v-divider></v-divider>
            <v-col cols="12" sm="6" md="12">
              <v-text-field
                  label="ระบุอาการเสีย"
                  v-model="adminDefectReview"
                  :disabled="isProcessing !== 'กำลังดำเนินการ'"
                  :hint="isProcessing !== 'กำลังดำเนินการ' ? 'ไม่สามารถแก้ไขได้ในสถานะนี้' : ''"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="12">
              <v-text-field
                  label="วิธีซ่อม"
                  v-model="fixMethod"
                  :disabled="isProcessing !== 'กำลังดำเนินการ'"
                  :hint="isProcessing !== 'กำลังดำเนินการ' ? 'ไม่สามารถแก้ไขได้ในสถานะนี้' : ''"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="3" md="6">
              <v-combobox
                  label="วิธีการซ่อม"
                  v-model="myMethodSelect"
                  :items="myMethodItems"
                  :disabled="isProcessing !== 'กำลังดำเนินการ'"
                  :hint="isProcessing !== 'กำลังดำเนินการ' ? 'ไม่สามารถแก้ไขได้ในสถานะนี้' : ''"
              ></v-combobox>
            </v-col>
            <v-col cols="12" sm="3" md="6">
              <v-text-field
                  label="ค่าใช้จ่าย"
                  v-model="costOfRepair"
                  :disabled="isProcessing !== 'กำลังดำเนินการ'"
                  :hint="isProcessing !== 'กำลังดำเนินการ' ? 'ไม่สามารถแก้ไขได้ในสถานะนี้' : ''"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row v-if="isProcessing == 'พร้อมส่งมอบ'">
            <v-divider></v-divider>
            <v-col cols="12" sm="6" md="12">
              <v-text-field
                  label="ผู้มารับ"
                  v-model="adminDefectReview"
                  :disabled="isProcessing !== 'พร้อมส่งมอบ' "
                  :hint="isProcessing !== 'เสร็จแล้ว' ? 'ไม่สามารถแก้ไขได้ แก้ไขได้เมื่อพร้อมส่งเท่านั้น' : ''"
                  persistent-hint
              ></v-text-field>
            </v-col>
          </v-row>

        </v-card-text>

        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="Red darken-2"
              text
              @click="cancel"
          >
            Cancel
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              v-if="isProcessing !== 'เสร็จแล้ว'"
              @click="Save()"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>


    </v-dialog>
  </v-container>
</template>

<script>
// import {get} from "core-js/internals/map-helpers";

import axios from "axios";

let url = "http://localhost:8080";
// let urlRepair = "http://localhost:8080/repair";
// let url = `${process.env.VUE_APP_BASE_URL}`;

export default {
  name: "TrackingStepSecondComponent",
  data() {
    return {
      textSearch: '',
      devPeaNoRule: [],
      pickOneDeviceItem: [],
      readingItemSelected: {},
      readingDialog: false,
      loading: false,
      dialog: false,
      adminReview: '',
      adminDefectReview: '',
      fixMethod: '',
      costOfRepair: '',


      // headers of table search
      headers: [
        {text: 'หมายเลขซ่อม', value: 'repairCode'},
        {text: 'รหัสทรัพย์สิน', align: 'start', sortable: false, value: 'device.devPeaNo'},
        {text: 'คำอธิบาย', value: 'device.devDescription'},
        {text: 'หมายเลขผลิตภัณฑ์', value: 'device.devSerialNo'},
        {text: 'ผู้ครอบครอง', value: 'device.tbEmployee.empName'},
        {text: 'อาการชำรุด', value: 'defectDetail'},
        {text: 'วันที่แเจ้งซ่อม', value: 'admitDate'},
        {text: 'สถานะ', value: 'repairStatus.statusName'},
        {text: 'Actions', value: 'actions', sortable: false},

      ],
      // fields: [],
      // data of table search
      repairLists: [],
      resultCreateRepairDeviceItem: [],

      myMethodSelect :'',
      myMethodItems :[
          {text: 'ดำเนินการซ่อมเอง' },
          {text: 'ส่งบริษัทดำเนินการ' }
      ],

    }
  },
  computed: {
    isProcessing() {
      const Status = this.fields[11].value
      switch (Status) {
        case 'กำลังดำเนินการ':
          // console.log("isProcessing : ", this.fields[11].value)
          return 'กำลังดำเนินการ';
        case 'พร้อมส่งมอบ':
          // console.log("isProcessing : ", this.fields[11].value)
          return 'พร้อมส่งมอบ'
        case 'เสร็จแล้ว':
          // console.log("isProcessing : ", this.fields[11].value)
          return 'เสร็จแล้ว'
        default :
          return null;
      }
    },
    fields() {
      const d = this.readingItemSelected;
      return [
        {label: "รหัสทรัพย์สิน", value: d?.device?.devPeaNo},
        {label: "คำอธิบาย", value: d?.device?.devDescription},
        {label: "หมายเลขผลิตภัณฑ์", value: d?.device?.devSerialNo},
        {label: "ผู้ครอบครอง", value: d?.device?.tbEmployee?.empName},
        {label: "เบอร์ติดต่อ", value: d?.sendPhoneNum},
        {label: "สังกัด", value: d?.device?.tbCostCenter?.ccShortName},
        {label: "วันที่แจ้งซ่อม", value: d?.admitDate},
        {label: "ดำเนินการล่าสุด", value: d?.lastModifyDate},
        {label: "อาการเสีย", value: d?.defectDetail},
        {label: "คนส่งเครื่อง", value: d?.empSend},
        {label: "เจ้าหน้าที่รับเครื่อง", value: d?.adminReceive},
        {label: "สถานะ", value: d?.repairStatus?.statusName}
      ];
    }
  },
  mounted() {
    this.loading = true;
    axios.get(url + "/repair/getRepairAct")
        .then((response) => {
          this.repairLists = response.data;
        }).finally(() => {
          this.loading = false;
        }
    );

  },
  methods: {
    getRepairByStepStatus(item) {
      this.loading = true;
      let params = {
        "status": item
      }
      axios.get(url + "/repair/getRepairByStatus", {params})
          .then((response) => {
            this.repairLists = response.data;
          }).finally(() => {
            this.loading = false;
          }
      );
    },
    searchDeviceByPeaNo() {
      this.loading = true;
      this.repairLists = [];
      this.pickOneDeviceItem = [];
      let params = {
        "textSearch": this.textSearch
      }
      axios.get(url + "/repair/getByRepairCodeOrPeaNO", {params})
          .then((response) => {
            this.repairLists = response.data;
          }).finally(() => {
            this.loading = false;
          }
      );
    },
    getStatusColor(item) {
      const Status = item.repairStatus.statusName
      const today = new Date();
      const date = new Date(item.admitDate);
      const diffTime = Math.abs(today - date);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (Status == "กำลังดำเนินการ") {
        return diffDays <= 5 ? 'green lighten-3' : 'red'
      } else if (Status == "พร้อมส่งมอบ") return 'orange lighten-3'
      else return 'green'
    }
    ,
    readItem(item) {
      this.readingDialog = true;
      // this.editedIndex = this.repairCodes.indexOf(item)
      this.readingItemSelected = Object.assign({}, item)
      console.log("readingItemSelected : ", this.readingItemSelected)
      // console.log("readingDialog : ", this.readingDialog)
      // v-if="isProcessing"
      this.isProcessing
    }
    ,
    Save() {
      this.readingDialog = false;
      const payload = {
        "textSearch": this.readingItemSelected.repairCode,
        "repairStatus": "2",
        "adminDefectReview": this.adminDefectReview,
        "fixMethod": this.fixMethod,
        "costOfRepair": this.costOfRepair,

      }
      console.log("payload : ", payload)
      axios.put(url + "/repair/updateRepair", payload)
          .then((response) => {
            console.log("response : ", response.data)
            console.log("done : ")
          }).finally(() => {
            this.loading = false;
          }
      );
      this.adminDefectReview = ''
      this.$refs.form.reset()
    },
    reset() {
      this.$refs.form.reset()
      this.pickOneDeviceItem = []
      this.resultSearchDeviceItem = [];
      this.textSearch = ''
    }
    ,
    cancel() {
      this.readingDialog = false;
      this.adminDefectReview = ''
    }
  }
}
</script>
<style scoped>
.readonly-textarea v-textarea {
  font-size: 10px;
}

.readonly-textarea .v-label {
  font-size: 15px;
}

</style>