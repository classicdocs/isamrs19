<template>
  <div>
    <v-dialog
      v-model="dialog"
      max-width="500px"
      persistent
    >
    <template v-slot:activator="{ on }">
      <v-btn color="primary" dark v-on="on">Add an airplane</v-btn>
    </template>
    <v-card>
      <v-form
        ref="form"
        v-model="form"
        lazy-validation
      >
      <v-card-title primary-title>
        <span class="headline">Add new airplane</span>
      </v-card-title>
      <v-card-text>
        <v-text-field
          label="Model"
          v-model="airplane.model"
          :rules= "[v => !!v || 'Model is required']"
        ></v-text-field>
        <v-text-field
          label="Number of seats rows in First class"
          v-model="airplane.seatsFirstRows"
          type = number
          :rules= "rule"
        ></v-text-field>
        <v-text-field
          label="Number of seats cols in First class"
          v-model="airplane.seatsFirstCols"
          type = number
          :rules= "rule"
        ></v-text-field>
        <v-text-field
          label="Number of seats rows in Business class"
          v-model="airplane.seatsBusinessRows"
          type = number
          :rules= "rule"
        ></v-text-field>
        <v-text-field
          label="Number of seats cols in Business class"
          v-model="airplane.seatsBusinessCols"
          type = number
          :rules= "rule"
        ></v-text-field>
        <v-text-field
          label="Number of seats rows in Economy class"
          v-model="airplane.seatsEconomyRows"
          type = number
          :rules= "rule"
        ></v-text-field>
        <v-text-field
          label="Number of seats cols in Economy class"
          v-model="airplane.seatsEconomyCols"
          type = number
          :rules= "rule"
        ></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
        <v-btn @click="resetValidation">Reset Validation</v-btn>
        <v-btn @click="reset">Reset Form</v-btn>
        <v-btn :disabled="!form" color="success" @click="validate">Add new</v-btn>
      </v-card-actions>
      </v-form>
    </v-card>
    </v-dialog>
  </div>    
</template>

<script>

import Airplane from "@/models/Airplane";
import AirlineCompanyContoller from "@/controllers/airline-company.controller";

export default {
  name: "AddAirplaneDialog",
  props: ['airlineCompany'],
  data:() => ({
    dialog: false,
    form: true,

    airplane: new Airplane(),
    rule: [v => !!v || 'Number is required',
                 v => /^[0-9]+$/.test(v) || "Number must be a positive number"],
  }),
  watch: {
    dialog(val) {
      !val && this.reset();
    }
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        AirlineCompanyContoller.addAirplane(this.airlineCompany.id, this.airplane)
          .then((response) => {
            this.dialog = false;
            this.$emit('snack', {color: "success", msg: "Airplane successfully added!"});
          })
          .catch((error) => {
            this.dialog = false;
            this.$emit("snack", {color:"error", msg: error.response.data});
          })
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  }

};
</script>
