export default class RentACar {
    constructor() {
      this.name = null,
      this.address = null,
      this.promotionalDescription = null,
      this.priceList = [],
      this.vehicles = [],
      this.branches = [],
      this.admins = []
    }
  
    reset() {
      this.name = null;
      this.address = null;
      this.promotionalDescription = null;
      this.priceList = [];
      this.vehicles = [];
      this.branches = [];
      this.admins = [];
    }
  }