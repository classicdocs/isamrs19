import UserApiService from "@/api-services/user.service";

export default {
  create(data) {
    return UserApiService.create(data);
  },
  get(){
    return UserApiService.get();
  }
};