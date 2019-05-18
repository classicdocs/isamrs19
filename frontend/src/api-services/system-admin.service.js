import Axios from 'axios';

const ENDPOINTS = {
  HOTEL: '/system-admin/addHotel',
  RENTACAR: '/system-admin/addRentACar',
  AIRLINE: '/system-admin/addAirlineCompany',
  HOTEL_ADMIN: '/system-admin/addHotelAdmin',
  AIRLINE_ADMIN: '/system-admin/addAirlineAdmin',
  RAC_ADMIN: '/system-admin/addRentACarAdmin'
};

export default {
  createHotel(data){
    return Axios.post(ENDPOINTS.HOTEL, data);
  },
  createRentACar(data){
    return Axios.post(ENDPOINTS.RENTACAR, data);
  },
  createAirline(data) {
    return Axios.post(ENDPOINTS.AIRLINE, data);
  },
  
  createHotelAdmin(data){
    return Axios.post(ENDPOINTS.HOTEL_ADMIN, data);
  },
  createAirlineAdmin(data){
    return Axios.post(ENDPOINTS.AIRLINE_ADMIN, data);
  },
  createRentACarAdmin(data){
    return Axios.post(ENDPOINTS.RAC_ADMIN, data);
  }
};