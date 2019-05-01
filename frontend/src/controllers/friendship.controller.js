import FriendshipApiService from "@/api-services/friendship.service";

export default {
  acceptFriendRequest(data) {
    return FriendshipApiService.acceptFriendRequest(data);
  },
  addFriend(data) {
    return FriendshipApiService.addFriend(data);
  }
};