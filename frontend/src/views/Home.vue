<template>
  <div>
    <p>Home</p>
    <search-flight></search-flight>
    <hotels-overview></hotels-overview>
    <airline-overview></airline-overview>
  </div>
</template>

<script>

import SearchFlight from "@/views/SearchFlight.vue";
import HotelsOverview from "@/views/HotelsOverview.vue";
import AirlineOverview from "@/views/AirlineOverview.vue";

export default {
  name: "Home",
  components: {
    'search-flight': SearchFlight,
    'hotels-overview': HotelsOverview,
    'airline-overview': AirlineOverview
  }
};
</script>
