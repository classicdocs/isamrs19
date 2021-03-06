<template>
  <div>
    <v-dialog v-model="addFormDialog" max-width="700px" persistent>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Change room</v-btn>
      </template>
      <v-card>
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-title primary-title>
            <span class="headline">Pick a room</span>
          </v-card-title>
          <v-card-text>
            <v-layout col wrap v-if="myHotel != null">
              <v-layout row wrap>
                <v-flex lg10 md10 sm12 xs12>
                  <v-layout row wrap>
                    <v-flex
                      lg12
                      md12
                      sm12
                      xs12
                      v-for="(floor, floorNUM) in myHotel.floors"
                      :key="floorNUM"
                    >
                      <v-btn flat>Floor {{ floor.level }}</v-btn>

                      <v-btn
                        small
                        v-for="(room, roomNUM) in floor.roomsOnFloor"
                        :key="roomNUM"
                        :disabled="room.roomTaken.length != 0"
                        @click="pickRoom(room, floor)"
                      >
                        {{ room.roomNumber }}</v-btn
                      >

                      <v-divider
                        v-if="floorNUM < myHotel.floors.length"
                        :key="`divider-${floorNUM}`"
                      ></v-divider>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-layout row wrap>
                  <v-flex lg10 md10 sm12 xs12 v-if="selected">
                    <h4>
                      Choosen room is on {{ pickedRoom.hotelFloor.level }}.
                      floor and has room number {{ pickedRoom.roomNumber }}.
                    </h4>
                  </v-flex>
                </v-layout>
              </v-layout>
              <v-layout row wrap>
                <v-flex v-if="selected">
                  <h2 v-if="numberOfBeds == 1">
                    This room has {{ numberOfBeds }} bed.
                  </h2>
                  <h2 v-else>This room has {{ numberOfBeds }} beds.</h2>
                </v-flex>
                <v-flex v-if="selected">
                  <h2>New number of beds in room:</h2>
                  <number-input
                    v-model="pickedRoom.numberOfBeds"
                    :min="1"
                    :max="5"
                    inline
                    center
                    controls
                  ></number-input>
                </v-flex>
              </v-layout>
            </v-layout>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken" flat @click="addFormDialog = false"
              >Close</v-btn
            >
            <!-- <v-btn @click="resetValidation">Reset Validation</v-btn> -->
            <v-btn @click="reset">Reset Form</v-btn>
            <v-btn :disabled="!selected" color="success" @click="validate"
              >Update</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import Room from "@/models/Room";
import RoomPosition from "@/models/RoomPosition";
import Hotel_Floor_Position from "@/models/Hotel_Floor_Position";
import Floor_Position from "@/models/Floor_Position";

import Hotel from "@/models/Hotel";
import HotelFloor from "@/models/HotelFloor";
import HotelController from "@/controllers/hotels.controller";
import { returnStatement } from "@babel/types";

import store from "@/store";

export default {
  name: "ChangeRoomDialog",
  data: () => ({
    form: true,
    selected: false,
    addFormDialog: false,

    hotel: new Hotel(),
    pickedRoom: new Room(),
    pickedFloor: new HotelFloor(),
    numberOfBeds: 0
  }),
  computed: {
    myHotel: function() {
      this.hotel = store.getters.newHotel;
      if (store.getters.newHotel != null) {
        this.hotel.floors.sort(function(a, b) {
          return a.level - b.level;
        });

        this.hotel.floors.forEach(floor => {
          floor.roomsOnFloor.sort(function(r1, r2) {
            return r1.roomNumber - r2.roomNumber;
          });
          floor.roomsOnFloor.forEach(singleRoom => {
            singleRoom.hotelFloor = null;
          });
        });
      }

      return this.hotel;
    }
  },
  created() {
    this.setDefault();
  },
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        HotelController.update(
          this.$route.params.id,
          this.pickedRoom,
          this.pickedRoom.hotelFloor.id
        )
          .then(response => {
            this.myHotel.floors.forEach(floor => {
              if (floor.level == this.pickedRoom.hotelFloor.level) {
                this.pickedRoom.hotelFloor.roomsOnFloor = floor.roomsOnFloor;

                floor.roomsOnFloor.forEach(roomOnFloor => {
                  if (roomOnFloor.id == this.pickedRoom.id) {
                    roomOnFloor.numberOfBeds = this.pickedRoom.numberOfBeds;
                    this.numberOfBeds = this.pickedRoom.numberOfBeds;
                  }
                });
              }
            });
            this.reset();
            this.$emit("finished", {
              msg: "Room successfully updated",
              color: "success"
            });
          })
          .catch(response => {
            this.$emit("finished", {
              msg: "Error! Something went wrong...",
              color: "error"
            });
          });
      }
    },
    setDefault() {
      this.pickedRoom.HotelFloor = new HotelFloor();
      this.pickedRoom.HotelFloor.level = 0;
      this.pickedRoom.numberOfBeds = 0;
    },
    reset() {
      this.$refs.form.reset();
      this.pickedRoom.HotelFloor = new HotelFloor();
      this.pickedRoom.HotelFloor.level = 0;
      this.pickedRoom.roomNUM = 0;
      this.numberOfBeds = 1;
      this.selected = false;
    },
    pickRoom(room, floor) {
      this.numberOfBeds = room.numberOfBeds;
      this.pickedRoom = new Room();

      this.pickedRoom.id = room.id;
      this.pickedRoom.roomNumber = room.roomNumber;
      this.pickedRoom.numberOfBeds = room.numberOfBeds;
      this.pickedRoom.roomTaken = room.roomTaken;
      this.pickedRoom.specialPrices = room.specialPrices;

      this.pickedRoom.hotelFloor = new HotelFloor();
      this.pickedRoom.hotelFloor.id = floor.id;
      this.pickedRoom.hotelFloor.level = floor.level;
      this.pickedRoom.hotelFloor.maxRooms = floor.maxRooms;
      this.pickedRoom.hotelFloor.hotel = floor.hotel;

      this.selected = true;
    }
  }
};
</script>
<style></style>
