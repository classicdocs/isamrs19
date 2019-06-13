<template>
  <div>
    <v-container >
      <v-layout row wrap>
        <v-flex lg6 md6 sm12 xs12>
          <v-text-field
            label="First name"
            v-model="passenger.firstname"
          ></v-text-field>
          <v-text-field
            label="Last name"
            v-model="passenger.lastname"
            
          ></v-text-field>
          <v-text-field
            label="Passport"
            v-model="passenger.passport"
          ></v-text-field>
        </v-flex>
        
        <v-flex lg6 md6 sm12 xs12>
          <v-text-field
            label="Email"
            v-model="passenger.email"
          ></v-text-field>
          <v-text-field
            label="Phone"
            v-model="passenger.phone"
          ></v-text-field>
          <v-text-field
            label="Address"
            v-model="passenger.address"
          ></v-text-field>
          
        </v-flex>
        <v-flex lg8 md8 sm12 xs12>
          <v-data-table
            :headers="headers"
            :items="luggage"
            hide-actions
            class="elevation-1"
          >
          <template v-slot:items="props">
            <td class="text-xs-center">{{ props.item.id }}</td>
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.dimensions }} cm</td>
            <td class="text-xs-center">{{ props.item.weight }} kg</td>
            <td class="text-xs-center"> {{getPrice(props.item.price)}} </td>
            <td class="text-xs-center">
              <v-checkbox
              v-if="props.item.price != 0"
              primary
              hide-details
              :input-value="props.selected"
              v-model="selectedLuggage"
              :value="props.item"
            ></v-checkbox>
            
            </td>
          </template>
          </v-data-table>
        </v-flex>
        <v-flex lg4 md4 sm12 xs12>
          <h1 style="float:right" >{{getPriceTotal}}</h1>
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
  props: ["passenger", "flightPrice", "luggage"],
  data:() => ({
    headers: [
      { text: 'Id', align: 'center',value: 'id' },
      { text: 'Name', value: 'name' , align: 'center'},
      { text: 'Dimensions', value: 'dimensions' , align: 'center'},
      { text: 'Weight', value: 'weight' , align: 'center'},
      { text: 'Price', value: 'price' , align: 'center'},
      { text: "Action", value: 'action', align: 'center'}
    ],
    selectedLuggage: []
  }),
  beforeMount() {
    this.setLuggage();
  },
  computed: {
    getPriceTotal() {
      let price = "Price: " + this.flightPrice + "€";
      if (this.passenger.luggages != []) {
        this.passenger.luggages.forEach(l => {
          if (l.price !== 0)
            price += " + " + l.price + "€";
        });
      } 
      return price;
    }
  },
  watch: {
    selectedLuggage() {
      this.passenger.luggages = this.selectedLuggage
    },
  },
  methods: {
    getPrice(price) {
      if (price === 0)
        return "FREE";
      else
        return price + "€";
    },
    setLuggage() {
      this.luggage.forEach(l => {
        if (l.price === 0)
          this.selectedLuggage = l;
      });
    }
  }
}
</script>

<style>

</style>
