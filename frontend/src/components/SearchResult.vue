<template>
  <div>
    <v-card class="card" >
      <v-card-text>
        <v-container >
          <v-layout wrap>
            <v-flex lg12 md12 sm12 xs12>
              <v-layout row-wrap >
                <v-flex lg2>
                  <a href="" class="company">Airserbia</a>
                </v-flex>
                <v-flex lg2>
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="date">2019-03-16</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="time">05:55</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="city">BGC</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex> 
                <v-flex lg2>
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label> Flight time: 7h</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12 id="arrow">
                      <v-icon class="icon">arrow_right_alt</v-icon>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12> 
                      <label>Stops: NY,LON</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex>
                <v-flex lg2>
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="date">2019-03-16</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="time">05:55</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="city">BGC</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex>
                <v-flex lg2>
                  <label class="class">Economy</label>
                </v-flex>
                <v-flex lg2>
                  <label class="passengers">7 passengers</label>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <v-btn style="float:right" color="success">text</v-btn>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <v-layout row-wrap >
                <v-flex lg2>
                  <a href="" class="company">Airserbia</a>
                </v-flex>
                <v-flex lg2 class="time-flex">
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="date">2019-03-16</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="time">05:55</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="city">BGC</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex> 
                <v-flex lg2>
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label> Flight time: 7h</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12 id="arrow">
                      <v-icon class="icon">arrow_right_alt</v-icon>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12> 
                      <label>Stops: NY,LON</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex>
                <v-flex lg2 class="time-flex">
                  <v-container >
                    <v-layout row wrap>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="date">2019-03-16</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="time">05:55</label>
                    </v-flex>
                    <v-flex lg12 md12 sm12 xs12>
                      <label class="city">BGC</label>
                    </v-flex>
                  </v-layout>
                  </v-container>
                </v-flex>
                <v-flex lg2>
                  <label class="class">Economy</label>
                </v-flex>
                <v-flex lg2>
                  <label class="passengers">7 passengers</label>
                </v-flex>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import Flight from "@/models/Flight.js";

export default {
  name: 'SearchResult',
  data:() => ({
    flight1: new Flight(),
    flight2: new Flight(),
  }),
};
</script>

<style>

.flex.lg2 {
  padding: 0px !important;
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

.flex.lg2 {
  display: flex;
  align-items: center;
}
.flex{
  /* display: flex; */
  align-items: center;
  padding: 0px;
  margin: 0px;
}
</style>
