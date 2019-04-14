import Axios from 'axios';

const ENDPOINTS = {
  DESTINATIONS: 'destinations/'
};

export default {
  get() {
    return Axios.get(ENDPOINTS.DESTINATIONS);
  }
};