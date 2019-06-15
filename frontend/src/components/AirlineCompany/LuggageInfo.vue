<template>
  <v-dialog
    v-model="dialog"
    max-width="800px"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn color="primary" dark v-on="on">Luggage Information</v-btn>
  </template>
  <v-card>
    
    <v-card-title primary-title>
      
      <span class="headline">Define luggage information</span>
      <v-spacer></v-spacer>
      <add-new-luggage v-on:newLuggage="newLuggage($event)"/>
      
    </v-card-title>
    <v-card-text>
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
          <v-btn flat @click="deleteLuggage(props.item.id)">Delete</v-btn>
        </td>
      </template>
      </v-data-table>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
    </v-card-actions>
  </v-card>
    
  </v-dialog>
</template>

<script>

import store from "@/store";
import AirlineCompanyController from "@/controllers/airline-company.controller";

import AddNewLuggageVue from './AddNewLuggage.vue';
export default {
  name: "LuggageInfo",
  components: {
    'add-new-luggage' : AddNewLuggageVue
  },
  data:() => ({
    dialog: false,
    luggage: [],
    headers: [
      { text: 'Id', align: 'center',value: 'id' },
      { text: 'Name', value: 'name' , align: 'center'},
      { text: 'Dimensions', value: 'dimensions' , align: 'center'},
      { text: 'Weight', value: 'weight' , align: 'center'},
      { text: 'Price', value: 'price' , align: 'center'},
      { text: "Action", value: 'action', align: 'center'}
    ],
  }),
  mounted() {
    this.getLuggage();
  },
  methods: {
    getLuggage() {
      AirlineCompanyController.getLuggage(this.$route.params.id)
        .then((response) => {
          this.luggage = response.data;
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color: "error"})
        })
    },
    newLuggage(data) {
      this.luggage.push(data);
    },
    deleteLuggage(id) {
      AirlineCompanyController.deleteLuggage(this.$route.params.id, id)
        .then((response) => {
          
          let ind = -1;
          this.luggage.forEach(l => {
            if (l.id == response.data.id)
              ind = this.luggage.indexOf(l);
          });
          if (ind != -1)
            this.luggage.splice(ind,1);

          store.commit("setSnack", {msg: "You have successfully deleted luggage", color: "success"})
            
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color: "error"})
        })
    },
    getPrice(price) {
      if (price === 0)
        return "FREE";
      else
        return price + "€";
    }
  }
}
</script>

<style>

</style>
