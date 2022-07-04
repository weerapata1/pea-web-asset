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
        <v-autocomplete v-model="model" :items="states" color="white" item-text="ccFullName"
          label="การไฟฟ้า">
        </v-autocomplete>
        <v-text-field color="white" label="เลขทรัพย์สิน"></v-text-field>
        <v-text-field color="white" label="เลขที่สัญญา"></v-text-field>
        <v-text-field color="white" label="อาการเสีย"></v-text-field>
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" @click="save" to="/repairForm">
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