import VehicleApiService from "@/api-services/vehicle.service";

export default {
  create(data) {
    return VehicleApiService.create(data);
  },
  get() {
    return VehicleApiService.get();
  },
  getSpecific(data) {
    return VehicleApiService.getSpecific(data);
  },
  remove(data) {
    return VehicleApiService.remove(data);
  },
  edit(data) {
    return VehicleApiService.edit(data);
  }
};
