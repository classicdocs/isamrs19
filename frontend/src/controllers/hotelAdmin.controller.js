import HotelAdminApiService from "@/api-services/hotelAdmin.service";

export default {
  get() {
    return HotelAdminApiService.get();
  }
};