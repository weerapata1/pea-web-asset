import axios from "axios"

export default {
    async getEvents() {
      let res = await axios.get("http://localhost:8081/api/dev/getAllDevice");
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8081/data/" + eventId);
      return res.data;
    },

    async getSearch(jdata){
      console.log("555 "+JSON.stringify(jdata[0])+JSON.stringify(jdata[1]));
      let res = await axios.get("http://localhost:8081/api/dev/getAllByPattern", {
        params: {
          page: 1,
          size: 10,
          test1: [JSON.stringify(jdata[1]),JSON.stringify(jdata[1]), JSON.stringify(jdata[1]),JSON.stringify(jdata[0])]
        }
      }).catch(function (error) {
        console.log(error.response.status);
      });
   
      return res.data;

    }
  }