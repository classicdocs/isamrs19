
import Hotel from "./Hotel";

export default class Room {
    constructor() {
      this.roomNumber = null,
      this.numberOfBeds = null,
      this.hotel = new Hotel()
    }
  
    reset() {
      this.roomNumber = null;
      this.numberOfBeds = null;
      this.hotel.reset();
    }
  }