import Vue from "vue";
import Router from "vue-router";
import AirlineCompany from "../views/AirlineCompany.vue";
import Home from "../views/Home.vue";
import SystemAdmin from "../views/SystemAdmin.vue";
import Registration from "../views/Registration.vue"
import Login from "../views/Login.vue"
import FlightReservation from "../views/FlightReservation.vue";
import Profil from "../views/Profil.vue";

export const routes = [
    {
      path: "/airline-company/:id",
      name: "airline-company",
      component: AirlineCompany,
      meta: {
        airlineadmin: true,
      }
    },
    {
      path: "/rentacar-service/:id",
      name: "rentacar-service",
      component: () => import("../views/RentACarService.vue"),
      meta: {
        rentacaradmin: true,
      }
    },
    {
      path: "/home",
      name: "home",
      component: Home,
      meta: {
        logged: true,
      },
    },
    {
      path: "/sys-admin",
      name: "sys-admin",
      component: SystemAdmin,
      meta: {
        sysadmin: true,
      }
    },
    {
      path: "/flight-reservation",
      name: "flight-reservation",
      component: FlightReservation
    },
    {
      path: "/registration",
      name: "/registration",
      component: Registration,
      meta: {
        guest: true,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        guest: true,
      },
    },
    {
      path: "/users/:id/profil",
      name: "user-profil",
      component: Profil,
    }
  ];
