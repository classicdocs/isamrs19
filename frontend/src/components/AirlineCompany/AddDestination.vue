<template>
  <div>
    <v-dialog v-model="dialog" max-width="500px" persistent>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add a destination</v-btn>
      </template>
      <v-card>
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-title primary-title>
            <span class="headline">Add new destination</span>
          </v-card-title>
          <v-card-text>
            <v-text-field
              label="Name"
              v-model="destination.name"
              :rules="[v => !!v || 'Name is required']"
            ></v-text-field>
            <v-text-field
              label="County"
              v-model="destination.country"
              :rules="[v => !!v || 'Country is required']"
            ></v-text-field>
            <v-text-field
              label="Zip"
              v-model="destination.zip"
              :rules="rule"
            ></v-text-field>
            <v-text-field
              label="Airport"
              v-model="destination.airport"
              :rules="[v => !!v || 'Airport is required']"
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken" flat @click="dialog = false"
              >Close</v-btn
            >
            <v-btn @click="resetValidation">Reset Validation</v-btn>
            <v-btn @click="reset">Reset Form</v-btn>
            <v-btn :disabled="!form" color="success" @click="validate"
              >Add new</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import Destination from "@/models/Destination";
import AirlineCompanyContoller from "@/controllers/airline-company.controller";
import store from "@/store";

export default {
  name: "AddDestination",
  props: ["airlineCompany"],
  data: () => ({
    dialog: false,
    form: true,

    destination: new Destination(),
    rule: [
      v => !!v || "Number is required",
      v => /^[A-Z]{3}$/.test(v) || "Zip must be 3 uppercase letters"
    ]
  }),
  watch: {
    dialog(val) {
      !val && this.reset();
    }
  },
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        AirlineCompanyContoller.addDestination(
          this.airlineCompany.id,
          this.destination
        )
          .then(response => {
            store.commit("setSnack", {
              msg: "Destination successfully added!",
              color: "success"
            });
            store.commit("newDestination", response.data);
            this.dialog = false;
          })
          .catch(error => {
            store.commit("setSnack", {
              msg: error.response.data,
              color: "error"
            });
            this.dialog = false;
          });
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    }
  }
};
</script>
