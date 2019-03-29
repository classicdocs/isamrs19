import Vue from "vue";
import Router from "vue-router";
import AirlineCompany from "./views/AirlineCompany.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/airline-company/:id",
      name: "airline-company",
      component: AirlineCompany
    },
  ]
});
