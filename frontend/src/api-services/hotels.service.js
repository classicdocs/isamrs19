import Axios, * as others from 'axios';

const ENDPOINTS = {
  HOTELS: 'hotels'
};

export default {
    getHotels() {
    return Axios.get(ENDPOINTS.HOTELS);
  }
};