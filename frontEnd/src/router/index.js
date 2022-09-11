import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import About from '../views/about/About.vue'
import Repair from '../views/repair/repair.vue'
import Tracking from '../views/TrackingRepair/TrackingRepair.vue'
import repairForm from '../views/repairForm/repairForm.vue'
import ListRepair from '../views/listRepair/listRepair.vue'
import checkQuota from '../views/checkQuota/checkQuota.vue'
import login from '../views/logIn/logIn.vue'

Vue.use(VueRouter)

function guardMyroute(to, from, next) {
  var isAuthenticated = false;
  //this is just an example. You will have to find a better or
  // centralised way to handle you localstorage data handling
  if (sessionStorage.getItem("LoggedUser") == "true") isAuthenticated = true;
  else isAuthenticated = false;
  if (isAuthenticated) {
    next(); // allow to enter route
  } else {
    next("/"); // go to '/login';
  }
}

const routes = [
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/repairForm',
    name: 'repairForm',
    component: repairForm
  },
  {
    path: '/repair',
    name: 'Repair',
    component: Repair
  },
  {
    path: '/listRepair',
    name: 'listRepair',
    component: ListRepair,
    beforeEnter: guardMyroute,
    meta: { requiresAuth: true },
  },
  {
    path: '/tracking',
    name: 'Tracking',
    component: Tracking
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: About
  },
  {
    path: '/checkQuota',
    name: 'checkQuota',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: checkQuota
  },
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
