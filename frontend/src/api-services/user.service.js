import Axios from 'axios';

const ENDPOINTS = {
  USERS: 'users/',
  FRIENDS: '/friends',
  SEARCH: "/search",
  REQUESTS: "/requests",
  FRIENDSHIP: "/friendship",
  FLIGHTS: "/flight",
  RESERVATIONS: "/reservations",
};

export default {
  create(data) {
    return Axios.post(ENDPOINTS.USERS, data);
  },
  get() {
    return Axios.get(ENDPOINTS.USERS);
  },
  getUser(id) {
    return Axios.get(ENDPOINTS.USERS + id);
  },
  update(id,data) {
    return Axios.put(ENDPOINTS.USERS + id, data);
  },
  searchFriends(id, data) {
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS + ENDPOINTS.SEARCH, {params: data});
  },
  getFriends(id) {  
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS);
  },
  getFriendRequests(id) {
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS + ENDPOINTS.REQUESTS );
  },
  getFlightReservations(id) {
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FLIGHTS + ENDPOINTS.RESERVATIONS);
  }

};