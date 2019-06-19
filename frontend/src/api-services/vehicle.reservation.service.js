import Axios from "axios";

const ENDPOINTS = {
  RESERVE: "rent-vehicle/"
};

export default {
  reserve(data) {
    return Axios.post(ENDPOINTS.RESERVE, data);
  },
  get(id) {
    return Axios.get(ENDPOINTS.RESERVE + id);
  },
  rate(data) {
    return Axios.put(ENDPOINTS.RESERVE, data);
  }
};
