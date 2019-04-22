import Axios from 'axios'

const ENDPOINTS = {
    RENTACAR_SERVICE: 'rentACars/'
};

export default {
    get(id) {
        return Axios.get(ENDPOINTS.RENTACAR_SERVICE + id);
    },

    getVehicles(id) {
        return Axios.get(ENDPOINTS.RENTACAR_SERVICE + id + "/vehicles");
    },

    update(id, data) {
        return Axios.put(ENDPOINTS.RENTACAR_SERVICE + id, data);
    },

    get(){
        return Axios.get(ENDPOINTS.RENTACAR_SERVICE);
    }
}

