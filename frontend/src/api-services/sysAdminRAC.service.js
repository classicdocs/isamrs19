import Axios from 'axios';

const ENDPOINTS = {
  RENTACARS: 'rentACar/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.RENTACARS, data);
  }
};