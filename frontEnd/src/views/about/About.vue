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
          <v-col cols="12" sm="6" md="3">
            <v-container fluid>
              <v-row>
                <!-- item-value="fruits.name" -->
                <v-select
                  v-model="selectedFruits"
                  :items=fruits
                  item-value="value"
                  item-text="name"
                  label="การไฟฟ้าในสังกัด กฟฉ.2"
                  @change="toggleBranch2"

                >
                <!-- multiple -->
                  <template v-slot:prepend-item>
                    <v-list-item ripple @mousedown.prevent @click="toggleBranch">
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

                  <!-- <template v-slot:append-item>
                    <v-divider class="mb-2"></v-divider>
                    <v-list-item disabled>
                      <v-list-item-avatar color="grey lighten-3">
                        <v-icon> mdi-food-apple </v-icon>
                      </v-list-item-avatar>
                      <v-list-item-content v-if="likesAllFruit">
                        <v-list-item-title>
                          Holy smokes, someone call the fruit police!
                        </v-list-item-title>
                      </v-list-item-content>
                      <v-list-item-content v-else-if="likesSomeFruit">
                        <v-list-item-title> Fruit Count </v-list-item-title>
                        <v-list-item-subtitle>
                          {{ selectedFruits.length }}
                        </v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-content v-else>
                        <v-list-item-title>
                          How could you not like fruit?
                        </v-list-item-title>
                        <v-list-item-subtitle>
                          Go ahead, make a selection above!
                        </v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </template> -->

                </v-select>
              </v-row>
            </v-container>
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-container fluid>
              <v-row>
                <v-text-field
                  v-model="textSearch"
                  label="ค้นหา"
                ></v-text-field>
              </v-row>
            </v-container>
          </v-col>

          <!-- <v-col cols="12" sm="6" md="3">
            <v-container>
              <v-row>

                <v-select
                  v-model="selectedTypeSearch"
                  :items="typeSearch"
                  label="ประเภทข้อมูลที่ค้นหา"
                  multiple
                  item-value="value"
                  item-text="name"
                  @change="toggleType2"
                >
                  <template v-slot:prepend-item>
                    <v-list-item ripple @mousedown.prevent @click="toggleType">
                      <v-list-item-action>
                        <v-icon
                          :color="
                            selectedTypeSearch.length > 0
                              ? 'indigo darken-4'
                              : ''
                          "
                        >
                          {{ icon2 }}
                        </v-icon>
                      </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-title> Select All </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-divider class="mt-2"></v-divider>
                  </template>

                  <template v-slot:append-item>
                    <v-divider class="mb-2"></v-divider>
                    <v-list-item disabled>
                      <v-list-item-avatar color="grey lighten-3">
                        <v-icon> mdi-food-apple </v-icon>
                      </v-list-item-avatar>
                      <v-list-item-content v-if="likesAllFruit">
                        <v-list-item-title>
                          Holy smokes, someone call the fruit police!
                        </v-list-item-title>
                      </v-list-item-content>
                      <v-list-item-content v-else-if="likesSomeFruit">
                        <v-list-item-title> Fruit Count </v-list-item-title>
                        <v-list-item-subtitle>
                          {{ selectedTypeSearch.length }}
                        </v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-content v-else>
                        <v-list-item-title>
                          How could you not like fruit?
                        </v-list-item-title>
                        <v-list-item-subtitle>
                          Go ahead, make a selection above!
                        </v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-select>
              </v-row>
            </v-container>
          </v-col> -->

          <v-col cols="12" sm="6" md="2">
            <v-container>
              <v-row>
                <v-btn elevation="3" @click="searchFunction" id="searchButton">Serach</v-btn>
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
      :server-items-length="totalItems"
      multi-sort
      class="elevation-1"
    ></v-data-table>
  </div>
</template>

<script src="./About.js"></script>