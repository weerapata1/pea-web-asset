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
          กรุณาเลือกขอบเขตการไฟฟ้าที่ต้องการค้นหา
        </v-alert>
      </div>
      <div>
        <v-alert
          :value="alert2"
          color="red"
          dark
          border="top"
          icon="mdi-home"
          transition="slide-y-transition"
        >
          กรุณาเลือกข้อมูลก่อนสร้าง PDF
        </v-alert>
      </div>
      <v-form>
        <v-row>
          <v-col cols="12" sm="4" md="2">
            <v-container fluid>
              <v-row>
                <!-- item-value="fruits.name" -->
                <v-select
                  v-model="selectedFruits"
                  :items="fruits"
                  item-value="value"
                  item-text="name"
                  label="การไฟฟ้าในสังกัด กฟฉ.2"
                  @change="toggleBranch2"
                >
                  <!-- multiple -->
                  <template v-slot:prepend-item>
                    <v-list-item
                      ripple
                      @mousedown.prevent
                      @click="toggleBranch"
                    >
                      <v-list-item-action>
                        <v-icon
                          :color="
                            selectedFruits.length > 0 ? 'indigo darken-4' : ''
                          "
                        >
                          {{ icon }}
                        </v-icon>
                      </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-title> Select All </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-divider class="mt-2"></v-divider>
                  </template>
                </v-select>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-container fluid>
              <v-row>
                <v-text-field v-model="textSearch" label="ค้นหา"></v-text-field>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="4" md="2">
            <v-container fluid>
              <v-row>
                <!-- item-value="fruits.name" -->
                <v-select
                  v-model="selectedAssetType"
                  :items="assetType"
                  item-value="value"
                  item-text="name"
                  label="ประเภททรัพย์สิน"
                  @change="toggleAssetType"
                >
                </v-select>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="3" md="1">
            <v-container>
              <v-row>
                <v-btn elevation="3" @click="searchFunction" id="searchButton"
                  >Serach</v-btn
                >
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="3" md="2">
            <v-container>
              <v-row>
                <v-btn>
                  <download-excel
                    :fetch="fetchData2"
                    :data="dataExcel"
                    :fields="excelHeaders"
                    worksheet="My Worksheet"
                    name="filename.xls"
                  >
                    Export Excel
                  </download-excel>
                </v-btn>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="3" md="1">
            <v-container>
              <v-row>
                <v-btn elevation="3" @click="generateReport" id="searchButton"
                  >QR_Code</v-btn
                >
              </v-row>
            </v-container>
          </v-col>
        </v-row>
      </v-form>
    </div>
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
              <b-table>
                
                  <tr
                    id="pdf_tr"
                    v-for="item in Math.ceil(qrcode_value2.length / 2)"
                    v-bind:key="item.devPeaNo"
                  >
                    <td
                      style="text-align: center"
                      class="pdf_td"
                      v-for="item2 in qrcode_value2.slice(
                        (item - 1) * 2,
                        item * 2
                      )"
                      v-bind:key="item2.devPeaNo"
                    >
                      <qrcode-vue
                        :value="item2"
                        :size="qrcode_size"
                        level="H"
                      ></qrcode-vue>

                      <H5>{{ JSON.parse(item2).devPeaNo }}</H5>
                      <!-- {{ item2 }} -->
                    </td>
                  </tr>
                  </b-table
              >
            </section>
          </VueHtml2pdf>
        </div>
      </template>
    </div>
    <!-- :footer-props="footerProps"
      @update:items-per-page="getItemPerPage" -->
    <v-data-table
      :headers="headers"
      :items="data1"
      :footer-props="footerProps"
      
      :items-per-page="itemsPerPage"
      multi-sort
      :loading="myloadingvariable"
      loading-text="Loading... Please wait"
      class="elevation-1"
      v-model="selected"
      show-select
      @input="enterSelect()"
    >
    <!-- :server-items-length="totalItems" -->
      <!-- :footer-props="{ 'items-per-page-options': [30, 50, 100] }" -->
      <template v-slot:top>
        <v-toolbar flat>
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <qrcode-vue
                      :value="qrcode_value"
                      :size="qrcode_size"
                      level="H"
                    ></qrcode-vue>
                    <H1> {{ formDevPeaNo }} </H1>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5"
                >เชื่อมต่อไปยังหน้าแจ้งซ่อม</v-card-title
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete"
                  >Cancel</v-btn
                >
                <v-btn color="blue darken-1" text @click="deleteItemConfirm"
                  >OK</v-btn
                >
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>

      <template v-slot:[`item.actions`]="{ item }">
        <v-icon medium class="mr-2" @click="editItem(item)">
          mdi-qrcode-scan
        </v-icon>
        <v-icon medium @click="deleteItem(item)">
          mdi-folder-wrench-outline
        </v-icon>
      </template>
    </v-data-table>
  </div>
</template>

<script src="./About.js"></script>
<style src="./about.css"></style>
