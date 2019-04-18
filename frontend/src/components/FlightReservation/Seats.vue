<template>
  <div >
    <v-card
    >
      <v-card-text>
        <h1>Departure flight</h1>
        <v-container >
          <v-layout row wrap>
            <v-flex lg10 md10 sm12 xs12>
              <v-layout row wrap>
                <v-flex lg12 md12 sm12 xs12 v-for="(row, index)  in seatsDeparture" :key="index">
                  <v-btn flat>Row {{index + 1}} </v-btn>
                  <v-btn small 
                  v-for="(seat,index2) in seatsDeparture[index].seats" :key="index2" 
                  :color="seat.color"
                  :disabled="seat.taken"
                  @click="pickSeatDeparture(seat)"
                  >{{seat.colNum}}</v-btn>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg2 md2 sm12 xs12>
              <h2>Seats picked:</h2>
              <ul>
                <li
                  v-for="(seat,ind) in seatsPickedDeparture" :key="ind"
                > <h3> Row: {{seat.rowNum}}, Column: {{seat.colNum}} </h3>
                </li>
              </ul>
            </v-flex>
          </v-layout>
        </v-container>
        <h1 v-if="seatsReturn !== null">Return flight</h1>
        <v-container v-if="seatsReturn !== null">
          <v-layout row wrap>
            <v-flex lg10 md10 sm12 xs12>
              <v-layout row wrap>
                <v-flex  lg12 md12 sm12 xs12 v-for="(row, index)  in seatsReturn" :key="index">
                  <v-btn flat>Row {{index + 1}} </v-btn>
                  <v-btn small 
                  v-for="(seat,index2) in seatsReturn[index].seats" :key="index2" 
                  :color="seat.color"
                  :disabled="seat.taken"
                  @click="pickSeatReturn(seat)"
                  >{{seat.colNum}}</v-btn>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg2 md2 sm12 xs12>
              <h2>Seats picked:</h2>
              <ul>
                <li
                  v-for="(seat,ind) in seatsPickedReturn" :key="ind"
                > <h3> Row: {{seat.rowNum}}, Column: {{seat.colNum}} </h3>
                </li>
              </ul>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>

import store from '@/store';

export default {
  name: "Seats",
  props: ['seatsDeparture', 'seatsReturn', 'passengersNumber'],
  data:() => ({
    seatsPickedDeparture: [],
    seatsPickedReturn: [],
    btnColor: "success",
  }),
  created() {
    this.setColor();
  },
  computed: {
    getColor() {
      return this.btnColor;
    }
  },
  methods: {
    setColor() {
      this.seatsDeparture.forEach(row => {
        row.seats.forEach(seat => {
          if (seat.taken === false)
            seat["color"] = "success";
          else
            seat["color"] = "error"; 
        });
      });
      if (this.seatsReturn === null)
        return;
      this.seatsReturn.forEach(row => {
        row.seats.forEach(seat => {
          if (seat.taken === false)
            seat["color"] = "success";
          else
            seat["color"] = "error"; 
        });
      });
    },
    pickSeatDeparture(seat) {
      let ind = this.seatsPickedDeparture.indexOf(seat);
      if (ind === -1) {
        if (this.seatsPickedDeparture.length >= this.passengersNumber) {
        this.$emit('snack', {msg: "Maximal number of seats you can pick is " + this.passengersNumber, color:"error"});
        return;
        }
        seat.color = "primary";
        this.seatsPickedDeparture.push(seat);
        store.commit('seatsPickedDeparture', this.seatsPickedDeparture);
      } else {
        seat.color = "success";
        this.seatsPickedDeparture.splice(ind,1);
      }
    },
    pickSeatReturn(seat) {
      let ind = this.seatsPickedReturn.indexOf(seat);
      if (ind === -1) {
        if (this.seatsPickedReturn.length >= this.passengersNumber) {
        this.$emit('snack', {msg: "Maximal number of seats you can pick is " + this.passengersNumber, color:"error"});
        return;
        }
        seat.color = "primary";
        this.seatsPickedReturn.push(seat);
        store.commit('seatsPickedReturn', this.seatsPickedReturn);
      } else {
        seat.color = "success";
        this.seatsPickedReturn.splice(ind,1);
      }
    },
  }
}
</script>

<style>
</style>
