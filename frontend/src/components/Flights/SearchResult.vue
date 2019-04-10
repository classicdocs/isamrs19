<template>
  <div>
    <v-container >
      <v-layout row wrap justify-end>
        <v-flex lg8 md8 sm8 xs8>
          <v-card class="card" >
            <v-card-text>
              <v-container >
                <v-layout wrap>
                  <v-flex lg12 md12 sm12 xs12>
                    <v-layout row-wrap >
                      <v-flex lg3>
                        <a href="" class="company">{{data.departureFlight.airlineCompany.name}}</a>
                      </v-flex>
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="date">{{data.departureFlight.departureDate}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="time">{{data.departureFlight.departureTime}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="city">{{data.departureFlight.startDestination.zip}} [{{data.departureFlight.startDestination.name}}]</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex> 
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label>Flight duration: {{data.departureFlight.flightTimeHours}}h {{data.departureFlight.flightTimeMinutes}}m</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12 id="arrow">
                            <v-icon class="icon">arrow_right_alt</v-icon>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label>Stops: </label>
                            <label v-if="data.departureFlight.transferDestinations.length == 0">0</label>
                            <label v-for="(item,index) in data.departureFlight.transferDestinations" :key="index">{{item}}</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex>
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="date">{{data.departureFlight.landingDate}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="time">{{data.departureFlight.landingTime}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="city">{{data.departureFlight.finalDestination.zip}}[{{data.departureFlight.finalDestination.name}}]</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex>
                      
                    </v-layout>
                  </v-flex>
                  <!-- RETURN FLIGHT -->
                  <v-flex lg12 md12 sm12 xs12 v-if="data.returnFlight !== null">
                    <v-layout row-wrap >
                      <v-flex lg3>
                        <a href="" class="company">{{data.returnFlight.airlineCompany.name}}</a>
                      </v-flex>
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="date">{{data.returnFlight.departureDate}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="time">{{data.returnFlight.departureTime}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="city">{{data.returnFlight.startDestination.zip}} [{{data.returnFlight.startDestination.name}}]</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex> 
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label>Flight time: {{data.returnFlight.flightTimeHours}}h {{data.returnFlight.flightTimeMinutes}}m</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12 id="arrow">
                            <v-icon class="icon">arrow_right_alt</v-icon>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label>Stops: </label>
                            <label v-if="data.returnFlight.transferDestinations.length == 0">0</label>
                            <label v-for="(item,index) in data.returnFlight.transferDestinations" :key="index">{{item}}</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex>
                      <v-flex lg3>
                        <v-container >
                          <v-layout row wrap>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="date">{{data.returnFlight.landingDate}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="time">{{data.returnFlight.landingTime}}</label>
                          </v-flex>
                          <v-flex lg12 md12 sm12 xs12>
                            <label class="city">{{data.returnFlight.finalDestination.zip}}[{{data.returnFlight.finalDestination.name}}]</label>
                          </v-flex>
                        </v-layout>
                        </v-container>
                      </v-flex>
                      
                    </v-layout>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex lg2 md2 sm2 xs2>
          <v-card>
            <v-card-text>
              <v-container>
                <v-layout row wrap>
                  <v-flex lg12 md12 sm12 xs12>
                    <label class="class">Class: {{searchParams.seatClass}}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                     <label class="passengers">Number of passengers: {{searchParams.passengersNumber}}</label>
                  </v-flex>
                  <v-flex lg12 md12 sm12 xs12>
                     <label class="passengers">Price per person: {{getPrice}} EUR</label>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex lg2 md2 sm2 xs2>
          <v-btn color="primary">{{getPrice * searchParams.passengersNumber}} EUR</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Flight from "@/models/Flight.js";

export default {
  name: 'SearchResult',
  props: ['data', 'searchParams'],
  computed: {
    getPrice() {
      let price = 0;
      switch(this.searchParams.seatClass) {
        case "First": {
          price += this.data.departureFlight.ticketPriceFirst;
          break;
        }
        case "Business": {
          price += this.data.departureFlight.ticketPriceBusiness;
          break;
        }
        case "Economy": {
          price += this.data.departureFlight.ticketPriceEconomy;
          break;
        }
      }
      if (this.data.returnFlight !== null) {
        switch(this.searchParams.seatClass) {
          case "First": {
            price += this.data.returnFlight.ticketPriceFirst;
            break;
          }
          case "Business": {
            price += this.data.returnFlight.ticketPriceBusiness;
            break;
          }
          case "Economy": {
            price += this.data.returnFlight.ticketPriceEconomy;
            break;
          }
        }
      }

      return price;
    }
  }
};
</script>

<style>

.flex.lg2.md2.sm2.xs2 {
  display: flex;
}
.flex.lg1.md1.sm1.xs1 {
  display: flex;
}
.flex.lg12.md12.sm12.xs12 {
  padding: 1px;
}
.v-icon.icon.material-icons.theme--light {
  font-size: 5em;
}
.v-card__text {
  padding: 0px !important;
}
.company, .class, .passengers, .city, .date{
  font-size: 1em;
}

.time {
  font-size: 2em;
}
.container {
  padding: 10px !important;
  margin: 10px !important;
}

.flex.lg3 {
  display: flex;
  align-items: center;
  padding: 0px !important;
}
.flex{
  /* display: flex; */
  align-items: center;
  padding: 0px;
  margin: 0px;
}
</style>
