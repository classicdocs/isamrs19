import FlightApiService from "@/api-services/flight.service";

export default {
  create(data) {
    return FlightApiService.create(data);
  }
};