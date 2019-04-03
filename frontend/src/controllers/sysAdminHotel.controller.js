import sysAdminHotelApiService from "@/api-services/sysAdminHotel.service";

export default {
  create(data) {
    return sysAdminHotelApiService.create(data);
  }
};