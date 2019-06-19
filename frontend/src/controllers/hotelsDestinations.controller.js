import HotelDestinationsApiService from "@/api-services/hotelsDestinations.service";

export default {
  get() {
    return HotelDestinationsApiService.get();
  }
};
