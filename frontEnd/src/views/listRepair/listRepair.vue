<template>
    <v-card class="pa-2">
        <v-container>
            <v-row>
                <v-col col="4" md="4">
                    <v-btn elevation="2" color="#fdcd26" block @click="reseveInState()">รับเครื่อง</v-btn>
                </v-col>
                <v-col col="4" md="4">
                    <v-btn elevation="2" color="#6a97ff" block @click="inProgressState()">กำลังดำเนินการ</v-btn>
                </v-col>
                <v-col col="4" md="4">
                    <v-btn elevation="2" color="#6eff78" block @click="doneState()">เสร็จแล้ว</v-btn>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="6" md="6">
                    <v-combobox v-model="value" :items="items" item-text="ccFullName" dense label="การไฟฟ้า">
                    </v-combobox>
                </v-col>
                <v-col cols="3" md="3">
                    <v-btn elevation="2" block @click="lookFor(value)">ค้นหา</v-btn>
                </v-col>
                <v-col cols="3" md="3">
                    <v-card-text v-if="this.state == null">สถานะ : รายการทั้งหมด</v-card-text>
                    <v-card-text v-else-if="this.state == 1">สถานะ : รับเครื่อง</v-card-text>
                    <v-card-text v-else-if="this.state == 2">สถานะ : กำลังดำเนินการ</v-card-text>
                    <v-card-text v-else>สถานะ : เสร็จแล้ว</v-card-text>
                </v-col>
            </v-row>
        </v-container>

        <v-data-table :headers="headers" :items="data1" :items-per-page="10" class="elevation-2">
            <template v-slot:item.recivedIn="{ item }">
                <span>{{ new Date(item.recivedIn).toLocaleString() }}</span>
            </template>
            <template v-slot:item="row">
                <tr>
                    <td>{{ row.item.device.devPeaNo }}</td>
                    <td>{{ row.item.device.tbCostCenterTest.ccShortName }}</td>
                    <td>{{ row.item.device.devDescription }}</td>
                    <td>{{ row.item.device.devSerialNo }}</td>
                    <td>{{ row.item.recivedIn }}</td>
                    <td>
                        <v-btn v-if="row.item.tbRepairStatus.id == 1" block color="#6a97ff" @click="recived(row.item)">
                            รับเครื่อง
                        </v-btn>
                        <v-btn v-else-if="row.item.tbRepairStatus.id == 2" block color="#6eff78" @click="inProgress(row.item)">
                            ดำเนินการแล้ว
                        </v-btn>
                        <v-btn v-else-if="row.item.tbRepairStatus.id == 3" disabled block color="#6eff78" @click="done(row.item)">
                            เสร็จแล้ว
                        </v-btn>
                    </td>
                </tr>
            </template>
        </v-data-table>
    </v-card>

</template>
<script src="./listRepair.js"></script>
<style src="./listRepair.css">
</style>