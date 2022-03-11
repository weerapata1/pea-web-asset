<template>
  <div class="content">
    <div>
      <v-form>
        <v-row>
          <v-col cols="12" sm="6" md="3">
            <v-container fluid>
              <v-row>
                <!-- <v-col cols="12">
                  <v-combobox
                    v-model="select"
                    :items="items"
                    label="Combobox"
                    multiple
                    outlined
                    dense
                  ></v-combobox>
                </v-col> -->
                <v-select
                  v-model="selectedFruits"
                  :items="fruits"
                  label="การไฟฟ้าในสังกัด กฟฉ.2"
                  multiple
                >
                  <template v-slot:prepend-item>
                    <v-list-item ripple @mousedown.prevent @click="toggle">
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
                  </template>
                </v-select>
              </v-row>
            </v-container>
          </v-col>
          <v-col cols="12" sm="6" md="3">
            <v-container fluid>
              <v-row>
                <v-text-field
                  label="pea_no-serial-description-user-cost_center-price"
                ></v-text-field>
              </v-row>
            </v-container>
          </v-col>
          <v-col cols="12" sm="6" md="3">
            <v-container>
              <v-row>
                <!-- <v-btn elevation="3">Serach</v-btn> -->
                <v-select
                  v-model="selectedFruits"
                  :items="fruits"
                  label="การไฟฟ้าในสังกัด กฟฉ.2"
                  multiple
                >
                  <template v-slot:prepend-item>
                    <v-list-item ripple @mousedown.prevent @click="toggle">
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
                  </template>
                </v-select>
              </v-row>
            </v-container>
          </v-col>
          <v-col cols="12" sm="6" md="2">
            <v-container>
              <v-row>
                <v-btn elevation="3">Serach</v-btn>
              </v-row>
            </v-container>
          </v-col>
        </v-row>
      </v-form>
    </div>
    <v-data-table
      :headers="headers"
      :items="events"
      :items-per-page="20"
      class="elevation-1"
    ></v-data-table>
  </div>
</template>

<script src="./About.js">
  // export default {
  //   data () {
  //     return {
  //       headers: [
  //         {
  //           text: 'Dessert (100g serving)',
  //           align: 'start',
  //           sortable: false,
  //           value: 'name',
  //         },
  //         { text: 'Calories', value: 'calories' },
  //         { text: 'Fat (g)', value: 'fat' },
  //         { text: 'Carbs (g)', value: 'carbs' },
  //         { text: 'Protein (g)', value: 'protein' },
  //         { text: 'Iron (%)', value: 'iron' },
  //       ],
  //       desserts: [
  //         {
  //           name: 'Frozen Yogurt',
  //           calories: 159,
  //           fat: 6.0,
  //           carbs: 24,
  //           protein: 4.0,
  //           iron: '1%',
  //         },
//           {
//             name: 'Ice cream sandwich',
//             calories: 237,
//             fat: 9.0,
//             carbs: 37,
//             protein: 4.3,
//             iron: '1%',
//           },
//           {
//             name: 'Eclair',
//             calories: 262,
//             fat: 16.0,
//             carbs: 23,
//             protein: 6.0,
//             iron: '7%',
//           },
//           {
//             name: 'Cupcake',
//             calories: 305,
//             fat: 3.7,
//             carbs: 67,
//             protein: 4.3,
//             iron: '8%',
//           },
//           {
//             name: 'Gingerbread',
//             calories: 356,
//             fat: 16.0,
//             carbs: 49,
//             protein: 3.9,
//             iron: '16%',
//           },
//           {
//             name: 'Jelly bean',
//             calories: 375,
//             fat: 0.0,
//             carbs: 94,
//             protein: 0.0,
//             iron: '0%',
//           },
//           {
//             name: 'Lollipop',
//             calories: 392,
//             fat: 0.2,
//             carbs: 98,
//             protein: 0,
//             iron: '2%',
//           },
//           {
//             name: 'Honeycomb',
//             calories: 408,
//             fat: 3.2,
//             carbs: 87,
//             protein: 6.5,
//             iron: '45%',
//           },
//           {
//             name: 'Donut',
//             calories: 452,
//             fat: 25.0,
//             carbs: 51,
//             protein: 4.9,
//             iron: '22%',
//           },
//           {
//             name: 'KitKat',
//             calories: 518,
//             fat: 26.0,
//             carbs: 65,
//             protein: 7,
//             iron: '6%',
//           },
  //       ],
  //     }
  //   },
  // }
</script>
