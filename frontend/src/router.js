import Vue from "vue";
import Router from "vue-router";
import AirlineCompany from "./views/AirlineCompany.vue";
import SystemAdmin from "./views/SystemAdmin.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/airline-company/:id",
      name: "airline-company",
      component: AirlineCompany
    },
    {
      path: "/sys-admin",
      name: "sys-admin",
      component: SystemAdmin
    },
    {
      path: "/rentacar-service/:id",
      name: "rentacar-service",
      component: () => import("./views/RentACarService.vue")
    }
  ]
});
