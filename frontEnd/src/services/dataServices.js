import axios from "axios"

export default {
    async getEvents() {
      let res = await axios.get("http://localhost:8081/data");
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8081/data/" + eventId);
      return res.data;
    },

    async getSearch(jdata){
      console.log("555 "+JSON.stringify(jdata[0]));
      let res = await axios.get("http://localhost:8081/getSearch/", {
        params: {
          brach: JSON.stringify(jdata[0]),
          type: JSON.stringify(jdata[1])
        }
      }).catch(function (error) {
        console.log(error.response.status);
      });
   
      return res.data;

    }
  }