<template>
  <div>
    <v-card>
      <v-card-title><h3 style="color: grey">Hotel: <br></h3><h1> {{hotel.name}}</h1></v-card-title>
      <div><v-rating id="hotelStars" :readonly="true" v-model="hotel.average_rating" half-increments></v-rating></div>

      <v-card-text id="info">
        <div v-if="hotel.destination != null"><h3 style="color: gray;">Destination: <br></h3><h3> {{hotel.destination.name}}</h3></div>
        
        
        <div>
          <gmap-map v-if="hotel != null"
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
          <gmap-marker 
            v-bind:clickable="true"
            @click="center=hotelPosition"

            :position="hotelPosition"
            :draggable="isAdmin" 
            @drag="updateCoordinates"
            @dragend="dragEnded"
          >
          <GmapInfoWindow :opened="infoWindowShown" @closeclick="infoWindowShown = false">
            {{hotel.address}}
          </GmapInfoWindow>
          </gmap-marker>

          
        </gmap-map>
        </div>

        <div>
          <v-btn v-if="mapChanged" @click="saveMapChanges()">Save map changes</v-btn>
          <v-spacer></v-spacer>
          <v-btn v-if="mapChanged" @click="cancelMapChanges()">Cancel map changes</v-btn>
        </div>

        <div><h3 style="color: gray;">Address: <br></h3><h3> {{hotel.address}}</h3></div>
        <div><h3 style="color: gray;">Description: <br></h3><h3> {{hotel.description}}</h3></div>
        <div><h3 style="color: gray;">Floors: <br></h3><h3> {{hotel.numOfFloors}}</h3></div>
        <div><h3 style="color: gray;">Rooms by floor: <br></h3><h3> {{hotel.roomsByFloor}}</h3></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";
import HotelFloor from "@/models/HotelFloor";
import Room from "@/models/Room";
import MapLocation from "@/models/MapLocation";
import HotelController from "@/controllers/hotels.controller";
import store from "@/store";

import * as VueGoogleMaps from 'vue2-google-maps'


export default {
  name: "HotelServiceInfo",
  props: ["hotel"],
  
  data: () => ({
      mapChanged: false,
      infoWindowShown: false,
      
      hotelPosition: {
        lat: 42.55139,
        lng: 21.90028
      },

      mapLocation : new MapLocation()
  }),
  watch: {
    hotel: function(){
      this.hotelPosition.lat = this.hotel.mapLocation.latitude;
      this.hotelPosition.lng = this.hotel.mapLocation.longitude;
    },
    mapChanged: function(){
      this.hotelPosition = {
          lat: this.coordinates.lat,
          lng: this.coordinates.lng,
      };
    }
  },

  computed: {
    isAdmin: function(){
      if (store.getters.activeUserRole === 'Hotel Admin')
      if (this.$route.params.id == store.getters.activeUser.idAdminOf)
        return true;
      else
        return false;
    }
  },

  methods: {
    dragEnded(location){
      this.hotelPosition = {
          lat: location.latLng.lat(),
          lng: location.latLng.lng(),
      };
    },
    updateCoordinates(location) {
            
      this.coordinates = {
        lat: location.latLng.lat(),
        lng: location.latLng.lng(),
      };
      this.mapChanged = true;

    },
    saveMapChanges(){
      this.mapLocation.latitude = this.hotelPosition.lat;
      this.mapLocation.longitude = this.hotelPosition.lng;

      HotelController.changeLocation(this.$route.params.id, this.mapLocation)
      .then(response => {
        store.commit('newHotel', response.data);
      }).catch(error => {
        alert(error);
      })
      this.mapChanged = false;
    },
    cancelMapChanges(){
      this.coordinates = {
          lat: this.hotel.mapLocation.latitude,
          lng: this.hotel.mapLocation.longitude,
      };
      this.mapChanged = false;
    }
  },
  
};
</script>

<style>
h3 {
  
  display: inline-flex;
}

#hotelStars {
  padding-left: 20px;
}

</style>
