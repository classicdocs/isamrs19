<template>
  <div>
    <v-card>

        <v-form ref="form" v-model="form" lazy-validation>

        <v-card-title primary-title>
            <span class="headline">Add hotel</span>
        </v-card-title>

        <v-card-text>
          <v-container fluid grid-list-xs>
            <v-layout wrap align-center>

                <v-flex xs6>
                    <v-text-field label="Name" v-model="flight.distance"
                        :rules="rules.distance" required>
                    </v-text-field>
                </v-flex>

                <v-flex xs6>
                    <v-text-field label="Address" v-model="flight.ticketPrice"
                        required :rules="rules.ticketPrice" >
                    </v-text-field>
                </v-flex>

                <v-flex xs6>
                    <v-text-field label="Promotional Description" v-model="flight.ticketPrice"
                        required :rules="rules.ticketPrice" >
                    </v-text-field>
                </v-flex>
                
                <v-flex xs6>
                    <v-text-field label="TODO hotel admin" v-model="flight.ticketPrice"
                        required :rules="rules.ticketPrice" >
                    </v-text-field>
                </v-flex>
              
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="reset">Reset Form</v-btn>
          <v-btn :disabled="!form" color="primary" @click="validate">Add new</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";
import HotelContoller from "@/controllers/hotels.controller";

export default {
  name: "AddHotelForm",
//   components: {
//     'date-time': Datetime,
//   },
  data: () => ({
    form: true,

    rules: {
      name: [v => !!v || 'Name is required'],
      address: [v => !!v || 'Address is required'],

    },
    
    hotel: new Hotel(),
  }),
//   beforeMount() {
//     this.flight.airlineCompany = this.$route.params.id;
//     this.startDestinations= ['1'],
//     this.finalDestinations= ['2'],
//     this.transferDestinations= ['3']
//   },
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        HotelContoller.create(this.hotel)
          .then((response) => {
            this.$emit("operation", {msg: "Hotel successfully added", color: "success"})
          })
          .catch((response) => {
            this.$emit("operation", {msg: "Error! Something went wrong...", color: "error"})
          })
      }
    },
    reset() {
      this.$refs.form.reset();
    }
  }
};
</script>

<style>

</style>

