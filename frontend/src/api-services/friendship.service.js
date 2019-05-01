import Axios from 'axios';

const ENDPOINTS = {
  FRIENDSHIP: "/friendship",
  ACCEPT: "/accept"
};

export default {
  acceptFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.ACCEPT, data );
  },
  addFriend(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP, data);
  }

};