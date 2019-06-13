<template>
  <v-dialog
    v-model="dialog"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn flat v-on="on" >History</v-btn>
  </template>
  <v-card>
    <v-card-title primary-title>
     <span class="headline">History of your flight reservations</span>
    </v-card-title>
    <v-card-text>
      <v-expansion-panel 
			  >
          <v-expansion-panel-content
            v-for="(reservation,index) in reservations" :key="index">
            <div slot='header'><h3>Reservation {{index + 1}} - {{getTitle(reservation)}}</h3></div>
            <v-btn v-if="reservation.completed" 
            color="blue darken" 
            flat 
            @click="rate(reservation.id, reservation.hasReturnFlight, reservation.sameCompanies)"
            >Rate</v-btn>
            <v-container>
              <v-layout row wrap>
                <v-flex lg6 md6 >
                  <v-layout row wrap style="margin:5px" > 
                    <v-flex lg12 md12>
                      <h3>Departure flight</h3>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <flight
                        v-bind:data="reservation.departureFlight"
                        v-bind:searchParams="getParams(reservation)"
                      ></flight>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-flex lg6 md6>
                  <v-layout row wrap style="margin:5px" v-if="reservation.returnFlight !== null">
                    <v-flex  lg12 md12>
                      <h3>Return flight</h3>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12 >
                      <flight
                        v-bind:data="reservation.returnFlight"
                        v-bind:searchParams="getParams(reservation)"
                      ></flight>
                    </v-flex>
                  </v-layout>
                </v-flex>
              </v-layout>
              <v-layout row wrap>
                <v-flex lg12 md12 sm12 xs12>
                  <v-data-table
                  :headers="getHeaders(reservation)"
                  :items="reservation.passengers"
                  hide-actions
                  class="elevation-1"
                >
                <template v-slot:items="props">
                  <td class="text-xs-left">{{ props.item.passenger.firstname}}</td>
                  <td class="text-xs-left">{{ props.item.passenger.lastname}}</td>
                  <td class="text-xs-left">{{ props.item.passenger.passport }}</td>
                  <td class="text-xs-left">{{ props.item.seatRowDeparture }}</td>
                  <td class="text-xs-left">{{ props.item.seatColDeparture }}</td>
                  <td class="text-xs-left" v-if="reservation.returnFlight !== null">{{ props.item.seatRowReturn }}</td>
                  <td class="text-xs-left" v-if="reservation.returnFlight !== null">{{ props.item.seatColReturn }}</td>
                  <td class="text-xs-left">{{ props.item.seatClass }}</td>
                  <td class="text-xs-left">{{getLuggage(props.item.passenger.luggageDTOSet)}}</td>
                  <td class="text-xs-left">{{ props.item.passenger.email }}</td>
                  <td class="text-xs-left">{{ props.item.passenger.phone }}</td> 
                  <td class="text-xs-left">{{ props.item.passenger.address }}</td>
                  <td class="text-xs-left">{{ isAccepted(props.item.passenger.accepted) }}</td>
                </template>
                </v-data-table>
                </v-flex>
                
                <v-flex lg6 md6 sm6 xs12>
                  <h3><br>{{"Date of reservation " + reservation.date}}</h3>
                </v-flex>
              </v-layout>
            </v-container>
           
          </v-expansion-panel-content>
          </v-expansion-panel>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
    </v-card-actions>
  </v-card>
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
    If you enjoyed using our company, please take a few seconds to rate your experience with us.
    <div class="text-xs-center">
        <v-rating
        v-model="companyRating"
        background-color="grey darken-1"
        half-increments
        hover
        ></v-rating>
    </div>
    Please rate the flight you took as well.
    <div class="text-xs-center">
        <v-rating
        v-model="flightRating"
        background-color="grey darken-1"
        half-increments
        hover
        ></v-rating>
    </div>

    <div v-if="hasReturnFlight">    

    <div v-if="!sameCompany">          
    Also,you can rate the company you used on your return flight.
    <div class="text-xs-center">
        <v-rating
        v-model="returnCompanyRating"
        background-color="grey darken-1"
        half-increments
        hover
        ></v-rating>
    </div>
    </div>
    And the return flight,too.
    <div class="text-xs-center">
        <v-rating
        v-model="returnFlightRating"
        background-color="grey darken-1"
        half-increments
        hover
        ></v-rating>
    </div>

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
    
  </v-dialog>
</template>

<script>

import store from "@/store";
import UserController from "@/controllers/user.controller.js";
import FlightInfoVue from '../Flights/FlightInfo.vue';
import FlightRatingController from '@/controllers/flight.rating.controller.js';
import Rating from "@/models/Rating.js";
import RatingWithReturn from "@/models/RatingWithReturn.js";
import RatingDifferentCompanies from "@/models/RatingDifferentCompanies.js";
import FlightReservationsHistoryVue from './FlightReservationsHistory.vue';

export default {
  name: "FlightReservations",
  components: {
    'flight' : FlightInfoVue,
    'history': FlightReservationsHistoryVue
  },
  data:() => ({
    dialog: false,
    expansion: [true],
    reservations: [],
    headers1: [
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: 'Passport number', value: 'passport' },
      { text: 'Seat row departure', value: 'rowDept' },
      { text: 'Seat column departure', value: 'colDept' },
      { text: 'Seat row return', value: 'rowRet' },
      { text: 'Seat column return', value: 'colRet' },
      { text: 'Seat class', value: 'class' },
      { text: 'Luggage', value: 'luggage'},
      { text: 'Email', value: 'email' },
      { text: 'Phone number', value: 'phone' },
      { text: 'Address', value: 'address' },
      { text: 'Accepted', value: 'accepted' },
    ],
    headers2: [
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: 'Passport number', value: 'passport' },
      { text: 'Seat row departure', value: 'rowDept' },
      { text: 'Seat column departure', value: 'colDept' },
      { text: 'Seat class', value: 'class' },
      { text: 'Luggage', value: 'luggage'},
      { text: 'Email', value: 'email' },
      { text: 'Phone number', value: 'phone' },
      { text: 'Address', value: 'address' },
      { text: 'Accepted', value: 'accepted' },
    ],
    rating: new Rating(),
    ratingWithReturn: new RatingWithReturn(),
    ratingDifferentCompanies: new RatingDifferentCompanies(),
    ratingDialog: false,
    companyRating: 0,
    flightRating: 0,
    id: -1,
    hasReturnFlight: false,
    sameCompany: false,
    returnCompanyRating: 0,
    returnFlightRating: 0,
  }),
  beforeMount() {
    UserController.getFlightReservationsHistory(store.getters.activeUser.id)
      .then((response) => {
        this.reservations = response.data;
      })
      .catch((error) => {
        console.log(error.response.data);
      })
  },
  methods: {
    isAccepted(accepted) {
      if (accepted == false) {
        return "Pending";
      } else {
        return "Accepted";
      } 
    },

    getParams(reservation) {
      let params = {
        'seatClass' : reservation.passengers[0].seatClass,
        'passengersNumber' : reservation.passengers.length,
      }
      return params;
    },
    getTitle(reservation){
      let title = "";
      title += reservation.departureFlight.startDestination.name + " - " + reservation.departureFlight.finalDestination.name;
      if (reservation.returnFlight != null) {
        title += " - "  + reservation.returnFlight.startDestination.name + " - " + reservation.returnFlight.finalDestination.name;
      }
      return title;
    },
    getHeaders(reservation) {
      if (reservation.returnFlight !== null) {
        return this.headers1;
      }
      return this.headers2;
    },
    cancelReservation(reservation) {
      let json = {
        'id': reservation.id
      }
      UserController.cancelReservation(store.getters.activeUser.id, json)
        .then((response) => {
          store.commit("setSnack", {msg: "You have successfully canceled reservation", color:"success"})
          let idx = this.reservations.indexOf(reservation);
          if (idx != -1)
            this.reservations.splice(idx,1);
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"})
        })
    },
    getLuggage(luggages) {
      let ret = "";
      luggages.forEach(l => {
        ret += l.name + ", ";
      });
      return ret.substring(0, ret.length - 2);
    },
    getPrice(item) {
      let price = item.price;
      item.passenger.luggages.forEach(l => {
        price += l.price;
      });
      return price;
    },
    rate(id,hasReturn,sameCompany) {      
      this.id = id;
      this.hasReturnFlight = hasReturn;
      this.sameCompany = sameCompany;
      this.ratingDialog = true;
    },
    noThanks() {
      this.ratingDialog = false;
      this.hasReturnFlight = false;
      this.sameCompany = false;
      this.companyRating = 0;
      this.flightRating = 0;
      this.id = -1;
      this.returnCompanyRating = 0;
      this.returnFlightRating = 0; 
    },
    rateService() {
      if(this.hasReturnFlight) {
        if(this.sameCompany){
          this.ratingWithReturn.id = this.id;
          this.ratingWithReturn.service = this.companyRating;
          this.ratingWithReturn.specific = this.flightRating;
          this.ratingWithReturn.returnFlightRating = this.returnFlightRating;
          
          FlightRatingController.rateWithReturn(this.ratingWithReturn)
          .then((response) => {
          store.commit("setSnack", {msg: "Rating successful", color:"success"})
          })
          .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"})
        });
          
        } else {
          this.ratingDifferentCompanies.id = this.id;
          this.ratingDifferentCompanies.service = this.companyRating;
          this.ratingDifferentCompanies.specific = this.flightRating;
          this.ratingDifferentCompanies.returnFlightRating = this.returnFlightRating;  
          this.ratingDifferentCompanies.returnCompanyRating = this.returnCompanyRating;

          FlightRatingController.rateDifferentCompanies(this.ratingDifferentCompanies)
          .then((response) => {
          store.commit("setSnack", {msg: "Rating successful", color:"success"})
          })
          .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"})
          });
        }
      } else {  
        this.rating.id = this.id;
        this.rating.service = this.companyRating;
        this.rating.specific = this.flightRating;
      
        FlightRatingController.rate(this.rating)
          .then((response) => {
          store.commit("setSnack", {msg: "Rating successful", color:"success"})
          })
          .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"})
          });
        }

        this.ratingDialog = false;
        this.companyRating = 0;
        this.flightRating = 0;
        this.id = -1;
        this.returnCompanyRating = 0;
        this.returnFlightRating = 0;
    }
  }
}
</script>

<style>

</style>

