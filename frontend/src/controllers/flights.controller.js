import FlightsApiService from "@/api-services/flights.service";

export default {
  create(data) {
    return FlightsApiService.create(data);
  }
};