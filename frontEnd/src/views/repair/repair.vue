<template>
  <!-- Step 1 ค้นหาเรื่องที่จะซ่อม -> Step 2 กรอกอาการที่เสียและรายละเอียด -> Step 3 ตรวจสอบความถูกต้อง -> Step 4 กดบันทึก -->
  <v-container fluid style="max-width: 98% ">
    <v-row no-gutters dense>
      <v-col col="12" sm="2" md="12">
        <v-stepper v-model="step">
          <v-stepper-header>
            <v-stepper-step :complete="step > 1" step="1">
              <v-icon left>mdi-magnify</v-icon>
              1 ค้นหาเครื่องที่จะซ่อม
            </v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step :complete="step > 2" step="2">
              <v-icon left>mdi-file-edit-outline</v-icon>
              2 กรอกอาการที่เสียและรายละเอียด
            </v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step step="3">
              <v-icon left>mdi-check-outline</v-icon>
              3 ตรวจสอบความถูกต้อง
            </v-stepper-step>
          </v-stepper-header>

          <v-stepper-items>
            <v-stepper-content step="1">
              <v-container fluid style="max-width:100%">
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
                            label="กรอกรหัสทรัพย์สินหรือหมายเลขผลิตภัณฑ์"
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
                          :items="resultSearchDeviceItem"
                          :single-select="singleSelect"
                          :loading="loading"
                          :items-per-page="6"
                          loading-text="กำลังดึงข้อมูล..."
                          item-key="devPeaNo"
                          show-select
                          class="elevation-1"
                      >
                      </v-data-table>
                    </v-col>
                  </v-card>
                </v-row>
              </v-container>
              <v-card-actions class="justify-center mt-0">
                <v-container>
                  <v-row no-gutters dense align="center" justify="space-around">
                    <v-col cols="12" sm="3" md="3">
                      <v-btn
                          :disabled="(pickOneDeviceItem.length === 0)"
                          color="green lighten-3"
                          block
                          large
                          @click="step = 2"
                      >
                        ต่อไป
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-actions>
            </v-stepper-content>

            <v-stepper-content step="2">
              <v-container fluid style="max-width:100%">
                <v-row no-gutters dense>
                  <v-card min-width="80%" max-width="100%" min-height="600px" max-height="400px" color="pink lighten-4"
                          v-scroll.self="onScroll" class="overflow-y-auto">
                    <v-card-text>
                      <v-card disabled>
                        <v-card-title>ข้อมูลเครื่อง</v-card-title>
                        <v-container fluid style="max-width:100%">

                          <v-row>
                            <v-col cols="12" sm="3" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devPeaNo || '' "
                                  label="รหัสทรัพย์สิน"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="6">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devDescription || '' "
                                  label="คำอธิบาย"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="3" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devSerialNo || '' "
                                  label="หมาบเลขผลิตภัณฑ์"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbEmployee.empId || '' "
                                  label="รหัสพนักงาน"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbEmployee.empName || '' "
                                  label="ผู้ครอบครอง"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbCostCenter.ccShortName || '' "
                                  label="สังกัด"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devReceivedDate || '' "
                                  label="วันที่รับเข้าเป็นทุน"
                              ></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card>
                      <v-divider></v-divider>
                      <v-card>
                        <v-card-title>
                          กรอกรายละเอียด
                        </v-card-title>
                        <v-card-text>
                          <v-form v-model="valid" ref="form">
                            <v-row>
                              <v-col cols="12" sm="6" md="12">
                                <v-text-field
                                    v-model="inputDetailForm.defectDetail"
                                    label="อาการเสีย"
                                    required
                                ></v-text-field>
                              </v-col>

                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                    v-model="inputDetailForm.empSend"
                                    label="ผู้ส่งเครื่อง"
                                    required
                                ></v-text-field>
                              </v-col>

                              <v-col cols="12" sm="3" md="4">
                                <v-text-field
                                    v-model="inputDetailForm.sendPhoneNum"
                                    label="เบอร์ติดต่อ"
                                    required
                                ></v-text-field>
                              </v-col>

<!--                              <v-col cols="12" sm="6" md="4">-->
<!--                                <v-select-->
<!--                                    v-model="inputDetailForm.adminReceive"-->
<!--                                    :items="adminReceiveItems"-->
<!--                                    item-text="adminRecName"-->
<!--                                    item-value="adminRecId"-->
<!--                                    label="ผู้รับเครื่อง"-->
<!--                                    data-vv-name="select"-->
<!--                                    single-line-->
<!--                                    required-->
<!--                                ></v-select>-->
<!--                              </v-col>-->
                            </v-row>
                          </v-form>
                        </v-card-text>
                      </v-card>
                    </v-card-text>
                  </v-card>
                </v-row>
              </v-container>
              <v-card-actions class="justify-center">
                <v-container>
                  <v-row no-gutters dense align="center"
                         justify="space-around">
                    <v-col cols="12" sm="3" md="3">
                      <v-btn
                          color="red lighten-3"
                          block
                          large
                          @click="step = 1"
                      >
                        กลับ
                      </v-btn>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-btn
                          :disabled="(inputDetailForm.sendPhoneNum?.length === 0 || inputDetailForm.empSend === 0 || inputDetailForm.defectDetail === 0 || inputDetailForm.adminReceive < 1)"
                          color="green lighten-2"
                          block
                          large
                          @click="step = 3 "
                      >
                        ต่อไป
                      </v-btn>
                    </v-col>

                  </v-row>
                </v-container>

              </v-card-actions>
            </v-stepper-content>

            <v-stepper-content step="3">
              <v-container fluid style="max-width:100%">
                <v-row no-gutters dense>
                  <v-card min-width="80%" max-width="100%" min-height="600px" max-height="400px" color="pink lighten-4"
                          v-scroll.self="onScroll" class="overflow-y-auto">
                    <v-card-text>
                      <v-card disabled>
                        <v-card-title>
                          ตรวจสอบรายละเอียด
                        </v-card-title>
                        <v-card-text>
                          <v-row>
                            <v-col cols="12" sm="3" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devPeaNo || '' "
                                  label="รหัสทรัพย์สิน"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="6">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devDescription || '' "
                                  label="คำอธิบาย"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="3" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devSerialNo || '' "
                                  label="หมายเลขผลิตภัณฑ์"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbEmployee.empId || '' "
                                  label="รหัสพนักงาน"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbEmployee.empName || '' "
                                  label="ผู้ครอบครอง"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.tbCostCenter.ccShortName || '' "
                                  label="สังกัด"
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6" md="3">
                              <v-text-field
                                  :value="pickOneDeviceItem[0]?.devReceivedDate || '' "
                                  label="วันที่รับเข้าเป็นทุน"
                              ></v-text-field>
                            </v-col>
                          </v-row>
                        </v-card-text>
                        <v-card-text>
                          <v-form v-model="valid" ref="form">
                            <v-divider></v-divider>
                            <v-row>
                              <v-col cols="12" sm="6" md="12">
                                <v-text-field
                                    v-model="inputDetailForm.defectDetail"
                                    label="อาการเสีย"
                                    required
                                ></v-text-field>
                              </v-col>

                              <v-col cols="12" sm="6" md="4">
                                <v-text-field
                                    v-model="inputDetailForm.empSend"
                                    label="ผู้ส่งเครื่อง"
                                    required
                                ></v-text-field>
                              </v-col>

                              <v-col cols="12" sm="3" md="4">
                                <v-text-field
                                    v-model="inputDetailForm.sendPhoneNum"
                                    label="เบอร์ติดต่อ"
                                    required
                                ></v-text-field>
                              </v-col>

                              <v-col cols="12" sm="6" md="4">
                                <v-select
                                    v-model="inputDetailForm.adminReceive"
                                    :items="adminReceiveItems"
                                    item-text="adminRecName"
                                    item-value="adminRecId"
                                    label="ผู้รับเครื่อง"
                                    data-vv-name="select"
                                    single-line
                                    required
                                ></v-select>
                              </v-col>
                            </v-row>
                          </v-form>
                        </v-card-text>
                      </v-card>
                    </v-card-text>
                  </v-card>
                </v-row>
              </v-container>
              <v-card-actions class="justify-center">
                <v-container vert>
                  <v-row>
                    <v-col cols="12" sm="12" md="12">

                    </v-col>
                  </v-row>
                  <v-row no-gutters dense align="center"
                         justify="space-around">
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                          v-model="checkRepair"
                          :label="`ตรวจสอบข้อมูลแล้ว`"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-btn
                          :disabled="!checkRepair"
                          color="green lighten-2"
                          large
                          block
                          @click="createRepair() "
                      >
                        บันทึก
                      </v-btn>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-btn
                          large
                          block
                          color="red"
                          @click="step = 1 "
                      >
                        แก้ไข
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-actions>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-col>
    </v-row>

  </v-container>

</template>

<script src="./repair.js"></script>

<style src="./repair.css"></style>
