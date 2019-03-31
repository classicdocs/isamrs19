import Axios from 'axios';

const ENDPOINTS = {
  // Fixme: proveri da li treba izmeniti bekend jer sve su ovo akcije SISTEM admina
  HOTELS: 'hotels/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.HOTELS, data);
  }
};