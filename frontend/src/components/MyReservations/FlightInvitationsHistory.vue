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
     <span class="headline">History of your flight intivations</span>
    </v-card-title>
    <v-card-text>
        <v-expansion-panel 
			  >
          <v-expansion-panel-content
            v-for="(invitation,index) in invitations" :key="index">
            <div slot='header'><h3>Invitation {{index + 1}} - {{getTitle(invitation.flightReservation, invitation.invitationFrom.username)}}</h3></div>
            <v-container>
              <v-layout row wrap>
                <v-flex lg12 md12 sm12 xs12>
                  <p>You have invitation from the user <b> {{invitation.invitationFrom.username}} </b>[{{invitation.invitationFrom.firstname}}
                    {{invitation.invitationFrom.lastname}}] 
                  </p>
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
       <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
    </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

import store from "@/store";
import UserController from "@/controllers/user.controller.js";
import FlightInfoVue from '../Flights/FlightInfo.vue';

export default {
  name: "FlightInvitationsHistory",
  components: {
    'flight' : FlightInfoVue
  },
  data:() => ({
    dialog: false,
    invitations: [],
  }),
  beforeMount() {
    let id = store.getters.activeUser.id;
    UserController.getFlightInvitationsHistory(id)
      .then((response) => {
        this.invitations = response.data;
      })
      .catch((error) => {
        console.log(error.response.data);
      })
  },
  methods: {
    getTitle(reservation, user){
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
        'passengerNumber' : null,
      }
      return params;
    },
  }
}
</script>

<style>

</style>
