<template>
  <div>
    <v-dialog
      v-model="addFormDialog"
      max-width="500px"
      persistent
    >
    <template v-slot:activator="{ on }">
      <v-btn color="primary" dark v-on="on">Add a flight</v-btn>
    </template>
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
            v-model="startDestination"
            label="Start destination"
            :rules="[v => !!v || 'Start destination is required']"
            required
            prepend-icon="flight_takeoff"
          ></v-select>
          <v-select
            :items="destinations"
            v-model="finalDestination"
            label="Final destination"
            :rules="[v => !!v || 'Final destination is required']"
            required
            prepend-icon="flight_land"
          ></v-select>
          <v-select
            :items="getAirplanes"
            v-model="flight.airplane"
            label="Airplane"
            :rules="[v => !!v || 'Airplane is required']"
            required
            prepend-icon="flight"
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
                      label="Landing time"
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
              <v-flex xs4>
                <v-text-field
                  label="First class"
                  v-model="flight.ticketPriceFirst"
                  required
                  :rules="rules.ticketPrice"
                  prepend-icon="euro_symbol"
                ></v-text-field>
              </v-flex>
              <v-flex xs4>
                <v-text-field
                  label="businessClass"
                  v-model="flight.ticketPriceBusiness"
                  required
                  :rules="rules.ticketPrice"
                  prepend-icon="euro_symbol"
                ></v-text-field>
              </v-flex>
              <v-flex xs4>
                <v-text-field
                  label="Economy Class"
                  v-model="flight.ticketPriceEconomy"
                  required
                  :rules="rules.ticketPrice"
                  prepend-icon="euro_symbol"
                ></v-text-field>
              </v-flex>
              <v-flex xs4>
                <v-text-field
                  label="Distance"
                  v-model="flight.distance"
                  :rules="rules.distance"
                  required
                  prepend-icon="compare_arrows"
                ></v-text-field>
              </v-flex>
              <v-flex xs4>
                <v-text-field
                  label="Hour"
                  v-model="flight.flightTimeHours"
                  required
                  :rules="rules.time"
                  prepend-icon="access_time"
                ></v-text-field>
              </v-flex>
              <v-flex xs4>
                <v-text-field
                  label="Minuts"
                  v-model="flight.flightTimeMinutes"
                  required
                  :rules="rules.time"
                  prepend-icon="access_time"
                ></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-select
                v-model="flight.transferDestinations"
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
          <v-btn color="blue darken" flat @click="addFormDialog = false">Close</v-btn> 
          <v-btn @click="resetValidation">Reset Validation</v-btn>
          <v-btn @click="reset">Reset Form</v-btn>
          <v-btn :disabled="!form" color="success" @click="validate">Add new</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    </v-dialog>
  </div>
</template>

<script>

import Flight from "@/models/Flight";
import FlightsController from "@/controllers/flights.controller";
import DestinationsController from "@/controllers/destinations.controller";
import AirlineCompanyController from "@/controllers/airline-company.controller"

export default {
  name: "AddFlightDialog",
  data: () => ({
    form: true,
    addFormDialog: false,
    menuDepartureDate: false,
    menuLandingDate: false,
    menuDepartureTime: false,
    menuLandingTime: false,
    menuFlightTime: false,

    startDestination: "",
    finalDestination: "",
    
    value: true,
    rules: {
      distance: [v => !!v || 'Distance is required',
                 v => /^[0-9]+$/.test(v) || "Distance must be a positive number"],
      ticketPrice: [v => !!v || 'Ticket price is required',
                 v => /^[0-9]+$/.test(v) || "Ticket price must be a positive number"],
      time: [v => !!v || 'Time is required',
                 v => /^[0-9]+$/.test(v) || "Time must be a positive number"],

    },
    destinations: [],
    airplanes: [],

    flight: new Flight(),
  }),
  created() {
    this.flight.airlineCompany.id = this.$route.params.id;

    AirlineCompanyController.getDestinations(this.flight.airlineCompany.id)
      .then((response) => {
        response.data.forEach(element => {
          this.destinations.push(element);
        });
      })
      .catch((error) => {
        alert(error.response.data);
      })

    AirlineCompanyController.getAirplanes(this.flight.airlineCompany.id)
      .then((response) => {
        response.data.forEach(element => {
          this.airplanes.push(element);
        })
      })
      .catch((error) => {
        alert(error.response.data);
      })
  },
  computed: {
    getAirplanes() {
      let result = []
      this.airplanes.forEach(element => {
        result.push("Id: " + element.id + ", Model: " + element.model);
      }); 
      return result;
    }
  },
  watch: {
    addFormDialog (val) {
      !val && this.reset();
    }
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        if (this.validateDestinations()) {
          let airplane = this.flight.airplane;
          let space1 = airplane.indexOf(" ");
          let space2 = airplane.lastIndexOf(":");
          let comma = airplane.indexOf(",");
          this.flight.airplane = {
            'id': airplane.substring(space1 + 1, comma),
            'model' : airplane.substr(space2 + 2, airplane.length)
          }

          let ind = this.startDestination.lastIndexOf(" ");
          this.flight.startDestination.name = this.startDestination.substring(0, ind);
          this.flight.startDestination.zip = this.startDestination.substring(ind+1, this.startDestination.length);

          ind = this.finalDestination.lastIndexOf(" ");
          this.flight.finalDestination.name = this.finalDestination.substring(0, ind);
          this.flight.finalDestination.zip = this.finalDestination.substring(ind+1, this.finalDestination.length);

          console.log(this.flight);
          FlightsController.create(this.flight)
          .then((response) => {
            this.addFormDialog = false;
            this.$emit("snack", {msg: "Flight successfully added", color: "success"})
          })
          .catch((error) => {
            this.addFormDialog = false;
            this.$emit("snack", {msg: error.response.data, color: "error"})
          })
        } else {
          this.$emit("snack", {msg: "Start,final and transfer destinations can't be the same!", color: "error"})
        }
      }
    },
    reset() {
      this.$refs.form.reset();
    },
     resetValidation() {
      this.$refs.form.resetValidation();
    },
    validateDestinations() {
      if (this.startDestination === this.finalDestination)
        return false;
      
      let retVal = true;

      this.flight.transferDestinations.forEach(e => {
       
        if(e === this.startDestination || e === this.finalDestination)
          retVal = false;
      });

      return retVal;
    },
  }
};
</script>

<style>

</style>

