<template>
  <div>
    <v-expansion-panel
      v-model="expansion"
			expand
    >
      <v-expansion-panel-content>
        <div slot='header'><h1>Search hotels</h1></div>
        <search-hotel-form v-on:search-result="showSearchResult($event)"></search-hotel-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>
<script>

import SearchHotelForm from "@/components/Hotel/SearchHotelForm.vue";
// import SearchResult from "@/components/Flights/SearchResult.vue";
// import FilterForm from "@/components/Flights/FilterForm.vue";
// import SearchParams from "@/models/SearchParams.js";

export default {
  name: "SearchFlight",
  components: {
    'search-hotel-form': SearchHotelForm,
    // 'search-result' : SearchResult,
    // 'filter-form': FilterForm,
  },
  data:() => ({
    expansion: [true],
    resultDialog: true,
    searchResult: [],
    filterResult: [],
    zeroResult: false,
  }),
//   methods: {
//     showSearchResult(data) {
//       this.filterResult = data.data;
//       this.searchResult = data.data;
//       this.searchParams.seatClass = data.seatClass;
//       this.searchParams.passengersNumber = data.passengersNumber;
//       this.zeroResult = true;
//     },
//     filter(data) {
//       let result = [];
//       this.searchResult.forEach(flight => {
//         let f1 = flight.departureFlight;
//         let f2 = flight.returnFlight;
//         if (data.stops !== null) {

//           let transfers = f1.transferDestinations.length;
//           if (f2 !== null)
//             transfers += f2.transferDestinations.length;

//           switch(data.stops) {
//             case "Direct": {
//               if (transfers !== 0)
//                 return;
//               break;
//             }
//             case "1": {
//               if (transfers !== 1)
//                 return;
//               break;
//             }
//             case "2+": {
//               if (transfers < 2) 
//                 return;
//               break;
//             }
//           }
//         }
//         let minPrice = data.priceSlider.range[0];
//         let maxPrice = data.priceSlider.range[1];

        
//         switch(this.searchParams.seatClass) {
//           case "First": {
//             let price = f1.ticketPriceFirst;
//             if (f2 !== null)
//               price += f2.ticketPriceFirst;
//             if (!(minPrice <= price && price <= maxPrice))
//               return;
//             break;
//           }
//           case "Business": {
//             let price = f1.ticketPriceBusiness;
//             if (f2 !== null)
//               price += f2.ticketPriceBusiness;
//             if (!(minPrice <= price && price <= maxPrice))
//               return;
//             break;
//           }
//           case "Economy": {
//             let price = f1.ticketPriceEconomy;
//             if (f2 !== null)
//               price += f2.ticketPriceEconomy;
//             if (!(minPrice <= price && price <= maxPrice))
//               return;
//             break;
//           }
//         }
//         let minTime = data.flightDurationSlider.range[0];
//         let maxTime = data.flightDurationSlider.range[1];
//         let time = f1.flightTimeHours;
//         if (f2 !== null)
//           time += f2.flightTimeHours;
//         if (!(minTime <= time && time <= maxTime))
//           return;

//         let r = {
//           "departureFlight": f1,
//           "returnFlight":  f2 ? f2 : null,
//         }
//         result.push(r);
//       });
//       this.filterResult = result;
//     }
//   }
}
</script>
<style>

#containter {
  margin: 0px;
}
</style>

