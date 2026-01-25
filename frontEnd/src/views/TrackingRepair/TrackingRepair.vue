<template>
  <!-- Step 1 ค้นหาเรื่องที่จะซ่อม -> Step 2 กรอกอาการที่เสียและรายละเอียด -> Step 3 ตรวจสอบความถูกต้อง -> Step 4 กดบันทึก -->
  <v-container fluid style="max-width: 98% ">
    <v-row no-gutters dense>
      <v-card>
        <v-tabs
            color="deep-purple accent-4"
            right
        >
          <v-tab>ตรวจสอบสถานะ</v-tab>
          <v-tab @click="adminLoginCheck()">อัพเดทสถานะ</v-tab>
          <v-tab @click="adminLoginCheck()">ประวัติการซ่อม</v-tab>
          <!--          item 1-->
          <v-tab-item>
            <TrackingStepFristComponent/>
          </v-tab-item>
          <!--          item 2 -->
          <v-tab-item v-if="!adminLogin">
            <v-card flat>
              <v-card-text>
                <p>
                  2- non admin
                </p>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item v-else>
            <TrackingStepSecondComponent/>
          </v-tab-item>
          <!--          item 3-->
          <v-tab-item v-if="!adminLogin">
            <v-card flat>
              <v-card-text>
                <p>
                  4- non admin
                </p>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item v-else>
            <v-card flat>
              <v-card-text>
                <p>
                  5- admin
                </p>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs>
        <v-card-actions v-if="adminLogin">
          <v-spacer></v-spacer>
          <v-btn @click="adminLogOutX()" :disabled="!adminLogin">
            logout
          </v-btn>
        </v-card-actions>

      </v-card>
    </v-row>
    <v-dialog
        v-model="dialog"
        max-width="290"
    >
      <v-card>
        <v-card-title class="text-h5">
          สำหรับเจ้าหน้าที่
        </v-card-title>

        <v-card-text>
          กรุณา Login
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
              color="green darken-1"
              text
              @click="dialog = false ;adminLoginX()"
          >
            Login
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>


  </v-container>

</template>

<script src="./TrackingRepair.js">
</script>

<style src="./TrackingRepair.css"></style>
