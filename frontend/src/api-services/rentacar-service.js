import Axios from "axios";

const ENDPOINTS = {
  RENTACAR_SERVICE: "rentACars/",
  CHANGE_LOCATION: "/changeLocation/"
};

export default {
  get(id) {
    return Axios.get(ENDPOINTS.RENTACAR_SERVICE + id);
  },

  getVehicles(id) {
    return Axios.get(ENDPOINTS.RENTACAR_SERVICE + id + "/vehicles");
  },

  update(id, data) {
    return Axios.put(ENDPOINTS.RENTACAR_SERVICE + id, data);
  },
  getAll() {
    return Axios.get(ENDPOINTS.RENTACAR_SERVICE);
  },
  changeLocation(rac_id, mapLocation) {
    return Axios.put(
      ENDPOINTS.RENTACAR_SERVICE + rac_id + ENDPOINTS.CHANGE_LOCATION,
      mapLocation
    );
  }
};
