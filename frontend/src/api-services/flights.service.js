import Axios from 'axios';

const ENDPOINTS = {
  FLIGHTS: 'flights/',
  SEARCH: 'search'
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.FLIGHTS, data);
  },
  search(data) {
    return Axios.get(ENDPOINTS.FLIGHTS + ENDPOINTS.SEARCH, {params: data});
  }
};