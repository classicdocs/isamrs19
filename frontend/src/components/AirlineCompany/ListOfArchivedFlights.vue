<template>
  <v-dialog
    v-model="dialog"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn flat v-on="on" >Archived flights</v-btn>
  </template>
  <v-card>
    <v-card-title primary-title>
     <span class="headline">List of archived flights</span>
    </v-card-title>
    <v-card-text>
      <v-data-table
          :headers="headers"
          :items="flights"
          hide-actions
          class="elevation-1"
          >
          <template v-slot:items="props">
            <td class="text-xs-center">{{ props.item.id }}</td>
            <td class="text-xs-center">{{ props.item.startDestination.name }}</td>
            <td class="text-xs-center">{{ props.item.finalDestination.name }}</td>
            <td class="text-xs-center">{{ props.item.departureDate }}</td>
            <td class="text-xs-center">{{ props.item.departureTime }}</td>
            <td class="text-xs-center">{{ props.item.landingDate }}</td>
            <td class="text-xs-center">{{ props.item.landingTime }}</td>
            <td class="text-xs-center">{{ props.item.flightTimeHours + "h " + props.item.flightTimeMinutes +"min"}}</td>
            <td class="text-xs-center">{{ props.item.airplane.model }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceFirst }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceBusiness }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceEconomy }}</td>
            <td class="text-xs-center">
              <v-rating id="airlineStars" :readonly="true" :value="props.item.averageRating" half-increments ></v-rating>
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

import AirlineCompanyController from "@/controllers/airline-company.controller";

export default {
  name: "ListOfArchivedFlights",
  data:() => ({
    dialog: false,
    flights: [],
    headers: [
        { text: 'ID', align: 'center',value: 'id' },
        { text: 'Start destination', value: 'startDestination' , align: 'center'},
        { text: 'Final destination', value: 'finalDestination' , align: 'center'},
        { text: 'Departure date', value: 'departureDate' , align: 'center'},
        { text: 'Departure time', value: 'departureTime' , align: 'center'},
        { text: 'Landing date', value: 'landingDate' , align: 'center'},
        { text: 'Landing time', value: 'landingTime' , align: 'center'},
        { text: 'Flight time', value: 'flightTime' , align: 'center'},
        { text: 'Airplane', value: 'airplane' , align: 'center'},
        { text: 'First ', value: 'ticketPriceFirst' , align: 'center'},
        { text: 'Business', value: 'ticketPriceBusiness' , align: 'center'},
        { text: 'Economy', value: 'ticketPriceEconomy' , align: 'center'},
        { text: 'Rating', value: 'rating', align: 'center'},
      ],
  }),
  mounted() {
    this.getFlights();
  },
  methods: {
    getFlights() {
       AirlineCompanyController.getArchivedFlights(this.$route.params.id)
      .then((response) => {
        console.log(response.data);
        response.data.forEach(element => {
          this.flights.push(element);
        });
      })
      .catch((error) => {
        alert(error.response.data);
      })
    }
  }
}
</script>

<style>
#airlineStars {
  width: 250px;
}
</style>
