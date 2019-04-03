import Axios from 'axios';

const ENDPOINTS = {
  HOTEL_ADMINS: 'hotel-admins/'
};

export default {
  get() {
    return Axios.get(ENDPOINTS.HOTEL_ADMINS);
  }
};