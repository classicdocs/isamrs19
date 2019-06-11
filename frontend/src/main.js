import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from './store';
import Vuetify from 'vuetify';
import Axios from 'axios';
import Config from './config';
Vue.config.productionTip = false;

import LoginController from './controllers/login.controller';

import VueNumberInput from '@chenfengyuan/vue-number-input';
Vue.use(VueNumberInput);

Vue.use(require('vue-moment'));

import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
Vue.use(Loading);

Vue.use(Vuetify);
Vue.prototype.router = router;

import 'vuetify/dist/vuetify.min.css'

// LoginController.initStoreAuth();

// Axios.defaults.baseURL = Config.getApiUrl();
Axios.defaults.baseURL = 'http://localhost:8088/api';
Axios.defaults.headers.Accept = 'application/json';
Axios.defaults.headers['Access-Control-Allow-Origin'] = '*';


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
