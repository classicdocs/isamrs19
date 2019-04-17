import Axios from 'axios';

const ENDPOINTS = {
  HOTELS: 'hotels/'
};

export default {
  createHotel(data) {
    return Axios.post(ENDPOINTS.HOTELS, data);
  },
  get(){
    return Axios.get(ENDPOINTS.HOTELS);
  }
};