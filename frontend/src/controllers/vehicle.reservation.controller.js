import VehicleReservationApiService from "@/api-services/vehicle.reservation.service";

export default {
  reserve(data) {
    return VehicleReservationApiService.reserve(data);
  },
  get(id) {
    return VehicleReservationApiService.get(id);
  }
};