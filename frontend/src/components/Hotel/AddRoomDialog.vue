<template>
  <div>
    <v-dialog
      v-model="addFormDialog"
      max-width="auto"
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
        <span class="headline">Pick position for new room</span>
      </v-card-title>
        <v-card-text>

          <v-layout row wrap>
            <v-flex lg10 md10 sm12 xs12>
              <v-layout row wrap>

                Hotel {{hotel.name}} configuration
                <v-flex lg12 md12 sm12 xs12 
                v-for="floor in hotel.numOfFloors" :key="floor">
                  
                  <v-btn flat>Floor {{floor}}</v-btn>
                  
                   <v-btn small 
                      v-for="(roomPosition,index) in hfp.floors[floor-1].positions" :key="index"
                      :disabled= "roomPosition.exists"
                      @click="pickRoom(floor,roomPosition.number)" 
                   >
                   {{roomPosition.number}}</v-btn>
                
                <v-divider v-if="floor <= hotel.numOfFloors" :key="`divider-${floor}`" class="divider"></v-divider>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex row wrap>
              <h2>New room will be in place:</h2>
                <h3> Floor: {{pickedPosition.level}}, Room number: {{pickedPosition.number}} </h3>
            </v-flex>

            <v-layout row wrap> 
               <v-flex>
                <h2>Number of beds in room:</h2>
                <number-input v-model="numberOfBeds" :min="1" :max="5" inline center controls></number-input>
              </v-flex>
            </v-layout>


          </v-layout>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="addFormDialog = false">Close</v-btn> 
          <!-- <v-btn @click="resetValidation">Reset Validation</v-btn> -->
          <v-btn @click="reset">Reset Form</v-btn>
          <v-btn :disabled="!selected" color="success" @click="validate">Add</v-btn>
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
import { returnStatement } from '@babel/types';

import store from "@/store";


export default {
  name: "AddRoomDialog",
  props: ['hotel'],
  data: () => ({
    form: true,
    selected: false,
    addFormDialog: false,
    room: new Room(),  
  
    pickedPosition: new RoomPosition(),
    numberOfBeds: 0,



    hfp: new Hotel_Floor_Position(),
    fp: new Floor_Position()
  }),
  watch: {
    hotel : function(){
      this.setDefault();
    }
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        var room = new Room();
        room.roomNumber = this.pickedPosition.number;
        room.numberOfBeds = this.numberOfBeds;

        var floorLVL = 0;

        this.hotel.floors.forEach(floor => {
          if(floor.level == this.pickedPosition.level){
            room.hotelFloor = floor;
            floorLVL = floor.level;
          }
        });
        
        HotelController.addRoom(this.$route.params.id, room)
          .then((response) => {
            this.hotel.floors.forEach(floor => {
              if(floor.level == floorLVL){
                floor.roomsOnFloor.push(room);
              }
            });

            this.setPositions();
            this.reset();

            store.commit("newHotel", this.hotel);

            this.$emit("finished", {msg: "Room successfully added", color: "success"})
            this.addFormDialog = false;
          })
          .catch((response) => {
            this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
          })

      }
    },
    reset() {
      this.$refs.form.reset();
      this.pickedPosition.level = 0;
      this.pickedPosition.number = 0;
      this.numberOfBeds = 1;
      this.selected = false;
    },
    setDefault(){
      this.hfp = new Hotel_Floor_Position();
      this.hfp.numOfFloors = this.hotel.numOfFloors;
      this.hotel.floors.sort(function(a, b){return a.level - b.level});
        this.hotel.floors.forEach(floor => {
            floor.roomsOnFloor.sort(function(r1, r2){return r1.roomNumber - r2.roomNumber});
        });
      this.setPositions();
    },
    setPositions(){
      var floorNUM = 0;

      this.hfp = new Hotel_Floor_Position();
      this.hfp.numOfFloors = this.hotel.numOfFloors;

      // Iteriram kroz sve spratove hotela
      this.hotel.floors.forEach(f => {   floorNUM++;

        this.fp = new Floor_Position();
        this.fp.level = f.level;
        // Prolazim kroz sve moguce pozicije u hotelu
        for(var roomNUM = 1; roomNUM <= this.hotel.roomsByFloor; roomNUM++){
          var position = new RoomPosition();
          // Inicijalno pozicija je false, odnosno prazna
          position.level = floorNUM;   position.exists = false;    position.number = roomNUM

          this.hotel.floors.forEach(floor =>{
            if (floor.level == floorNUM){
              floor.roomsOnFloor.forEach(room => {
                // Ukoliko tu postoji soba, pozicija postaje true
                if(room.roomNumber == roomNUM){   
                  position.exists = true;   
                  }
              }); 
            }
          });
          this.fp.positions.push(position);
        }
        this.hfp.floors.push(this.fp);
      });
    },

    pickRoom(floor,roomNumber){
      this.pickedPosition.level = floor;
      this.pickedPosition.number = roomNumber;
      this.selected = true;
    },
  }
};
</script>
<style>

.divider {
  border-width: 7px;
  border-color: dodgerblue;
}

</style>