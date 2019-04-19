import LoginApiService from "@/api-services/login.service";

export default {
  login(data) {
    return LoginApiService.login(data);
  }
};