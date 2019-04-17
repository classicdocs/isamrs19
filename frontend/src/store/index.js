import Vue from 'vue';
import Vuex from 'vuex';

import { reservation } from "./modules";

Vue.use(Vuex);

const storeData = {
  modules: {
    reservation,
  }
}

export default new Vuex.Store(storeData);