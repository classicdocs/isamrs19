import Axios from "axios";

const ENDPOINTS = {
  FLIGHTS: "flights/",
  SEARCH: "search",
  RESERVE: "reserve",
  DISCOUNT: "discount",
  ARCHIVE: "archive"
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.FLIGHTS, data);
  },
  search(data) {
    return Axios.get(ENDPOINTS.FLIGHTS + ENDPOINTS.SEARCH, { params: data });
  },
  reserve(data) {
    return Axios.post(ENDPOINTS.FLIGHTS + ENDPOINTS.RESERVE, data);
  },
  addDiscount(data) {
    return Axios.post(ENDPOINTS.FLIGHTS + ENDPOINTS.DISCOUNT, data);
  },
  archive(id) {
    return Axios.put(ENDPOINTS.FLIGHTS + ENDPOINTS.ARCHIVE, id);
  }
};
