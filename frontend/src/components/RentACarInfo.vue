<template>
    <div id="info">
        <v-layout id="lay">
            <v-card-text id="heading"><h2> {{ rentacar.name }} </h2></v-card-text>
            <v-container id="rate">
            <v-rating :readonly="true" id="stars" v-model="rentacar.rating" x-large half-increments></v-rating>
            </v-container>
        </v-layout>
        <v-card-text> {{ rentacar.address }} </v-card-text>
        <v-card-text>
        <div>
            <gmap-map v-if="rentacar != null"
                v-bind:center="racPosition"
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
                @click="center=racPosition"

                :position="racPosition"
                :draggable="isAdmin" 
                @drag="updateCoordinates"
                @dragend="dragEnded"
            >
            <GmapInfoWindow :opened="infoWindowShown" @closeclick="infoWindowShown = false">
                {{rentacar.address}}
            </GmapInfoWindow>
            </gmap-marker>

            </gmap-map>
        </div>

        <div>
          <v-btn v-if="mapChanged" @click="saveMapChanges()">Save map changes</v-btn>
          <v-spacer></v-spacer>
          <v-btn v-if="mapChanged" @click="cancelMapChanges()">Cancel map changes</v-btn>
        </div>
        </v-card-text>
        <v-card-text> {{ rentacar.promotionalDescription }} </v-card-text>
    </div>
</template>

<script>

import RentACar from "@/models/RentACar";
import store from "@/store";
import MapLocation from "@/models/MapLocation";

import RentACarController from "@/controllers/rentacar.controller"
import * as VueGoogleMaps from 'vue2-google-maps'

export default {
    name: "RentACarInfo",
    props: ["rentacar"],
    data: () => ({
        name:"",
        address: "",
        promotionalDescription: "",

        mapChanged: false,
        infoWindowShown: false,
        racPosition: {
            lat: 42.55139,
            lng: 21.90028
        },

        mapLocation : new MapLocation()
    }),
    watch: {
        rentacar: function(){
            this.racPosition.lat = this.rentacar.mapLocation.latitude;
            this.racPosition.lng = this.rentacar.mapLocation.longitude;
        },
        mapChanged: function(){
            this.racPosition = {
                lat: this.coordinates.lat,
                lng: this.coordinates.lng,
            };
        }
    },
    computed: {
        isAdmin: function(){
        if (store.getters.activeUserRole === 'RentACar Admin')
        if (this.$route.params.id == store.getters.activeUser.idAdminOf)
            return true;
        else
            return false;
        }
    },
    methods: {
        dragEnded(location){
            this.racPosition = {
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
            this.mapLocation.latitude = this.racPosition.lat;
            this.mapLocation.longitude = this.racPosition.lng;

            RentACarController.changeLocation(this.$route.params.id, this.mapLocation)
            .then(response => {
                this.rentacar.mapLocation.latitude = this.racPosition.lat;
                this.rentacar.mapLocation.longitude = this.racPosition.lng;
            }).catch(error => {
                alert(error);
            })
            this.mapChanged = false;
            },
        cancelMapChanges(){
            this.coordinates = {
                lat: this.rentacar.mapLocation.latitude,
                lng: this.rentacar.mapLocation.longitude,
            };
            this.mapChanged = false;
        }
    },
}
</script>

<style>

#heading {
    max-width: 70%;
}

#stars {
    padding-top: 2%;
}

#rate {
    max-width: 400px;
    min-width: 400px;
}

#lay {
    max-height: 100px;
}

</style>

