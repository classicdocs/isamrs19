import AirlineCompany from "./AirlineCompany";

export default class AirlineAdmin {
  constructor() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null),
      (this.airlineCompany = new AirlineCompany());
  }

  reset() {
    (this.username = null),
      (this.password = null),
      (this.firstname = null),
      (this.lastname = null),
      (this.email = null),
      (this.address = null),
      (this.phone = null);
    this.airlineCompany.reset();
  }
}
