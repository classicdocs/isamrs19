<template>
  <div>
    <v-container >
      <v-layout row wrap>
        <v-flex lg6 md6 sm12 xs12>
          <v-text-field
            label="First name"
            v-model="passenger.firstname"
            solo
          ></v-text-field>
          <v-text-field
            label="Last name"
            v-model="passenger.lastname"
            solo
          ></v-text-field>
          <v-text-field
            label="Passport"
            v-model="passenger.passport"
            solo
          ></v-text-field>
        </v-flex>
        <v-flex lg6 md6 sm12 xs12>
          <v-text-field
            label="Email"
            v-model="passenger.email"
            solo
          ></v-text-field>
          <v-text-field
            label="Phone"
            v-model="passenger.phone"
            solo
          ></v-text-field>
          <v-text-field
            label="Address"
            v-model="passenger.address"
            solo
          ></v-text-field>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

import store from "@/store";
import Friend from "@/models/Friend.js";
import {mapGetters} from 'vuex';

export default {
  name: "PassengerInfo",
  props: ["passenger"],
}
</script>

<style>

</style>
