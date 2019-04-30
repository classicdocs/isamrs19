export default class Hotel {
    constructor() {
      this.id = null,
      this.name = null,
      this.address = null,
      this.description = null,
      this.numOfFloors = null,
      this.roomsByFloor = null,
      this.priceList = [],
      this.floors = [],
      this.admins = []
    }
  
    reset() {
      this.id = null,
      this.name = null;
      this.address = null;
      this.description = null;
      this.numOfFloors = null,
      this.roomsByFloor = null,
      this.priceList = [];
      this.floors = [];
      this.admins = [];
    }
  }