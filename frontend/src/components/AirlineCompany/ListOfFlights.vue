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
            <td class="text-xs-center">
              {{ props.item.startDestination.name }}
            </td>
            <td class="text-xs-center">
              {{ props.item.finalDestination.name }}
            </td>
            <td class="text-xs-center">{{ props.item.departureDate }}</td>
            <td class="text-xs-center">{{ props.item.departureTime }}</td>
            <td class="text-xs-center">{{ props.item.landingDate }}</td>
            <td class="text-xs-center">{{ props.item.landingTime }}</td>
            <td class="text-xs-center">
              {{
                props.item.flightTimeHours +
                  "h " +
                  props.item.flightTimeMinutes +
                  "min"
              }}
            </td>
            <td class="text-xs-center">{{ props.item.airplane.model }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceFirst }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceBusiness }}</td>
            <td class="text-xs-center">{{ props.item.ticketPriceEconomy }}</td>
            <td class="text-xs-center" v-if="isAdmin">
              <flight-discount v-bind:flight="props.item"></flight-discount>
            </td>
            <td class="text-xs-center">
              <v-btn flat @click="archive(props.item.id)" v-if="isAdmin"
                >Archive</v-btn
              >
            </td>
          </template>
        </v-data-table>
        <archived-flights v-if="isAdmin" />
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import FlightController from "@/controllers/flights.controller";
import AirlineCompanyController from "@/controllers/airline-company.controller";
import store from "@/store";
import FlightDiscountVue from "./FlightDiscount.vue";
import ListOfArchivedFlightsVue from "./ListOfArchivedFlights.vue";
import { mapGetters } from 'vuex';

export default {
  name: "ListOfFlights",
  components: {
    "flight-discount": FlightDiscountVue,
    "archived-flights": ListOfArchivedFlightsVue
  },
  data: () => ({
    flights: [],
    headers: [
      { text: "ID", align: "center", value: "id" },
      { text: "Start destination", value: "startDestination", align: "center" },
      { text: "Final destination", value: "finalDestination", align: "center" },
      { text: "Departure date", value: "departureDate", align: "center" },
      { text: "Departure time", value: "departureTime", align: "center" },
      { text: "Landing date", value: "landingDate", align: "center" },
      { text: "Landing time", value: "landingTime", align: "center" },
      { text: "Flight time", value: "flightTime", align: "center" },
      { text: "Airplane", value: "airplane", align: "center" },
      { text: "First ", value: "ticketPriceFirst", align: "center" },
      { text: "Business", value: "ticketPriceBusiness", align: "center" },
      { text: "Economy", value: "ticketPriceEconomy", align: "center" }
    ]
  }),
  computed: {
    ...mapGetters({
    reload: 'reload'
    }),
    isAdmin() {
      return store.getters.isAirlineAdmin;
      
    }
  },
  mounted() {
    this.getFlights();
  },
  watch: {
    reload(newValue) {
      if (newValue === true ) {
        this.flights = [];
        this.getFlights();
        this.$nextTick(() => {
            store.commit("reload", true);
          })
      } 
    }
  },
  methods: {
    getFlights() {
      AirlineCompanyController.getFlights(this.$route.params.id)
        .then(response => {
          console.log(response.data);
          response.data.forEach(element => {
            this.flights.push(element);
          });
        })
        .catch(error => {
          alert(error.response.data);
        });
    },
    archive(id) {
      let data = {
        id: id
      };
      FlightController.archive(data)
        .then(response => {
          let ind = -1;
          this.flights.forEach(f => {
            if (f.id == response.data.id) ind = this.flights.indexOf(f);
          });
          if (ind != -1) this.flights.splice(ind, 1);
          store.commit("setSnack", {
            msg: "You have successfully archived flight",
            color: "success"
          });
          store.commit("reload", true);
        })
        .catch(error => {
          store.commit("setSnack", {
            msg: error.response.data,
            color: "error"
          });
        });
    }
  }
};
</script>

<style></style>
