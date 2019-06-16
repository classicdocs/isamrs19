<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new airline company <v-icon x-large>flight_takeoff</v-icon></span>
          </v-card-title>



          <!-- LABELE -->
          <v-card-text>
            <v-container>
              <v-text-field label="airline company name" v-model="airlineCompany.name"
              :rules="[v => !!v || 'airline company name is required']">
              </v-text-field> 

              <v-text-field label="street address" v-model="airlineCompany.address"
              :rules="[v => !!v || 'street address is required']">
              </v-text-field> 
              
              <div>
                <v-subheader>You can represent address on map if you want.</v-subheader>
                <h4>latitude = {{lat}} <br>longitude = {{lng}}</h4>
              <gmap-map
                v-bind:center="airlinePosition"
                v-bind:zoom="7"
                style="height: 225px"
                :options="{
                  zoomControl: false,
                  mapTypeControl: false,
                  scaleControl: false,
                  streetViewControl: false,
                  rotateControl: false,
                  fullscreenControl: true,
                  disableDefaultUi: false,
                }"
              >
                <gmap-marker
                  v-bind:clickable="true"
                  @click="center=airlinePosition"

                  :position="airlinePosition"
                  :draggable="true" 
                  @drag="updateCoordinates"
                >
                </gmap-marker>
              </gmap-map>
            </div>

            <br>
            <div>
              <v-textarea name="promotionalDescription" label="promotional description" 
                v-model="airlineCompany.description" 
                hint="Say something good about airline company services and prices..." box>
              </v-textarea>
            </div>
            </v-container>

            

          </v-card-text>

          <!-- DUGMAD -->

          <v-card-actions>
          <v-spacer></v-spacer>
            <v-btn v-on:click="cancel" color="info">CANCEL</v-btn>
            <v-btn v-on:click="reset" color="error">RESET</v-btn>
            <v-btn :disabled="!form" v-on:click="validate" color="success">ADD</v-btn>
          </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import AirlineCompany from "@/models/AirlineCompany";
import SystemAdminControler from "@/controllers/system-admin.controller";
import MapLocation from "@/models/MapLocation";
import store from "@/store";
import { thisExpression } from '@babel/types';

export default {
  name: "AddAirlineForm",
  data: () => ({
    form: true,

    country : "",
    city : "",
    street : "",

    airlineCompany: new AirlineCompany(),
    center: {
      lat: 42.55139,
      lng: 21.90028
    },
    airlinePosition: {lat: 42.55139, lng: 21.90028},
    coordinates: {lat: 42.55139, lng: 21.90028},

  }),
  computed: {
    lat: function() {
      return this.coordinates.lat;
    },
    lng: function() {
      return this.coordinates.lng;
    }
  },
  methods: {
    updateCoordinates(location) {
        this.coordinates = {
            lat: location.latLng.lat(),
            lng: location.latLng.lng(),
        };
    },
    validate() {
      if(this.$refs.form.validate()) {
        this.airlineCompany.mapLocation = new MapLocation();
        this.airlineCompany.mapLocation.latitude = this.coordinates.lat;
        this.airlineCompany.mapLocation.longitude = this.coordinates.lng;
        


        SystemAdminControler.createAirline(this.airlineCompany)
        .then((response) => {
          var allAirlines = store.getters.allAirlines;
          allAirlines.push(response.data);
          store.commit('allAirlines', allAirlines);
          
          this.$emit("finished", {msg: "Airline company successfully added", color: "success"})
        })
        .catch((response) => {
          this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
        })

      }
    },
    reset() {
      this.$refs.form.reset();
    },
    cancel(){
      this.$emit("cancel")
    },
    
  }
};
</script>

<style>

</style>

