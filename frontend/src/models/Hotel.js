import MapLocation from "./MapLocation";

export default class Hotel {
  constructor() {
    (this.id = null),
      (this.name = null),
      (this.address = null),
      (this.description = null),
      (this.numOfFloors = null),
      (this.roomsByFloor = null),
      (this.priceList = []),
      (this.floors = []),
      (this.admins = []),
      (this.destination = null),
      (this.mapLocation = new MapLocation());
  }

  reset() {
    (this.id = null), (this.name = null);
    this.address = null;
    this.description = null;
    (this.numOfFloors = null),
      (this.roomsByFloor = null),
      (this.priceList = []);
    this.floors = [];
    this.admins = [];
    this.destination = null;
    this.mapLocation = new MapLocation();
  }
}
