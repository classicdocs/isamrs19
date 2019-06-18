import HotelFloor from "./HotelFloor";

export default class Room {
    constructor() {
        this.id = null,
        this.roomNumber = null,
        this.numberOfBeds = null,
        this.roomTaken = [],
        this.specialPrices = [],
        this.roomDiscounts = [],
        this.hotelFloor = new HotelFloor()
    }
  
    reset() {
        this.id = null;
        this.roomNumber = null;
        this.numberOfBeds = null;
        this.roomTaken = [];
        this.specialPrices = [];
        this.roomDiscounts = [];
        this.hotelFloor.reset();
    }
  }