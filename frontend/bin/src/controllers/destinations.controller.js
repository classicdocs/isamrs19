import DestinationsApiService from "@/api-services/destinations.service";

export default {
  get() {
    return DestinationsApiService.get();
  }
};