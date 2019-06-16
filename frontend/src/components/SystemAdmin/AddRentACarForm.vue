<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new rent-a-car service  <v-icon x-large>commute</v-icon>
            <v-icon x-large>plus_one</v-icon></span>
          </v-card-title>

          <!-- LABELE -->
          <v-card-text>
            <v-container>
              <v-text-field label="service name" v-model="rentACar.name"
              :rules="[v => !!v || 'rent a car service name is required']">
              </v-text-field> 

              <v-text-field label="street address" v-model="rentACar.address"
              :rules="[v => !!v || 'street address is required']">
              </v-text-field>

              <div>
                <v-subheader>You can represent address on map if you want.</v-subheader>
                <h4>latitude = {{lat}} <br>longitude = {{lng}}</h4>
              <gmap-map
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
              >
                <gmap-marker
                  v-bind:clickable="true"
                  @click="center=racPosition"

                  :position="racPosition"
                  :draggable="true" 
                  @drag="updateCoordinates"
                >
                </gmap-marker>
              </gmap-map>
            </div>

            <br>
            <v-textarea name="promotionalDescription" label="promotional description" 
              v-model="rentACar.promotionalDescription" 
              hint="Say something good about rent-a-car services and prices" box>
            </v-textarea>

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

import RentACar from "@/models/RentACar";
import MapLocation from "@/models/MapLocation";
import SystemAdminController from "@/controllers/system-admin.controller.js"
import store from "@/store";

export default {
  name: "AddRentACarForm",

  data: () => ({
    form: true,
    country: "",
    city: "",
    street : "",
    rentACar: new RentACar(),

    center: {
      lat: 42.55139,
      lng: 21.90028
    },
    racPosition: {lat: 42.55139, lng: 21.90028},
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
        this.rentACar.priceList = [];
        this.rentACar.vehicles = [];
        this.rentACar.branches = [];

        this.rentACar.mapLocation = new MapLocation();
        this.rentACar.mapLocation.latitude = this.coordinates.lat;
        this.rentACar.mapLocation.longitude = this.coordinates.lng;

        SystemAdminController.createRentACar(this.rentACar)
          .then((response) => {
            var allRAC = store.getters.allRentACars;
            if(allRAC != null){
              allRAC.push(response.data);
              store.commit('allRentACars', allRAC);
            }

            this.$emit("finished", {msg: "RentACar successfully added", color: "success"})
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
    }
  }
};
</script>

<style>

</style>

