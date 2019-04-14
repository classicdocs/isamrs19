import Axios from 'axios';

const ENDPOINTS = {
  HOTEL_ADMINS: 'hotel-admins/'
};

export default {
  get() {
    return Axios.get(ENDPOINTS.HOTEL_ADMINS);
  },
  getAdmin(username) {
    return Axios.get(ENDPOINTS.HOTEL_ADMINS, username);
  },
  createAdmin(data){
    return Axios.post(ENDPOINTS.HOTEL_ADMINS, data);
  }
};