export default class Hotel {
    constructor() {
      this.name = null,
      this.address = null,
      this.description = null,
      this.priceList = [],
      this.roomConfiguration = [],
      this.admins = []
    }
  
    reset() {
      this.name = null;
      this.address = null;
      this.description = null;
      this.priceList = [];
      this.roomConfiguration = [];
      this.admins = [];
    }
  }