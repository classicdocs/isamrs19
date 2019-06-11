import Vue from 'vue';
import Vuex from 'vuex';

import { reservation } from "./modules";
import { auth } from './modules';
import { snackbar} from "./modules";
import { friendship} from "./modules";
import { destination } from "./modules";
import { hotel } from "./modules";
import { hotels } from "./modules";
import { airlines } from "./modules";
import { rentacars } from "./modules";
import { rooms } from "./modules";
import { searchHotelParams } from "./modules";
import { hotelReservations } from "./modules";
import { discount } from "./modules";

Vue.use(Vuex);

const storeData = {
  modules: {
    reservation,
    auth,
    snackbar,
    friendship,
    destination,
    hotel,
    hotels,
    airlines,
    rentacars,
    rooms,
    searchHotelParams,
    hotelReservations,
    discount,
  }
}

export default new Vuex.Store(storeData);