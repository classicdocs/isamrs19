import Axios, * as others from 'axios';

const ENDPOINTS = {
  HOTELS: '/hotels/',
  ROOMS: '/rooms',
  ROOMS_CONFIGURATION: '/roomsConfiguration',
  HOTELS_OFFER: '/addHotelsOffer',
  UPDATE_PRICELIST: '/updatePriceList',
  SEARCH: '/search',
  RESERVE: '/reserve',
  RESERVATIONS: 'reservations/',
  ADDPRICE: '/addRoomPrice/'
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
    return Axios.get(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.ROOMS_CONFIGURATION);
  },
  addOffer(hotelID, data){
    return Axios.put(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.HOTELS_OFFER, data);
  },
  updatePricelist(hotelID, data){
    return Axios.put(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.UPDATE_PRICELIST, data);
  },
  search(data){
    return Axios.get(ENDPOINTS.HOTELS + ENDPOINTS.SEARCH, {params: data});
  },
  reserve(hotelID, data){
    return Axios.post(ENDPOINTS.HOTELS + hotelID + ENDPOINTS.RESERVE, data);
  },
  getReservations(user_id){
    return Axios.get(ENDPOINTS.HOTELS + ENDPOINTS.RESERVATIONS + user_id);
  },
  addSpecialPrice(hotel_id, room_id, specialPrice){
    return Axios.put(ENDPOINTS.HOTELS + hotel_id + ENDPOINTS.ADDPRICE + room_id, specialPrice);
  }
};