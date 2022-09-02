<template >
  <v-card class="outside-card" color="purple  lighten-5">
    <v-toolbar flat color="purple" dark>
      <v-icon>mdi-database-search</v-icon>
      <v-toolbar-title class="font-weight-light">
        แจ้งซ่อม
      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>

    <v-card-text>
      <v-form v-model="valid">
        <v-row>
          <v-col col="12" md="12">
            <v-autocomplete v-model="ccNameSeclected" :items="itemCC" item-text="ccFullName" item-value="ccLongCode"
              color="black" :rules="ccNameRules" label="การไฟฟ้า" @change="toggleBranch2">
            </v-autocomplete>
            <v-autocomplete v-model="devPeaNO" :items="itemDevice" item-text="devPeaNo" color="black"
              :rules="devPeaNoRules" label="เลขทรัพย์สิน"></v-autocomplete>
            <v-text-field v-model="damage" color="black" :rules="damageRules" label="อาการเสียเบื้องต้น"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col col="12" md="6">
            <v-text-field v-model="empData.empName" color="black" :rules="empsendRules" label="ชื่อ" disabled></v-text-field>
          </v-col>
          <v-col col="12" md="3">
            <v-text-field v-model="empSend" color="black" :rules="empsendRules" label="รหัสพนักงานผู้ส่ง">
            </v-text-field>

          </v-col>
          <v-col col="12" md="3">
            <v-btn color="success" block @click="findEmp(empSend)">
        ค้นหา
      </v-btn>
          </v-col>
        </v-row>



      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" @click="save()">
        ส่ง
      </v-btn>
    </v-card-actions>
    <v-snackbar v-model="hasSaved" :timeout="2000" absolute bottom left>
      Your profile has been updated
    </v-snackbar>
  </v-card>

</template>

<script src="./repair.js"></script>

<style src="./repair.css">
</style>