import Axios from 'axios';

const ENDPOINTS = {
  HOTELS: 'hotels/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.HOTELS, data);
  }
};