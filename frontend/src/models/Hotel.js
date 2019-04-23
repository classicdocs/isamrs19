export default class Hotel {
    constructor() {
      this.id = null,
      this.name = null,
      this.address = null,
      this.description = null,
      this.priceList = [],
      this.roomConfiguration = [],
      this.admins = []
    }
  
    reset() {
      this.id = null,
      this.name = null;
      this.address = null;
      this.description = null;
      this.priceList = [];
      this.roomConfiguration = [];
      this.admins = [];
    }
  }