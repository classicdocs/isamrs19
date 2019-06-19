import Axios from "axios";

const ENDPOINTS = {
  RATE: "rate-hotel/"
};

export default {
  rate(data) {
    return Axios.put(ENDPOINTS.RATE, data);
  }
};
