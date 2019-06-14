<template>
  <v-dialog
    v-model="dialog"
    max-width="600px"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn color="primary" dark v-on="on">Reserve</v-btn>
  </template>
  <v-card>
      
    <v-card-title primary-title>
     <span class="headline">Fill your passport information</span>
    </v-card-title>
    <v-card-text>
      <v-form
      ref="form"
      v-model="form"
      >
        
      <v-text-field
        label="Passport"
        v-model="passport"
        required
        type="number"
        :rules="[v => !!v || 'Passport is required']"
      ></v-text-field>
      </v-form>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
      <v-btn :disabled="!form" color="success" @click="reserve">Submit</v-btn>  
    </v-card-actions>
  </v-card>
    
  </v-dialog>
</template>

<script>
import FlightController from "@/controllers/flights.controller";
import UserController from "@/controllers/user.controller";

import store from "@/store";

export default {
  name: "ReserveFlightWithDiscount",
  props: ["flight"],
  data: () => ({
    dialog: false,
    myInfo: null,
    form: false,
    passport: ""
  }),
  beforeMount() {
    this.getMyInfo();
  },
  methods: {
    reserve() {
      if (this.$refs.form.validate()) {
        this.myInfo.passport = this.passport;
        let data = {
          "myInfo" : this.myInfo,
          "flights" : {
            "departureFlight": this.flight.flight
          },
          "seatsPickedDeparture": [this.flight.seat],
          "passengers": [],
          "price": this.flight.price
        }
        
        let loader = this.$loading.show()
        FlightController.reserve(data)
          .then((response) => {
            loader.hide();
            this.$emit("reservedFlight", response.data);
            store.commit("setSnack", {msg: "You have successfully booked flight on discount", color: "success"});
            this.dialog = false;
          })
          .catch((error) => {
            loader.hide();
            this.dialog = false;;
          })
      }
      
    },
     getMyInfo(passport) {
      UserController.getUser(store.getters.activeUser.id)
      .then((response) => {
        this.myInfo = response.data;
      })
    },
  }
}
</script>
