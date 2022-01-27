import DashboardLayout from '../layout/DashboardLayout.vue'
// GeneralViews
import NotFound from '../pages/NotFoundPage.vue'

// Admin pages
import Overview from 'src/pages/Overview.vue'
import UserProfile from 'src/pages/UserProfile.vue'
import TableList from 'src/pages/TableList.vue'
import Typography from 'src/pages/Typography.vue'
import Icons from 'src/pages/Icons.vue'
import Maps from 'src/pages/Maps.vue'
import Notifications from 'src/pages/Notifications.vue'
import Upgrade from 'src/pages/Upgrade.vue'
import DataTable from 'src/pages/DataTable/index.vue'
import Conveyancing from 'src/pages/Conveyancing/index.vue'
import AssetTracking from 'src/pages/AssetTracking/index.vue'
import Repairing from 'src/pages/Repairing/index.vue'

const routes = [
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/admin/assetChk'
  },

  {
    path: '/admin',
    component: DashboardLayout,
    redirect: '/admin/assetChk',
    children: [
      {
        path: 'overview',
        name: 'Overview',
        component: Overview
      },
      {
        path: 'conv',
        name: 'Conveyancing',
        component: Conveyancing
      },
      {
        path: 'assetChk',
        name: 'DataTable',
        component: DataTable
      },
      {
        path: 'assetTracking',
        name: 'AssetTracking',
        component: AssetTracking
      },
      {
        path: 'repair',
        name: 'Repairing',
        component: Repairing
      },
      {
        path: 'user',
        name: 'User',
        component: UserProfile
      },
      {
        path: 'table-list',
        name: 'Table List',
        component: TableList
      },
      {
        path: 'typography',
        name: 'Typography',
        component: Typography
      },
      {
        path: 'icons',
        name: 'Icons',
        component: Icons
      },
      {
        path: 'maps',
        name: 'Maps',
        component: Maps
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications
      },

    ]
  },
  { path: '*', component: NotFound }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
