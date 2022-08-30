<template >
  <v-card class="outside-card" color="purple lighten-1" dark>
    <v-toolbar flat color="purple">
      <v-icon>mdi-database-search</v-icon>
      <v-toolbar-title class="font-weight-light">
        ค้นหา
      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>

    <v-card-text>
      <v-form v-model="valid">
        <v-autocomplete v-model="ccNameSeclected" :items="itemCC" item-text="ccFullName" item-value="ccLongCode" color="white"
        label="การไฟฟ้า" @change="toggleBranch2">
        </v-autocomplete>
        <!-- <v-autocomplete v-model="devPeaNO" :items="itemDevice" item-text="devPeaNo" color="white" label="เลขทรัพย์สิน"></v-autocomplete> -->
        <!-- <v-text-field v-model="peaNO" color="white" label="เลขที่สัญญา"></v-text-field> -->
        <v-text-field v-model="damage" color="white" label="อาการเสีย"></v-text-field>
        <v-text-field v-model="empsend" color="white" label="ผู้ส่ง"></v-text-field>
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" @click="save()" >
        ค้นหา
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