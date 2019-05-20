<template>
  <div>
    <v-card >
      <v-form
          ref="form"
          v-model="form"
        >
        <v-card-text>
          <v-container fluid>
            <v-layout row wrap>
              <v-flex lg2>
                <v-autocomplete
                v-model="hotelDestination"
                :items="destinations"
                label="Hotel Destination"
                persistent-hint
                prepend-icon="location_on"
              ></v-autocomplete>
              </v-flex>
              <v-flex lg2>
                <v-menu
                    ref="menuCheckInDate"
                    v-model="menuCheckInDate"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="checkInDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                >
                <template v-slot:activator="{ on }">
                    <v-text-field
                    v-model="checkInDate"
                    label="Check-in date"
                    prepend-icon="event"
                    readonly
                    required
                    :rules="[v => !!v || 'Check-in date is required']"
                    v-on="on"
                    ></v-text-field>
                </template>
                <v-date-picker v-model="checkInDate" no-title scrollable >
                    <v-btn flat color="primary" @click="menuCheckInDate = false" >Cancel</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="$refs.menuCheckInDate.save(checkInDate)">OK</v-btn>
                </v-date-picker>
                </v-menu> 
              </v-flex>
              <v-flex lg2>
                <v-menu
                    ref="menuCheckOutDate"
                    v-model="menuCheckOutDate"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="checkOutDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                >
                <template v-slot:activator="{ on }">
                    <v-text-field
                    v-model="checkOutDate"
                    label="Check-out date"
                    prepend-icon="event"
                    readonly
                    required
                    :rules="[v => !!v || 'Check-out date is required']"
                    v-on="on"
                    ></v-text-field>
                </template>
                <v-date-picker v-model="checkOutDate" no-title scrollable >
                    <v-btn flat color="primary" @click="menuCheckOutDate = false" >Cancel</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="$refs.menuCheckOutDate.save(checkOutDate)">OK</v-btn>
                </v-date-picker>
                </v-menu> 
              </v-flex>
              <v-flex lg2>
                <v-select
                :items="people"
                label="Number of people"
                v-model="numOfPeople"
                prepend-icon="people"
                required
                :rules="[v => !!v || 'Number of people is required']"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!form" @click="searchHotels">Search</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
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
  </div>
</template>

<script>
import HotelDestinationsController from "@/controllers/hotelsDestinations.controller.js";

export default {
  name: "SearchHotelForm",
  components: {
  },
  data:() => ({
    form: true,
    
    menuCheckInDate: false,
    menuCheckOutDate: false,

    checkInDate: null,
    checkOutDate: null,

    hotelDestination: "",
    destinations: [],
    people: [1,2,3,4,5,6],
    numOfPeople: 0,

    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
  }),
  created() {
    HotelDestinationsController.get()
      .then((response) => {
        response.data.forEach(element => {
          this.destinations.push(element.name);
        });
      });
  },
  methods: {
    searchHotels(){

    },
    
    validate() {

    }
  }
}
</script>


