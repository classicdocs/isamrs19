<template>
  <div>
    <div v-for="(reservation, index) in hotelReservations" :key="index">
      <div>
        <v-data-iterator
          :items="reservation.rooms"
          :pagination.sync="pagination"
          content-tag="v-layout"
          row
          wrap
          hide-actions
        >
          <template v-slot:header>
            <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
              <v-toolbar-title
                ><h3>
                  Reservation in hotel {{ reservation.hotel.name }}. From
                  {{ reservation.checkInDate }} to
                  {{ reservation.checkOutDate }}
                </h3></v-toolbar-title
              >
            </v-toolbar>
          </template>
          <template v-slot:item="props">
            <v-flex xs12 sm6 md4 lg3>
              <v-card>
                <v-card-title
                  ><h4 v-if="props.item.numberOfBeds > 1">
                    {{ props.item.numberOfBeds }} beds
                  </h4>
                  <h4 v-else>{{ props.item.numberOfBeds }} bed</h4>
                  <v-icon large right> airline_seat_individual_suite</v-icon>
                  <v-btn
                    v-if="reservation.completed"
                    color="blue darken"
                    flat
                    @click="rate(reservation.id)"
                    >Rate</v-btn
                  >
                </v-card-title>
                <v-divider></v-divider>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>Floor:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{
                      props.item.hotelFloor.level
                    }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Room number:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{
                      props.item.roomNumber
                    }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Room price:</v-list-tile-content>
                    <v-list-tile-content
                      class="align-end"
                      v-if="props.item.numberOfBeds == 1"
                      >{{ room_price(reservation, "OneBed") }}
                    </v-list-tile-content>
                    <v-list-tile-content
                      class="align-end"
                      v-if="props.item.numberOfBeds == 2"
                      >{{ room_price(reservation, "TwoBeds") }}
                    </v-list-tile-content>
                    <v-list-tile-content
                      class="align-end"
                      v-if="props.item.numberOfBeds == 3"
                      >{{ room_price(reservation, "ThreeBeds") }}
                    </v-list-tile-content>
                    <v-list-tile-content
                      class="align-end"
                      v-if="props.item.numberOfBeds == 4"
                      >{{ room_price(reservation, "FourBeds") }}
                    </v-list-tile-content>
                    <v-list-tile-content
                      class="align-end"
                      v-if="props.item.numberOfBeds == 5"
                      >{{ room_price(reservation, "FiveBeds") }}
                    </v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-card>
            </v-flex>
          </template>
        </v-data-iterator>
        <div
          v-for="(service, index) in reservation.additionalServices"
          :key="index"
        >
          <span class="grey--text" v-if="service.type == 'AdditionalService'"
            >Additional service : {{ service.description }} -
            {{ service.price }} EUR.</span
          >
        </div>
        <div>
          <span class="grey--text"
            ><h1>Total price {{ reservation.totalPrice }} EUR.</h1></span
          ><br />
        </div>
      </div>
    </div>
    <v-dialog width="300" v-model="ratingDialog">
      <v-card class="elevation-16 mx-auto">
        <v-card-title class="headline" primary-title>
          Rate Our Hotel
        </v-card-title>
        <v-card-text>
          If you enjoyed staying in our hotel, please take a few seconds to rate
          your experience with us.
          <div class="text-xs-center">
            <v-rating
              v-model="hotelRating"
              background-color="grey darken-1"
              half-increments
              hover
            ></v-rating>
          </div>
          Also, please rate your room.
          <div class="text-xs-center">
            <v-rating
              v-model="roomRating"
              background-color="grey darken-1"
              half-increments
              hover
            ></v-rating>
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions class="justify-space-between">
          <v-btn flat @click="noThanks">No Thanks</v-btn>
          <v-btn color="primary" flat @click="rateService">
            Rate Now
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import HotelRatingController from "@/controllers/hotel.rating.controller.js";
import HotelController from "@/controllers/hotels.controller";
import HotelReservation from "@/models/HotelReservation";
import Rating from "@/models/Rating.js";

import store from "@/store";

export default {
  name: "HotelReservations",
  data: () => ({
    hotelReservations: [],
    roomPrice: 0,

    pagination: {
      rowsPerPage: 6
    },
    ratingDialog: false,
    hotelRating: 0,
    roomRating: 0,
    id: -1,
    rating: new Rating(),

  }),
  methods: {
    room_price(reservation, type) {
      if (!(typeof reservation.hotel === "undefined")) {
        reservation.hotel.priceList.forEach(service => {
          if (service.type == type) {
            let str = "" + service.price;
            return str;
          }
        });
      }
    },
    rate(id) {
      this.ratingDialog = true;
      this.id = id;
    },
    noThanks() {
      this.ratingDialog = false;
      this.hotelRating = 0;
      this.roomRating = 0;
      this.id = -1;
    },
    rateService() {
      this.rating.id = this.id;
      this.rating.service = this.hotelRating;
      this.rating.specific = this.roomRating;

      HotelRatingController.rate(this.rating)
        .then(response => {
          store.commit("setSnack", {
            msg: "Rating successful",
            color: "success"
          });
        })
        .catch(error => {
          store.commit("setSnack", {
            msg: error.response.data,
            color: "error"
          });
        });

      this.ratingDialog = false;
      this.hotelRating = 0;
      this.roomRating = 0;
      this.id = -1;
    }
  },
  beforeMount() {
    HotelController.getReservations(store.getters.activeUser.id)
      .then(response => {
        response.data.forEach(element => {
          this.hotelReservations.push(element);
        });
      })
      .catch(error => {
        alert(error);
      });
  }
};
</script>

<style></style>
