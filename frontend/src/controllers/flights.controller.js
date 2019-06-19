import FlightsApiService from "@/api-services/flights.service";

export default {
  create(data) {
    return FlightsApiService.create(data);
  },
  search(data) {
    return FlightsApiService.search(data);
  },
  reserve(data) {
    return FlightsApiService.reserve(data);
  },
  addDiscount(data) {
    return FlightsApiService.addDiscount(data);
  },
  archive(id) {
    return FlightsApiService.archive(id);
  }
};
