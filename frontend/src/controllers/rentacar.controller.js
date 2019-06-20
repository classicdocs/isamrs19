import RentACarService from "@/api-services/rentacar-service.js";

export default {
  get(id) {
    return RentACarService.get(id);
  },

  getVehicles(id) {
    return RentACarService.getVehicles(id);
  },

    update(id, data){
        return RentACarService.update(id, data);
    },
    getAll() {
        return RentACarService.getAll();
    },
    changeLocation(rac_id, mapLocation){
        return RentACarService.changeLocation(rac_id, mapLocation);
    },
    getQuickReservations(id) {
        return RentACarService.getQuickReservations(id);
    },
    createNewQuickReservation(data) {
        return RentACarService.createNewQuickReservation(data);
    },
    getQuickReservationsAdmin(id) {
        return RentACarService.getQuickReservationsAdmin(id);
    },
    getReports(id, data) {
        return RentACarService.getReports(id, data);
    },
    addBranch(id, data) {
        return RentACarService.addBranch(id,data);
    },
    removeBranch(id, data) {
        return RentACarService.removeBranch(id,data);
    }
}
