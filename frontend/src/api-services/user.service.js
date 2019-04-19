import Axios from 'axios';

const ENDPOINTS = {
  USERS: 'users/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.USERS, data);
  },
  get() {
    return Axios.get(ENDPOINTS.USERS);
  }
};