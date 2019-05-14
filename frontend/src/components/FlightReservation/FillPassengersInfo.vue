<template>
  <div>
    <v-stepper v-model="stepper">
      <v-stepper-header>
        <v-stepper-step :complete="stepper > 1" step="1">Your information</v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step v-for="(item,index) in passengers" :key="index" :complete="stepper > index + 2" :step="index+2"
          >Passenger {{index + 1}} of {{passengers.length}}
        </v-stepper-step>
        <v-divider ></v-divider>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <passenger-info
            v-bind:passenger="myInfo"
          ></passenger-info>
          <v-btn
            style="float:right"
            color="primary"
            @click="stepper = 2"
            :disabled="isDisabled(myInfo)"
          >
            Next
          </v-btn>
        </v-stepper-content>

        <v-stepper-content v-for="(item,index) in passengers" :key="index" :step="index + 2" >
          <passenger-info
            v-bind:passenger="passengers[index]"
          ></passenger-info>
          <v-btn
            color="primary"
            @click="stepper = index + 3"
            style="float:right"
            v-if="stepper < passengersNumber"
            :disabled="isDisabled(passengers[index])"
          >
            Next
          </v-btn>
          <v-btn flat
            @click="stepper = index + 1"
            style="float:right"
          >Back</v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </div>
</template>

<script>

import store from "@/store";
import PassengerInfoVue from './PassengerInfo.vue';
import {mapGetters} from 'vuex';

export default {
  name: "FillPassengersInfo",
  components: {
    'passenger-info': PassengerInfoVue,
  },
  props: ["passengersNumber"],
  data:() => ({
    stepper: 0,
    friends: [],
  }),
  mounted() {
    this.stepper = 0;
  },
  computed: {
    ...mapGetters({
      passengers: 'passengers',
      myInfo: 'myInfo',
    }),
  },
  methods: {
    isDisabled(passenger) {
      if (passenger.firstname === "" || passenger.lastname === "" || passenger.email === "" || passenger.address === "" || 
      passenger.passport === "" || passenger.phone === "") {
        return true;
      } else {
        return false;
      }
    }
  }
}
</script>

<style>

</style>
