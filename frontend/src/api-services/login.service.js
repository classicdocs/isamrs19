import Axios from 'axios';

const ENDPOINTS = {
  LOGIN: 'login/'
};

export default {
  login(data) {
    return Axios.post(ENDPOINTS.LOGIN, data);
  }
};