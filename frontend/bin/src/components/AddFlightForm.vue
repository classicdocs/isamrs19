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
    <v-card>
      <v-form
        ref="form"
        v-model="form"
        lazy-validation
      >
      <v-card-title primary-title>
        <span class="headline">Add flight</span>
      </v-card-title>
        <v-card-text>
          
          <v-select
            :items="destinations"
            v-model="flight.startDestination"
            label="Start destination"
            :rules="[v => !!v || 'Start destination is required']"
            required
          ></v-select>
          <v-select
            :items="destinations"
            v-model="flight.finalDestination"
            label="Final destination"
            :rules="[v => !!v || 'Final destination is required']"
            required
          ></v-select>
          <v-container fluid grid-list-xs>
            <v-layout wrap align-center>
              <v-flex xs6>
                <v-menu
                  ref="menuDepartureDate"
                  v-model="menuDepartureDate"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="flight.departureDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="flight.departureDate"
                      label="Departure date"
                      prepend-icon="event"
                      readonly
                      required
                      :rules="[v => !!v || 'Departure date is required']"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="flight.departureDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="menuDepartureDate = false">Cancel</v-btn>
                    <v-btn flat color="primary" @click="$refs.menuDepartureDate.save(flight.departureDate)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-flex>
              <v-flex xs6>
                <v-menu
                  ref="menuDepartureTime"
                  v-model="menuDepartureTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="flight.departureTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="flight.departureTime"
                      label="Departure time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Departure time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="menuDepartureTime"
                    v-model="flight.departureTime"
                    full-width
                    @click:minute="$refs.menuDepartureTime.save(flight.departureTime)"
                  ></v-time-picker>
                </v-menu>
              </v-flex>
              <v-flex xs6>
                <v-menu
                  ref="menuLandingDate"
                  v-model="menuLandingDate"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="flight.landingDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="flight.landingDate"
                      label="Landing date"
                      prepend-icon="event"
                      readonly
                      required
                      :rules="[v => !!v || 'Landing date is required']"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="flight.landingDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="menuLandingDate = false">Cancel</v-btn>
                    <v-btn flat color="primary" @click="$refs.menuLandingDate.save(flight.landingDate)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-flex>
              <v-flex xs6>
                <v-menu
                  ref="menuLandingTime"
                  v-model="menuLandingTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="flight.landingTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="flight.landingTime"
                      label="Departure time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Landing time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="menuLandingTime"
                    v-model="flight.landingTime"
                    full-width
                    @click:minute="$refs.menuLandingTime.save(flight.landingTime)"
                  ></v-time-picker>
                </v-menu>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  label="Distance"
                  v-model="flight.distance"
                  :rules="rules.distance"
                  required
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  label="Ticket price"
                  v-model="flight.ticketPrice"
                  required
                  :rules="rules.ticketPrice"
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <v-menu
                  ref="menuFlightTime"
                  v-model="menuFlightTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="flight.flightTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="flight.flightTime"
                      label="Flight time"
                      prepend-icon="access_time"
                      readonly
                      required
                      :rules= "[v => !!v || 'Flight time is required']"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="menuFlightTime"
                    v-model="flight.flightTime"
                    full-width
                    format="24hr"
                    @click:minute="$refs.menuFlightTime.save(flight.flightTime)"
                  ></v-time-picker>
                </v-menu>
              </v-flex>
              <v-flex xs12>
                <v-select
                v-model="flight.transferDestination"
                :items="destinations"
                label="Transfer destinations"
                multiple
                chips
                hint="You can select multiple destinations"
                persistent-hint
              ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="reset">Reset Form</v-btn>
          <v-btn :disabled="!form" color="primary" @click="validate">Add new</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    
  </div>
</template>

<script>

import Flight from "@/models/Flight";
import FlightsController from "@/controllers/flights.controller";
import DestinationsController from "@/controllers/destinations.controller";

export default {
  name: "AddFlightForm",
  data: () => ({
    form: true,

    menuDepartureDate: false,
    menuLandingDate: false,
    menuDepartureTime: false,
    menuLandingTime: false,
    menuFlightTime: false,

    value: true,

    snackbar: {
      show: false,
      color: "",
      msg: "",
    },

    rules: {
      distance: [v => !!v || 'Distance is required',
                 v => /^[0-9]+$/.test(v) || "Distance must be a positive number"],
      ticketPrice: [v => !!v || 'Ticket price is required',
                 v => /^[0-9]+$/.test(v) || "Ticket price must be a positive number"],

    },
    destinations: [],

    flight: new Flight(),
  }),
  created() {
    this.flight.airlineCompany = this.$route.params.id;

    DestinationsController.get()
      .then((response) => {
        response.data.forEach(element => {
          this.destinations.push(element.name);
        });
      })
      .catch((response) => {

      })
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        if (this.validateDestinations()) {
          FlightsController.create(this.flight)
          .then((response) => {
            this.$emit("operation", {msg: "Flight successfully added", color: "success"})
          })
          .catch((response) => {
            this.$emit("operation", {msg: "Error! Something went wrong...", color: "error"})
          })
        } else {
          this.$emit("destination-error", {msg: "Start,final and transfer destinations can't be the same!", color: "error"})
        }
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    validateDestinations() {
      if (this.flight.startDestination === this.flight.finalDestination)
        return false;
      
      let retVal = true;

      this.flight.transferDestination.forEach(e => {
       
        if(e === this.flight.startDestination || e === this.flight.finalDestination)
          retVal = false;
      });

      return retVal;
    }
  }
};
</script>

<style>

</style>

