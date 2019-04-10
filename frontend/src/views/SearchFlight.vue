<template>
  <div>
    <v-expansion-panel
      v-model="expansion"
			expand
    >
      <v-expansion-panel-content>
        <div slot='header'><h1>Search a flight</h1></div>
        <search-form v-on:search-result="showSearchResult($event)"></search-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
    <v-container grid-list-xl>
      <v-layout row wrap >
        <v-flex lg4 md4 sm6 xs12 v-if="zeroResult" >
          <filter-form v-on:filter="filter($event)"></filter-form>
        </v-flex>
        <v-flex lg8 md8 sm6 xs12 v-if="filterResult.length !== 0">
          <search-result v-for="(item, index) in filterResult"
            :key="index" 
            v-bind:data="item" 
            v-bind:searchParams="searchParams"
            >
          </search-result>
        </v-flex>
        <v-flex v-else-if="zeroResult" lg8 md8 sm6 xs12>
          <h3>No result found!</h3>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

import SearchForm from "@/components/Flights/SearchForm.vue";
import SearchResult from "@/components/Flights/SearchResult.vue";
import FilterForm from "@/components/Flights/FilterForm.vue";

export default {
  name: "SearchFlight",
  components: {
    'search-form': SearchForm,
    'search-result' : SearchResult,
    'filter-form': FilterForm,
  },
  data:() => ({
    expansion: [true],
    resultDialog: true,
    searchResult: [],
    filterResult: [],
    searchParams: null,
    zeroResult: false,
  }),
  methods: {
    showSearchResult(data) {
      this.filterResult = data.data;
      this.searchResult = data.data;
      this.searchParams = {
        'seatClass': data.seatClass,
        'passengersNumber': data.passengersNumber
      }
      this.zeroResult = true;
    },
    filter(data) {
      console.log(this.filterResult);
      let result = [];
      this.searchResult.forEach(flight => {
        let f1 = flight.departureFlight;
        let f2 = flight.returnFlight;
        if (f2 === null) {
          if (data.stops !== null) {
            switch(data.stops) {
              case "Direct": {
                if (f1.transferDestinations.length !== 0) {
                  return;
                }
                break;
              }
              case "1": {
                if (f1.transferDestinations.length !== 1) 
                  return;
                break;
              }
              case "2+": {
                if (f1.transferDestinations.length < 2) 
                  return;
                break;
              }
            }
          }
          console.log("CENAA");
          let minPrice = data.priceSlider.range[0];
          let maxPrice = data.priceSlider.range[1];
          console.log(minPrice);
          console.log(maxPrice);
          console.log(f1.ticketPriceFirst);
          console.log(this.searchParams.seatClass);
          switch(this.searchParams.seatClass) {
            case "First": {
              if (!(minPrice <= f1.ticketPriceFirst && f1.ticketPriceFirst <= maxPrice))
                return;
              break;
            }
            case "Business": {
              if (!(minPrice <= f1.ticketPriceBusiness && f1.ticketPriceBusiness <= maxPrice))
                return;
              break;
            }
            case "Economy": {
              if (!(minPrice <= f1.ticketPriceEconomy && f1.ticketPriceEconomy <= maxPrice))
                return;
              break;
            }
          }
          let minTime = data.flightDurationSlider.range[0];
          let maxTime = data.flightDurationSlider.range[1];
          console.log(minTime);
          console.log(maxTime);
          console.log(f1.flightTimeHours);
          console.log(!(minTime <= f1.flightTimeHours && f1.flightTimeHours <= maxTime));
          if (!(minTime <= f1.flightTimeHours && f1.flightTimeHours <= maxTime))
            return;
          console.log("KRAJ");
          let r = {
            "departureFlight": f1,
            "returnFlight": null,
          }
          result.push(r);
        }
        
      });
      this.filterResult = result;
      console.log(this.filterResult);
    }
  }
}
</script>
<style>

</style>

