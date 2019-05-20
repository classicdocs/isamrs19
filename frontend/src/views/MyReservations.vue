<template>
    <div>
        <!--<v-list two-line>
        <v-list-tile v-for="reservation in reservations" v-bind:key="reservation">
        <v-list-tile-content>
            <v-layout>
            <v-flex>
              Reserved from: {{ reservation.reservedFrom | moment("dddd, MMMM Do YYYY, h:mm:ss a")}} <br/>
            </v-flex>
            </v-layout>
        </v-list-tile-content>
        </v-list-tile>
        </v-list>-->
        <v-data-table
        :items="reservations"
        :headers="headers"
        class="elevation-1"
        >
        <template v-slot:items="props">
            <td>{{ props.item.vehicle.vehicleManufacturer}} {{ props.item.vehicle.vehicleModel }} </td>
            <td>{{ props.item.vehicle.vehicleType}} </td>
            <td>{{ props.item.reservedFrom | moment("dddd, MMMM Do YYYY, HH:mm")}} </td>
            <td>{{ props.item.reservedUntil | moment("dddd, MMMM Do YYYY, HH:mm")}} </td>
            <td>{{ props.item.vehicle.numberOfPassengers}} </td>
            <td>{{ props.item.totalPrice }} &euro; </td>
            <td>{{ props.item.rentACar.name  }} </td>
            <td>{{ props.item.pickupLocation }} </td>
            <td>{{ props.item.returnLocation }} </td>
        </template>
        </v-data-table>
    </div>
</template>

<script>

import VehicleReservationController from "@/controllers/vehicle.reservation.controller.js";

export default {
    name: "MyReservations",
    data: () => ({
        reservations: [],
        headers: [
        {
          text: 'Car',
          align: 'left',
          sortable: false,
          value: 'car'
        },
        { text: 'Car type', value: 'type',sortable: false, },
        { text: 'Reservation start', value: 'start',sortable: false, },
        { text: 'Reservation end', value: 'end' ,sortable: false, },
        { text: 'Passenger spaces', value: 'passengers',sortable: false, },
        { text: 'Reservation price', value: 'price' ,sortable: false, },
        { text: 'Rent A Car company', value: 'company' ,sortable: false, },
        { text: 'Pick up location', value: 'pickup' ,sortable: false, },
        { text: 'Return location', value: 'return' ,sortable: false, },
      ],
    }),
    beforeMount() {
        VehicleReservationController.get(this.$route.params.id).then((response) => {
        response.data.forEach(element => {
          this.reservations.push(element);
        });
      });
    }
}
</script>

<style>

</style>

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
