<template>
  <div>
    <v-container fluid>
      <v-layout row wrap align-center>
        <v-flex lg9 md9 sm12 xs12>
           <v-layout row wrap>
             <v-flex lg12 md12 sm12 xs12 >
               <flight-info
                v-bind:data="data.departureFlight"
                v-bind:searchParams="searchParams"
                v-on:price="setPriceDeparture($event)"
               ></flight-info>
             </v-flex>
             <v-flex lg12 md12 sm12 xs12 v-if="data.returnFlight != null" >
               <flight-info
                v-bind:data="data.returnFlight"
                v-bind:searchParams="searchParams"
                v-on:price="setPriceReturn($event)"
               ></flight-info>
             </v-flex>
           </v-layout>
        </v-flex>
        <v-flex lg1 md1 sm12 xs12>
          <v-btn @click="goToReservationView" color="primary">{{getPrice * searchParams.passengersNumber}} EUR</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Flight from "@/models/Flight.js";
import FlightReservation from "@/models/FlightReservation.js";
import FlightInfo from "@/components/Flights/FlightInfo.vue";
import store from '@/store';

export default {
  name: 'SearchResult',
  components: {
    'flight-info': FlightInfo,
  },
  props: ['data', 'searchParams'],
  data:() => ({
    priceDeparture: 0,
    priceReturn: 0,
  }),
  computed: {
    getPrice() {
      return this.priceDeparture  + this.priceReturn;
    }
  },
  methods: {
    goToReservationView() {
      let f = new FlightReservation();
      f.flights = this.data;
      f.searchParams = this.searchParams;
      f.price = this.getPrice;
      console.log(f);
      store.commit('flightReservation', f);
      console.log(store.getters.flightReservation);
      this.$router.push({name: "flight-reservation"});
    },
    setPriceDeparture(price) {
      this.priceDeparture = 0;
      this.priceDeparture += price;
    },
    setPriceReturn(price) {
      this.priceReturn = 0;
      this.priceReturn += price;
    },
  }
};
</script>

<style>

</style>
