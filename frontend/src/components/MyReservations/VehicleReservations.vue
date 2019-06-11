<template>
    <div>
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
            <td><v-btn v-if="props.item.completed" color="blue darken" flat @click="rate(props.item.id)">Rate</v-btn></td>
        </template>
        </v-data-table>
        <v-dialog width="300" v-model="ratingDialog">
            <v-card
            class="elevation-16 mx-auto"
            >
            <v-card-title
            class="headline"
            primary-title
            >
            Rate Our Service
            </v-card-title>
            <v-card-text>
            If you enjoyed using our service, please take a few seconds to rate your experience with the company.
            <div class="text-xs-center">
                <v-rating
                v-model="serviceRating"
                background-color="grey darken-1"
                half-increments
                hover
                ></v-rating>
            </div>
            Also, please rate the vehicle you used.
            <div class="text-xs-center">
                <v-rating
                v-model="vehicleRating"
                background-color="grey darken-1"
                half-increments
                hover
                ></v-rating>
            </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions class="justify-space-between">
            <v-btn flat @click="noThanks">No Thanks</v-btn>
            <v-btn
                color="primary"
                flat
                @click="rateService"
            >
                Rate Now
            </v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
</template>

<script>

import VehicleReservationController from "@/controllers/vehicle.reservation.controller.js";
import Rating from "@/models/Rating.js";
import store from '@/store';

export default {
    name: "VehicleReservations",
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
        { text: ' ', value: 'btn' ,sortable: false, },
      ],
      ratingDialog: false,
      serviceRating: 0,
      vehicleRating: 0,
      id: -1,
      rating: new Rating(),
    }),
    beforeMount: function() {
        this.getData();
    },
    methods: {
        getData() {
            VehicleReservationController.get(store.getters.activeUser.id).then((response) => {
              response.data.forEach(element => {
                this.reservations.push(element);
              });
            });
        },
        rateService() {
          this.rating.id = this.id;
          this.rating.service = this.serviceRating;
          this.rating.specific = this.vehicleRating;

          

          VehicleReservationController.rate(this.rating).catch((error) => {
            alert(error.response.data);
          });

          this.ratingDialog = false;
          this.serviceRating = 0;
          this.vehicleRating = 0;
          this.id = -1;

          ///this.getData();

          //this.$forceUpdate();

          //this.forceRerender();

          /*this.reservations = [];

          alert("Rate-ovano");

          VehicleReservationController.get(store.getters.activeUser.id).then((response) => {
            response.data.forEach(element => {
              this.reservations.push(element);
            });
          });*/

        },
        noThanks() {
          this.ratingDialog = false;
          this.serviceRating = 0;
          this.vehicleRating = 0;
          this.id = -1;
        },
        rate(id) {
          this.ratingDialog = true;
          this.id = id;
        }
    }
}
</script>

<style>

</style>
