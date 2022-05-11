import axios from "axios"

export default {
    async getEvents() {
<<<<<<< Updated upstream
      let res = await axios.get("http://localhost:8081/data");
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8081/data/" + eventId);
      return res.data;
    }
=======
      let res = await axios.get("http://localhost:8080/api/dev/getAllDevice",{headers: {'Access-Control-Allow-Origin': '*'}});
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8080/data/" + eventId,{headers: {'Access-Control-Allow-Origin': '*'}});
      return res.data;
    },

//    async getSearch(jdata){
//      var obj = JSON.parse(jdata);
//
//      let objText = obj[1]['text'];
//      console.log("dataService ", objText, ":", obj[0]['branch']);
//
//      let res = await axios.get('http://localhost:8080/api/dev/getAllByPattern',
//      {
//        params: {
//          page: 1,
//          size: 10,
//          legion: 'E3010'
//        }
//        // paramsSerializer: function(params) {
//        //   return qs.stringify(params)
//        // }
//
//      }).catch(function (error) {
//        console.log(error.response.status);
//      });
//      return res.data;
//
//    }
>>>>>>> Stashed changes
  }