<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app>
        <!-- sidebar -->
        <div class="wrapper">
            <side-bar>
                <sidebar-link to="/about">
                  <i class="nc-icon nc-zoom-split"></i>
                  <p>ระบบตรวจสอบทรัพย์สิน</p>
                </sidebar-link>
                <!-- <sidebar-link to="/">
                  <i class="nc-icon nc-notes"></i>
                  <p>ระบบโอนทรัพย์สิน</p>
                </sidebar-link>
                <sidebar-link to="">
                  <i class="nc-icon nc-square-pin"></i>
                  <p>ระบบติดตามทรัพย์สิน</p>
                </sidebar-link> -->
                <sidebar-link to="/repair">
                  <i class="nc-icon nc-settings-90"></i>
                  <p>ระบบแจ้งซ่อม</p>
                </sidebar-link>
                <sidebar-link to="/tracking" >
                  <i class="nc-icon nc-settings-90"></i>
                  <p>ติดตามการซ่อม</p>
                </sidebar-link>
                <sidebar-link to="/listRepair" >
                  <i class="nc-icon nc-settings-90"></i>
                  <p>รายการซ่อม</p>
                </sidebar-link>
            </side-bar>
        </div>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon> <!-- hideSidebar icon-->

      <v-toolbar-title>Assets Management System</v-toolbar-title> <!-- title -->
    </v-app-bar>

    <v-main>
      <router-view @click="toggleSidebar"/>
    </v-main>
  </v-app>
</template>

<script>
import sideBar from '../components/SidebarPlugin/SideBar.vue'

  export default {
    data: () => ({ drawer: null }),

    contents :{
        sideBar
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    },
  }
</script>