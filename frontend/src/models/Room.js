
import HotelFloor from "./HotelFloor";

export default class Room {
    constructor() {
      this.roomNumber = null,
      this.numberOfBeds = null,
      this.hotelFloor = new HotelFloor()
    }
  
    reset() {
      this.roomNumber = null;
      this.numberOfBeds = null;
      this.hotelFloor.reset();
    }
  }