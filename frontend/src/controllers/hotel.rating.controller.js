import HotelRatingApiService from "@/api-services/hotel.rating.service";

export default {
  rate(data) {
    return HotelRatingApiService.rate(data);
  }
};