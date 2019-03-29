import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import Vuetify from 'vuetify';
import Axios from 'axios';
import Config from './config';
Vue.config.productionTip = false;


Vue.use(Vuetify);

<<<<<<< HEAD
import VueTimepicker from 'vue2-timepicker'
import Datepicker from 'vuejs-datepicker' 
import VueNumberInput from '@chenfengyuan/vue-number-input';

Vue.use(VueTimepicker);
Vue.use(Datepicker);
Vue.use(VueNumberInput)
=======
import 'vuetify/dist/vuetify.min.css'

// Axios.defaults.baseURL = Config.getApiUrl();
Axios.defaults.baseURL = 'http://localhost:8088/api';
Axios.defaults.headers.Accept = 'application/json';
Axios.defaults.headers['Access-Control-Allow-Origin'] = '*';
>>>>>>> flights

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
