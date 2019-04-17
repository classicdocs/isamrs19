import sysAdminHotelApiService from "@/api-services/sysAdminHotel.service";

export default {
  createHotel(data) {
    return sysAdminHotelApiService.createHotel(data);
  },
  get(){
    return sysAdminHotelApiService.get();
  }
};