export default class VehicleReservation {
    constructor() {
        this.id = null,
        this.pickupDate = null,
        this.pickupTime = null,
        this.returnDate = null,
        this.returnTime = null,
        this.pickupLocation = null,
        this.returnLocation = null,
        this.carId = null,
        this.user = null,
        this.rentACarId = null,
        this.completed = false,
        this.gpsIncluded = false,
        this.childSeatIncluded = false,
        this.collisionInsuranceIncluded = false,
        this.theftInsuranceIncluded = false
    }
}