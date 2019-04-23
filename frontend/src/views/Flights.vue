<template>
  <div>
    <search-flight></search-flight>
    <airline-overview></airline-overview>
  </div>
</template>

<script>

import SearchFlight from "@/components/Flights/SearchFlight.vue";
import AirlineOverview from "@/components/AirlineCompany/AirlineOverview.vue";

export default {
  name: "Flights",
  components: {
    'search-flight': SearchFlight,
    'airline-overview': AirlineOverview,
  }
}
</script>

<style>

</style>
