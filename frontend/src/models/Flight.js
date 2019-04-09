
export default class Flight {
  constructor() {
    this.airlineCompany = null,
    this.airplane = null,
    this.startDestination = null,
    this.finalDestination = null,
    this.transferDestinations = [],
    this.departureDate = null,
    this.departureTime = null,
    this.landingDate = null,
    this.landingTime = null,
    this.flightTimeHours = null,
    this.flightTimeMinutes = null;
    this.ticketPriceFirst = null,
    this.ticketPriceBuissness = null,
    this.ticketPriceEconomy = null,
    this.distance = null
  }

  reset() {
    this.startDestination = null;
    this.airplane = null,
    this.finalDestination = null;
    this.transferDestinations = [];
    this.departureDate = null,
    this.departureTime = null,
    this.landingDate = null,
    this.landingTime = null,
    this.flightTimeHours = null,
    this.flightTimeMinutes = null;
    this.ticketPriceFirst = null,
    this.ticketPriceBuissness = null,
    this.ticketPriceEconomy = null,
    this.distance = null;
  }
}