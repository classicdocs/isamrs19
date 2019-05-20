<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>REVIEW</h2>
      </v-card-title>
      <v-card-text>
        <v-data-table
        :headers="headers"
        :items="passengers"
        hide-actions
        class="elevation-1"
      >
      <template v-slot:items="props">
        <td class="text-xs-left">{{ props.item.firstname}}</td>
        <td class="text-xs-left">{{ props.item.lastname}}</td>
        <td class="text-xs-left">{{ props.item.passport }}</td>
        <td class="text-xs-left">{{ props.item.email }}</td>
        <td class="text-xs-left">{{ props.item.phone }}</td> 
        <td class="text-xs-left">{{ props.item.address }}</td>
      </template>
      </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>

import store from "@/store";
import {mapGetters} from 'vuex';
import Friend from "@/models/Friend.js";
export default {
  name: "Review",
  data:() => ({
    headers: [
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: 'Passport number', value: 'passport' },
      { text: 'Email', value: 'email' },
      { text: 'Phone number', value: 'phone' },
      { text: 'Address', value: 'address' },
    ],
  }),
  computed: {
    ...mapGetters({
      passengers: 'passengers',
      myInfo: 'myInfo',
    }),
  }
}
</script>

<style>

</style>
