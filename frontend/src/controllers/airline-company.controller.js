import AirlineCompanyApiService from "@/api-services/airline-company.service";

export default {
  getDestinations(id) {
    return AirlineCompanyApiService.getDestinations(id);
  },

  get(id) {
    return AirlineCompanyApiService.get(id);
  },
  update(id, data) {
    return AirlineCompanyApiService.update(id, data);
  }
};