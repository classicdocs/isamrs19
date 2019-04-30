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
        <span class="headline">Pick position for new room</span>
      </v-card-title>
        <v-card-text>

          <v-layout row wrap>
            <v-flex lg10 md10 sm12 xs12>
              <v-layout row wrap>


                <v-flex lg12 md12 sm12 xs12 v-for="floor in hotel.numOfFloors" :key="floor">
                  <v-btn flat>Floor {{floor}}</v-btn>
                  <v-btn small 
                  v-for="roomNumber in hotel.roomsByFloor" :key="roomNumber"
                  :disabled= "checkDisabled(floor,roomNumber)"
                  @click="pickRoom(floor,roomNumber)" 
                   >{{roomNumber}}</v-btn>
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
          <v-btn :disabled="!form" color="success" @click="validate">Add</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
    </v-dialog>
  </div>
</template>

<script>

import Room from "@/models/Room";
import RoomPosition from "@/models/RoomPosition";

import Hotel from "@/models/Hotel";
import HotelFloor from "@/models/HotelFloor";
import HotelController from "@/controllers/hotels.controller";


export default {
  name: "AddRoomDialog",
  //props: ['hotel'],
  data: () => ({
    form: true,
    addFormDialog: false,
    room: new Room(),  
    positions: [],
  
    pickedPosition: new RoomPosition(),
    numberOfBeds: 0,

    hotel: new Hotel(),
    counter: 0,
    isDisabled: false
  }),
  created() {
    HotelController.getHotel(this.$route.params.id)
        .then((response) => {
          this.hotel = response.data;
          this.setPositions();
        })
        .catch(() => {
          alert("Something went wrong with fetching hotel. AddRoomDialog");
        });
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        var room = new Room();
        room.roomNumber = this.pickedPosition.number;

        this.hotel.floors.forEach(floor => {
          if(floor.level == this.pickedPosition.level){
            room.hotelFloor = floor;
          }
        });

        room.numberOfBeds = this.numberOfBeds;

        HotelController.addRoom(this.$route.params.id, room)
          .then((response) => {
            this.$emit("finished", {msg: "Room successfully added", color: "success"})
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
    },
    setPositions(){

      this.pickedPosition.level = 0;
      this.pickedPosition.number = 0;
      // prodjem kroz sve spratove, pa kroz sve sobe, pa ako ima soba neka bice true, ako nema onda false
      var floor = 1;
      // console.log(this.hotel.numOfFloors + " : " + this.hotel.roomsByFloor);
      for (floor = 1; floor <= this.hotel.numOfFloors; floor++) { 
        var room = 1;
        for(room = 1; room <= this.hotel.roomsByFloor; room++){

            var rp = new RoomPosition();
            rp.level = floor;
            rp.number = room;

            // FIXME - NIJE DOBRA PROVERA, NECE DA OPALI NA NULL
            if(typeof this.hotel.floors[floor] !== 'undefined'){
              if(typeof this.hotel.floors[floor].roomsOnFloor[room] !== 'undefined'){
              //if(this.hotel.floors[floor].roomsOnFloor[room].id !== null){
                rp.exists = true; // postoji soba na tom mestu, ne sme tu da doda, dugme mora da bude disable ili error
              }else{
                rp.exists = false;  // ne postoji soba na tom mestu, dugme mora da bude enable i success
              }
            }else{
              rp.exists = false;  // ne postoji soba na tom mestu, dugme mora da bude enable i success
            }
            this.positions.push(rp);
        }
      }

    },
    checkDisabled(floor, roomNumber){
      this.positions.forEach(position => {
        if(position.level == floor && position.number == roomNumber){
          if(position.exists){
            console.log("Room on level: " + position.level + " number: " + position.number + " should be disabled.");
          }else{
            console.log("Room on level: " + position.level + " number: " + position.number + " should NOT be disabled.");
          }

          //this.isDisabled = position.exists;
          return position.exists;
        }
      });
    },
    pickRoom(floor,roomNumber){
      this.pickedPosition.level = floor;
      this.pickedPosition.number = roomNumber;
    }
  }
};
</script>
<style>

</style>