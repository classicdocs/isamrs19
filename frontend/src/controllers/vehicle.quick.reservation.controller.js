import VehicleQuickReservationApiService from "@/api-services/vehicle.quick.reservation.service";

export default {
  get(id) {
    return VehicleQuickReservationApiService.get(id);
  }
};