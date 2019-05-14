<template>
  <div>
    <v-card>
      <v-card-text>
        <v-expansion-panel 
			  >
          <v-expansion-panel-content
            v-for="(reservation,index) in reservations" :key="index">
            <div slot='header'><h1>Reservation {{index + 1}} - {{getTitle(reservation)}}</h1></div>
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
                  <td class="text-xs-left">{{ props.item.passenger.email }}</td>
                  <td class="text-xs-left">{{ props.item.passenger.phone }}</td> 
                  <td class="text-xs-left">{{ props.item.passenger.address }}</td>
                </template>
                </v-data-table>
                </v-flex>
                
                <h3><br>{{"Date of reservation " + reservation.date}}</h3>
              </v-layout>
            </v-container>
           
          </v-expansion-panel-content>
        </v-expansion-panel>
          
      </v-card-text>
    </v-card>
  </div>
</template>

<script>

import store from "@/store";
import UserController from "@/controllers/user.controller.js";
import FlightInfoVue from '../Flights/FlightInfo.vue';

export default {
  name: "FlightReservations",
  components: {
    'flight' : FlightInfoVue
  },
  data:() => ({
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
      { text: 'Email', value: 'email' },
      { text: 'Phone number', value: 'phone' },
      { text: 'Address', value: 'address' },
    ],
    headers2: [
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: 'Passport number', value: 'passport' },
      { text: 'Seat row departure', value: 'rowDept' },
      { text: 'Seat column departure', value: 'colDept' },
      { text: 'Seat class', value: 'class' },
      { text: 'Email', value: 'email' },
      { text: 'Phone number', value: 'phone' },
      { text: 'Address', value: 'address' },
    ],
  }),
  beforeMount() {
    UserController.getFlightReservations(store.getters.activeUser.id)
      .then((response) => {
        console.log(response.data);
        this.reservations = response.data;
      })
      .catch((error) => {
        console.log(error.response.data);
      })
  },
  methods: {
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
    }
  }


}
</script>

<style>

</style>
