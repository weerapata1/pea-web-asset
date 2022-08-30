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
          <template v-if="showVRow">
          <v-row>
            <v-col cols="12" sm="4" md="2">
              <H5>พนักงานจำนวน {{totalEmployeeResult}} คน</H5>
            </v-col>
            <v-col cols="12" sm="4" md="3">
              <H5>ทรัพย์สินคอมพิวเตอร์จำนวน {{totalDeviceResult}} เครื่อง</H5>
            </v-col>
            <v-col cols="12" sm="6" md="4">
              <H5>{{checkQuotaResult}}</H5>
            </v-col>
          </v-row>
        </template>
        </v-form>
      </v-card-text>
    </div>
    <div>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="12" md="4"
            ><H4>พนักงานในสังกัด</H4
            ><v-data-table
              class="elevation-1"
              :headers="employeeheaders"
              :items="getEmployeeResult"
            >
            </v-data-table>
          </v-col>
          <v-col cols="12" sm="12" md="8"
            ><H4>รายการทรัพย์สิน</H4>
            <v-data-table
              class="elevation-1"
              :headers="deviceheaders"
              :items="getDeviceResult"
              sort-by="devReceivedDate"
              :sort-desc="true"
            >
            </v-data-table
          ></v-col>
        </v-row>
      </v-card-text>
    </div>
  </div>
</template>

<script src="./checkQuota.js"></script>
<style src="./checkQuota.css"></style>
