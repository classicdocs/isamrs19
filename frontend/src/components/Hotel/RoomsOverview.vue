<template>
  <div>
    <v-card class="card">
      <v-form
          ref="form"
          v-model="form"
        >

        <v-toolbar flat color="white">
          <v-toolbar-title>Room configuration</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="search"
            label="Search rooms" 
            single-line
            hide-details
          ></v-text-field>


        </v-toolbar>

        <v-data-table :headers="headers" :items="myRooms" :search="search"
          class="elevation-1" :expand="expand" item-key="roomNumber"
        >
          <template v-slot:items="props">
          <tr @click="props.expanded = !props.expanded">
            <td class="text-xs-center">{{ props.item.hotelFloor.level }}</td>
            <td class="text-xs-center">{{ props.item.roomNumber }}</td>

            <td class="text-xs-center" v-if="props.item.numberOfBeds == 1">1 - ONE</td>
            <td class="text-xs-center" v-if="props.item.numberOfBeds == 2">2 - TWO</td>
            <td class="text-xs-center" v-if="props.item.numberOfBeds == 3">3 - THREE</td>
            <td class="text-xs-center" v-if="props.item.numberOfBeds == 4">4 - FOUR</td>
            <td class="text-xs-center" v-if="props.item.numberOfBeds == 5">5 - FIVE</td>

            <td class="text-xs-center" v-if="props.item.roomTaken.length == 0">FREE</td>
            <td class="text-xs-center" v-if="props.item.roomTaken.length != 0">HAS RESERVATIONS</td>
            <td class="text-xs-center">
              <v-btn color="info" @click="showRoom(props.item.id)">
                <v-icon left>more_horiz</v-icon>
                  details
              </v-btn>
              </td>

          </tr>
          </template>

          <template v-slot:no-results>
            <v-alert :value="true" color="error" icon="warning">
              Your search for "{{ search }}" found no results.
            </v-alert>
          </template>          
        </v-data-table>
        <v-card-text>
        </v-card-text>

        <v-dialog v-model="roomInfo" max-width="500px">
            <room-info v-bind:room="this.room">
            </room-info>
        </v-dialog>


      </v-form>
    </v-card>
  </div>
</template>

<script>
import HotelsController from "@/controllers/hotels.controller.js";
import RoomInfo from "@/components/Hotel/RoomInfo.vue";
import Room from "@/models/Room";
import store from"@/store";

export default {
  name: "RoomsOverview",
  components: {
    'room-info' : RoomInfo
  },
  data:() => ({
    search: '',
    expand: false,
    roomInfo: false,

    headers: [
          {
            text: 'Floor',
            align: 'center',
            value: 'hotelFloor.level'
          },
          {
            text: 'Room number',
            align: 'center',
            value: 'roomNumber'
          },
          { text: 'Beds', value: 'numberOfBeds' , align: 'center'},
          { text: 'Booked', value: 'booked' , align: 'center'},
          { text: 'Average rate', value: 'avgRate' , align: 'center'}
        ],
    rooms: [],
    room: new Room(),
    form : true,
    
    snackbar: {
      show: false,
      color: "",
      msg: "",
    }

  }),
  computed: {
      myRooms: function() {
        var hotel = store.getters.newHotel;
        // console.log("newHotel")
        // console.log(hotel);
        // var allRooms = store.getters.allRooms;
        // if(allRooms == null){
        //   allRooms = []
        // }
        // return allRooms;

        this.rooms = [];

        if(store.getters.newHotel != null){
            hotel.floors.forEach(floor => {
              floor.roomsOnFloor.forEach(storeRoom => {
                storeRoom.hotelFloor = floor;
                if(storeRoom.roomTaken == null){
                  storeRoom.roomTaken = [];
                }
                if(storeRoom.specialPrices == null){
                  storeRoom.specialPrices = [];
                }
                this.rooms.push(storeRoom);
              })
            })
        }
        return this.rooms;
      },
  },
  methods: {
    showRoom(roomID){
      this.myRooms.forEach(room => {
          if(room.id == roomID){
              this.room = room;
              this.roomInfo = true;
          }
      })
    },
    
  }
}
</script>
<style>
.text-xs-center{
  font-size: xx-large;
}
</style>

