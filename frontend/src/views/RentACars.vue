<template>
  <div>
    <rentacar-overview></rentacar-overview>
  </div>
</template>
<script>
import RentACarOverview from "@/components/RentACar/RentACarOverview.vue";

export default {
  name: "RentACars",
  components: {
    "rentacar-overview": RentACarOverview
  }
};
</script>

<style></style>
