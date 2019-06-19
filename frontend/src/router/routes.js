import Vue from "vue";
import Router from "vue-router";
import AirlineCompany from "../views/AirlineCompany.vue";
import SystemAdmin from "../views/SystemAdmin.vue";
import Registration from "../views/Registration.vue";
import Login from "../views/Login.vue";
import FlightReservation from "../views/FlightReservation.vue";
import Profil from "../views/Profil.vue";
import Flights from "../views/Flights.vue";
import Hotels from "../views/Hotels.vue";
import RentACars from "../views/RentACars.vue";
import Logout from "../views/Logout.vue";
import Friends from "../views/Friends.vue";
import ChangePassword from "../views/ChangePassword.vue";
import VehicleReservation from "../components/RentACar/VehicleReservation.vue";
import MyReservations from "../views/MyReservations.vue";

import store from "@/store";

export const routes = [
  {
    path: "*",
    redirect() {
      if (store.getters.isHotelAdmin) {
        return "/hotel-service/" + store.getters.activeUser.idAdminOf;
      }
      if (store.getters.isAirlineAdmin) {
        return "/airline-company/" + store.getters.activeUser.idAdminOf;
      }
      if (store.getters.isRentacarAdmin) {
        return "/rentacar-service/" + store.getters.activeUser.idAdminOf;
      }
      if (store.getters.isSysAdmin) {
        return "/sys-admin";
      }

      return "/flights";
    }
  },
  {
    path: "/airline-company/:id",
    name: "airline-company",
    component: AirlineCompany,
    meta: {
      guest: true,
      user: true,
      airlineAdmin: true
    },
    props: true
  },
  {
    path: "/rentacar-service/:id",
    name: "rentacar-service",
    component: () => import("../views/RentACarService.vue"),
    meta: {
      guest: true,
      user: true,
      rentacarAdmin: true
    }
  },
  {
    path: "/hotel-service/:id",
    name: "hotel-service",
    component: () => import("../views/HotelService.vue"),
    meta: {
      guest: true,
      user: true,
      hotelAdmin: true
    }
  },
  {
    path: "/sys-admin",
    name: "sys-admin",
    component: SystemAdmin,
    meta: {
      sysadmin: true
    }
  },
  {
    path: "/flight-reservation",
    name: "flight-reservation",
    component: FlightReservation,
    meta: {
      user: true
    }
  },
  {
    path: "/registration",
    name: "registration",
    component: Registration,
    meta: {
      guest: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: Login,
    meta: {
      guest: true
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: Logout,
    meta: {
      logged: true
    }
  },
  {
    path: "/users/:id/profil",
    name: "user-profil",
    component: Profil,
    meta: {
      logged: true
    }
  },
  {
    path: "/flights",
    name: "flights",
    component: Flights,
    meta: {
      guest: true,
      user: true
    }
  },
  {
    path: "/hotels",
    name: "hotels",
    component: Hotels,
    meta: {
      guest: true,
      user: true
    }
  },
  {
    path: "/rent-a-cars",
    name: "rent-a-cars",
    component: RentACars,
    meta: {
      guest: true,
      user: true
    }
  },
  {
    path: "/friends",
    name: "friends",
    component: Friends,
    meta: {
      user: true
    }
  },
  {
    path: "/change-password",
    name: "change-password",
    component: ChangePassword,
    meta: {
      admin: true
    }
  },
  {
    path: "/vehicle-reservation",
    name: "vehicle-reservation",
    component: VehicleReservation,
    meta: {
      user: true
    }
  },
  {
    path: "/:id/my-reservations/",
    name: "my-reservations",
    component: MyReservations,
    meta: {
      user: true
    }
  }
];
