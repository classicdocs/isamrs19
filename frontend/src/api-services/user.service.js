import Axios from 'axios';

const ENDPOINTS = {
  USERS: 'users/',
  FRIENDS: '/friends',
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
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS, {params: data});
  }

};