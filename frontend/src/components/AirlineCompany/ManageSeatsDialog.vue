<template>
  <v-dialog
    v-model="dialog"
    max-width="600px"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn color="primary" dark v-on="on">Manage seats</v-btn>
  </template>
  <v-card>
    <v-form
    ref="form"
    v-model="form"
    >
      
    <v-card-title primary-title>
     <span class="headline">Manage seats on airplane</span>
    </v-card-title>
    <v-card-text>
      <v-select
        :items="getAirplanes"
        v-model="model"
        required
        :rules="[v => !!v || 'Model is required']"
        label="Select airplane model"
      ></v-select>
      <v-container v-if="model">
        <v-layout row wrap>
          <v-flex lg6 md6 sm12 xs12>
            <v-flex lg12 md12 sm6 xs12>
              <span>Number of rows in First class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsFirstRows" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
          <v-flex lg6 md6 sm6 xs12>
            <v-flex lg12 md12 sm12 xs12>
              <span>Number of columns in First class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsFirstCols" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
          <v-flex lg6 md6 sm12 xs12>
            <v-flex lg12 md12 sm6 xs12>
              <span>Number of rows in Business class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsBusinessRows" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
          <v-flex lg6 md6 sm6 xs12>
            <v-flex lg12 md12 sm12 xs12>
              <span>Number of columns in Business class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsBusinessCols" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
          <v-flex lg6 md6 sm12 xs12>
            <v-flex lg12 md12 sm6 xs12>
              <span>Number of rows in Economy class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsEconomyRows" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
          <v-flex lg6 md6 sm6 xs12>
            <v-flex lg12 md12 sm12 xs12>
              <span>Number of columns in Economy class</span>
            </v-flex>
            <v-flex lg12 md12 sm12 xs12>
              <number-input v-model="airplane.seatsEconomyCols" :min="1" :max="6" inline center controls></number-input>
            </v-flex>
          </v-flex>
        </v-layout>
      </v-container>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
      <v-btn @click="resetValidation">Reset Validation</v-btn>
      <v-btn @click="reset">Reset Form</v-btn>
      <v-btn :disabled="!form" color="success" @click="validate">Submit</v-btn>  
    </v-card-actions>
    </v-form>
  </v-card>
    
  </v-dialog>
</template>

<script>

import AirlineCompanyController from "@/controllers/airline-company.controller";
import Airplane from "@/models/Airplane.js";

export default {
  name: "ManageSeatsDialog",
  data: () => ({
    dialog: false,
    form: true,
    airplanes: [],
    airplane: new Airplane(),
    model: null,
    airlineCompany: null,
    id: null,
  }),
  beforeMount() {
      this.airlineCompany = this.$route.params.id;
      AirlineCompanyController.getAirplanes(this.airlineCompany)
      .then((response) => {
        response.data.forEach(element => {
          this.airplanes.push(element);
        })
      })
      .catch((error) => {
        alert(error.response.data);
      })
  },
  watch: {
    'model' : 'setAirplane'
  },
  computed: {
    getAirplanes() {
      let result = []
      this.airplanes.forEach(element => {
        result.push("Id: " + element.id + ", Model: " + element.model);
      }); 
      return result;
    }
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        AirlineCompanyController.updateAirplane(this.airlineCompany, this.id, this.airplane)
          .then((response) => {
            this.reset();
            this.dialog = false;
            this.$emit('snack', {msg: "Airplane seats successfully updated!", color: "success"});
          })
          .catch((error) => {
            this.reset();
            this.dialog = false;
            this.$emit('snack', {msg: error.response.data, color:"error"});
          })
      }
    },
    reset() {
      this.model = null;
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    setAirplane() {
      if (this.model === undefined) {
        return;
      }
      this.airplanes.forEach(element => {
        this.id = this.model.substring(this.model.indexOf(" ") + 1, this.model.indexOf(","));
        if (this.id == element.id) {
          this.airplane.model = element.model;
          this.airplane.seatsFirstRows = element.seatsFirstRows;
          this.airplane.seatsFirstCols = element.seatsFirstCols;
          this.airplane.seatsBusinessRows = element.seatsBusinessRows;
          this.airplane.seatsBusinessCols = element.seatsBusinessCols;
          this.airplane.seatsEconomyRows = element.seatsEconomyRows;
          this.airplane.seatsEconomyCols = element.seatsEconomyCols;
        }
      });
    }

  }
}
</script>
