<template>
  <div>
    <v-dialog
      v-model="dialog"
      max-width="500px"
      persistent
    >
      <v-card>
        <v-card-title primary-title>
          <h2>Do you want to reserve a hotel or rent a car?</h2>
        </v-card-title>
        <v-card-text>
          <p>You can pick to reserve a hotel or rent a car, or you can click on close if you don't want any aditional reservations</p>
          <v-container>
            <v-layout row wrap>
              <v-flex lg6 md6 sm12 xs12>
                <v-btn large @click="toHotel">
                  Hotel
                  <v-icon top dark>hotel</v-icon>
                </v-btn>
              </v-flex>
              <v-flex  lg6 md6 sm12 xs12>
                <v-btn large @click="toRentACar">
                  <v-icon right dark>directions_car</v-icon>
                  Rent-a-car
                </v-btn>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="close">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>

import store from "@/store";

export default {
  name: "OptionalReservationDialog",
  data:() => ({
    dialog: true,
  }),
  methods: {
    close() {
      this.$router.push({name: "my-reservations", params: {id: store.getters.activeUser.id}});
    },
    toRentACar() {
      this.$router.push({name: "rent-a-cars"});
    },
    toHotel() {
      this.$router.push({name: "hotels"}); 
    }
  }
}
</script>

<style>

</style>
