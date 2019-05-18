<template>
  <div>
    <v-expansion-panel >
      <v-expansion-panel-content>
        <div slot="header"><h2>Flight Reservations</h2></div>
        <flight-reservations></flight-reservations>
        
      </v-expansion-panel-content>
      <v-expansion-panel-content>
        <div slot="header"><h2>Flight invitations</h2></div>
        <flight-invitations></flight-invitations>
        
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>

<script>
import FlightReservationsVue from '../components/MyReservations/FlightReservations.vue';
import FlightInvitationsVue from '../components/MyReservations/FlightInvitations.vue';

export default {
  name: "MyReservations",
  components: {
    'flight-reservations' : FlightReservationsVue,
    'flight-invitations': FlightInvitationsVue
  }
}
</script>

<style>

</style>
