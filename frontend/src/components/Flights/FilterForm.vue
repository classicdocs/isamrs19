<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>Filter</h2>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-layout row wrap id="filter-layout">
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
              <v-layout row>
                <v-flex class="px-3">
                  <v-form ref="form1" v-model="form1" lazy-validation>
                    <v-text-field
                      label="Max ticket price"
                      v-model="price"
                      type="number"
                      style="width:150px"
                      :rules="[
                        v =>
                          /^[1-9]{1}[0-9]*$/.test(v) ||
                          'Ticket price must be a positive number'
                      ]"
                    ></v-text-field>
                  </v-form>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <v-layout row>
                <v-flex class="px-3">
                  <v-form ref="form2" v-model="form2" lazy-validation>
                    <v-text-field
                      label="Max flight duration"
                      v-model="duration"
                      type="number"
                      style="width:150px"
                      :rules="[
                        v =>
                          /^[1-9]{1}[0-9]*$/.test(v) ||
                          'Flight duration must be a positive number'
                      ]"
                    ></v-text-field>
                  </v-form>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <v-btn color="primary" style="float:left" @click="doFilter"
                >Filter</v-btn
              >
              <v-btn color="default" style="float:left" @click="resetFilter"
                >Reset</v-btn
              >
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: "FilterForm",
  data: () => ({
    stops: ["Direct", "1", "2+"],
    radioGroup: 1,
    filter: {
      stops: null,
      priceSlider: {
        min: 1,
        max: 5000,
        range: [1, 5000]
      },
      flightDurationSlider: {
        min: 1,
        max: 30,
        range: [1, 30]
      }
    },
    form1: true,
    form2: true,
    price: 5000,
    duration: 30
  }),
  methods: {
    doFilter() {
      if (this.$refs.form1.validate() && this.$refs.form2.validate()) {
        this.filter.priceSlider.range[1] = this.price;
        this.filter.flightDurationSlider.range[1] = this.duration;
        this.$emit("filter", this.filter);
      } else {
        store.commit("setSnack", {
          msg: "Price or duration must be a positive number",
          color: "error"
        });
      }
    },
    resetFilter() {
      this.filter.stops = null;
      this.price = this.filter.priceSlider.max;
      this.duration = this.filter.flightDurationSlider.max;

      this.doFilter();
    }
  }
};
</script>

<style></style>
