<template>

    <v-sheet color="white" elevation="6" height="210mm" width="197mm" class="repairForm-container">
        <div class="firstForm">
            <v-container>
                <v-row>
                    <v-col md="12">
                        <v-img src="@/assets/logo.png" max-width="120"></v-img>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col md="1">จาก</v-col>
                    <v-col md="5">{{ station }}</v-col>
                    <v-col md="1">ถึง</v-col>
                    <v-col md="5">{{ operDep }}</v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col md="1">เลขที่</v-col>
                    <v-col md="5">{{ station }}</v-col>
                    <v-col md="1">วันที่</v-col>
                    <v-col md="5">{{ currentDate() }}</v-col>
                </v-row>

                <v-row no-gutters>
                    <v-col col="1" md="1">เรื่อง</v-col>
                    <v-col md="11">{{ defTemp.subject }}</v-col>
                </v-row>
                <v-row>
                    <hr>
                </v-row>
                <v-row>
                    <v-col col="1" md="1">เรียน</v-col>
                    <v-col md="11">หผ.ปค.</v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col md="1"></v-col>
                    <v-col md="11">{{ defTemp.substance }}</v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col md="1">ประเภท</v-col>
                    <v-col md="1">{{ devi }}</v-col>
                    <v-col md="1">ยี่ห้อ</v-col>
                    <v-col md="2"></v-col>
                    <v-col md="1">รุ่น</v-col>
                    <v-col md="2"></v-col>
                    <v-col md="2">สัญญาเลขที่</v-col>
                    <v-col md="2"></v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col md="2">การรับประกัน</v-col>
                    <v-col md="3">{{ varante }}</v-col>
                    <v-col md="2">อาการเสีย</v-col>
                    <v-col md="3">{{ damage }}</v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col md="2">สถานที่ติดตั้ง</v-col>
                    <v-col md="10"> {{ location }}</v-col>
                </v-row>
                <v-row>
                    <v-col md="1"></v-col>
                    <v-col md="10">
                        <v-card elevation="4">
                            555
                        </v-card>
                    </v-col>
                    <v-col md="1"></v-col>

                </v-row>

            </v-container>

        </div>

    </v-sheet>




</template>

<script src="./repairForm.js"></script>

<style src="./repairForm.css">
</style>