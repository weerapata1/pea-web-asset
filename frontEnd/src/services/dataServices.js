import axios from "axios"
// const qs = require('qs');

export default {
    async getEvents() {
      let res = await axios.get("http://localhost:8081/api/dev/getAllDevice",{headers: {'Access-Control-Allow-Origin': '*'}});
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8081/data/" + eventId,{headers: {'Access-Control-Allow-Origin': '*'}});
      return res.data;
    },

    async getSearch(jdata){
      var obj = JSON.parse(jdata);
      
      let objText = obj[1]['text'];
      console.log("dataService ", objText, ":", obj[0]['branch']);
      
      let res = await axios.get("http://localhost:8081/api/dev/getAllByPattern",{headers: {'Access-Control-Allow-Origin': '*'}} ,{
        params: {
          page: 1,
          size: 10,
          legion: 'E3010'
        }
        // paramsSerializer: function(params) {
        //   return qs.stringify(params)
        // }
      }).catch(function (error) {
        console.log(error.response.status);
      });
      return res.data;

    }
  }