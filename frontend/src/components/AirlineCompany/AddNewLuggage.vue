<template>
  <v-dialog v-model="dialog" persistent width="350px">
    <template v-slot:activator="{ on }">
      <v-btn v-on="on" dark fab color="success" style="float:right">
        <v-icon>add</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-form ref="form" v-model="formValid" lazy-validation>
        <v-card-title primary-title>
          <span class="headline">Add new luggage</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="Name:"
            v-model="name"
            required
            :rules="nameRules"
            style="width:300px"
          ></v-text-field>
          <v-text-field
            label="Dimensions:"
            style="width:300px"
            hint="Example: 10x10x10"
            :rules="dimensionsRules"
            suffix="cm"
            required
            v-model="dimensions"
          ></v-text-field>
          <v-text-field
            style="width:300px"
            label="Weight:"
            v-model="weight"
            :rules="weightRules"
            required
            type="number"
            suffix="kg"
          ></v-text-field>
          <v-text-field
            label="Price:"
            style="width:300px"
            :rules="priceRules"
            v-model="price"
            type="number"
            required
            suffix="€"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="closeDialog">Close</v-btn>
          <v-btn :disabled="!formValid" color="success" @click="submit"
            >Submit</v-btn
          >
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import store from "@/store";
import AirlineCompanyController from "@/controllers/airline-company.controller";

export default {
  name: "AddNewLuggage",
  data: () => ({
    dialog: false,
    formValid: false,
    nameRules: [v => !!v || "Name is required"],
    dimensionsRules: [
      v => !!v || "Dimensions are required",
      v =>
        /^[1-9]{1}[0-9]{0,2}x[1-9]{1}[0-9]{0,2}x[1-9]{1}[0-9]{0,2}$/.test(v) ||
        "Example: 10x10x10"
    ],
    priceRules: [
      v => !!v || "Price is required",
      v => /^[0-9]*$/.test(v) || "Price must be a positive number"
    ],
    weightRules: [
      v => !!v || "Weight is required",
      v => /^[1-9]{1}[0-9]*$/.test(v) || "Weight must be a positive number"
    ],
    name: "",
    price: "",
    dimensions: "",
    weight: ""
  }),
  mounted() {
    this.name = "TestBag";
    this.price = 20;
    this.dimensions = "20x20x20";
    this.weight = 20;
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        let data = {
          name: this.name,
          dimensions: this.dimensions,
          weight: this.weight,
          price: this.price
        };
        AirlineCompanyController.addLuggage(this.$route.params.id, data)
          .then(response => {
            this.$emit("newLuggage", response.data);
            this.closeDialog();
            store.commit("setSnack", {
              msg: "You have successfully added a new luggage",
              color: "success"
            });
          })
          .catch(error => {
            this.closeDialog();
            store.commit("setSnack", {
              msg: error.response.data,
              color: "error"
            });
          });
      }
    },
    closeDialog() {
      this.$refs.form.reset();
      this.name = "";
      this.price = "";
      this.dimensions = "";
      this.weight = "";
      this.dialog = false;
    }
  }
};
</script>

<style></style>
