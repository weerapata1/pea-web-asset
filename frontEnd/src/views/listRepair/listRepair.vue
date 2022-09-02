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
                    <v-btn elevation="2" color="#E1BEE7" block @click="returnState()">คืนเครื่องแล้ว</v-btn>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="6" md="6">
                    <v-combobox v-model="selectCCvalue" :items="CcItems" item-text="ccFullName" dense label="การไฟฟ้า">
                    </v-combobox>
                </v-col>
                <v-col cols="3" md="3">
                    <v-btn elevation="2" block color="primary" @click="lookFor(selectCCvalue)">ค้นหา</v-btn>
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

        <v-data-table :headers="DataTableHeaders" :items="dataTableItems" :items-per-page="10" :sort-by="['sendDate']"
            :sort-desc="[true]">

            <template v-slot:item="row">
                <tr>
                    <td>{{ row.item.device.devPeaNo }}</td>
                    <td>{{ row.item.device.tbCostCenterTest.ccShortName }}</td>
                    <td>{{ row.item.device.devDescription }}</td>
                    <td>{{ row.item.device.devSerialNo }}</td>
                    <td>{{ formatDate(row.item.sendDate) }}</td>
                    <td>{{ row.item.empSend.empId }}</td>

                    <td>
                        <v-spacer></v-spacer>
                        <div v-if="row.item.repairStatus.id == 1">
                            <v-btn block color="#fdcd26" @click="dialog1 = true ; openDialog(row.item)">
                                รับเครื่อง
                            </v-btn>
                        </div>

                        <div v-else-if="row.item.repairStatus.id == 2">
                            <v-btn block color="#6a97ff" @click="dialog2 = true ; openDialog(row.item)">
                                กำลังดำเนินการ
                            </v-btn>
                        </div>

                        <div v-else-if="row.item.repairStatus.id == 3">

                            <v-btn block color="#6eff78" @click="dialog3 = true ; openDialog(row.item)">
                                เสร็จแล้ว
                            </v-btn>

                        </div>

                        <div v-else-if="row.item.repairStatus.id == 4">
                            <v-btn disabled block color="#E1BEE7">
                                คืนเครื่องแล้ว
                            </v-btn>
                        </div>

                    </td>

                    <td>
                        <v-btn @click="dialogInfo = true ; openDialogInfo(row.item)">
                            <v-icon color="teal darken-2" small>
                                mdi-message-text
                            </v-icon>
                        </v-btn>
                    </td>
                </tr>
            </template>
        </v-data-table>

        <v-dialog v-model="dialog1" persistent width="700">
            <v-card>
                <v-toolbar color="primary" dark>
                    <span class="text-h5">รับเครื่องและอุปกรณ์</span>
                </v-toolbar>
                <v-form ref="form" v-model="valid" lazy-validation>
                    <v-card-text>
                        <v-container>

                            <v-row>
                                <v-col cols="12" sm="6">
                                    <v-select v-model="dialog1Value.adminName" :items="adminNames" item-text="adName"
                                        :rules="adNameRules" item-value="adName" label="ผู้รับเรื่อง *" required>
                                    </v-select>
                                </v-col>

                                <v-col cols="12" sm="6">
                                    <v-select v-model="dialog1Value.caues" :items="caues" item-text="name"
                                        :rules="causeRules" item-value="value" label="อาการเสียเบื้องต้น *" required>
                                    </v-select>
                                </v-col>
                            </v-row>


                        </v-container>
                        <small>*indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue-darken-1" text @click="dialog1 = false ; reset()">
                            Close
                        </v-btn>
                        <v-btn color="success" text @click="saveDialog1(dialog1Value)">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-form>
            </v-card>
        </v-dialog>

        <v-dialog v-model="dialog2" persistent width="700">
            <v-card>
                <v-toolbar color="primary" dark>
                    <span class="text-h5">วิธีแก้ไข</span>
                </v-toolbar>
                <v-form ref="form" v-model="valid" lazy-validation>
                    <v-card-text>
                        <v-container>
                            <v-row>

                                <v-col cols="12" sm="12">
                                    <v-textarea fluid v-model="dialog2Value.treatment" solo name="treatment"
                                        label="ดำเนินการโดยวิธี เช่น เปลี่ยนแรม *"></v-textarea>
                                </v-col>
                            </v-row>
                        </v-container>
                        <small>*indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue-darken-1" text @click="dialog2 = false ; reset()">
                            Close
                        </v-btn>
                        <v-btn color="success" text @click="dialog2 = false ; openDialog2(dialog2Value)">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-form>

            </v-card>
        </v-dialog>

        <v-dialog v-model="dialog3" persistent width="700">
            <v-card>
                <v-toolbar color="primary" dark>
                    <span class="text-h5">ผู้รับเครื่องคืน</span>
                </v-toolbar>
                <v-form ref="form" v-model="valid" lazy-validation>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-text-field label="รหัสพนักงานผู้รับเครื่อง" v-model="dialog3Value.returnEmp"
                                    :rules="returnEnpRules" hide-details="auto">
                                </v-text-field>
                            </v-row>
                        </v-container>
                        <small>*indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue-darken-1" text @click="dialog3 = false ; reset()">
                            Close
                        </v-btn>
                        <v-btn color="success" text @click="dialog3 = false ; openDialog3(dialog3Value)">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-form>

            </v-card>
        </v-dialog>
        <v-dialog v-model="dialogInfo" width="75%">
            <v-card>
                <v-toolbar color="primary" dark>
                    <span class="text-h5">รายละเอียด</span>
                    <v-spacer></v-spacer>
                    <span class="text-h5">สถานะ : {{ dialogInfoValue.stage }}</span>
                </v-toolbar>

                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" sm="3">
                                เลขทรัพย์สิน : {{ dialogInfoValue.peaNo }}
                            </v-col>
                            <v-col cols="12" sm="3">
                                การไฟฟ้า : {{ dialogInfoValue.location }}
                            </v-col>
                            <v-col cols="12" sm="2">
                                ศูนย์ต้นทุน : {{ dialogInfoValue.ccFull }}
                            </v-col>
                            <v-col cols="12" sm="2">
                                ผู้ครอบครอง : <b>{{ dialogInfoValue.empOwnerName }}</b>
                            </v-col>
                            <v-col cols="12" sm="2">
                                รหัสพนักงาน : {{ dialogInfoValue.empOwnerId }}
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" sm="3">
                                ผู้ส่งเครื่อง : <b>{{ dialogInfoValue.empSendName }}</b>
                            </v-col>
                            <v-col cols="12" sm="2">
                                รหัสพนักงาน : {{ dialogInfoValue.empSendId }}
                            </v-col>
                            <v-col cols="12" sm="3">
                                วันที่ส่ง : {{ dialogInfoValue.admitDate }}
                            </v-col>
                            <v-col cols="12" sm="4">
                                อาการเสียเบื้องต้น : <b>{{ dialogInfoValue.damage }}</b>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" sm="3">
                                เจ้าหน้ารับเครื่อง : <b>{{ dialogInfoValue.adminName }}</b>
                            </v-col>
                            <v-col cols="12" sm="2">
                                รหัสพนักงาน : {{ dialogInfoValue.adminID }}
                            </v-col>
                            <v-col cols="12" sm="3">
                                วันที่ซ่อมเสร็จ : {{ dialogInfoValue.treatComplete }}
                            </v-col>
                            <v-col cols="12" sm="4">
                                วิธีแก้ไข : <b>{{ dialogInfoValue.treatment }}</b>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" sm="3">
                                ผู้รับเครื่องคืน : <b>{{ dialogInfoValue.returnEmp }}</b>
                            </v-col>
                            <v-col cols="12" sm="3">
                                วันที่รับเครื่องคืน : <b>{{ dialogInfoValue.returnDate }}</b>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue-darken-1" text @click="dialogInfo = false">
                        Close
                    </v-btn>

                </v-card-actions>

            </v-card>
        </v-dialog>
    </v-card>

</template>
<script src="./listRepair.js"></script>
<style src="./listRepair.css">
</style>