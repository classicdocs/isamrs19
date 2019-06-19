import systemAdminService from "@/api-services/system-admin.service";

export default {
  createHotel(data) {
    return systemAdminService.createHotel(data);
  },
  createRentACar(data) {
    return systemAdminService.createRentACar(data);
  },
  createAirline(data) {
    return systemAdminService.createAirline(data);
  },
  createHotelAdmin(data) {
    return systemAdminService.createHotelAdmin(data);
  },
  createAirlineAdmin(data) {
    return systemAdminService.createAirlineAdmin(data);
  },
  createRentACarAdmin(data) {
    return systemAdminService.createRentACarAdmin(data);
  }
};
