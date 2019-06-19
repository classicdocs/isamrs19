import RentACar from "./RentACar";

export default class RentACarAdmin {
  constructor() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null),
      (this.rentACar = new RentACar());
  }

  reset() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null),
      this.rentACar.reset();
  }
}
