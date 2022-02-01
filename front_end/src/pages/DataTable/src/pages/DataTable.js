import Card from 'src/components/Cards/Card.vue'
import LTable from 'src/components/Table.vue'
import  Loading  from 'element-ui';

const tableData = [
              {
                date: '2016-05-03',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles'
              }, {
                date: '2016-05-02',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles'
              }, {
                date: '2016-05-04',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles'
              }, {
                date: '2016-05-01',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles'
              }
  ]

export default {
  name: 'data-table',
  components: {
    LTable,
    Card
  },
  props: [],
   data() {
      return {
        tableData: [...tableData],
        loading: true,
        currentPage1: 5,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4
      }
   },
  computed: {

  },
  mounted () {
//console.log("555");
  },
  methods: {
      handleSizeChange(val) {
        console.log(`${val} items per page`);
      },
      handleCurrentChange(val) {
        console.log(`current page: ${val}`);
      }
  }
}


