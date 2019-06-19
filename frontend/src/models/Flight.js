import Destination from "./Destination";
import AirlineCompany from "./AirlineCompany";

export default class Flight {
  constructor() {
    (this.airlineCompany = new AirlineCompany()),
      (this.airplane = null),
      (this.startDestination = new Destination()),
      (this.finalDestination = new Destination()),
      (this.transferDestinations = []),
      (this.departureDate = null),
      (this.departureTime = null),
      (this.landingDate = null),
      (this.landingTime = null),
      (this.flightTimeHours = null),
      (this.flightTimeMinutes = null);
    (this.ticketPriceFirst = null),
      (this.ticketPriceBusiness = null),
      (this.ticketPriceEconomy = null),
      (this.distance = null),
      (this.seatsFirst = null),
      (this.seatsBusiness = null),
      (this.seatsEconomy = null);
  }

  reset() {
    this.airlineCompany.reset();
    this.startDestination.reset();
    (this.airplane = null), this.finalDestination.reset();
    this.transferDestinations = [];
    (this.departureDate = null),
      (this.departureTime = null),
      (this.landingDate = null),
      (this.landingTime = null),
      (this.flightTimeHours = null),
      (this.flightTimeMinutes = null);
    (this.ticketPriceFirst = null),
      (this.ticketPriceBusiness = null),
      (this.ticketPriceEconomy = null),
      (this.distance = null);
  }
}
