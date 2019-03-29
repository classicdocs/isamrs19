
export default class Flight {
  constructor() {
    this.startDestination = null,
    this.finalDestination = null,
    this.transferDestination = [],
    this.departureDate = null,
    this.departureTime = null,
    this.landingDate = null,
    this.landingTime = null,
    this.flightTime = null,
    this.ticketPrice = null,
    this.distance = null
  }

  reset() {
    this.startDestination = null;
    this.finalDestination = null;
    this.transferDestination = [];
    this.departureDate = null,
    this.departureTime = null,
    this.landingDate = null,
    this.landingTime = null,
    this.flightTime = null,
    this.ticketPrice = null;
    this.distance = null;
  }
}