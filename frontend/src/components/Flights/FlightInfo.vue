<template>
  <div>
    <v-card>
      <v-card-text style="padding: 0px">
        <v-container class="container-flight-info">
          <v-layout row wrap align-center>
            <v-flex lg1 md12 sm12 xs12>
              <a href="">{{ data.airlineCompany.name }}</a>
            </v-flex>
            <v-flex lg3 md4 sm4 xs12>
              <v-container class="container-flight-info">
                <v-layout row wrap>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="date">{{ data.departureDate }}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="time">{{ data.departureTime }}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="city"
                      >{{ data.startDestination.zip }} [{{
                        data.startDestination.name
                      }}]</label
                    >
                  </v-flex>
                </v-layout>
              </v-container>
            </v-flex>
            <v-flex lg3 md4 sm4 xs12>
              <v-container class="container-flight-info">
                <v-layout row wrap>
                  <v-flex lg12 md12 sm12 xs12>
                    <label
                      >Flight duration: {{ data.flightTimeHours }}h
                      {{ data.flightTimeMinutes }}m</label
                    >
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12 id="arrow">
                    <v-icon class="icon">arrow_right_alt</v-icon>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                    <label>Stops: </label>
                    <label v-if="data.transferDestinations.length == 0"
                      >0</label
                    >
                    <label
                      v-for="(item, index) in data.transferDestinations"
                      :key="index"
                      >{{ item }}</label
                    >
                  </v-flex>
                </v-layout>
              </v-container>
            </v-flex>
            <v-flex lg3 md4 sm4 xs12>
              <v-container class="container-flight-info">
                <v-layout row wrap>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="date">{{ data.landingDate }}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="time">{{ data.landingTime }}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="city"
                      >{{ data.finalDestination.zip }}[{{
                        data.finalDestination.name
                      }}]</label
                    >
                  </v-flex>
                </v-layout>
              </v-container>
            </v-flex>
            <v-flex lg2 md12 sm12 xs12>
              <v-container class="container-flight-info">
                <v-layout row wrap justify-center>
                  <v-flex lg12 md4 sm4 xs12>
                    <label class="class"
                      >Class: {{ searchParams.seatClass }}</label
                    >
                  </v-flex>
                  <v-flex lg12 md4 sm4 xs12>
                    <label
                      class="passengers"
                      v-if="searchParams.passengerNumber !== null"
                      >Number of passengers:
                      {{ searchParams.passengersNumber }}</label
                    >
                  </v-flex>
                  <v-flex lg12 md4 sm4 xs12>
                    <label class="passengers"
                      >Price per person: {{ getPrice }} EUR</label
                    >
                  </v-flex>
                </v-layout>
              </v-container>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import Flight from "@/models/Flight.js";
import FlightReservation from "@/models/FlightReservation.js";
import store from "@/store";

export default {
  name: "FlightInfo",
  props: ["data", "searchParams", "title"],
  computed: {
    getPrice() {
      let price = 0;
      switch (this.searchParams.seatClass) {
        case "First": {
          price += this.data.ticketPriceFirst;
          break;
        }
        case "Business": {
          price += this.data.ticketPriceBusiness;
          break;
        }
        case "Economy": {
          price += this.data.ticketPriceEconomy;
          break;
        }
      }
      this.$emit("price", price);
      return price;
    }
  },
  methods: {
    goToReservationView() {
      console.log("OPA");
      let f = new FlightReservation();
      f.flights = this.data;
      f.searchParams = this.searchParams;
      console.log(f);
      store.commit("flightReservation", f);
      this.$router.push("flight-reservation");
    }
  }
};
</script>

<style>
.flex.lg12.md12.sm12.xs12 {
  padding: 1px;
}
.v-icon.icon.material-icons.theme--light {
  font-size: 5em;
}

.time {
  font-size: 2em;
}

.container.container-flight-info {
  padding: 0px 0px 0px 10px;
  margin: 0px 0px 10px 0px;
}
</style>
