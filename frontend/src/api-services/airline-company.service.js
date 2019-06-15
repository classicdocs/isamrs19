import Axios from 'axios';

const ENDPOINTS = {
  AIRLINE_COMPANY: 'airline-companies/',
  AIRPLANES: '/airplanes/',
  AIRLINE_COMPANIES: 'airline-companies',
  DESTINATIONS: "/destinations",
  FLIGHTS: "/flights",
  DISCOUNT: "/discount",
  REPORTS: "/reports",
  SOLD_TICKETS: "/sold-tickets",
  LUGGAGE: "/luggage/",
  ARCHIVED: "/archived",
};

export default {
  getDestinations(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.DESTINATIONS);
  },
  get(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id);
  },
  update(id, data) {
    return Axios.put(ENDPOINTS.AIRLINE_COMPANY + id, data);
  },
  getAirplanes(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.AIRPLANES);
  },
  addAirplane(id, data) {
    return Axios.post(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.AIRPLANES, data)
  },
  updateAirplane(airlineCompany, airplane, data) {
    return Axios.put(ENDPOINTS.AIRLINE_COMPANY + airlineCompany + ENDPOINTS.AIRPLANES + airplane, data);
  },
  findAllAirlines(){
    return Axios.get(ENDPOINTS.AIRLINE_COMPANIES);
  },
  addDestination(id,data) {
    return Axios.post(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.DESTINATIONS, data);
  },
  getFlights(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.FLIGHTS);
  },
  getArchivedFlights(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.FLIGHTS + ENDPOINTS.ARCHIVED);
  },
  getFlightsDiscount(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.FLIGHTS + ENDPOINTS.DISCOUNT);
  },
  getReports(id, data) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.REPORTS, {params: data});
  },
  getLuggage(id) {
    return Axios.get(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.LUGGAGE);
  },
  addLuggage(id, data) {
    return Axios.post(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.LUGGAGE, data);
  },
  deleteLuggage(id, luggageId) {
    return Axios.delete(ENDPOINTS.AIRLINE_COMPANY + id + ENDPOINTS.LUGGAGE + luggageId);
  }
};