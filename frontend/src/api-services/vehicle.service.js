import Axios from 'axios';

const ENDPOINTS = {
  VEHICLES: 'vehicles/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.VEHICLES, data);
  }
};