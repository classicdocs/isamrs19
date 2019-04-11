import Axios from 'axios';

const ENDPOINTS = {
  AIRLINE_COMPANY: 'airline-companies/',
  AIRPLANES: '/airplanes/',
};

export default {
  getDestinations(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + "/destinations");
  },
  get(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id);
  },
  update(id, data) {
    return Axios.put(ENDPOINTS.AIRLINE_COMPANY + id, data);
  },
  getAirplanes(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.AIRPLANES);
  },
  addAirplane(id, data) {
    return Axios.post(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.AIRPLANES, data)
  }
};