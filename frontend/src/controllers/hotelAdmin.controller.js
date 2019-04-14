import HotelAdminApiService from "@/api-services/hotelAdmin.service";

export default {
  get() {
    return HotelAdminApiService.get();
  },
  getAdmin(username) {
    return HotelAdminApiService.getAdmin(username);
  },
  createAdmin(data){
    return HotelAdminApiService.createAdmin(data);
  }
};