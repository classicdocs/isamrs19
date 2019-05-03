import Axios, * as others from 'axios';

const ENDPOINTS = {
  HOTELS: '/hotels/',
  ROOMS: '/rooms'
};

export default {
  getHotels() {
    return Axios.get(ENDPOINTS.HOTELS);
  },
  addRoom(id, data){
    return Axios.post(ENDPOINTS.HOTELS + id + ENDPOINTS.ROOMS, data);
  },
  getHotel(id){
    return Axios.get(ENDPOINTS.HOTELS + id);
  }
};