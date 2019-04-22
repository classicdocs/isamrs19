<template>
  <div>
    <hotels-overview></hotels-overview>
  </div>
</template>

<script>

import HotelsOverview from "@/components/Hotel/HotelsOverview.vue";

export default {
  name: "Hotels",
  components: {
    'hotels-overview': HotelsOverview,
  }
}
</script>

<style>

</style>
