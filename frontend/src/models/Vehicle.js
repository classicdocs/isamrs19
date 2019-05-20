export default class Vehicle {
    constructor(rentacar,name,manufacturer,model,type,passengers,year,price) {
      this.rentACar = rentacar,
      this.name = name,
      this.vehicleManufacturer = manufacturer,
      this.vehicleModel = model,
      this.vehicleType = type,
      this.numberOfPassengers = passengers,
      this.yearOfProduction = year,
      this.pricePerDay = price,
      this.id = null
    }
}