import HotelApiService from "@/api-services/hotel.service";

export default {
  create(data) {
    return HotelApiService.create(data);
  }
};