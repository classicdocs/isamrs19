<template>
  <div>
    <v-dialog
      v-model="dialog"
      persistent
    >
    <template v-slot:activator="{ on }">
      <v-btn color="primary" dark v-on="on">Manage flights discount</v-btn>
    </template>
    <v-card>
       <v-card-title primary-title>
        <span class="headline">Manage flights discount</span>
      </v-card-title>
      <v-card-text>
        <div>Select class, then choose a seat and enter a discount</div><br>
         <v-expansion-panel>
            <v-expansion-panel-content >
              <template v-slot:header>
                <h3>First class</h3>
              </template>
              
              <seats v-bind:seatsDeparture="flight.seatsFirst" v-bind:seatsReturn="null" v-bind:passengers="100" v-bind:discount="true"></seats>
              <v-text-field box type="number" label="Enter discount for first class in percent" v-model="discountFirst"></v-text-field>
            </v-expansion-panel-content>
            <v-expansion-panel-content>
              <template v-slot:header>
                <h3>Business class</h3>
              </template>
              <seats v-bind:seatsDeparture="flight.seatsBusiness" v-bind:seatsReturn="null" v-bind:passengers="100" v-bind:discount="true"></seats>
              <v-text-field box type="number" label="Enter discount for business class in percent" v-model="discountBusiness"></v-text-field>
            </v-expansion-panel-content>
            <v-expansion-panel-content>
              <template v-slot:header>
                <h3>Economy class</h3>
              </template>
              <seats v-bind:seatsDeparture="flight.seatsEconomy" v-bind:seatsReturn="null" v-bind:passengers="100" v-bind:discount="true"></seats>
              <v-text-field box type="number"  label="Enter discount for economy class in percent" v-model="discountEconomy"></v-text-field>
            </v-expansion-panel-content>
          </v-expansion-panel>
        
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken" flat @click="close">Close</v-btn> 
        <v-btn color="primary" style="float:right" @click="submit" :disabled="isSubmitDisabled">Submit</v-btn>
      </v-card-actions>
    </v-card>
    </v-dialog>
  </div>    
</template>

<script>

import store from "@/store";
import SeatsVue from '../FlightReservation/Seats.vue';
import FlightContoller from "@/controllers/flights.controller";

export default {
  name: "FlightDiscount",
  props: ["flight"],
  components: {
    'seats' : SeatsVue
  },
  data:() => ({
    dialog: false,
    discountFirst: 0,
    discountBusiness: 0,
    discountEconomy: 0,
  }),
  computed: {
    isSubmitDisabled() {
      let seats = store.getters.seats;
      if (seats.length != 0)
        return false;
      return true;
    }
  },
  methods: {
    submit() {
      let data = {
        "discountFirst": this.discountFirst,
        "discountBusiness": this.discountBusiness,
        "discountEconomy": this.discountEconomy,
        "seats" : store.getters.seats,
        "flight": this.flight.id,
        "airlineCompany": this.$route.params.id
      }
      FlightContoller.addDiscount(data)
        .then((response) => {
          this.close();
          store.commit("setSnack", {msg: "You have successfully add discount", color:"success"});
        })
        .catch((error) => {
          console.log(error.response.data);
        })
    },
    close() {
      
      this.dialog = false;
    }
  }

};
</script>
