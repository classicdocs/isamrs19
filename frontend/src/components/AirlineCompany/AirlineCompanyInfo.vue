<template>
  <div>
    <v-card>
      <v-card-title
        ><h3 style="color: grey">Airline-company: <br /></h3>
        <h1>{{ airlineCompany.name }}</h1></v-card-title
      >
      <div>
        <v-rating
          id="airlineStars"
          :readonly="true"
          v-model="airlineCompany.averageRating"
          half-increments
        ></v-rating>
      </div>
      <v-card-text id="info">
        <div>
          <h3 style="color: gray;">Address: <br /></h3>
          <h3>{{ airlineCompany.address }}</h3>
        </div>

        <div>
          <gmap-map
            v-if="airlineCompany != null"
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
              disableDefaultUi: false
            }"
            @click="infoWindowShown = true"
          >
            <gmap-marker
              v-bind:clickable="true"
              @click="center = airlinePosition"
              :position="airlinePosition"
              :draggable="isAdmin"
              @drag="updateCoordinates"
              @dragend="dragEnded"
            >
              <GmapInfoWindow
                :opened="infoWindowShown"
                @closeclick="infoWindowShown = false"
              >
                {{ airlineCompany.address }}
              </GmapInfoWindow>
            </gmap-marker>
          </gmap-map>
        </div>
        <div>
          <v-btn v-if="mapChanged" @click="saveMapChanges()"
            >Save map changes</v-btn
          >
          <v-spacer></v-spacer>
          <v-btn v-if="mapChanged" @click="cancelMapChanges()"
            >Cancel map changes</v-btn
          >
        </div>

        <div>
          <h3 style="color: gray;">Description: <br /></h3>
          <h3>{{ airlineCompany.description }}</h3>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import MapLocation from "@/models/MapLocation";
import AirlineCompanyController from "@/controllers/airline-company.controller";
import store from "@/store";

import * as VueGoogleMaps from "vue2-google-maps";
export default {
  name: "AirlineCompanyInfo",
  props: ["airlineCompany"],
  data: () => ({
    name: "",
    address: "",
    desctiption: "",

    mapChanged: false,
    infoWindowShown: false,

    airlinePosition: {
      lat: 42.55139,
      lng: 21.90028
    },

    mapLocation: new MapLocation()
  }),
  watch: {
    airlineCompany: function() {
      this.airlinePosition.lat = this.airlineCompany.mapLocation.latitude;
      this.airlinePosition.lng = this.airlineCompany.mapLocation.longitude;
    },
    mapChanged: function() {
      this.airlinePosition = {
        lat: this.coordinates.lat,
        lng: this.coordinates.lng
      };
    }
  },

  computed: {
    isAdmin: function() {
      if (store.getters.activeUserRole === "Airline Company Admin")
        if (this.$route.params.id == store.getters.activeUser.idAdminOf)
          return true;
        else return false;
    }
  },

  methods: {
    dragEnded(location) {
      this.airlinePosition = {
        lat: location.latLng.lat(),
        lng: location.latLng.lng()
      };
    },
    updateCoordinates(location) {
      this.coordinates = {
        lat: location.latLng.lat(),
        lng: location.latLng.lng()
      };
      this.mapChanged = true;
    },
    saveMapChanges() {
      this.mapLocation.latitude = this.airlinePosition.lat;
      this.mapLocation.longitude = this.airlinePosition.lng;

      AirlineCompanyController.changeLocation(
        this.$route.params.id,
        this.mapLocation
      )
        .then(response => {
          this.airlineCompany.mapLocation.latitude = this.airlinePosition.lat;
          this.airlineCompany.mapLocation.longitude = this.airlinePosition.lng;
        })
        .catch(error => {
          alert(error);
        });
      this.mapChanged = false;
    },
    cancelMapChanges() {
      this.coordinates = {
        lat: this.airlineCompany.mapLocation.latitude,
        lng: this.airlineCompany.mapLocation.longitude
      };
      this.mapChanged = false;
    }
  }
};
</script>

<style>
h3 {
  display: inline-flex;
}

#airlineStars {
  padding-left: 20px;
}
</style>
