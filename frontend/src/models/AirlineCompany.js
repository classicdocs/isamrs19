import MapLocation from "./MapLocation";

export default class AirlineCompany {
  constructor() {
    (this.id = null),
      (this.name = null),
      (this.address = null),
      (this.description = null),
      (this.averageRating = 0),
      (this.mapLocation = new MapLocation());
  }
  reset() {
    (this.id = null),
      (this.name = null),
      (this.address = null),
      (this.description = null),
      (this.averageRating = 0),
      (this.mapLocation = new MapLocation());
  }
}
