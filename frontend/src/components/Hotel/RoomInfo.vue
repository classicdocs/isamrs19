<template>
  <div>
    <v-card max-width="auto">
      <v-form ref="form" v-model="form" lazy-validation>
          
          <v-card-title primary-title>
            <span class="headline">
              <h2>Room details <v-icon x-large>weekend</v-icon></h2><br>
              <h3>Floor {{room.hotelFloor.level}}|
              Number {{room.roomNumber}}|
              Beds {{room.numberOfBeds}}</h3></span>
          </v-card-title>

         <v-card-text>
            <v-layout justify-center justify-space-around >
            <v-flex >
                <span v-if="room.roomTaken.length>0"><h3>Dates when room is reserved: </h3></span>
                <v-list>
                <v-list-tile
                  v-for="(item,index) in room.roomTaken"
                  :key="index"
                >
                  <v-list-tile-content>
                    <v-list-tile-title v-text="getDates(item)"></v-list-tile-title>
                  </v-list-tile-content>
                </v-list-tile>
                </v-list>

            </v-flex>

            </v-layout>
            <v-layout>
              <v-flex>
              <span v-if="room.specialPrices.length>0"><h3>Dates when room has special prices: </h3></span>
                <v-list>
                <v-list-tile two-line
                  v-for="(item,index) in room.specialPrices"
                  :key="index"
                >
                <v-list-tile-content>
                <v-list-tile-title>{{item.startDate}} - {{item.endDate}}</v-list-tile-title>
                <v-list-tile-sub-title>Price: {{item.price}}</v-list-tile-sub-title>
                </v-list-tile-content>
                </v-list-tile>
                </v-list>
            </v-flex>
            </v-layout>
        </v-card-text>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";
import { returnStatement } from '@babel/types';


export default {
  name: "RoomInfo",
  props: ['room'],


  data: () => ({
    form: true,
    datesWhenTaken: [],
    headersSpecialPrices: [
          { text: 'Start date', value: 'startDate' },
          { text: 'End date', value: 'endDate' },
          { text: 'Price', value: 'price' },
        ],
  }),
  methods: {
    getDates(roomTaken){
      return roomTaken.startDate + "-" + roomTaken.endDate + "\n";
    },
    getSpecial(specialPrice){
      return "Start date: " + specialPrice.startDate + "End date: " +  specialPrice.endDate + "\n" + "Price: " +  "(" + specialPrice.price + ")";
    }
  },
  watch: {
    room: function(){
      console.log(this.room);
      this.room.roomTaken.forEach(element => {
        this.datesWhenTaken.push(element.startDate + " / " + element.endDate);
      });
      console.log("duzina sp: " + this.room.specialPrices.length)
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

