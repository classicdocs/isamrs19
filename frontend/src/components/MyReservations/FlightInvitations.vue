<template>
  <div>
    <v-card>
      <v-card-text>
        <v-expansion-panel 
			  >
          <v-expansion-panel-content
            v-for="(invitation,index) in invitations" :key="index">
            <div slot='header'><h1>Invitation {{index + 1}} - {{getTitle(invitation.flightReservation, invitation.invitationFrom.username)}}</h1></div>
            <v-container>
              <v-layout row wrap>
                <v-flex lg12 md12 sm12 xs12>
                  <p>You have invitation from the user <b> {{invitation.invitationFrom.username}} </b>[{{invitation.invitationFrom.firstname}}
                    {{invitation.invitationFrom.lastname}}] 
                  </p>
                  <v-btn color="success" @click="accept(invitation)">Accept</v-btn>
                  <v-btn color="error" @click="decline(invitation)">Decline</v-btn>
                </v-flex>
              </v-layout>
              <v-layout row wrap>
                <v-flex lg6 md6 >
                  <v-layout row wrap style="margin:5px" > 
                    <v-flex lg12 md12>
                      <h3>Departure flight</h3>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <flight
                        v-bind:data="invitation.flightReservation.departureFlight"
                        v-bind:searchParams="getParams(invitation)"
                      ></flight>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-flex lg6 md6>
                  <v-layout row wrap style="margin:5px" v-if="invitation.flightReservation.returnFlight !== null">
                    <v-flex  lg12 md12>
                      <h3>Return flight</h3>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12 >
                      <flight
                        v-bind:data="invitation.flightReservation.returnFlight"
                        v-bind:searchParams="getParams(invitation)"
                      ></flight>
                    </v-flex>
                  </v-layout>
                </v-flex>
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
  name: "FlightInvitations",
  components: {
    'flight' : FlightInfoVue
  },
  data:() => ({
    invitations: [],
  }),
  beforeMount() {
    let id = store.getters.activeUser.id;
    UserController.getFlightInvitations(id)
      .then((response) => {
        console.log(response.data);
        this.invitations = response.data;
      })
      .catch((error) => {
        console.log(error.response.data);
      })
  },
  methods: {
    getTitle(reservation, user){
      console.log(reservation);
      let title = "";
      title += reservation.departureFlight.startDestination.name + " - " + reservation.departureFlight.finalDestination.name;
      if (reservation.returnFlight != null) {
        title += " - "  + reservation.returnFlight.startDestination.name + " - " + reservation.returnFlight.finalDestination.name;
      }
      title += " [" + user + "] ";
      return title;
    },
    getParams(invitation) {
      let params = {
        'seatClass' : invitation.seatClass,
        'passengersNumber' : null,
      }
      return params;
    },
    accept(invitation) {

    },
    decline(invitation) {
      
    }
  }
}
</script>

<style>

</style>
