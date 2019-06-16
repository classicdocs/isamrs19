export default class RentACar {
    constructor() {
      this.name = null,
      this.address = null,
      this.promotionalDescription = null,
      this.priceList = [],
      this.vehicles = [],
      this.branches = [],
      this.admins = [],
      this.rating = 0
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
    }
  }
    