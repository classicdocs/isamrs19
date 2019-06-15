import Axios from 'axios';

const ENDPOINTS = {
  VEHICLE_CANCEL: 'cancel-vehicle-reservations/'
};

export default {
  cancel(id) {
    return Axios.get(ENDPOINTS.VEHICLE_CANCEL + id);
  }
};