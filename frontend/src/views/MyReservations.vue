<template>
  <div>
    <flight-reservations></flight-reservations>
  </div>
</template>

<script>
import FlightReservationsVue from '../components/MyReservations/FlightReservations.vue';

export default {
  name: "MyReservations",
  components: {
    'flight-reservations' : FlightReservationsVue
  }
}
</script>

<style>

</style>
