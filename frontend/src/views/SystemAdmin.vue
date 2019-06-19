<template>
  <div>
    <v-container grid-list-md text-xs-center>
      <v-btn color="info" @click="openHotelDialog">
        <v-icon left>hotel</v-icon>
        Add new hotel
      </v-btn>

      <v-btn color="info" @click="openRentACarDialog">
        <v-icon left>directions_car</v-icon>
        Add new rent-a-car
      </v-btn>

      <v-btn color="info" @click="openAirlineCompanyDialog">
        <v-icon left>flight_takeoff</v-icon>
        Add new airline company
      </v-btn>

      <v-dialog v-model="addHotelDialog" max-width="500px">
        <add-hotel-form
          v-if="addHotelDialog"
          v-on:finished="closeAddHotel($event)"
          v-on:cancel="cancel"
        >
        </add-hotel-form>
      </v-dialog>

      <v-dialog v-model="addRentACarDialog" max-width="500px">
        <add-rentacar-form
          v-on:finished="closeAddRentACar($event)"
          v-on:cancel="cancel"
        >
        </add-rentacar-form>
      </v-dialog>

      <v-dialog v-model="addAirlineDialog" max-width="500px">
        <add-airline-form
          v-if="addAirlineDialog"
          v-on:finished="closeAddAirline($event)"
          v-on:cancel="cancel"
        >
        </add-airline-form>
      </v-dialog>
    </v-container>
    <v-container grid-list-md text-xs-center>
      <v-btn color="info" @click="openAdminDialog">
        <v-icon left>accessibility_new</v-icon>
        Add admin
      </v-btn>

      <v-dialog v-model="addAdminDialog" max-width="500px" persistent>
        <add-admin-form
          v-if="addAdminDialog"
          v-on:finished="closeAddAdmin($event)"
          v-on:cancel="cancel"
          ><!--v-bind:hotels="hotels" v-bind:rentACars="rentACars" v-bind:airlines="airlines" -->
        </add-admin-form>
      </v-dialog>
    </v-container>

    <v-snackbar
      v-model="snackbar.show"
      :timeout="5000"
      :color="snackbar.color"
      :top="true"
    >
      {{ snackbar.msg }}
      <v-btn dark flat @click="snackbar.show = false">
        Close
      </v-btn>
    </v-snackbar>
  </div>
</template>

<script>
import AddHotelForm from "@/components/SystemAdmin/AddHotelForm.vue";
import AddRentACarForm from "@/components/SystemAdmin/AddRentACarForm.vue";
import AddAirlineForm from "@/components/SystemAdmin/AddAirlineForm.vue";
import AddAdminForm from "../components/SystemAdmin/AddAdminForm.vue";

import HotelController from "@/controllers/hotels.controller";
import AirlineController from "@/controllers/airline-company.controller";
import RentACarController from "@/controllers/rentacar.controller";

import store from "@/store";

export default {
  name: "sys-admin",
  components: {
    "add-hotel-form": AddHotelForm,
    "add-rentacar-form": AddRentACarForm,
    "add-airline-form": AddAirlineForm,
    "add-admin-form": AddAdminForm
  },
  data: () => ({
    addHotelDialog: false,
    addRentACarDialog: false,
    addAirlineDialog: false,
    addAdminDialog: false,

    // hotels: [],
    // airlines: [],
    // rentACars: [],

    snackbar: {
      show: false,
      color: "",
      msg: ""
    }
  }),
  created() {
    HotelController.getHotels().then(response => {
      // response.data.forEach(element => {
      //   this.hotels.push(element.name);
      // });
      store.commit("allHotels", response.data);
    });
    AirlineController.findAllAirlines().then(response => {
      // response.data.forEach(element => {
      //   this.airlines.push(element.name);
      // });
      store.commit("allAirlines", response.data);
    });
    RentACarController.getAll().then(response => {
      // response.data.forEach(element => {
      //   this.rentACars.push(element.name);
      // });
      store.commit("allRentACars", response.data);
    });
  },
  methods: {
    closeAddHotel(data) {
      this.addHotelDialog = false;
      this.showSnackbar(data);
    },
    closeAddRentACar(data) {
      this.addRentACarDialog = false;
      this.showSnackbar(data);
    },
    closeAddAirline(data) {
      this.addAirlineDialog = false;
      this.showSnackbar(data);
    },
    closeAddRentACar(data) {
      this.addRentACarDialog = false;
      this.showSnackbar(data);
    },
    closeAddAdmin(data) {
      this.addAdminDialog = false;
      this.showSnackbar(data);
    },
    cancel() {
      this.addHotelDialog = false;
      this.addRentACarDialog = false;
      this.addAirlineDialog = false;
      this.addAdminDialog = false;
    },
    openHotelDialog() {
      this.addRentACarDialog = false;
      this.addAirlineDialog = false;
      this.addHotelDialog = true;
    },
    openRentACarDialog() {
      this.addHotelDialog = false;
      this.addAirlineDialog = false;
      this.addRentACarDialog = true;
    },
    openAirlineCompanyDialog() {
      this.addHotelDialog = false;
      this.addRentACarDialog = false;
      this.addAirlineDialog = true;
    },
    openAdminDialog() {
      this.addHotelDialog = false;
      this.addRentACarDialog = false;
      this.addAirlineDialog = false;
      this.addAdminDialog = true;
    },
    showSnackbar(obj) {
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    }
  }
};
</script>
