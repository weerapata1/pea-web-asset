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
                  <!-- multiple -->
                  <!-- <template v-slot:prepend-item>
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
                    </v-list-item>
                   
                  </template> -->
                </v-select>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="4" md="2">
            <v-container>
              <v-row>
                <v-btn elevation="3" @click="searchFunction" id="searchButton"
                  >Serach</v-btn
                >
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="4" md="2">
            <v-container>
              <v-row>
                <v-btn>
                  <download-excel
                    :data="data1"
                    :fields="headers.text"
                    worksheet="My Worksheet"
                    name="filename.xls"
                  >
                    Export Excel
                  </download-excel>
                </v-btn>
              </v-row>
            </v-container>
          </v-col>
        </v-row>
      </v-form>
    </div>

    <v-data-table
      :headers="headers"
      :items="data1"
      :items-per-page="itemsPerPage"
      :footer-props="{ 'items-per-page-options': [30, 50, 100] }"
      :server-items-length="totalItems"
      multi-sort
      :loading="myloadingvariable"
      loading-text="Loading... Please wait"
      class="elevation-1"
    ></v-data-table>
  </div>
</template>

<script src="./About.js"></script>
