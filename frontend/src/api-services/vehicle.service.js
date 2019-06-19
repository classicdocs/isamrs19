import Axios from "axios";

const ENDPOINTS = {
  VEHICLES: "vehicles/"
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.VEHICLES + "create", data);
  },
  get() {
    return Axios.get(ENDPOINTS.VEHICLES);
  },
  getSpecific(data) {
    return Axios.post(ENDPOINTS.VEHICLES + "specific", data);
  },
  remove(data) {
    return Axios.post(ENDPOINTS.VEHICLES + "remove", data);
  },
  edit(data) {
    return Axios.put(ENDPOINTS.VEHICLES + "edit", data);
  }
};
