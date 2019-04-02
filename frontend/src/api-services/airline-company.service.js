import Axios from 'axios';

const ENDPOINTS = {
  AIRLINE_COMPANY: 'airline-companies/'
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
  }
};