import HotelReservationCancelApiService from "@/api-services/hotel-reservation-cancel.service";

export default {
  cancel(hotel_id, reservation_id) {
    return HotelReservationCancelApiService.cancel(hotel_id, reservation_id);
  }
};
