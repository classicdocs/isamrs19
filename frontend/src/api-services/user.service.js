import Axios from "axios";

const ENDPOINTS = {
  USERS: "users/",
  FRIENDS: "/friends",
  SEARCH: "/search",
  REQUESTS: "/requests",
  FRIENDSHIP: "/friendship",
  FLIGHTS: "/flight",
  RESERVATIONS: "/reservations",
  INVITATIONS: "/invitations",
  ACCEPT: "/accept",
  DECLINE: "/decline",
  CANCEL: "/cancel",
  HISTORY: "/history"
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
  update(id, data) {
    return Axios.put(ENDPOINTS.USERS + id, data);
  },
  searchFriends(id, data) {
    return Axios.get(
      ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS + ENDPOINTS.SEARCH,
      { params: data }
    );
  },
  getFriends(id) {
    return Axios.get(ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS);
  },
  getFriendRequests(id) {
    return Axios.get(
      ENDPOINTS.USERS + id + ENDPOINTS.FRIENDS + ENDPOINTS.REQUESTS
    );
  },
  getFlightReservations(id) {
    return Axios.get(
      ENDPOINTS.USERS + id + ENDPOINTS.FLIGHTS + ENDPOINTS.RESERVATIONS
    );
  },
  getFlightReservationsHistory(id) {
    return Axios.get(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.RESERVATIONS +
        ENDPOINTS.HISTORY
    );
  },
  getFlightInvitations(id) {
    return Axios.get(
      ENDPOINTS.USERS + id + ENDPOINTS.FLIGHTS + ENDPOINTS.INVITATIONS
    );
  },
  getFlightInvitationsHistory(id) {
    return Axios.get(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.INVITATIONS +
        ENDPOINTS.HISTORY
    );
  },
  acceptInvitation(id, data) {
    return Axios.post(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.INVITATIONS +
        ENDPOINTS.ACCEPT,
      data
    );
  },
  declineInvitation(id, data) {
    return Axios.post(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.INVITATIONS +
        ENDPOINTS.DECLINE,
      data
    );
  },
  cancelReservation(id, reservationId) {
    return Axios.post(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.RESERVATIONS +
        ENDPOINTS.CANCEL,
      reservationId
    );
  },
  cancelInvitation(id, invitationId) {
    return Axios.post(
      ENDPOINTS.USERS +
        id +
        ENDPOINTS.FLIGHTS +
        ENDPOINTS.INVITATIONS +
        ENDPOINTS.CANCEL,
      invitationId
    );
  }
};
