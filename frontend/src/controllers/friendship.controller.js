import FriendshipApiService from "@/api-services/friendship.service";

export default {
  acceptFriendRequest(data) {
    return FriendshipApiService.acceptFriendRequest(data);
  },
  addFriend(data) {
    return FriendshipApiService.addFriend(data);
  },
  isRequestAlreadySent(data) {
    return FriendshipApiService.isRequestAlreadySent(data);
  },
  withdrawFriendRequest(data) {
    return FriendshipApiService.withdrawFriendRequest(data);
  },
  cancelFriendRequest(data) {
    return FriendshipApiService.cancelFriendRequest(data);
  }
};