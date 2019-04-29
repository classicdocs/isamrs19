import Axios from 'axios';

const ENDPOINTS = {
  VEHICLES: 'vehicles/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.VEHICLES, data);
  },
  get() {
    return Axios.get(ENDPOINTS.VEHICLES);
  },
  getSpecific(data) {
    return Axios.get(ENDPOINTS.VEHICLES + '/specific', data);
  },
};