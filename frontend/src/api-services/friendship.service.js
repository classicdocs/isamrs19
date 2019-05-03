import Axios from 'axios';

const ENDPOINTS = {
  FRIENDSHIP: "/friendship",
  ACCEPT: "/accept",
  CHECK_REQUEST: "/check-request",
  CANCEL: "/cancel",
  REMOVE: "/remove",
  CHECK_FRIEND: "/check-friend"
};

export default {
  acceptFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.ACCEPT, data );
  },
  addFriend(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP, data);
  },
  isRequestAlreadySent(data) {
    return Axios.get(ENDPOINTS.FRIENDSHIP + ENDPOINTS.CHECK_REQUEST, {params: data});
  },
  cancelFriendRequest(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.CANCEL, data);
  },
  removeFriend(data) {
    return Axios.post(ENDPOINTS.FRIENDSHIP + ENDPOINTS.REMOVE, data);
  },
  isFriend(data) {
    return Axios.get(ENDPOINTS.FRIENDSHIP + ENDPOINTS.CHECK_FRIEND, {params: data});
  }

};