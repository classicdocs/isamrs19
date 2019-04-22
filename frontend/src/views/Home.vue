<template>
  <div>
    <p>Home</p>
    <search-flight></search-flight>
    <hotels-overview></hotels-overview>
  </div>
</template>

<script>

import SearchFlight from "@/views/SearchFlight.vue";
import HotelsOverview from "@/views/HotelsOverview.vue";

export default {
  name: "Home",
  components: {
    'search-flight': SearchFlight,
    'hotels-overview': HotelsOverview
  }
};
</script>
