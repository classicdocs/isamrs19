<template>
 
 <div>
    
    <v-card>
          <v-container>
          <v-stepper v-model="step" vertical>
            <v-stepper-header>
              <v-stepper-step step="1" :complete="step > 1">Select rooms</v-stepper-step>
              <v-divider></v-divider>
              <v-stepper-step step="2" :complete="step > 2">Overview</v-stepper-step>
              <v-divider></v-divider>
            </v-stepper-header>
            <v-stepper-items>
              <!-- <v-stepper-content step="1">
                <v-layout col span>
                  <v-flex>
                    <v-menu
                      ref="menuCheckInDate"
                      v-model="menuCheckInDate"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="checkInDate"
                      lazy
                      transition="scale-transition"
                      offset-y
                      full-width
                      min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="checkInDate"
                        label="Check-in date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Check-in date is required']"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="checkInDate" no-title scrollable >
                      <v-btn flat color="primary" @click="menuCheckInDate = false" >Cancel</v-btn>
                      <v-spacer></v-spacer>
                      <v-btn flat color="primary" @click="$refs.menuCheckInDate.save(checkInDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu> 
                  </v-flex>
                  <v-flex></v-flex>
                  <v-flex>
                    <span><v-subheader>Number of overnights</v-subheader></span>
                    <number-input v-model="numOfovernights" :min="0" inline center controls  :rules="[v => !!v || 'Num of overnights is required']"></number-input>
                  </v-flex>
                  
                 </v-layout>


                <v-btn color="primary" @click.native="continueTo(2)">Continue</v-btn>
              </v-stepper-content> -->
              <v-stepper-content step="1">
                  
                <v-layout row wrap v-if=" myHotel != null">

                    <v-flex lg12 md12 sm12 xs12 
                    v-for="(floor,floorNUM) in myHotel.floors" :key="floorNUM">
                    
                    <v-btn flat >Floor {{floor.level }}</v-btn>
                    
                    <v-btn small 
                        v-for="(room,roomNUM) in floor.roomsOnFloor" :key="roomNUM"
                        @click="pickRoom(room, floor)" 
                    >
                    {{room.numberOfBeds}}  -  
                        
                    <v-icon > airline_seat_individual_suite</v-icon>
                    </v-btn>
                    
                    <v-divider v-if="floorNUM < myHotel.floors.length" :key="`divider-${floorNUM}`"></v-divider>
                    </v-flex>
                </v-layout>

                <v-layout row wrap>
                    <v-flex lg10 md10 sm12 xs12 v-for="(room, number) in pickedRooms" :key="number"> 
                    <h4>Floor: {{room.hotelFloor.level}} Number: {{room.roomNumber}} </h4>
                    </v-flex>
                </v-layout>

                <v-btn flat @click.native="step = 1">Previous</v-btn>
                <v-btn color="primary" @click.native="step = 3">Continue</v-btn>
                
              </v-stepper-content>
              <v-stepper-content step="3">
                
                
                <v-btn flat @click.native="step = 2">Previous</v-btn>
                <v-btn color="primary" @click.prevent="submit">Save</v-btn>
                
              </v-stepper-content>
            </v-stepper-items>
          </v-stepper>         
              
          <v-dialog v-model="reserveRoom" max-width="500px">
              <reserve-room v-bind:room="this.pickedRoom"
              v-on:book="closeReserveRoom($event)"
              v-on:cancel  ="reserveRoom = false">
             </reserve-room>
          </v-dialog>
        </v-container>    
    
    <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
    >
      {{snackbar.msg}}
      <v-btn
          dark
          flat
          @click="snackbar.show = false"
      >
      Close
      </v-btn>
    </v-snackbar>


    </v-card>


</div>
</template>

<script>

import Hotel from "@/models/Hotel"; 
import store from "@/store";

import ReserveRoom from "@/components/HotelReservation/ReserveRoom.vue";
import Room from "@/models/Room";

export default {
    name: "ReservationForm",
    // props: ['hotel'],
    components: {
      'reserve-room' : ReserveRoom
    },
    data: () => ({
      hotel: new Hotel(),
      checkInDate: null,
      step:1,
      reserveRoom: false,

      menuCheckInDate: false,

      numOfovernights: 0,

      pickedRooms: [],
      pickedRoom: new Room(),
      notAvailableRooms: [],

      snackbar: {
        show: false,
        color: "",
        msg: "",
      },

    }),   
    computed: {
      myHotel: function() {
        this.hotel = store.getters.newHotel
        if(store.getters.newHotel != null){
          this.hotel.floors.sort(function(a, b){return a.level - b.level});
          this.hotel.floors.forEach(floor => {
            floor.roomsOnFloor.sort(function(r1, r2){return r1.roomNumber - r2.roomNumber});
          });
        }
        return this.hotel;
      }
    }, 
    methods: {
      pickRoom(room, floor){
        this.pickedRoom = JSON.parse(JSON.stringify(room));
        this.pickedRoom.hotelFloor = JSON.parse(JSON.stringify(floor));
        this.pickedRoom.hotelFloor.roomsOnFloor = [];
        this.reserveRoom = true;          
      },
      closeReserveRoom(data){
        this.reserveRoom = false;
        this.addRoom();
        this.showSnackbar(data);
      },
      addRoom(){
          var exists = false;
          if(this.pickedRooms.length != 0){
            this.pickedRooms.forEach(room => {
            if(room.hotelFloor.level == this.pickedRoom.hotelFloor.level && 
              room.roomNumber == this.pickedRoom.roomNumber){
                exists = true;
              }
            })
            if(!exists){
              this.pickedRooms.push(this.pickedRoom);
            }
          }else{
            this.pickedRooms.push(this.pickedRoom);
          }
      },
      continueTo(number){
        if(number == 2){
          if(this.checkInDate != null && this.numOfovernights != 0){
            this.checkDates();
            this.step = 2;
          }
        }else if(number == 3){
            //TODO
        }
        
      },
      getMaxRooms (number){
        var numOfRooms = 0;
        if(this.myHotel != null){
          this.myHotel.floors.forEach(floor => {
            floor.roomsOnFloor.forEach(room => {
              if(room.numberOfBeds == number){
                if(room.roomTaken.length == 0){
                  numOfRooms++;
                }
              }
            })
          })
        }
        return numOfRooms;
      },
      clearRooms(){
          if(this.myHotel != null){
            this.notAvailableRooms.forEach(room =>{
              this.myHotel.floors.forEach(floor => {
                var idx = 0;
                floor.roomsOnFloor.forEach(singleRoom => {
                  if(singleRoom.id == room.id){
                    idx = 1;
                  }
                })
                // var idx = floor.roomsOnFloor.indexOf(room);
                if(idx == 1){
                  idx = floor.roomsOnFloor.indexOf(room);
                  floor.roomsOnFloor.splice(idx,1);
                }
              })
            })
          }
      },
      checkDates(){
        var startDate = new Date(this.checkInDate);
        var endDate = new Date();
        endDate.setDate(startDate.getDate() + this.numOfovernights);

        if(this.myHotel != null){

          this.myHotel.floors.forEach(floor => {
            floor.roomsOnFloor.forEach(room => {
              room.roomTaken.forEach(takenDate => {

                var startTakenDate = new Date(takenDate.startDate);
                var endTakenDate = new Date(takenDate.endDate);

                if(endDate < startTakenDate || startDate > endTakenDate){
                    if(this.notAvailableRooms.indexOf(room) == -1){
                      this.notAvailableRooms.push(room);
                    }
                }
              })
            })
          })
        }
        this.clearRooms();
      },

      showSnackbar(obj) {
        this.snackbar.color = obj.color;
        this.snackbar.msg = obj.msg;
        this.snackbar.show = true;
      }
      }
}
</script>