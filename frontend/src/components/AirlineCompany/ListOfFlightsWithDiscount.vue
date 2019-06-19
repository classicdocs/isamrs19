<template>
  <div v-if="flights.length > 0">
    <v-card>
      <v-card-title primary-title>
        <h2>Flights on discount</h2>
      </v-card-title>
      <v-card-text>
        <v-data-table
          :headers="headers"
          :items="flights"
          hide-actions
          class="elevation-1"
        >
          <template v-slot:items="props">
            <td class="text-xs-center">{{ props.item.flight.id }}</td>
            <td class="text-xs-center">
              {{ props.item.flight.startDestination.name }}
            </td>
            <td class="text-xs-center">
              {{ props.item.flight.finalDestination.name }}
            </td>
            <td class="text-xs-center">
              {{ props.item.flight.departureDate }}
            </td>
            <td class="text-xs-center">
              {{ props.item.flight.departureTime }}
            </td>
            <td class="text-xs-center">{{ props.item.flight.landingDate }}</td>
            <td class="text-xs-center">{{ props.item.flight.landingTime }}</td>
            <td class="text-xs-center">
              {{
                props.item.flight.flightTimeHours +
                  "h " +
                  props.item.flight.flightTimeMinutes +
                  "min"
              }}
            </td>
            <td class="text-xs-center">
              {{ props.item.flight.airplane.model }}
            </td>
            <td class="text-xs-center">{{ props.item.price }}</td>
            <td class="text-xs-center">{{ props.item.seat.seatClass }}</td>
            <td class="text-xs-center">{{ props.item.seat.rowNum }}</td>
            <td class="text-xs-center">{{ props.item.seat.colNum }}</td>
            <td class="text-xs-center" v-if="isUser">
              <reserve
                v-bind:flight="props.item"
                v-on:reservedFlight="removeFlight($event)"
              />
            </td>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import AirlineCompanyController from "@/controllers/airline-company.controller";
import store from "@/store";
import FlightDiscountVue from "./FlightDiscount.vue";
import Friend from "@/models/Friend.js";
import ReserveFlightWithDiscountVue from "./ReserveFlightWithDiscount.vue";

export default {
  name: "ListOfFlightsWithDiscount",
  components: {
    reserve: ReserveFlightWithDiscountVue
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
      { text: "Price ", value: "price", align: "center" },
      { text: "Class", value: "seatClass", align: "center" },
      { text: "Row", value: "rowNum", align: "center" },
      { text: "Column", value: "colNum", align: "center" }
    ]
  }),
  computed: {
    isUser() {
      return store.getters.isUser;
    }
  },
  beforeMount() {
    this.getFlights();
  },
  methods: {
    getFlights() {
      AirlineCompanyController.getFlightsDiscount(this.$route.params.id)
        .then(response => {
          response.data.forEach(element => {
            this.flights.push(element);
          });
        })
        .catch(error => {
          alert(error.response.data);
        });
    },
    removeFlight(flight) {
      this.flights = [];
      this.getFlights();
    }
  }
};
</script>
