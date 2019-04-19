import Axios from 'axios';

const ENDPOINTS = {
  HOTEL: '/system-admin/addHotel',
  RENTACAR: '/system-admin/addRentACar',
  AIRLINE: '/system-admin/addAirlineCompany'
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
  }
};