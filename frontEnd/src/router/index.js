import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import About from '../views/about/About.vue'
import Repair from '../views/repair/repair.vue'
import TrackingRepair from "../views/TrackingRepair/TrackingRepair.vue";

import checkQuota from '../views/checkQuota/checkQuota.vue'
import Upload from '../views/upload/upload.vue'


Vue.use(VueRouter)

// function guardMyroute(to, from, next) {
//   var isAuthenticated = false;
//   //this is just an example. You will have to find a better or
//   // centralised way to handle you localstorage data handling
//   if (sessionStorage.getItem("LoggedUser") == "true") isAuthenticated = true;
//   else isAuthenticated = false;
//   if (isAuthenticated) {
//     next(); // allow to enter route
//   } else {
//     next("/login"); // go to '/login';
//   }
// }

const routes = [
  {
    path: '/',
    redirect: '/about'  // 👈 Add this redirect
  },
  // {
  //   path: '/login',
  //   name: 'login',
  //   component: login
  // },
  {
    path: '/trackingRepair',
    name: 'TrackingRepairComponent',
    component: TrackingRepair
  },
  {
    path: '/repair',
    name: 'Repair',
    component: Repair
  },
  // {
  //   path: '/listRepair',
  //   name: 'listRepair',
  //   component: ListRepair,
  //   beforeEnter: guardMyroute,
  //   meta: { requiresAuth: true },
  // },

  {
    path: '/about',
    name: 'About',
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
    {
    path: '/upload',
    name: 'upload',
    component: Upload
  },
]

const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router
