import Vue from 'vue';
import Vuex from 'vuex';

import { reservation } from "./modules";
import { auth } from './modules';
import { snackbar} from "./modules";
import { friendship} from "./modules";
import { destination } from "./modules";
import { hotel } from "./modules";

Vue.use(Vuex);

const storeData = {
  modules: {
    reservation,
    auth,
    snackbar,
    friendship,
    destination,
    hotel,
  }
}

export default new Vuex.Store(storeData);