
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import 'core-js'

// LightBootstrap plugin
import LightBootstrap from './pea-web-asset'

// router setup
import routes from './routes/routes'

import './registerServiceWorker'
//import './assets/sass/pea-sty.scss'

// plugin setup
import Vuetify from 'vuetify';

Vue.use(VueRouter)
Vue.use(Vuetify)
Vue.use(LightBootstrap)

// configure router
const router = new VueRouter({
    routes, // short for routes: routes
    linkActiveClass: 'nav-item active',
    scrollBehavior: (to) => {
        if (to.hash) {
            return { selector: to.hash }
        } else {
            return { x: 0, y: 0 }
        }
    }
})


/* eslint-disable no-new */
new Vue({
    el: '#app',
    render: h => h(App),
    router,
    vuetify : new Vuetify(),
})
