import RentACarService from "@/api-services/rentacar-service.js";

export default {
    get(id) {
        return RentACarService.get(id);
    },

    getVehicles(id){
        return RentACarService.getVehicles(id);
    },

    update(id, data){
        return RentACarService.update(id, data);
    },

    get() {
        return RentACarService.get();
    }
}

