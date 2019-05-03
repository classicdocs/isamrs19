import Axios from 'axios';

const ENDPOINTS = {
  FRIENDSHIP: "/friendship",
  ACCEPT: "/accept",
  CHECK: "/check-request",
  WITHDRAW: "/withdraw",
  CANCEL: "/cancel",
};

export default {
  acceptFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.ACCEPT, data );
  },
  addFriend(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP, data);
  },
  isRequestAlreadySent(data) {
    return Axios.get(ENDPOINTS.FRIENDSHIP + ENDPOINTS.CHECK, {params: data});
  },
  withdrawFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.WITHDRAW, data);
  },
  cancelFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.CANCEL, data);
  }

};