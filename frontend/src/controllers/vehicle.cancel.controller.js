import VehicleCancelApiService from "@/api-services/vehicle.cancel.service";

export default {
  cancel(id) {
    return VehicleCancelApiService.cancel(id);
  }
};
