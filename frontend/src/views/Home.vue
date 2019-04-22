<template>
  <div>
    <p>Home</p>
    <search-flight></search-flight>
    <hotels-overview></hotels-overview>
    <airline-overview></airline-overview>
    <rentacar-overview></rentacar-overview>
  </div>
</template>

<script>

import SearchFlight from "@/views/SearchFlight.vue";
import HotelsOverview from "@/views/HotelsOverview.vue";
import AirlineOverview from "@/views/AirlineOverview.vue";
import RentACarOverview from "@/views/RentACarOverview.vue";

export default {
  name: "Home",
  components: {
    'search-flight': SearchFlight,
    'hotels-overview': HotelsOverview,
    'airline-overview': AirlineOverview,
    'rentacar-overview': RentACarOverview
  }
};
</script>
