<template>
  <div>
    <search-hotel></search-hotel>
    <hotels-overview></hotels-overview>
  </div>
</template>

<script>
import SearchHotel from "@/components/Hotel/SearchHotel.vue";
import HotelsOverview from "@/components/Hotel/HotelsOverview.vue";

export default {
  name: "Hotels",
  components: {
    "hotels-overview": HotelsOverview,
    "search-hotel": SearchHotel
  }
};
</script>

<style></style>
