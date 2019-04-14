import Axios from 'axios';

const ENDPOINTS = {
  //FIXME testiraj ovo, proveri je l treba ovako
  HOTELS: 'sys-admin-page/'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.HOTELS, data);
  }
};