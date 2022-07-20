<template>
    <v-card class="pa-2">
        <v-container>
            <v-row>
                <v-col col="2" md="2">
                    <v-btn elevation="2" color="#c7833d" block @click="allStste()">รายการทั้งหมด</v-btn>
                </v-col>
                <v-col col="2" md="2">
                    <v-btn elevation="2" color="#fdcd26" block @click="reseveInState()">รับเครื่อง</v-btn>
                </v-col>
                <v-col col="2" md="2">
                    <v-btn elevation="2" color="#6a97ff" block @click="inProgressState()">กำลังดำเนินการ</v-btn>
                </v-col>
                <v-col col="2" md="2">
                    <v-btn elevation="2" color="#6eff78" block @click="doneState()">เสร็จแล้ว</v-btn>
                </v-col>
                <v-col col="2" md="2">
                    <v-btn elevation="2" color="#d1d1d1" block @click="returnState()">คืนเครื่องแล้ว</v-btn>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="6" md="6">
                    <v-combobox v-model="value" :items="items" item-text="ccFullName" dense label="การไฟฟ้า">
                    </v-combobox>
                </v-col>
                <v-col cols="3" md="3">
                    <v-btn elevation="2" block color="primary" @click="lookFor(value)">ค้นหา</v-btn>
                </v-col>
                <v-col cols="3" md="3">
                    <v-card-text v-if="this.state == null || this.state == 0">สถานะ : รายการทั้งหมด</v-card-text>
                    <v-card-text v-else-if="this.state == 1">สถานะ : รับเครื่อง</v-card-text>
                    <v-card-text v-else-if="this.state == 2">สถานะ : กำลังดำเนินการ</v-card-text>
                    <v-card-text v-else-if="this.state == 3">สถานะ : เสร็จแล้ว</v-card-text>
                    <v-card-text v-else-if="this.state == 4">สถานะ : คืนเครื่องแล้ว</v-card-text>
                </v-col>
            </v-row>
        </v-container>

        <v-data-table :headers="headers" :items="data1" :items-per-page="10" class="elevation-2">

            <template v-slot:item="row">
                <tr>
                    <td>{{ row.item.device.devPeaNo }}</td>
                    <td>{{ row.item.device.tbCostCenterTest.ccShortName }}</td>
                    <td>{{ row.item.device.devDescription }}</td>
                    <td>{{ row.item.device.devSerialNo }}</td>
                    <td>{{ row.item.sendDate }}</td>
                    <td>{{ row.item.empSend.empId }}</td>

                    <td>
                        <div v-if="row.item.repairStatus.id == 1">
                            <v-dialog v-model="diaRec" persistent width="700">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn block color="#fdcd26" v-bind="attrs" v-on="on" @click="recived(row.item)">
                                        รับเครื่อง
                                    </v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                        <span class="text-h5">User Profile</span>
                                    </v-card-title>
                                    <v-card-text>
                                        <v-container>
                                            <v-row>
                                                <!-- <v-col cols="12" sm="6">
                                                    <v-select v-model="adNameSelect"  item-text="adName" label="ผู้รับเรื่อง"
                                                        required><option>:value="adName"</option></v-select>
                                                </v-col> -->
                                                
                                                <v-col cols="12" sm="6">
                                                    <v-select
                                                        v-model="examineSelect" :items="examines" item-text="name"
                                                        label="อาการเสียเบื้องต้น" required></v-select>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                        <small>*indicates required field</small>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="blue-darken-1" text @click="diaRec = false">
                                            Close
                                        </v-btn>
                                        <v-btn color="blue-darken-1" text @click="RecSave(adNameSelect,examineSelect)">
                                            Save
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </div>


                        <div v-else-if="row.item.repairStatus.id == 2">
                            <v-dialog width="500">

                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn block color="#6a97ff" v-bind="attrs" v-on="on" @click="inProgress(row.item)">
                                        กำลังดำเนินการ
                                    </v-btn>
                                </template>


                                <v-card>
                                    <v-card-title>
                                        วิธีแก้ไข
                                    </v-card-title>

                                    <v-card-text>
                                        5555+
                                    </v-card-text>
                                </v-card>
                            </v-dialog>
                        </div>

                        <div v-else-if="row.item.repairStatus.id == 3">
                            <v-dialog width="500">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn block color="#6eff78" v-bind="attrs" v-on="on" @click="done(row.item)">
                                        เสร็จแล้ว
                                    </v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                        ผู้รับเครื่องคืน
                                    </v-card-title>

                                    <v-card-text>
                                        5555+
                                    </v-card-text>
                                </v-card>
                            </v-dialog>
                        </div>

                        <div v-else-if="row.item.repairStatus.id == 4">
                            <v-btn disabled block color="#6eff78">
                                คืนเครื่องแล้ว
                            </v-btn>
                        </div>

                    </td>
                </tr>
            </template>
        </v-data-table>
    </v-card>

</template>
<script src="./listRepair.js"></script>
<style src="./listRepair.css">
</style>