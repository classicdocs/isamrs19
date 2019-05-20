import Vue from "vue";
import Router from "vue-router";
import AirlineCompany from "../views/AirlineCompany.vue";
import SystemAdmin from "../views/SystemAdmin.vue";
import Registration from "../views/Registration.vue"
import Login from "../views/Login.vue"
import FlightReservation from "../views/FlightReservation.vue";
import Profil from "../views/Profil.vue";
import Flights from "../views/Flights.vue";
import Hotels from "../views/Hotels.vue";
import RentACars from "../views/RentACars.vue";
import Logout from "../views/Logout.vue";
import Friends from "../views/Friends.vue";
import ChangePassword from "../views/ChangePassword.vue";
import MyReservations from "../views/MyReservations.vue";

export const routes = [
    {
      path: "*",
      redirect: '/flights'
    },
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
      path: "/hotel-service/:id",
      name: "hotel-service",
      component: () => import("../views/HotelService.vue"),
      meta: {
        hoteladmin: true,
      }
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
      name: "registration",
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
      path: "/logout",
      name: "logout",
      component: Logout,
    },
    {
      path: "/users/:id/profil",
      name: "user-profil",
      component: Profil,
      meta: {
        guest: true,
      },
    },
    {
      path: "/flights",
      name: "flights",
      component: Flights,
    },
    {
      path: "/hotels",
      name: "hotels",
      component: Hotels,
    },
    {
      path: "/rent-a-cars",
      name: "rent-a-cars",
      component: RentACars,
    },
    {
      path: "/friends",
      name: "friends",
      component: Friends,
    },
    {
      path: "/change-password",
      name: "change-password",
      component: ChangePassword
    },
    {
      path: "/my-reservations/:id",
      name: "my-reservations",
      component: MyReservations
    }
  ];
