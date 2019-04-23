<template>
  <div>
    <v-dialog
      v-model="addFormDialog"
      max-width="500px"
      persistent
    >
    <template v-slot:activator="{ on }">
      <v-btn color="primary" dark v-on="on">Add new room</v-btn>
    </template>
    <v-card>
      <v-form
        ref="form"
        v-model="form"
        lazy-validation
      >
      <v-card-title primary-title>
        <span class="headline">Add room</span>
      </v-card-title>
        <v-card-text>
          <v-layout > <!--v-bind="binding"-->
              <v-flex>
                Number of beds in room:
                <number-input v-model="room.numberOfBeds" :min="1" :max="5" inline center controls></number-input>
              </v-flex>
              <v-flex>
                <v-text-field label="room number" v-model="room.roomNumber"
                :rules="[v => !!v || 'room number is required']">
                </v-text-field> 
              </v-flex>
            </v-layout>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="addFormDialog = false">Close</v-btn> 
          <!-- <v-btn @click="resetValidation">Reset Validation</v-btn> -->
          <v-btn @click="reset">Reset Form</v-btn>
          <v-btn :disabled="!form" color="success" @click="validate">Add</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    </v-dialog>
  </div>
</template>

<script>

import Room from "@/models/Room";
import Hotel from "@/models/Hotel";
import HotelController from "@/controllers/hotels.controller";

import { resolve } from 'url';

export default {
  name: "AddRoomDialog",
  data: () => ({
    form: true,
    addFormDialog: false,

    room: new Room(),  
    hotel: new Hotel(),
    rooms: [],
    id: 0,
  }),
  created() {
    this.room.hotel.id = this.$route.params.id;

    HotelController.getRooms(this.room.hotel.id)
    .then((response) => {
      response.data.forEach(element => {
        this.rooms.push(element);
      });
    })
    .catch((error) => {
      alert(error);
    })
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        if (this.validateRoomNumber()) {
        HotelController.addRoom(this.$route.params.id, this.room)
        .then((response) => {
          this.addFormDialog = false;
          this.$emit("snack", {msg: "Room successfully added", color: "success"})
        })
        .catch((error) => {
          this.addFormDialog = false;
          this.$emit("snack", {msg: error.response.data, color: "error"})
        })
      } else {
          this.$emit("snack", {msg: "Room with same number already exists!", color: "error"})
        }
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    validateRoomNumber(){
      this.rooms.forEach(singleRoom => {
        if(this.room.roomNumber == singleRoom.roomNumber){
          return false;
        }
      });
      return true;
    }
  }
};
</script>
<style>

</style>