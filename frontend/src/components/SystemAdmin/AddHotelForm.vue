<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new hotel <v-icon x-large>hotel</v-icon></span>
          </v-card-title>

          <!-- LABELE -->
          <v-card-text>
            <v-container>
              <v-text-field label="hotel name" v-model="hotel.name"
              :rules="[v => !!v || 'hotel name is required']">
              </v-text-field> 

              <v-layout> 
                <v-flex>
                  <v-select :items="hotelDestinations" v-model="hotel.destination" 
                  label="choose hotel location" :rules="destination_rules" 
                  required></v-select>
                </v-flex>
              </v-layout>

              <v-text-field label="street address" v-model="street"
              :rules="[v => !!v || 'street address is required']">
              </v-text-field> 
              
              <div>
                <v-subheader>You can represent location on map if you want.</v-subheader>
                <h4>latitude = {{lat}} <br>longitude = {{lng}}</h4>
              <gmap-map
                v-bind:center="hotelPosition"
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
                @click="infoWindowShown = true"
              >

                  <!-- v-bind:position="hotelPosition" -->
                
                <gmap-marker
                  v-bind:clickable="true"
                  @click="center=m.position"

                  :position="hotelPosition"
                  :draggable="true" 
                  @drag="updateCoordinates"
                >
                <GmapInfoWindow :opened="infoWindowShown" @closeclick="infoWindowShown = false">
                  {{hotel.address}}
                </GmapInfoWindow>
                </gmap-marker>
              </gmap-map>
            </div>

            <br>
            <div>
              <v-textarea name="promotionalDescription" label="promotional description" 
                v-model="hotel.description" 
                hint="Say something good about hotel services and prices..." box>
              </v-textarea>
              <v-layout row span>
                
                <v-flex>
                  Number of floors
                  <number-input v-model="hotel.numOfFloors" :min="1" :max="5" inline center controls></number-input>
                </v-flex>
                
                <v-flex>
                  Number of rooms by floor
                  <number-input v-model="hotel.roomsByFloor" :min="1" :max="50" inline center controls></number-input>
                </v-flex>
              

              </v-layout>
              </div>
            </v-container>

            

          </v-card-text>
          <!-- DUGMAD -->

          <v-card-actions>
            <v-spacer></v-spacer>
              <v-btn v-on:click="cancel" color="info">CANCEL</v-btn>
              <v-btn v-on:click="reset" color="error">RESET</v-btn>
              <v-btn :disabled="!form" v-on:click="validate" color="success">NEXT</v-btn>
           </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";


import SystemAdminController from "@/controllers/system-admin.controller"
import HotelDestinationsController from "@/controllers/hotelsDestinations.controller.js";
import MapLocation from "@/models/MapLocation";

import store from "@/store";
import { thisExpression } from '@babel/types';

export default {
  name: "AddHotelForm",
  data: () => ({
    form: true,

    country : "",
    city : "",
    street : "",
    hotelDestinations: [],
    destinations: [],
    hotel: new Hotel(),

    destination_rules:[
        v => !!v || 'You need to choose existing location from list'
    ],

    infoWindowShown: false,
    center: {
      lat: 42.55139,
      lng: 21.90028
    },
    hotelPosition: {lat: 42.55139, lng: 21.90028},
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
  created() {
    HotelDestinationsController.get()
      .then((response) => {
        response.data.forEach(element => {
          this.hotelDestinations.push(element.name);
          this.destinations.push(element);
        });
      });
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
        // this.hotel.address = this.country + '/' + this.city + '/' + this.street;
        this.hotel.address = this.street;
        this.hotel.priceList = [];
        this.hotel.floors = [];
        this.hotel.admins = [];
        
        this.hotel.mapLocation = new MapLocation();
        this.hotel.mapLocation.latitude = this.coordinates.lat;
        this.hotel.mapLocation.longitude = this.coordinates.lng;
        
        this.destinations.forEach(dest => {
          if(dest.name == this.hotel.destination){
            this.hotel.destination = dest;
          }
        })

        SystemAdminController.createHotel(this.hotel)
          .then((response) => {
            this.$emit("finished", {msg: "Hotel successfully added", color: "success"})
            store.commit("newHotel", response.data);
            var hotels = store.getters.allHotels;
            hotels.push(response.data);

            store.commit("allHotels", hotels);
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

