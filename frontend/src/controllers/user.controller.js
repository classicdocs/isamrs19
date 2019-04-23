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
};