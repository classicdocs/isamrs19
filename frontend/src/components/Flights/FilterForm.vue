<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>Filter</h2>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-layout row wrap  id="filter-layout">
            <v-flex lg12 md12 sm12 xs12>
              <h4>Stops:</h4>
               <v-radio-group v-model="filter.stops">
                <v-radio
                  v-for="n in stops"
                  :key="n"
                  :label="n"
                  :value="n"
                ></v-radio>
              </v-radio-group>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <h4>Ticket price: </h4>
              <v-layout row >
                <v-flex
                  style="width: 150px"
                >
                  <v-text-field
                    v-model="filter.priceSlider.range[0]"
                    class="mt-0"
                    hide-details
                    single-line
                    type="number"
                  ></v-text-field>
                </v-flex>
                <v-flex class="px-3">
                  <v-range-slider
                    v-model="filter.priceSlider.range"
                    :max="filter.priceSlider.max"
                    :min="filter.priceSlider.min"
                  ></v-range-slider>
                </v-flex>
                <v-flex
                  style="width: 150px"
                >
                  <v-text-field
                    v-model="filter.priceSlider.range[1]"
                    class="mt-0"
                    hide-details
                    single-line
                    type="number"
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <h4>Flight duration: </h4>
              <v-layout row >
                <v-flex
                  style="width: 150px"
                >
                  <v-text-field
                    v-model="filter.flightDurationSlider.range[0]"
                    class="mt-0"
                    hide-details
                    single-line
                    type="number"
                  ></v-text-field>
                </v-flex>
                <v-flex class="px-3">
                  <v-range-slider
                    v-model="filter.flightDurationSlider.range"
                    :max="filter.flightDurationSlider.max"
                    :min="filter.flightDurationSlider.min"
                  ></v-range-slider>
                </v-flex>
                <v-flex
                  style="width: 150px"
                >
                  <v-text-field
                    v-model="filter.flightDurationSlider.range[1]"
                    class="mt-0"
                    hide-details
                    single-line
                    type="number"
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <v-btn color="primary" style="float:right" @click="doFilter">Filter</v-btn>
              <v-btn color="default" style="float:right" @click="resetFilter">Reset</v-btn>
            </v-flex>
            
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "FilterForm",
  data:() => ({
    stops: ["Direct", "1", "2+"],
    radioGroup: 1,
    filter: {
      "stops": null,
      priceSlider: {
        "min": 0,
        "max":5000,
        "range": [0,5000]
      },
      flightDurationSlider: {
        "min": 0,
        "max":30,
        "range": [0,30]
      }
    },
    
  }),
  methods: {
    doFilter() {
      this.$emit('filter', this.filter);
    },
    resetFilter() {
      this.filter.stops = null;
      this.filter.priceSlider.range[0] = this.filter.priceSlider.min;
      this.filter.priceSlider.range[1] = this.filter.priceSlider.max;

      this.filter.flightDurationSlider.range[0] = this.filter.flightDurationSlider.min;
      this.filter.flightDurationSlider.range[1] = this.filter.flightDurationSlider.max;

      this.$emit('filter', this.filter);
    }
  }
}
</script>

<style>
#filter-layout {
  padding: 0px 30px 0px 0px;
}
</style>
