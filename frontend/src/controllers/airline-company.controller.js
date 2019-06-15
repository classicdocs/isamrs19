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
  },
  getAirplanes(id) {
    return AirlineCompanyApiService.getAirplanes(id);
  },
  addAirplane(id, data) {
    return AirlineCompanyApiService.addAirplane(id, data);
  },
  updateAirplane(airlineCompany, airplane, data) {
    return AirlineCompanyApiService.updateAirplane(airlineCompany, airplane, data);
  },
  findAllAirlines(){
    return AirlineCompanyApiService.findAllAirlines();
  },
  addDestination(id,data) {
    return AirlineCompanyApiService.addDestination(id, data);
  },
  getFlights(id) {
    return AirlineCompanyApiService.getFlights(id);
  },
  getArchivedFlights(id) {
    return AirlineCompanyApiService.getArchivedFlights(id);
  },
  getFlightsDiscount(id) {
    return AirlineCompanyApiService.getFlightsDiscount(id);
  },
  getReports(id, data) {
    return AirlineCompanyApiService.getReports(id,data);
  },
  getLuggage(id) {
    return AirlineCompanyApiService.getLuggage(id);
  },
  addLuggage(id, data) {
    return AirlineCompanyApiService.addLuggage(id,data);
  },
  deleteLuggage(id, luggageId) {
    return AirlineCompanyApiService.deleteLuggage(id,luggageId);
  },
  changeLocation(airline_id, mapLocation){
    return AirlineCompanyApiService.changeLocation(airline_id, mapLocation);
  }

};