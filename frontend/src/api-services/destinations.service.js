import Axios from 'axios';

const ENDPOINTS = {
  FLIGHTS: 'destinations/'
};

export default {
  get() {
    return Axios.get(ENDPOINTS.FLIGHTS);
  }
};