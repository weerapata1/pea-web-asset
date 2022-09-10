<template>
  <div class="content">
    <div>
      <div>
        <v-alert
          :value="alert"
          color="red"
          dark
          border="top"
          icon="mdi-home"
          transition="slide-y-transition"
        >
          กรุณาเลือกหน่วยงานที่ต้องการตรวจสอบ
        </v-alert>
      </div>
      <v-card-text>
        <v-form v-model="valid">
          <v-row>
            <v-col cols="12" sm="8" md="8">
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
            <v-col cols="12" sm="2" md="1">
              <v-btn elevation="3" @click="checkQuota" id="searchButton"
                >ตรวจสอบ
              </v-btn>
            </v-col>
            <v-col cols="12" sm="2" md="1">
              <v-btn elevation="3" @click="genQuotaReport" id="genQuotaReportt"
                >สร้างรายงาน</v-btn
              >
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <div>
        <template>
          <div>
            <VueHtml2pdf
              :show-layout="false"
              :float-layout="true"
              :enable-download="true"
              :preview-modal="true"
              :paginate-elements-by-height="1400"
              filename="myPDF"
              :pdf-quality="2"
              :manual-pagination="false"
              pdf-format="a4"
              pdf-orientation="portrait"
              pdf-content-width="800px"
              ref="html2Pdf"
              :html-to-pdf-options="{
                margin: [5, 10, 5, 10],
              }"
            >
              <section slot="pdf-content">
                <!-- <table cellspacing="0" class="no-spacing"> -->
                <v-card-text>
                  <v-form>
                    <template v-if="showVRow">
                      <v-row>
                        <h5>{{ itemName }} - {{ checkQuotaResult }}</h5>
                      </v-row>
                      <v-row>
                        <H5
                          >พนักงานจำนวน {{ totalEmployeeResult }} คน -
                          ทรัพย์สินคอมพิวเตอร์จำนวน
                          {{ totalDeviceResult }} เครื่อง</H5
                        >
                      </v-row>
                    </template>
                  </v-form>

                  <v-row>
                    <v-col cols="12" sm="11" md="11">
                      <H5>พนักงานในสังกัด</H5
                      ><v-data-table
                        class="elevation-1"
                        :headers="employeeheaders"
                        :items="getEmployeeResult"
                        :hide-default-footer="true"
                        :items-per-page=-1
                      >
                      </v-data-table>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="11" md="11"
                      ><H5>รายการทรัพย์สิน</H5>
                      <v-data-table
                        class="elevation-1"
                        :headers="deviceheaders"
                        :items="getDeviceResult"
                        sort-by="devReceivedDate"
                        :sort-desc="true"
                        :hide-default-footer="true"
                        :items-per-page=-1
                      >
                      </v-data-table
                    ></v-col>
                  </v-row>
                </v-card-text>
              </section>
            </VueHtml2pdf>
          </div>
        </template>
      </div>

      <v-card-text>
        <v-form>
          <template v-if="showVRow">
            <v-row>
              <v-col cols="12" sm="4" md="2">
                <H5>พนักงานจำนวน {{ totalEmployeeResult }} คน</H5>
              </v-col>
              <v-col cols="12" sm="4" md="3">
                <H5
                  >ทรัพย์สินคอมพิวเตอร์จำนวน {{ totalDeviceResult }} เครื่อง</H5
                >
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <H5>{{ checkQuotaResult }}</H5>
              </v-col>
            </v-row>
          </template>
        </v-form>
      </v-card-text>

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
