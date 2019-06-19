<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form">
        <v-card-text>
          <v-btn @click="oneWayBtnClicked" :color="oneWayBtnColor"
            >One-Way</v-btn
          >
          <v-btn @click="roundBtnClicked" :color="roundBtnColor">Round</v-btn>
          <v-container fluid>
            <v-layout row wrap>
              <v-flex lg2>
                <v-autocomplete
                  v-model="search.startDestination"
                  :items="destinations"
                  label="From"
                  persistent-hint
                  prepend-icon="flight_takeoff"
                ></v-autocomplete>
              </v-flex>
              <v-flex lg2>
                <v-autocomplete
                  v-model="search.finalDestination"
                  :items="destinations"
                  label="To"
                  persistent-hint
                  prepend-icon="flight_land"
                ></v-autocomplete>
              </v-flex>
              <v-flex lg2>
                <v-menu
                  ref="menuDepartureDate"
                  v-model="menuDepartureDate"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="search.departureDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="search.departureDate"
                      label="Departure date"
                      prepend-icon="event"
                      readonly
                      required
                      :rules="[v => !!v || 'Departure date is required']"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="search.departureDate"
                    no-title
                    scrollable
                  >
                    <v-spacer></v-spacer>
                    <v-btn
                      flat
                      color="primary"
                      @click="menuDepartureDate = false"
                      >Cancel</v-btn
                    >
                    <v-btn
                      flat
                      color="primary"
                      @click="
                        $refs.menuDepartureDate.save(search.departureDate)
                      "
                      >OK</v-btn
                    >
                  </v-date-picker>
                </v-menu>
              </v-flex>
              <v-flex lg2 v-if="!oneWay">
                <v-menu
                  ref="menuReturnDate"
                  v-model="menuReturnDate"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="search.returnDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="search.returnDate"
                      label="Return date"
                      prepend-icon="event"
                      readonly
                      required
                      :rules="[v => !!v || 'Return date is required']"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="search.returnDate"
                    no-title
                    scrollable
                  >
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="menuReturnDate = false"
                      >Cancel</v-btn
                    >
                    <v-btn
                      flat
                      color="primary"
                      @click="$refs.menuReturnDate.save(search.returnDate)"
                      >OK</v-btn
                    >
                  </v-date-picker>
                </v-menu>
              </v-flex>
              <v-flex lg2>
                <v-select
                  :items="passengersNumber"
                  label="Number of passengers"
                  v-model="search.passengersNumber"
                  prepend-icon="people"
                  required
                  :rules="[v => !!v || 'Number of passengers is required']"
                ></v-select>
              </v-flex>
              <v-flex lg2>
                <v-select
                  :items="seatClasses"
                  label="Seat class"
                  v-model="search.seatClass"
                  required
                  :rules="[v => !!v || 'Seat class is required']"
                  prepend-icon="airline_seat_recline_extra"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!form" @click="searchFlights">Search</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    <v-snackbar
      v-model="snackbar.show"
      :timeout="5000"
      :color="snackbar.color"
      :top="true"
    >
      {{ snackbar.msg }}
      <v-btn dark flat @click="snackbar.show = false">
        Close
      </v-btn>
    </v-snackbar>
  </div>
</template>

<script>
import SearchFlight from "@/models/SearchFlight.js";
import FlightController from "@/controllers/flights.controller.js";
import DestinationController from "@/controllers/destinations.controller.js";
import store from "@/store";

export default {
  name: "SearchForm",
  components: {},
  data: () => ({
    form: true,
    oneWay: true,
    oneWayBtnColor: "primary",
    roundBtnColor: "default",
    menuDepartureDate: false,
    menuReturnDate: false,

    search: new SearchFlight(),
    destinations: [],
    passengersNumber: [1, 2, 3, 4, 5, 6],
    seatClasses: ["First", "Business", "Economy"],

    snackbar: {
      show: false,
      color: "",
      msg: ""
    }
  }),
  created() {
    (this.search.startDestination = "London"),
      (this.search.finalDestination = "Belgrade"),
      (this.search.departureDate = "2019-06-22"),
      (this.search.returnDate = "2019-06-25"),
      (this.search.seatClass = "First"),
      (this.search.passengersNumber = 1);

    DestinationController.get().then(response => {
      response.data.forEach(element => {
        this.destinations.push(element.name);
      });
    });
  },
  computed: {
    getStartDestinations() {
      return this.destinations;
    },
    getFinalDestinations() {
      return this.destinations;
    }
  },
  methods: {
    oneWayBtnClicked() {
      this.oneWay = true;
      this.roundBtnColor = "default";
      this.oneWayBtnColor = "primary";
    },
    roundBtnClicked() {
      this.oneWay = false;
      this.roundBtnColor = "primary";
      this.oneWayBtnColor = "default";
    },
    searchFlights() {
      if (this.$refs.form.validate()) {
        if (this.validate()) {
          if (this.oneWay) {
            this.search.returnDate = null;
          }
          FlightController.search(this.search)
            .then(response => {
              var json = {
                data: response.data,
                seatClass: this.search.seatClass,
                passengersNumber: this.search.passengersNumber
              };
              this.$emit("search-result", json);
            })
            .catch(error => {
              store.commit("setSnack", {
                msg: error.response.data,
                color: "error"
              });
            });
        }
      }
    },
    validate() {
      if (this.search.startDestination === this.search.finalDestination) {
        this.snackbar.color = "error";
        this.snackbar.msg = "Start and final destination can't be the same!";
        this.snackbar.show = true;
        return false;
      }
      let departureDate = new Date(this.search.departureDate);
      let returnDate = new Date(this.search.returnDate);
      if (!this.oneWay && returnDate !== null) {
        if (returnDate < departureDate) {
          this.snackbar.color = "error";
          this.snackbar.msg = "Return date can't be before departure date!";
          this.snackbar.show = true;
          return false;
        }
      }
      return true;
    }
  }
};
</script>
