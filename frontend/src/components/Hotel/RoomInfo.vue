<template>
  <div>
    <v-dialog v-model="roomInfo" width="500">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on"><v-icon left>more_horiz</v-icon> details</v-btn>
      </template>
      <v-card max-width="auto" >
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-title primary-title>
            <span class="headline">
              <h2 style="vertical-align:middle">
                Room details <v-icon x-large>weekend</v-icon>
              </h2>
              <br />
              <v-subheader
                ><h3>
                  Floor {{ room.hotelFloor.level }}| Number {{ room.roomNumber }}|
                  Beds {{ room.numberOfBeds }}
                </h3></v-subheader
              >
            </span>
          </v-card-title>

          <v-card-text>
            <v-layout row wrap align-center>
              <v-flex xs6>
                <v-subheader
                  ><span><h3>Room image:</h3></span></v-subheader
                >
              </v-flex>
              <v-flex xs6>
                <img
                  id="pic"
                  src="@/assets/oneBed.jpg"
                  v-if="room.numberOfBeds == 1"
                />
                <img
                  id="pic"
                  src="@/assets/twoBeds.jpg"
                  v-if="room.numberOfBeds == 2"
                />
                <img
                  id="pic"
                  src="@/assets/threeBeds.jpg"
                  v-if="room.numberOfBeds == 3"
                />
                <img
                  id="pic"
                  src="@/assets/fourBeds.jpg"
                  v-if="room.numberOfBeds == 4"
                />
                <img
                  id="pic"
                  src="@/assets/fiveBeds.jpg"
                  v-if="room.numberOfBeds == 5"
                />
              </v-flex>
              <v-flex xs6>
                <v-subheader
                  ><span v-if="room.roomTaken.length > 0"
                    ><h3>Dates when room is reserved:</h3></span
                  ></v-subheader
                >
              </v-flex>
              <v-flex xs6>
                <v-list>
                  <v-list-tile
                    v-for="(item, index) in room.roomTaken"
                    :key="index"
                  >
                    <v-list-tile-content>
                      <v-list-tile-title
                        v-text="getDates(item)"
                      ></v-list-tile-title>
                    </v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-flex>
              <v-spacer></v-spacer>

              <v-flex xs6>
                <v-subheader
                  ><span v-if="room.specialPrices.length > 0"
                    ><h3>Dates when room has special prices:</h3></span
                  ></v-subheader
                >
              </v-flex>
              <v-flex xs6>
                <v-list>
                  <v-list-tile
                    two-line
                    v-for="(item, index) in room.specialPrices"
                    :key="index"
                  >
                    <v-list-tile-content>
                      <v-list-tile-title
                        >{{ item.startDate }} -
                        {{ item.endDate }}</v-list-tile-title
                      >
                      <v-list-tile-sub-title
                        >Price: {{ item.price }}</v-list-tile-sub-title
                      >
                    </v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-flex>
              <v-flex xs6>
                <v-btn v-if="room.roomTaken.length == 0" @click="removeRoom">delete room <v-icon>clear</v-icon></v-btn>
              </v-flex>
            </v-layout>
          </v-card-text>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import Hotel from "@/models/Hotel";
import HotelsController from "@/controllers/hotels.controller.js";
import store from "@/store";
import { returnStatement } from "@babel/types";

export default {
  name: "RoomInfo",
  props: ["room"],

  data: () => ({
    form: true,
    roomInfo: false,
    datesWhenTaken: []
  }),
  methods: {
    getDates(roomTaken) {
      return roomTaken.startDate + "-" + roomTaken.endDate + "\n";
    },
    removeRoom(){
      if(this.room.roomTaken.length == 0){
        HotelsController.deleteRoom(this.$route.params.id, this.room.id)
        .then(response => {
          var hotel = store.getters.newHotel;
          hotel.floors.forEach(floor => {
            var idx = -1;

            floor.roomsOnFloor.forEach(r => {
              if(r.id == this.room.id){
                idx = floor.roomsOnFloor.indexOf(r);
              }
            })

            if(idx != -1){
              floor.roomsOnFloor.splice(idx,1);
            }
          })
          store.commit('newHotel', hotel);
          this.roomInfo = false;
          store.commit("setSnack", {
            msg: "You have successfully deleted room",
            color: "success"
          });
        })
      }
    }
  },
  watch: {
    room: function() {
      this.room.roomTaken.forEach(element => {
        this.datesWhenTaken.push(element.startDate + " / " + element.endDate);
      });
    }
  }
};
</script>

<style>
.divider {
  border-width: 3px;
  border-color: dodgerblue;
}
</style>
