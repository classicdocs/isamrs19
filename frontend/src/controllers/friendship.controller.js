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
  cancelFriendRequest(data) {
    return FriendshipApiService.cancelFriendRequest(data);
  },
  removeFriend(data) {
    return FriendshipApiService.removeFriend(data);
  },
  isFriend(data) {
    return FriendshipApiService.isFriend(data);
  }
};