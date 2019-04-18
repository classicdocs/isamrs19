<template>
  <div>
    <v-container fluid> 
      <!-- FLIGHTS INFO -->
      <v-layout row wrap>
        <v-flex lg6 md6 >
          <v-layout row wrap style="margin:5px" > 
            <v-flex lg12 md12>
              <h3>Departure flight</h3>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <flight
                v-bind:data="flightReservation.flights.departureFlight"
                v-bind:searchParams="flightReservation.searchParams"
              ></flight>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-flex lg6 md6>
          <v-layout row wrap style="margin:5px" v-if="flightReservation.flights.returnFlight !== null">
             <v-flex  lg12 md12>
              <h3>Return flight</h3>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12 >
              <flight
                v-bind:data="flightReservation.flights.returnFlight"
                v-bind:searchParams="flightReservation.searchParams"
              ></flight>
            </v-flex>
          </v-layout>
        </v-flex>

        <!-- RESERVATION -->
        <v-layout row wrap justify-center>
          <v-flex lg12 md12>
           <v-stepper v-model="stepper">
            <v-stepper-header>
              <v-stepper-step :complete="stepper > 1" step="1">Pick a seats</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step :complete="stepper > 2" step="2">Invite friends</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step :complete="stepper > 3" step="3">Fill passengers information</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step step="4">Review</v-stepper-step>
            </v-stepper-header>

            <v-stepper-items>
              <v-stepper-content step="1">
                <seats
                  v-bind:seatsDeparture="getDepartureSeats"
                  v-bind:seatsReturn="getReturnSeats"
                ></seats>

                <v-btn
                  color="primary"
                  @click="stepper = 2"
                >
                  Next
                </v-btn>
              </v-stepper-content>

              <v-stepper-content step="2">
                <v-card
                  class="mb-5"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn
                  color="primary"
                  @click="stepper = 3"
                >
                  Next
                </v-btn>

                <v-btn flat
                  @click="stepper = 1"
                >Back</v-btn>
              </v-stepper-content>

              <v-stepper-content step="3">
                <v-card
                  class="mb-5"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn
                  color="primary"
                  @click="stepper = 4"
                >
                  Next
                </v-btn>

                <v-btn flat
                  @click="stepper = 2"
                >Back</v-btn>
              </v-stepper-content>
              <v-stepper-content step="4">
                <v-card
                  class="mb-5"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn
                  color="primary"
                >
                  Reserve
                </v-btn>

                <v-btn flat
                  @click="stepper = 3"
                >Back</v-btn>
              </v-stepper-content>

            </v-stepper-items>
          </v-stepper>
          </v-flex>
        </v-layout>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

import store from '@/store';
import FlightInfo from "@/components/Flights/FlightInfo.vue";
import Seats from "@/components/FlightReservation/Seats.vue";

export default {
  name: "FlightReservation",
  components: {
    'flight': FlightInfo,
    'seats':  Seats,
  },
  data:() => ({
    flightReservation: null,
    stepper: 0,
  }),
  computed: {
    getDepartureSeats() {
      let flight = this.flightReservation.flights.departureFlight;
      switch(this.flightReservation.searchParams.seatClass) {
        case "First": {
          return flight.seatsFirst;
        }
        case "Business": {
          return flight.seatsBusiness;
        }
        case "Economy":  {
          return flight.seatsEconomy;
        }
      }
    },
    getReturnSeats() {
      if (this.flightReservation.flights.returnFlight === null)
        return null;

      let flight = this.flightReservation.flights.returnFlight;
      switch(this.flightReservation.searchParams.seatClass) {
        case "First": {
          return flight.seatsFirst;
        }
        case "Business": {
          return flight.seatsBusiness;
        }
        case "Economy":  {
          return flight.seatsEconomy;
        }
      }
    }
  },
  created() {
    console.log(store.getters.flightReservation);
    let fr = store.getters.flightReservation;
    if (fr)
      this.flightReservation = fr;
    else
      this.$router.push({name: "home"});
  },
}
</script>

<style>

</style>
