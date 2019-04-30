import systemAdminService from "@/api-services/system-admin.service";

export default {
  createHotel(data){
    return systemAdminService.createHotel(data);
  },
  createRentACar(data){
    return systemAdminService.createRentACar(data);
  },
  createAirline(data) {
    return systemAdminService.createAirline(data);
  }
};