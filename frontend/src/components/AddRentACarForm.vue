<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new rent-a-car service  <v-icon x-large>commute</v-icon>
            <v-icon x-large>plus_one</v-icon></span>
          </v-card-title>

          <!-- LABELE -->

          <v-container>
            <v-text-field label="service name" v-model="rentACar.name"
            :rules="[v => !!v || 'rent a car service name is required']">
            </v-text-field> 

            <v-layout>
              <v-flex>
                <v-text-field label="country" v-model="country"
                :rules="[v => !!v || 'country name is required']">
                </v-text-field> 
              </v-flex>
              <v-flex>
                <v-text-field label="city" v-model="city" 
                :rules="[v => !!v || 'city name is required']">
                </v-text-field> 
              </v-flex>
            </v-layout>

            <v-text-field label="street address" v-model="street"
            :rules="[v => !!v || 'street address is required']">
            </v-text-field> 

            <v-textarea name="promotionalDescription" label="promotional description" 
              v-model="rentACar.description" 
              hint="Say something good about rent-a-car services and prices" box>
            </v-textarea>

            <v-checkbox label="create default service administrator - (admin : admin)"
             :rules="[v => !!v || 'you need to have admin for every rent-a-car service.']">
              </v-checkbox>  
          </v-container>

          <!-- DUGMAD -->

          <v-card-actions>
            <v-spacer></v-spacer>
              <v-btn :disabled="!form" v-on:click="validate" color="success">NEXT</v-btn>
              <v-btn v-on:click="reset" color="error">RESET</v-btn>
              <v-btn v-on:click="cancel" color="info">CANCEL</v-btn>
           </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import RentACar from "@/models/RentACar";
import RentACarAdmin from "@/models/RentACarAdmin";
import RentACarController from "@/controllers/sysAdminRAC.controller.js"

export default {
  name: "AddRentACarForm",

  data: () => ({
    form: true,
    country: "",
    city: "",
    street : "",
    rentACar: new RentACar(),
    rentACarAdmin: new RentACarAdmin(),
  }),
  created() {
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        this.rentACar.address = this.coutry + '/' + this.city + '/' + this.street;
        this.rentACar.priceList = [];
        this.rentACar.vehicles = [];
        this.rentACar.branches = [];

        // create rentACarAdmin
        this.rentACarAdmin.username = "admin_" + this.rentACar.name;
        this.rentACarAdmin.password = "admin";
        this.rentACarAdmin.rentACar = rentACar;

        RentACarController.create(this.rentACar)
          .then((response) => {
            this.$emit("finished", {msg: "RentACar successfully added", color: "success"})
            console.log("Uspeh");
          })
          .catch((response) => {
            this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
            console.log("Neuspeh");
          })
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    cancel(){
      this.$emit("cancel")
    }
  }
};
</script>

<style>

</style>

