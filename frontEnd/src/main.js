import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

import AMS from './pea_ams.js'

Vue.config.productionTip = false
Vue.use(AMS)


new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
