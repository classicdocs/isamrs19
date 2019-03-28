import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import BootstrapVue from 'bootstrap-vue';

Vue.config.productionTip = false;


import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import VueTimepicker from 'vue2-timepicker';
import Datepicker from 'vuejs-datepicker';
import Multiselect from 'vue-multiselect';

import 'vue-multiselect/dist/vue-multiselect.min.css'

Vue.use(BootstrapVue);
Vue.use(VueTimepicker);
Vue.use(Datepicker);
Vue.use(Multiselect);

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
