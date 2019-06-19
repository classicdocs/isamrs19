import Axios from "axios";

const ENDPOINTS = {
  RATE: "rate-flight/"
};

export default {
  rate(data) {
    return Axios.put(ENDPOINTS.RATE, data);
  },
  rateWithReturn(data) {
    return Axios.put(ENDPOINTS.RATE + "1/", data);
  },
  rateDifferentCompanies(data) {
    return Axios.put(ENDPOINTS.RATE + "2/", data);
  }
};
