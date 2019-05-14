import Axios, * as others from 'axios';

const ENDPOINTS = {
  HOTELS: '/hotels/',
  ROOMS: '/rooms',
  ROOMS_CONFIGURATION: '/roomsConfiguration'
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
  },
  update(hotelID, room, floorID){
    return Axios.put(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.ROOMS + "/" + floorID, room);
  },
  getRooms(hotelID){
    return Axios.get(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.ROOMS_CONFIGURATION)
  }
};