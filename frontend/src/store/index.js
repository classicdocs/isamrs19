import Vue from 'vue';
import Vuex from 'vuex';

import { reservation } from "./modules";
import { auth } from './modules';

Vue.use(Vuex);

const storeData = {
  modules: {
    reservation,
    auth,
  }
}

export default new Vuex.Store(storeData);