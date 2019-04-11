<template>
  <div>
    <v-card>
      <v-form
        ref="form"
        v-model="form"
        lazy-validation
      >
      <v-card-title primary-title>
        <span class="headline">Edit airline company information</span>
      </v-card-title>
      <v-card-text>
        <v-text-field
          label="Name"
          v-model="newAirlineCompany.name"
          :rules= "[v => !!v || 'Name is required']"
          @input="changed = true"
        ></v-text-field>
        <v-text-field
          label="Address"
          v-model="newAirlineCompany.address"
          @input="changed = true"
          :rules= "[v => !!v || 'Address is required']"
        ></v-text-field>
        <v-textarea
          label="Description" 
          auto-grow
          @input="changed = true"
          v-model="newAirlineCompany.description"
       ></v-textarea>
      </v-card-text>
      
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken" flat @click="closeDialog">Close</v-btn> 
        <v-btn @click="resetValidation">Reset Validation</v-btn>
        <v-btn @click="reset">Reset Form</v-btn>
        <v-btn :disabled="!form || !changed" color="success" @click="validate">Update</v-btn>
      </v-card-actions>
      </v-form>
    </v-card>
  </div>    
</template>

<script>

import AirlineCompany from "@/models/AirlineCompany";
import AirlineCompanyContoller from "@/controllers/airline-company.controller";

export default {
  name: "EditAirlineCompanyForm",
  props: ["airlineCompany"],
  data:() => ({
    form: true,
    changed: false,
    newAirlineCompany: new AirlineCompany(),
  }),
  beforeMount() {
    this.newAirlineCompany = JSON.parse(JSON.stringify(this.airlineCompany));
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        AirlineCompanyContoller.update(this.airlineCompany.id, this.newAirlineCompany)
          .then((response) => {
            this.$emit("info-update", response.data);
          })
          .catch((error) => {
            this.$emit("update-error", {color:"error", msg: error.response.data});
          })
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    closeDialog() {
      this.$emit("closeDialog", false);
    }
  }

};
</script>
