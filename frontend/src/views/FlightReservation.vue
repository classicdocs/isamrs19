<template>
  <div>
     <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
    >
      {{snackbar.msg}}
      <v-btn
          dark
          flat
          @click="snackbar.show = false"
      >
      Close
      </v-btn>
    </v-snackbar>
    <v-container fluid> 
      <!-- FLIGHTS INFO -->
      <v-layout row wrap>
        <v-flex lg6 md6 >
          <v-layout row wrap style="margin:5px" > 
            <v-flex lg12 md12>
              <h3>Departure flight</h3>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <flight
                v-bind:data="flightReservation.flights.departureFlight"
                v-bind:searchParams="flightReservation.searchParams"
              ></flight>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-flex lg6 md6>
          <v-layout row wrap style="margin:5px" v-if="flightReservation.flights.returnFlight !== null">
             <v-flex  lg12 md12>
              <h3>Return flight</h3>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12 >
              <flight
                v-bind:data="flightReservation.flights.returnFlight"
                v-bind:searchParams="flightReservation.searchParams"
              ></flight>
            </v-flex>
          </v-layout>
        </v-flex>

        <!-- RESERVATION -->
        <v-layout row wrap justify-center>
          <v-flex lg12 md12>
           <v-stepper v-model="stepper">
            <v-stepper-header>
              <v-stepper-step :complete="stepper > 1" step="1">Pick a seats</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step :complete="stepper > 2" step="2">Invite friends</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step :complete="stepper > 3" step="3">Fill passengers information</v-stepper-step>
              <v-divider></v-divider>
              <!-- <v-stepper-step step="4">Review</v-stepper-step> -->
            </v-stepper-header>

            <v-stepper-items>
              <v-stepper-content step="1">
                <seats
                  v-bind:seatsDeparture="getDepartureSeats"
                  v-bind:seatsReturn="getReturnSeats"
                  v-bind:passengersNumber="flightReservation.searchParams.passengersNumber"
                  v-on:snack="showSnackBar($event)"
                ></seats>

                <v-btn
                  style="float:right"
                  color="primary"
                  @click="goToFriendsInvitation"
                  :disabled="!enableBtnStep1"
                >
                  Next
                </v-btn>
              </v-stepper-content>

              <v-stepper-content step="2">
                <friends-invitation
                  :key="componentKey"
                  v-on:friendsInvited="setInvitedFriends($event)"
                ></friends-invitation>
                <v-btn
                  color="primary"
                  @click="goToStepThree"
                  style="float:right"
                >
                  Next
                </v-btn>

                <v-btn flat
                  @click="stepper = 1"
                  style="float:right"
                >Back</v-btn>
              </v-stepper-content>

              <v-stepper-content step="3">
                <fill-passengers-info
                  v-bind:passengersNumber="flightReservation.searchParams.passengersNumber"
                ></fill-passengers-info>

                <v-btn
                  color="primary"
                  @click="reserve"
                  style="float:right"
                >
                  Next
                </v-btn>

                <v-btn flat
                  @click="goToStepTwoOrOne"
                  style="float:right"
                >Back</v-btn>
              </v-stepper-content>
              <!-- <v-stepper-content step="4">
                <review
                ></review>

                <v-btn
                  color="primary"
                  style="float:right"
                >
                  Reserve
                </v-btn>

                <v-btn flat
                  @click="stepper = 3"
                  style="float:right"
                >Back</v-btn>
              </v-stepper-content> -->

            </v-stepper-items>
          </v-stepper>
          </v-flex>
        </v-layout>
      </v-layout>
    </v-container>
    <optional-reservation-dialog v-if="optional"></optional-reservation-dialog>
  </div>
</template>

<script>

import store from '@/store';
import FlightInfo from "@/components/Flights/FlightInfo.vue";
import Seats from "@/components/FlightReservation/Seats.vue";
import UserController from "@/controllers/user.controller.js";
import FriendsInvitationVue from '../components/FlightReservation/FriendsInvitation.vue';
import FillPassengersInfoVue from '../components/FlightReservation/FillPassengersInfo.vue';
import Friend from "@/models/Friend.js";
import ReviewVue from '../components/FlightReservation/Review.vue';
import FlightController from "@/controllers/flights.controller.js";
import OptionalReservationDialogVue from '../components/FlightReservation/OptionalReservationDialog.vue';
export default {
  name: "FlightReservation",
  components: {
    'flight': FlightInfo,
    'seats':  Seats,
    'friends-invitation' : FriendsInvitationVue,
    'fill-passengers-info' : FillPassengersInfoVue,
    'review' : ReviewVue,
    'optional-reservation-dialog' : OptionalReservationDialogVue,
  },
  data:() => ({
    flightReservation: null,
    stepper: 0,
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
    friends: false,
    invitedFriends: [],
    passengers: [],
    optional: false,
    componentKey: 0,
  }),
  computed: {
    getDepartureSeats() {
      let flight = this.flightReservation.flights.departureFlight;
      switch(this.flightReservation.searchParams.seatClass) {
        case "First": {
          return flight.seatsFirst;
        }
        case "Business": {
          return flight.seatsBusiness;
        }
        case "Economy":  {
          return flight.seatsEconomy;
        }
      }
    },
    getReturnSeats() {
      if (this.flightReservation.flights.returnFlight === null)
        return null;

      let flight = this.flightReservation.flights.returnFlight;
      switch(this.flightReservation.searchParams.seatClass) {
        case "First": {
          return flight.seatsFirst;
        }
        case "Business": {
          return flight.seatsBusiness;
        }
        case "Economy":  {
          return flight.seatsEconomy;
        }
      }
    },
    enableBtnStep1() {
      let seatsPickedDeparture = store.getters.seatsPickedDeparture.length;
      let seatsPickedReturn = store.getters.seatsPickedReturn.length;
      let num = this.flightReservation.searchParams.passengersNumber;
      if (seatsPickedDeparture ===  num) {
        if (this.flightReservation.flights.returnFlight === null)
          return true;
        else
          if (seatsPickedReturn === num)
            return true;
      }
      return false;
    },
    
  },
  created() {
    let fr = store.getters.flightReservation;
    if (fr !== null)
      this.flightReservation = fr;
    else
      
      this.$router.push({name: "flights"});

    UserController.getFriends(store.getters.activeUser.id)
        .then((response) => {
          store.commit("setFriends", response.data);
          
        })
    store.commit("setPassengersDefault", this.flightReservation.searchParams.passengersNumber);
    this.getMyInfo();
  },
  methods: {
    showSnackBar(data) {
      this.snackbar.msg = data.msg;
      this.snackbar.color = data.color;
      this.snackbar.show = true;
    },
    goToFriendsInvitation() {
      this.forceRerender();
      if (store.getters.friends.length === 0 || this.flightReservation.searchParams.passengersNumber === 1) {
        this.friends = false;
        this.stepper = 3;
      } else {
        this.friends = true;
        this.stepper = 2
      }
    },
    goToStepTwoOrOne() {
      if (this.friends)
        this.stepper = 2;
      else
        this.stepper = 1;
    },
    getMyInfo() {
      UserController.getUser(store.getters.activeUser.id)
      .then((response) => {
        let f = new Friend();
        Object.assign(f,response.data)
        store.commit("myInfo", f);
      })
      .catch((error) => {
        alert(error.response.data);
      })
    },
    setInvitedFriends(data) {
      if (data.length === 0)
        this.invitedFriends = []
      else {
        this.invitedFriends = []
        Object.assign(this.invitedFriends, data);
      }
        
    },
    goToStepThree() {
      this.passengers = [];
      store.commit("setPassengersDefault", this.flightReservation.searchParams.passengersNumber);
      
      let promises = [];
      for(let element of this.invitedFriends) {
        promises.push(
          UserController.getUser(element.id)
        )
      }

      Promise.all(promises).then((values)=> {
        for(let val of values) {
          let f = new Friend();
          Object.assign(f, val.data);
          this.passengers.push(f);
        }
        store.commit("friendsInvited", this.passengers)
        this.stepper = 3;
      }).
      catch((errors) => {
        console.log(errors);
      })
    },
    reserve() {
      let passengers = store.getters.passengers;
      for (let passenger of passengers) {
        if (passenger.firstname === "" || passenger.lastname === "" || passenger.email === "" || passenger.address === "" || 
            passenger.passport === "" || passenger.phone === "") {
          store.commit("setSnack", {msg: "You must fill information about every passenger!", color: "primary"});
          return;
        }
      }
      let data = store.getters.flightReservation;
      data.passengers = store.getters.passengers;
      data.myInfo = store.getters.myInfo;

      let loader = this.$loading.show()
      FlightController.reserve(data)
        .then((response) => {
          loader.hide();
          store.commit("setSnack", {msg: "You have successfully booked a flight!", color: "success"});
          this.optional = true;
        })
        .catch((error) => {
          loader.hide();
          this.$router.push({name: "flights"})
          store.commit("setSnack", {msg: error.response.data, color: "error"});
        })

    },
    forceRerender() {
      this.componentKey += 1;
    }
    
  },
  
}
</script>

<style>

</style>
