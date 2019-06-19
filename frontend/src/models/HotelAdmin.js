import Hotel from "./Hotel";

export default class HotelAdmin {
  constructor() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null),
      (this.hotel = new Hotel());
  }

  reset() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null);
    this.hotel.reset();
  }
}
