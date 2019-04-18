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
    <v-container grid-list-xl id="container" fluid>
      <v-layout row wrap >
        <v-flex lg4 md4 sm12 xs12 v-if="searchResult.length !== 0" >
          <filter-form v-on:filter="filter($event)"></filter-form>
        </v-flex>
      <v-flex lg8 md8 sm12 xs12 v-if="filterResult.length !== 0">
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
import SearchParams from "@/models/SearchParams.js";

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
    searchParams: new SearchParams(),
    zeroResult: false,
  }),
  methods: {
    showSearchResult(data) {
      this.filterResult = data.data;
      this.searchResult = data.data;
      this.searchParams.seatClass = data.seatClass;
      this.searchParams.passengersNumber = data.passengersNumber;
      this.zeroResult = true;
    },
    filter(data) {
      console.log(this.filterResult);
      let result = [];
      this.searchResult.forEach(flight => {
        let f1 = flight.departureFlight;
        let f2 = flight.returnFlight;
        if (data.stops !== null) {

          let transfers = f1.transferDestinations.length;
          if (f2 !== null)
            transfers += f2.transferDestinations.length;

          switch(data.stops) {
            case "Direct": {
              if (transfers !== 0)
                return;
              break;
            }
            case "1": {
              if (transfers !== 1)
                return;
              break;
            }
            case "2+": {
              if (transfers < 2) 
                return;
              break;
            }
          }
        }
        console.log("transfer");
        let minPrice = data.priceSlider.range[0];
        let maxPrice = data.priceSlider.range[1];

        
        switch(this.searchParams.seatClass) {
          case "First": {
            let price = f1.ticketPriceFirst;
            if (f2 !== null)
              price += f2.ticketPriceFirst;
            if (!(minPrice <= price && price <= maxPrice))
              return;
            break;
          }
          case "Business": {
            let price = f1.ticketPriceBusiness;
            if (f2 !== null)
              price += f2.ticketPriceBusiness;
            if (!(minPrice <= price && price <= maxPrice))
              return;
            break;
          }
          case "Economy": {
            let price = f1.ticketPriceEconomy;
            if (f2 !== null)
              price += f2.ticketPriceEconomy;
            if (!(minPrice <= price && price <= maxPrice))
              return;
            break;
          }
        }
        console.log("price");
        let minTime = data.flightDurationSlider.range[0];
        let maxTime = data.flightDurationSlider.range[1];
        let time = f1.flightTimeHours;
        if (f2 !== null)
          time += f2.flightTimeHours;
        if (!(minTime <= time && time <= maxTime))
          return;

        console.log("time");
        let r = {
          "departureFlight": f1,
          "returnFlight":  f2 ? f2 : null,
        }
        result.push(r);
      });
      this.filterResult = result;
    }
  }
}
</script>
<style>

#containter {
  margin: 0px;
}
</style>

