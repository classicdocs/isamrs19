<template>
  <div>
    <p>Home</p>
    <search-flight></search-flight>
  </div>
</template>

<script>

import SearchFlight from "@/views/SearchFlight.vue";

export default {
  name: "Home",
  components: {
    'search-flight': SearchFlight
  }
};
</script>
