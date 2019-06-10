import Axios from 'axios';

const ENDPOINTS = {
  DESTINATIONS: 'hotelDestinations/'
};

export default {
  get() {
    return Axios.get(ENDPOINTS.DESTINATIONS);
  }
};