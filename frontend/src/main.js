import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import BootstrapVue from 'bootstrap-vue';

Vue.config.productionTip = false;

Vue.use(BootstrapVue);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import VueTimepicker from 'vue2-timepicker'
import Datepicker from 'vuejs-datepicker' 

Vue.use(VueTimepicker);
Vue.use(Datepicker);

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
