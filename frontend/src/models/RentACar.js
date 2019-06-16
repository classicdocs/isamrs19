import MapLocation from "./MapLocation";

export default class RentACar {
    constructor() {
      this.name = null,
      this.address = null,
      this.promotionalDescription = null,
      this.priceList = [],
      this.vehicles = [],
      this.branches = [],
      this.admins = [],
      this.rating = 0,
      this.mapLocation = new MapLocation()
    }
    reset() {
      this.name = null;
      this.address = null;
      this.promotionalDescription = null;
      this.priceList = [];
      this.vehicles = [];
      this.branches = [];
      this.admins = [];
      this.rating = 0;
      this.mapLocation = new MapLocation();
    }
  }
    