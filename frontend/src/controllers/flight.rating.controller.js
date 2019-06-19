import FlightRatingApiService from "@/api-services/flight.rating.service";

export default {
  rate(data) {
    return FlightRatingApiService.rate(data);
  },
  rateWithReturn(data) {
    return FlightRatingApiService.rateWithReturn(data);
  },
  rateDifferentCompanies(data) {
    return FlightRatingApiService.rateDifferentCompanies(data);
  }
};
