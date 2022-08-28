<template>
  <div class="content">
    <div>
      <v-card-text>
        <v-form v-model="valid">
          <v-row>
            <v-col cols="12" sm="10" md="10">
              <v-autocomplete
                v-model="model"
                :items="items"
                color="white"
                item-text="ccFullName"
                label="การไฟฟ้า"
                item-value="ccLongCode"
                return-object
              >
              </v-autocomplete>
            </v-col>
            <v-col cols="12" sm="2" md="2">
              <v-btn elevation="3" @click="checkQuota" id="searchButton"
                >ตรวจสอบ
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </div>
    <div>
      <v-row>
        <v-col cols="12" sm="12" md="6"
          ><H4>พนักงานในสังกัด</H4
          ><v-data-table
            class="elevation-1"
            :headers="employeeheaders"
            :items="getEmployeeResult"
          >
          </v-data-table>
        </v-col>
        <v-col cols="12" sm="12" md="6"
          ><H4>รายการทรัพย์สิน</H4>
          <v-data-table
            class="elevation-1"
            :headers="deviceheaders"
            :items="getDeviceResult"
          >
          </v-data-table
        ></v-col>
      </v-row>
    </div>
  </div>
</template>

<script src="./checkQuota.js"></script>
<style src="./checkQuota.css"></style>
