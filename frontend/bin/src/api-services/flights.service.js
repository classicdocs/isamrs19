import Axios from 'axios';

const ENDPOINTS = {
  FLIGHTS: 'flights/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.FLIGHTS, data);
  }
};