<template>
    <div>
        <div ref='content'>
            <v-sheet color="white" elevation="6" height="252mm" width="238mm" class="repairForm-container">
                <div class="firstForm">
                    <v-container>
                        <v-row>
                            <v-col md="12">
                                <v-img src="@/assets/logo.png" max-width="120"></v-img>
                            </v-col>
                        </v-row>
                        <v-row no-gutters>
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
                        <v-row no-gutters>
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
                        <br>
                        <v-row no-gutters>
                            <v-col md="1"></v-col>
                            <v-col md="10">
                                <table>
                                    <td>
                                        ในกรณีที่ไม่อยู่ในสัญญาประกัน ขอให้กรท.ฉ.2
                                        ดำเนินการซ่อมและตัดงบค่าใช้จ่ายจากศูนย์ต้นทุนของ
                                        E301023000 รหัสบัญชี 53051060
                                    </td>
                                </table>
                            </v-col>
                            <v-col md="1"></v-col>

                        </v-row>
                        <v-row>
                            <v-col md="1"></v-col>
                            <v-col md="10">
                                จึงเรียนมาเพื่อโปรดแจ้งผู้ที่เกี่ยวข้องดำเนินการต่อไปด้วย
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col md="7"></v-col>
                            <v-col md="5">&nbsp;&nbsp;&nbsp;ชื่อ ........................................</v-col>
                        </v-row>
                        <v-row no-gutters>
                            <v-col md="6"></v-col>
                            <v-col md="3">ตำแหน่ง ........................</v-col>
                            <v-col md="3">รหัสพนักงาน .....................</v-col>
                        </v-row>
                        <v-row no-gutters>
                            <v-col md="6"></v-col>
                            <v-col md="2">เบอร์ติดต่อกลับ</v-col>
                            <v-col md="3">......................................</v-col>
                        </v-row>
                        <v-row no-gutters>
                            <table style='width:100%'>
                                <!-- A -->
                                <tr height=18 style='height:13.8pt'>
                                    <td style='height:13.8pt;width:250pt'>
                                        การตรวจสอบอุปกรณ์ก่อนส่งซ่อม (ผปค.กรท.ฉ.2)
                                    </td>
                                    <td class=xl66 width=72 style='width:54pt'>&nbsp;</td>
                                    <td class=xl67 width=78 style='width:59pt'>&nbsp;</td>
                                    <td width=70 style='width:53pt'></td>
                                    <td width=70 style='width:53pt'></td>
                                    <td width=70 style='width:53pt'></td>
                                </tr>
                                <tr>
                                    <td height=18 class=xl68 style='height:13.8pt'>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td align=left width=70 style='width:100pt'>
                                        ผู้รับเครื่อง/ตรวจสอบ
                                    </td>
                                    <td colspan=2 align=left>
                                        (......................................)</td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 style='height:13.8pt'>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td align=left>ตำแหน่ง</td>
                                    <td colspan=2 align=left>
                                        ...................................</td>
                                    <td colspan=3 style=''></td>
                                </tr>
                                <tr height=19 style='height:14.4pt'>
                                    <td height=19 class=xl73 style='height:14.4pt'>&nbsp;</td>
                                    <td class=xl71>&nbsp;</td>
                                    <td class=xl71>&nbsp;</td>
                                    <td class=xl71>&nbsp;</td>
                                    <td class=xl71 colspan=2 align=left>.........../.........../...........</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <!-- A -->
                                <tr height=19 style='height:14.4pt'>
                                    <td height=19 class=xl68 colspan=2 align=left style='height:14.4pt;'>
                                        เรียน &nbsp;อก.รท.ฉ.2</td>
                                    <td class=xl75>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 colspan=3 align=left style='height:13.8pt;'>
                                        ดำเนินการจัดซ่อมโดยวิธี
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 colspan=3 align=left style='height:13.8pt;'>
                                        &nbsp;&nbsp; &nbsp;&nbsp;จัดซื้ออุปกรณ์มาเปลี่ยน เนื่องจาก
                                        ...............................</td>
                                    <td></td>
                                    <td></td>

                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=22 style='mso-height-source:userset;height:16.8pt'>
                                    <td height=22 class=xl68 colspan=3 align=left style='height:16.8pt;'>
                                        ................................................................................................
                                    </td>
                                    <td></td>
                                    <td>อนุมัต</td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 colspan=3 align=left style='height:13.8pt;'>
                                        &nbsp;&nbsp; &nbsp;&nbsp;ส่งให้บริษัทดำเนินการ เนื่องจาก
                                        ...............................</td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=22 style='mso-height-source:userset;height:16.8pt'>
                                    <td height=22 class=xl68 colspan=3 align=left style='height:16.8pt;'>
                                        ................................................................................................
                                    </td>
                                    <td></td>
                                    <td align=left></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td>
                                        &nbsp;&nbsp; &nbsp;&nbsp; ไม่ดำเนินการจัดดซ่อม เนื่องจาก
                                        ...........................</td>
                                    <td colspan=2></td>
                                    <td colspan=3>(.................................)</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 colspan=3 align=left style='height:13.8pt;'>
                                        ................................................................................................
                                    </td>
                                    <td colspan=3 align=left>...................................</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 colspan=2 align=left style='height:13.8pt;'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;เพื่อ โปรดพิจารณา
                                    </td>
                                    <td class=xl70></td>
                                    <td colspan=3 align=left><span style='mso-spacerun:yes'>
                                        </span>.........../.........../...........</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=18 style='height:13.8pt'>
                                    <td height=18 class=xl68 style='height:13.8pt'>&nbsp;</td>
                                    <td></td>
                                    <td class=xl70>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=20 style='mso-height-source:userset;height:15.0pt'>
                                    <td height=20 class=xl68 style='height:15.0pt' colspan=2 align=left>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(.................................)</td>
                                    <td>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=22 style='mso-height-source:userset;height:16.2pt'>
                                    <td height=22 class=xl68 style='height:16.2pt'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...................................
                                    </td>
                                    <td colspan=2 align=left>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td class=xl69>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height=19 style='mso-height-source:userset;height:14.4pt'>
                                    <td height=19 class=xl73 style='height:14.4pt'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.........../.........../...........
                                    </td>
                                    <td class=xl71 colspan=2 align=left>
                                    </td>
                                    <td class=xl71>&nbsp;</td>
                                    <td class=xl71>&nbsp;</td>
                                    <td class=xl74>&nbsp;</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>


                            </table>
                        </v-row>


                    </v-container>

                </div>

            </v-sheet>

        </div>
        <v-btn class="btn-save" @click="download2">บันทึก</v-btn>
    </div>


</template>

<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
<script src="./repairForm.js"></script>
<style src="./repairForm.css">
</style>