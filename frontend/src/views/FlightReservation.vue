<template>
  <div>
    <p>Flight Reservation</p>
  </div>
</template>

<script>

import store from '@/store';

export default {
  name: "FlightReservation",
  data:() => ({
    fr: null,
  }),
  created:() => {
      console.log(store.getters.flightReservation);
  }
}
</script>

<style>

</style>
