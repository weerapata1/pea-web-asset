<template>
  <div class="pa-4 ma-2" style="width: 100%">
    
    <div v-if="loading" class="d-flex align-center justify-center fill-height">
      <v-progress-circular indeterminate color="primary" size="96" width="8" />
    </div>

    <v-row v-else>
      <v-expansion-panels
        v-model="expandedRegions"
        class="outlined-panels"
        focusable
        multiple
      >
        <!-- Region -->
        <v-expansion-panel v-for="reg in regions" :key="reg.regionKey">
          <v-expansion-panel-header class="custom-header">
            <div class="w-100 header-grid">
              <div class="text-left">
                <!-- <v-chip large color="purple" outlined> -->
                <v-chip
                  large
                  color="white"
                  class="pl-4 pr-4 purple--text text--darken-2"
                  style="font-size: 18px"
                >
                  <v-icon medium class="mr-2" color="purple darken-2">
                    mdi-home-city
                  </v-icon>
                  <b>
                    {{ (reg.firstDept && reg.firstDept.ccShortName) || "—" }}
                  </b>
                  <span class="ml-2">
                    {{
                      (reg.firstDept && reg.firstDept.ccLongCode) ||
                      reg.regionKey
                    }}
                  </span>
                </v-chip>
              </div>
              <div class="text-left">
                <v-chip
                  large
                  color="white"
                  class="pl-4 pr-4 indigo--text text--darken-2"
                  style="font-size: 18px"
                >
                  <v-icon medium class="mr-2" color="indigo darken-2">
                    mdi-laptop
                  </v-icon>
                  จำนวนทั้งหมด :
                  <span class="ml-1 mr-1">
                    <b>{{ reg.totalRecords }}</b></span
                  >
                  รายการ</v-chip
                >
              </div>
              <div class="text-left">
                <!-- <v-badge color="green" overlap> -->
                <v-chip
                  large
                  color="white"
                  class="pl-4 pr-4 green--text text--darken-3"
                  style="font-size: 18px"
                >
                  <v-icon medium class="mr-2" color="green darken-3">
                    mdi-desktop-classic </v-icon
                  >ยังไม่ทดแทน :
                  <span class="ml-1 mr-1"
                    ><b>{{ reg.newCount }} </b></span
                  ></v-chip
                >
                <!-- </v-badge> -->
              </div>
              <div class="text-left">
                <v-chip
                  large
                  color="white"
                  class="pl-4 pr-4 warning--text text--darken-2"
                  style="font-size: 18px"
                >
                  <v-icon medium class="mr-2" color="warning darken-2">
                    mdi-delete </v-icon
                  >ทดแทนแล้ว :
                  <span class="ml-1 mr-1"
                    ><b>{{ reg.oldCount }}</b></span
                  >
                  <span v-if="reg.unknownCount">
                    | ไม่ทราบ {{ reg.unknownCount }}</span
                  ></v-chip
                >
              </div>
            </div>
            <template v-slot:actions>
              <v-icon color="grey lighten-3">mdi-chevron-down</v-icon>
            </template>
          </v-expansion-panel-header>

          <v-expansion-panel-content>
            <v-expansion-panels
              v-model="expandedDivisions[reg.regionKey]"
              multiple
            >
              <v-expansion-panel
                v-for="div in reg.divisions"
                :key="div.divisionCode"
              >
                <v-expansion-panel-header>
                  <div class="w-100 header-grid">
                    <div class="text-left">
                      <b class="black--text">
                        {{
                          (div.firstDept && div.firstDept.ccShortName) || "—"
                        }}
                      </b>
                      {{ div.firstDept ? div.firstDept.ccLongCode : "" }}
                    </div>
                    <div class="text-left">
                      จำนวน
                      <b class="black--text">{{ div.totalRecords }}</b> เครื่อง
                    </div>
                    <div class="text-left">
                      ยังไม่ทดแทน <b class="black--text">{{ div.newCount }}</b>
                    </div>
                    <div class="text-left">
                      ทดแทนแล้ว <b class="black--text">{{ div.oldCount }}</b>
                      <span v-if="div.unknownCount">
                        | ไม่ทราบ {{ div.unknownCount }}</span
                      >
                    </div>
                  </div>
                </v-expansion-panel-header>

                <v-expansion-panel-content>
                  <!-- 2) Department (ccLongCode) level -->
                  <v-expansion-panels
                    v-model="expandedDepartments[div.divisionCode]"
                    multiple
                  >
                    <v-expansion-panel
                      v-for="dept in div.departments || []"
                      :key="dept.ccLongCode"
                    >
                      <v-expansion-panel-header>
                        <div class="w-100 header-grid">
                          <div class="text-left">
                            <b class="black--text">{{
                              dept.ccShortName || "—"
                            }}</b>
                            {{ dept.ccLongCode }}
                          </div>
                          <div class="text-left">
                            จำนวน
                            <b class="black--text">{{ dept.items.length }}</b>
                            เครื่อง
                          </div>
                          <div class="text-left">
                            ยังไม่ทดแทน
                            <b class="black--text">{{ dept.newCount }}</b>
                          </div>
                          <div class="text-left">
                            ทดแทนแล้ว
                            <b class="black--text">{{ dept.oldCount }}</b>
                            <span v-if="dept.unknownCount">
                              | ไม่ทราบ {{ dept.unknownCount }}</span
                            >
                          </div>
                        </div>
                      </v-expansion-panel-header>

                      <v-expansion-panel-content>
                        <!-- Keep it simple for now: a compact table of rows in this division -->
                        <v-simple-table dense>
                          <thead>
                            <tr>
                              <!-- <th class="text-left">Device ID</th> -->
                              <th class="text-center">รหัสทรัพย์สิน</th>
                              <th class="text-center">คำอธิบาย</th>
                              <th class="text-center">วันที่ได้รับ</th>
                              <th class="text-center">Tag</th>
                              <th class="text-center">ผู้ครอบครอง</th>
                              <th class="text-center">ตำแหน่ง</th>
                              <th class="text-center">สังกัด</th>
                              <th class="text-center">ศูนย์ต้นทุน</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr
                              v-for="row in dept.items"
                              :key="`${row.deviceId}-${dept.ccLongCode}`"
                            >
                              <!-- <td>{{ row.deviceId }}</td> -->
                              <td>{{ row.devPeaNo }}</td>
                              <td
                                class="text-truncate"
                                style="max-width: 420px"
                              >
                                {{ row.devDescription }}
                              </td>
                              <td>{{ row.devReceivedDate }}</td>
                              <td>
                                <v-chip
                                  x-small
                                  :color="
                                    row._tag === 'new'
                                      ? 'green'
                                      : row._tag === 'old'
                                      ? 'orange'
                                      : 'grey'
                                  "
                                  dark
                                >
                                  {{
                                    row._tag === "new"
                                      ? "ยังไม่ทดแทน"
                                      : row._tag === "old"
                                      ? "ทดแทนแล้ว"
                                      : "ไม่ทราบ"
                                  }}
                                </v-chip>
                              </td>
                              <td>{{ row.empName }}</td>
                              <td>{{ row.empRank }}</td>
                              <td>{{ row.ccShortName }}</td>
                              <td>{{ row.ccLongCode }}</td>
                            </tr>
                          </tbody>
                        </v-simple-table>
                      </v-expansion-panel-content>
                    </v-expansion-panel>
                  </v-expansion-panels>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-row>
  </div>
</template>

<script src="./deviceByDep.js"></script>
<style src="./deviceByDep.css"></style>
<link
  rel="stylesheet"
  href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,0,0"
/>
