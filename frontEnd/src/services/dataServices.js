import axios from "axios"

export default {
    async getEvents() {
      let res = await axios.get("http://localhost:8081/data");
      return res.data;
    },
    async getEventSingle(eventId) {
      let res = await axios.get("http://localhost:8081/data/" + eventId);
      return res.data;
    }
  }