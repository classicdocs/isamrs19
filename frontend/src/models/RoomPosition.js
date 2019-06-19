export default class RoomPosition {
  constructor() {
    (this.level = null), (this.number = null), (this.exists = null);
  }

  reset() {
    this.level = null;
    this.number = null;
    this.exists.reset();
  }
}
