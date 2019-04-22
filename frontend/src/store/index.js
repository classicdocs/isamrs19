import Vue from 'vue';
import Vuex from 'vuex';

import { reservation } from "./modules";
import { auth } from './modules';
import { snackbar} from "./modules";

Vue.use(Vuex);

const storeData = {
  modules: {
    reservation,
    auth,
    snackbar,
  }
}

export default new Vuex.Store(storeData);