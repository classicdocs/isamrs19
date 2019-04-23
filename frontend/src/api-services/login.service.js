import Axios from 'axios';

const ENDPOINTS = {
  LOGIN: 'login/',
  CHANGE_PASSWORD: 'change-password/',
  LOGOUT: 'logout/',
  ACTIVE_USER: 'me/',
};

export default {
  login(data) {
    return Axios.post(ENDPOINTS.LOGIN, data);
  },
  changePassword(data) {
    return Axios.put(ENDPOINTS.CHANGE_PASSWORD, data);
  },

  logout() {
    return Axios.post(ENDPOINTS.LOGOUT);
  },

  refreshUser() {
    return Axios.get(ENDPOINTS.ACTIVE_USER);
  }
};