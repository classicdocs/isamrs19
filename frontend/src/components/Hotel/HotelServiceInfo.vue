<template>
  <div>
    <v-card>
      <v-card-title><h3 style="color: grey">Hotel: <br></h3><h1> {{hotel.name}}</h1></v-card-title>
      <v-card-text id="info">
        <div v-if="hotel.destination != null"><h3 style="color: gray;">Destination: <br></h3><h3> {{hotel.destination.name}}</h3></div>
        <div>

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

        <div><h3 style="color: gray;">Address: <br></h3><h3> {{hotel.address}}</h3></div>
        <div><h3 style="color: gray;">Description: <br></h3><h3> {{hotel.description}}</h3></div>
        <div><h3 style="color: gray;">Floors: <br></h3><h3> {{hotel.numOfFloors}}</h3></div>
        <div><h3 style="color: gray;">Rooms by floor: <br></h3><h3> {{hotel.roomsByFloor}}</h3></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import * as ol from 'ol';
import Feature from 'ol/Feature.js';
import geom from 'ol/geom.js'
import Map from 'ol/Map.js';
import View from 'ol/View.js';
import TileLayer from 'ol/layer/Tile.js'  
import OSM from "ol/source/OSM"

import * as VueGoogleMaps from 'vue2-google-maps'


export default {
  name: "HotelServiceInfo",
  props: ["hotel"],
  
  data(){
    return{
      infoWindowShown: false,
      center: {
        lat: 42.55139,
        lng: 21.90028
      },
      hotelPosition: {lat: 42.55139, lng: 21.90028},

    };
  },


  methods: {
    updateCoordinates(location) {
        this.coordinates = {
            lat: location.latLng.lat(),
            lng: location.latLng.lng(),
        };
        console.log(location);
    },
  }

};
</script>

<style scoped lang="stylus">
  @import "~ol/ol.css"
  #mapOL
    height 300px

  h3 {
    display: inline-flex;
  }
</style> 
