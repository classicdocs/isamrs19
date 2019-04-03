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

            <v-layout v-bind="binding">
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
              hint="Say something good about hotel services and prices" box>
            </v-textarea>
<!-- 
            <v-checkbox label="create default hotel administrator - (admin : admin)"
             :rules="[v => !!v || 'you need to have admin for every hotel.']" class="check">
            </v-checkbox>   -->

            <v-select :items="admins" v-model="hotel.admins"
            label="Available hotel administrators"  item-value="last"
            :rules="[v => !!v || 'Hotel administrator is required']">
            persistent-hint = true
            </v-select>

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

import Hotel from "@/models/Hotel";
import HotelAdmin from "@/models/HotelAdmin"
import HotelAdminController from "@/controllers/hotelAdmin.controller"
import { thisExpression } from '@babel/types';

export default {
  name: "AddHotelForm",
  data: () => ({
    form: true,

    country : "",
    city : "",
    street : "",


    hotel: new Hotel(),
    hotelAdmin: new HotelAdmin(),

    admins : [],
  }),
  created() {
      HotelAdminController.get()
      .then((response) => {
        response.data.forEach(element => {
          this.admins.push(element);
        });
      })
  },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        this.hotel.address = this.country + '/' + this.city + '/' + this.street;
        this.hotel.priceList = [];
        this.hotel.roomConfiguration = [];
        
        //FIXME create hotelAdmin
        this.hotelAdmin.username = "admin";
        this.hotelAdmin.password = "admin";
        this.hotelAdmin.hotel = this.hotel;

        sysAdminContoller.create(this.hotel)
          .then((response) => {

            this.$emit("finished", {msg: "Hotel successfully added", color: "success"})
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

