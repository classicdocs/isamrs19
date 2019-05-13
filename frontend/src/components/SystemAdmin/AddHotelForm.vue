<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>
          <!-- NASLOV -->
          <v-card-title primary-title>
            <span class="headline">Add new hotel <v-icon x-large>hotel</v-icon></span>
          </v-card-title>

          <!-- LABELE -->

          <v-container>
            <v-text-field label="hotel name" v-model="hotel.name"
            :rules="[v => !!v || 'hotel name is required']">
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
              v-model="hotel.description" 
              hint="Say something good about hotel services and prices..." box>
            </v-textarea>

            Number of floors
            <number-input v-model="hotel.numOfFloors" :min="1" :max="5" inline center controls></number-input>
            Number of rooms by floor
            <number-input v-model="hotel.roomsByFloor" :min="1" :max="50" inline center controls></number-input>
          </v-container>

          <!-- DUGMAD -->

          <v-card-actions>
            <v-spacer></v-spacer>
              <v-btn v-on:click="cancel" color="info">CANCEL</v-btn>
              <v-btn v-on:click="reset" color="error">RESET</v-btn>
              <v-btn :disabled="!form" v-on:click="validate" color="success">NEXT</v-btn>
           </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";


import SystemAdminController from "@/controllers/system-admin.controller"
import { thisExpression } from '@babel/types';

export default {
  name: "AddHotelForm",
  data: () => ({
    form: true,

    country : "",
    city : "",
    street : "",

    hotel: new Hotel(),
  }),
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        this.hotel.address = this.country + '/' + this.city + '/' + this.street;
        this.hotel.priceList = [];
        this.hotel.floors = [];
        this.hotel.admins = [];

        SystemAdminController.createHotel(this.hotel)
          .then((response) => {
            this.$emit("finished", {msg: "Hotel successfully added", color: "success"})
          })
          .catch((response) => {
            this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
          })
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

