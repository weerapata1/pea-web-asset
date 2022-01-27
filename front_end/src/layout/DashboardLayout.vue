<template>
  <div class="">
    <div class="wrapper">
      <side-bar>
        <mobile-menu slot="content"></mobile-menu>
          <!----------- add sidebar menu  --------->
        <sidebar-link to="/admin/assetChk">
          <i class="nc-icon nc-zoom-split"></i>
          <p>ระบบตรวจสอบทรัพย์สิน</p>
        </sidebar-link>
        <sidebar-link to="/admin/conv">
          <i class="nc-icon nc-notes"></i>
          <p>ระบบโอนทรัพย์สิน</p>
        </sidebar-link>
        <sidebar-link to="/admin/assetTracking">
          <i class="nc-icon nc-square-pin"></i>
          <p>ระบบติดตามทรัพย์สิน</p>
        </sidebar-link>
        <sidebar-link to="/admin/repair">
          <i class="nc-icon nc-settings-90"></i>
          <p>ระบบแจ้งซ่อม</p>
        </sidebar-link>

          <!----------------------->
        <sidebar-link to="/admin/user">
          <i class="nc-icon nc-notes"></i>
          <p>user profile</p>
        </sidebar-link>

        <template slot="bottom-links">
          <sidebar-link class="active"
                        to="/admin/upgrade">
            <i class="nc-icon nc-alien-33"></i>
            <p>Upgrade to PRO</p>
          </sidebar-link>
        </template>
      </side-bar>
      <div class="main-panel">
        <top-navbar></top-navbar>

        <dashboard-content @click="toggleSidebar">

        </dashboard-content>

        <content-footer></content-footer>
      </div>
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
  import TopNavbar from './TopNavbar.vue'
  import ContentFooter from './ContentFooter.vue'
  import DashboardContent from './Content.vue'
  import MobileMenu from './MobileMenu.vue'
  export default {
    components: {
      TopNavbar,
      ContentFooter,
      DashboardContent,
      MobileMenu
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>
