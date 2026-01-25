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
                color="red lighten-3"
                @click="reset"
            >
              ล้างค่า
            </v-btn>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <v-btn
                large
                block
                color="primary"
                @click="searchDeviceByPeaNo"
            >
              ค้นหา
            </v-btn>
          </v-col>
        </v-card-actions>

        <v-col cols="12" sm="12" md="12">
          <v-data-table
              v-model="pickOneDeviceItem"
              :headers="headers"
              :items="repairCodes"
              :loading="loading"
              :items-per-page="6"
              loading-text="กำลังดึงข้อมูล..."
              item-key="devPeaNo"
              class="elevation-1"
          >
            <template v-slot:[`item.repairStatus.statusName`]="{ item }">
              <v-chip
                  :color="getStatusColor(item)"
              >
                {{ item.repairStatus.statusName }}
              </v-chip>
            </template>
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

    <v-dialog
        v-model="readingDialog"
        max-width="60%"
        persistent
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">สถานะการซ่อม : </span>
        </v-card-title>

        <v-card-text>
          <v-container fluid>
            <v-row>
              <v-col
                  v-for="i in fields.length"
                  :key="i.label"
                  cols="12"
                  md="4"
              >
                <v-textarea
                    :label="fields[i-1].label"
                    :value="fields[i-1].value || 'No data'"
                    rows="2"
                    row-height="20"
                    readonly
                    auto-grow
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="cancel"
          >
            Cancel
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from "axios";

let url = "http://localhost:8080";
// let urlRepair = "http://localhost:8080/repair";
// let url = `${process.env.VUE_APP_BASE_URL}`;

export default {
  name: "TrackingStepFristComponent",
  data() {
    return {
      textSearch: '',
      devPeaNoRule: [],
      pickOneDeviceItem: [],
      readingItemSelected: {},
      readingDialog: false,
      loading: false,
      dialog: false,

      // headers of table search
      headers: [
        {text: 'รหัสทรัพย์สิน', align: 'start', sortable: false, value: 'device.devPeaNo'},
        {text: 'คำอธิบาย', value: 'device.devDescription'},
        {text: 'หมายเลขผลิตภัณฑ์', value: 'device.devSerialNo'},
        {text: 'ผู้ครอบครอง', value: 'device.tbEmployee.empName'},
        {text: 'สังกัด', value: 'device.tbCostCenter.ccShortName'},
                {text: 'หมายเลขซ่อม', value: 'repairCode'},
        {text: 'วันที่แเจ้งซ่อม', value: 'admitDate'},
        {text: 'สถานะ', value: 'repairStatus.statusName'},
        {text: 'Actions', value: 'actions', sortable: false},

      ],
      // fields: [],
      // data of table search
      repairCodes: [],
      resultCreateRepairDeviceItem: [],

    }
  },
  computed: {
    fields() {
      const d = this.readingItemSelected;
      return [
        { label: "รหัสทรัพย์สิน", value: d?.device?.devPeaNo },
        { label: "คำอธิบาย", value: d?.device?.devDescription },
        { label: "หมายเลขผลิตภัณฑ์", value: d?.device?.devSerialNo },
        { label: "ผู้ครอบครอง", value: d?.device?.tbEmployee?.empName },
        { label: "เบอร์ติดต่อ", value: d?.sendPhoneNum },
        { label: "สังกัด", value: d?.device?.tbCostCenter?.ccShortName },
        { label: "วันที่แจ้งซ่อม", value: d?.admitDate },
        { label: "ดำเนินการล่าสุด", value: d?.lastModifyDate },
        { label: "อาการเสีย", value: d?.defectDetail },
        { label: "คนส่งเครื่อง", value: d?.empSend },
        { label: "เจ้าหน้าที่รับเครื่อง", value: d?.adminReceive },
        { label: "สถานะ", value: d?.repairStatus?.statusName },

      ];
    }
  },
  mounted() {  },

  methods: {
    searchDeviceByPeaNo() {
      this.loading = true;
      this.repairCodes = [];
      this.pickOneDeviceItem = [];
      let params = {
        "textSearch": this.textSearch
      }
      axios.get(url + "/repair/getByRepairCodeOrPeaNO", {params})
          .then((response) => {
            this.repairCodes = response.data;
          }).finally(() => {
            this.loading = false;
          }
      );
    },
    getStatusColor (item) {
      const Status = item.repairStatus.statusName
      const today = new Date();
      const date = new Date(item.admitDate);
      const diffTime = Math.abs(today - date);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (Status == "กำลังดำเนินการ"){
        return  diffDays <= 5 ? 'green lighten-3' : 'red'
      }
      else if (Status == "พร้อมส่งมอบ") return 'orange lighten-3'
      else return 'green'
    },
    readItem(item) {
      this.readingDialog = true;
      // this.editedIndex = this.repairCodes.indexOf(item)
      this.readingItemSelected = Object.assign({}, item)
    },
    reset() {
      this.$refs.form.reset()
      this.pickOneDeviceItem = []
      this.resultSearchDeviceItem = [];
      this.textSearch = ''
    },
    cancel(){
      this.readingDialog = false;
    }
  }
};
</script>
<style></style>