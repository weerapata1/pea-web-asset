<template>
    <v-card class="pa-2">

        <v-container>
            <v-row>
                <v-col cols="6" md="6">
                    <v-combobox v-model="value" :items="items" item-text="ccFullName" dense label="การไฟฟ้า">
                    </v-combobox>
                </v-col>
                <v-col cols="6" md="6">
                    <v-btn elevation="2" @click="find(value)">ค้นหา</v-btn>
                </v-col>
            </v-row>
        </v-container>

        <v-data-table :headers="headers" :items="data1" :items-per-page="10" class="elevation-2">
            <template v-slot:item.recivedIn="{item}">
                <span>{{new Date(item.recivedIn).toLocaleString() }}</span>
            </template>
        </v-data-table>
    </v-card>

</template>
<script src="./TrackingRepair.js"></script>
<style src="./TrackingRepair.css">
</style>