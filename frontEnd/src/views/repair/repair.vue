<template >
  <div>
    <v-card class="outside-card" color="purple  lighten-5">
      <v-toolbar flat color="purple" dark>
        <v-icon>mdi-database-search</v-icon>
        <v-toolbar-title class="font-weight-light">
          แจ้งซ่อม
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <v-card-text>
        <v-form>
          <v-row>
            <v-col col="12" md="12">
              <v-autocomplete v-model="ccNameSeclected" :items="itemCC" item-text="ccFullName" item-value="ccLongCode"
                color="black" label="การไฟฟ้า" @change="$v.ccNameSeclected.$touch() ; toggleBranch2(ccNameSeclected)"
                :error-messages="ccNameSeclectedErrors">
              </v-autocomplete>
            </v-col>
          </v-row>
          <v-row>
            <v-col col="12" md="6">
              <v-autocomplete v-model="devPeaNoSelceted" :items="itemDevice" item-text="devPeaNo" color="black"
                label="เลขทรัพย์สิน" @change="$v.devPeaNoSelceted.$touch() ; findDiscDevice(devPeaNoSelceted)"
                :error-messages="devPeaNoSelcetedErrors">
              </v-autocomplete>
            </v-col>
            <v-col col="12" md="6">
              <v-text-field v-model="devDesc.devDescription" color="black" label="รายละเอียด" disabled></v-text-field>
            </v-col>
          </v-row>
          <v-text-field v-model="damage" color="black" label="อาการเสียเบื้องต้น" :counter="100"
            :error-messages="damageErrors">
          </v-text-field>
          <v-row>
            <v-col col="12" md="3">
              <v-text-field v-model="empSend" color="black" label="รหัสพนักงานผู้ส่ง"
                @keyup.enter="$v.empSend.$touch() ; findEmp(empSend) " :error-messages="empSendErrors">
              </v-text-field>
            </v-col>
            <v-col col="12" md="3">
              <v-text-field v-model="empData.empName" color="black" label="ชื่อพนักงานผู้ส่ง" disabled>
              </v-text-field>
            </v-col>
            <v-col col="12" md="3">
              <v-text-field  v-model="empPhoneNumb" :error-messages="empPhoneNumbErrors" color="black" label="เบอร์ติดต่อกลับ"></v-text-field>
            </v-col>
            <v-col col="12" md="3">
              <v-btn color="success" block @click="$v.empSend.$touch() ; findEmp(empSend)">
                ค้นหา
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <a @click="dialog = true" target="_blank">*หมายเหตุ : บันทึก ฉ.2 กรท.(ก) 97/2564 ลว.27 ม.ค. 2564</a>

        <v-spacer></v-spacer>
        <v-btn color="error" @click="clear()">
          ล้างค่า
        </v-btn>
        <v-btn color="success" @click="continues()">
          ต่อไป
        </v-btn>
      </v-card-actions>

    </v-card>
    <div class="text-center">
      <v-dialog v-model="dialog" width="700">
        <v-card>
          <v-card-text>
            <img src="@/assets/resume.jpg" width="650" />
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="dialog = false">
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <!-- <VueHtml2pdf :show-layout="false" :float-layout="true" :enable-download="true" :preview-modal="true"
      :paginate-elements-by-height="1400" filename="myPDF" :pdf-quality="2" :manual-pagination="false" pdf-format="a4"
      pdf-orientation="portrait" pdf-content-width="800px" ref="html2Pdf" :html-to-pdf-options="{
        filename: `คำร้องแจ้งซ่อม ` +devPeaNoSelceted +` .pdf`,
        margin: [0, 0, 0, 0],
        image: { type: 'jpeg', quality: 0.98 ,size : 20},
        html2canvas: { dpi: 192, letterRendering: true },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
      }">
      <section slot="pdf-content">
        <div class="firstForm">
          <v-container>
            <v-row>
              <v-col md="12">
                <img width=150 src="@/assets/logo.png" />
              </v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="1">จาก</v-col>
              <v-col md="5">{{ fBody.empSendRole }}</v-col>
              <v-col md="1">ถึง</v-col>
              <v-col md="5">
                <div v-if="first5char == 1">
                  {{ StaticHeader.to1 }}
                </div>
                <div v-else-if="first5char == 2">
                  {{ StaticHeader.to2 }}</div>
              </v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="1">เลขที่</v-col>
              <v-col md="5">{{ StaticHeader.No }}</v-col>
              <v-col md="1">วันที่</v-col>
              <v-col md="5">{{ currentDate() }}</v-col>
            </v-row>

            <v-row no-gutters>
              <v-col col="1" md="1">เรื่อง</v-col>
              <v-col md="11">{{ StaticBoby.subject }}</v-col>
            </v-row>
            <v-row>
              <hr>
            </v-row>
            <v-row no-gutters>
              <v-col col="1" md="1">เรียน</v-col>
              <v-col md="11">
                <div v-if="first5char == 1">
                  {{ StaticBoby.to1 }}
                </div>
                <div v-else-if="first5char == 2">
                  {{ StaticBoby.to2 }}</div>
              </v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="1"></v-col>
              <v-col md="11">{{ StaticBoby.substance }}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="2">เลขทรัพย์สิน</v-col>
              <v-col md="3">{{fBody.peaNo}}</v-col>
              <v-col md="2">รายละเอียด</v-col>
              <v-col md="5">{{fBody.discription}}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="1">ประเภท</v-col>
              <v-col md="3">{{ fBody.deviceType }}</v-col>
              <v-col md="1">ยี่ห้อ</v-col>
              <v-col md="1"></v-col>
              <v-col md="1">รุ่น</v-col>
              <v-col md="2"></v-col>
              <v-col md="2">สัญญาเลขที่</v-col>
              <v-col md="1"></v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="2">การรับประกัน</v-col>
              <v-col md="3"><v-icon>mdi-border-all-variant</v-icon> อยู่ในการรับประกัน</v-col>
              <v-col md="5"><v-icon>mdi-border-all-variant</v-icon> ไม่อยู่ในการรับประกัน</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="2">อาการเสีย</v-col>
              <v-col md="10">{{ fBody.damage }}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="2">สถานที่ติดตั้ง</v-col>
              <v-col md="10"> {{ fBody.location }}</v-col>
            </v-row>
            <br>
            <v-row no-gutters>
              <v-col md="1"></v-col>
              <v-col md="10">
                <table>
                  <td>
                    {{ StaticBoby.note1 }}
                  </td>
                </table>
              </v-col>
              <v-col md="1"></v-col>

            </v-row>
            <v-row>
              <v-col md="1"></v-col>
              <v-col md="10">
                {{ StaticBoby.note2 }}
              </v-col>
            </v-row>
            <v-row>
              <v-col md="7"></v-col>
              <v-col md="5">&nbsp;&nbsp;&nbsp;ชื่อ {{ fBody.empSendName }}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="6"></v-col>
              <v-col md="3">ตำแหน่ง {{ fBody.empSendRole }}</v-col>
              <v-col md="3">รหัสพนักงาน {{ fBody.empSendId}}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col md="6"></v-col>
              <v-col md="2">เบอร์ติดต่อกลับ</v-col>
              <v-col md="3">{{fBody.empPhoneNumb}}</v-col>
            </v-row>
            <table width="100%">
              <v-row no-gutters>
                <v-col col="12" md="1">เรียน</v-col>
                <v-col col="12" md="2">
                  <div v-if="first5char == 1">{{ StaticFoot.to1 }}</div>
                  <div v-else-if="first5char == 2">{{ StaticFoot.to2 }}</div>
                </v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="3">ดำเนินการจัดซ่อมโดยวิธี</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="4"><v-icon>mdi-border-all-variant</v-icon> จัดซื้ออุปกรณ์มาเปลี่ยน เนื่องจาก</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">{{ StaticFoot.dot1 }}</v-col>

              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="4"><v-icon>mdi-border-all-variant</v-icon> ส่งให้บริษัทดำเนินการ เนื่องจาก</v-col>
                <v-col col="12" md="4"></v-col>
                <v-col col="12" md="2">อนุมัติ</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">{{ StaticFoot.dot1 }}</v-col>
                <v-col col="12" md="2">&nbsp;</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="4"><v-icon>mdi-border-all-variant</v-icon> ไม่ดำเนินการจัดดซ่อม เนื่องจาก</v-col>

              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="4">{{ StaticFoot.dot1 }}</v-col>
                <v-col col="12" md="4"></v-col>
                <v-col col="12" md="2">{{ StaticFoot.dot2 }}</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="7">&nbsp;</v-col>
                <v-col col="12" md="1">ตำแหน่ง</v-col>
                <v-col col="12" md="4">{{ StaticFoot.dot3 }}</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">&nbsp;</v-col>
                <v-col col="12" md="2"></v-col>
                <v-col col="12" md="4">{{ StaticFoot.date1 }}</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="6">{{ StaticFoot.note1 }}</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">&nbsp;</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">&nbsp;</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="6">&nbsp;</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="5">{{ StaticFoot.dot2 }}</v-col>
              </v-row>
              <v-row no-gutters>

                <v-col col="12" md="1">ตำแหน่ง</v-col>
                <v-col col="12" md="5">{{ StaticFoot.dot3 }}</v-col>
              </v-row>
              <v-row no-gutters>
                <v-col col="12" md="1"></v-col>
                <v-col col="12" md="5">{{ StaticFoot.date1 }}</v-col>
              </v-row>
            </table>
          </v-container>

        </div>
      </section>
    </VueHtml2pdf> -->
  </div>


</template>

<script src="./repair.js"></script>

<style src="./repair.css">
</style>