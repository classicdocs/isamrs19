<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new airline company <v-icon x-large>flight_takeoff</v-icon></span>
          </v-card-title>

          <!-- LABELE -->

          <v-container>
            <v-text-field label="airline company name" v-model="airlineCompany.name"
            :rules="[v => !!v || 'airline company name is required']">
            </v-text-field> 

            <v-layout > <!--v-bind="binding"-->
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
              v-model="airlineCompany.description" 
              hint="Say something good about airline company services and prices..." box>
            </v-textarea>
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

import AirlineCompany from "@/models/AirlineCompany";
import SystemAdminControler from "@/controllers/system-admin.controller";
import { thisExpression } from '@babel/types';

export default {
  name: "AddAirlineForm",
  data: () => ({
    form: true,

    country : "",
    city : "",
    street : "",

    airlineCompany: new AirlineCompany(),

  }),
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        this.airlineCompany.address = this.country + '/' + this.city + '/' + this.street;


        SystemAdminControler.createAirline(this.airlineCompany)
        .then((Response) => {
          this.$emit("finished", {msg: "Airline company successfully added", color: "success"})
        })
        .catch((response) => {
          this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
        })

        // SysHotelControler.createHotel(this.hotel)
        //   .then((response) => {
        //     this.$emit("finished", {msg: "Hotel successfully added", color: "success"})
        //   })
        //   .catch((response) => {
        //     this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
        //   })
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    cancel(){
      this.$emit("cancel")
    },
    
  }
};
</script>

<style>

</style>

