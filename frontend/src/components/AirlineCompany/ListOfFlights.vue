<template>
  <div>
    <v-card>
       <v-card-title primary-title>
        <h2>List of flights</h2>
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
        <td class="text-xs-center">{{ props.item.flightTimeHours + " " + props.item.flightTimeMinutes }}</td>
        <td class="text-xs-center">{{ props.item.airplane.model }}</td>
        <td class="text-xs-center">{{ props.item.ticketPriceFirst }}</td>
        <td class="text-xs-center">{{ props.item.ticketPriceBusiness }}</td>
        <td class="text-xs-center">{{ props.item.ticketPriceEconomy }}</td>
        <td class="text-xs-center" v-if="isAdmin">
          <flight-discount v-bind:flight="props.item"></flight-discount>
        </td>
      </template>
      </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import AirlineCompanyController from "@/controllers/airline-company.controller"
import store from "@/store";
import FlightDiscountVue from './FlightDiscount.vue';


export default {
  name:'ListOfFlights',
  components: {
    'flight-discount' : FlightDiscountVue
  },
  data:() => ({
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
      ],
  }),
  computed: {
    isAdmin() {
      return store.getters.isAirlineAdmin;
    },
  },
  mounted() {
    this.getFlights();
  },
  methods: {
    
    getFlights() {
       AirlineCompanyController.getFlights(this.$route.params.id)
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

</style>
