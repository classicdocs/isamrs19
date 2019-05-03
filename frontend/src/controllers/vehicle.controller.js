import VehicleApiService from "@/api-services/vehicle.service";

export default {
  create(data) {
    return VehicleApiService.create(data);
  },
  get(){
    return VehicleApiService.get();
  },
  getSpecific() {
    return VehicleApiService.getSpecific(data);
  }
};