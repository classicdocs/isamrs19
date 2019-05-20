import UserApiService from "@/api-services/user.service";

export default {
  create(data) {
    return UserApiService.create(data);
  },
  get(){
    return UserApiService.get();
  },
  getUser(id) {
    return UserApiService.getUser(id);
  },
  update(id, data) {
    return UserApiService.update(id,data);
  },
  searchFriends(id, data) {
    return UserApiService.searchFriends(id,data);
  },
  getFriends(id) {
    return UserApiService.getFriends(id);
  },
  getFriendRequests(id) {
    return UserApiService.getFriendRequests(id);
  },
  getFlightReservations(id) {
    return UserApiService.getFlightReservations(id);
  },
  getFlightInvitations(id) {
    return UserApiService.getFlightInvitations(id);
  },
  acceptInvitation(id, data) {
    return UserApiService.acceptInvitation(id, data);
  },
  declineInvitation(id, data) {
    return UserApiService.declineInvitation(id, data);
  }
};