import Hotel from "./Hotel";

export default class HotelFloor {
  constructor() {
    (this.id = null),
      (this.level = null),
      (this.maxRooms = null),
      (this.roomsOnFloor = []),
      (this.hotel = new Hotel());
  }

  reset() {
    this.id = null;
    this.level = null;
    this.maxRooms = null;
    this.roomsOnFloor = [];
    this.hotel.reset();
  }
}
