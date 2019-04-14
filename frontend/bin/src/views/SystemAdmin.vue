<template>
    <div>
        <v-toolbar dark>
          <v-toolbar-title> SYSTEM ADMIN PAGE</v-toolbar-title>
        </v-toolbar>
        <v-container grid-list-md text-xs-center>

              <v-btn color="info" @click="openHotelDialog">
                <v-icon left>hotel</v-icon>
                  Add new hotel
              </v-btn>

              <v-btn color="info" @click="openRentACarDialog">
                <v-icon left>directions_car</v-icon>
                  Add new rent-a-car
              </v-btn>

              <v-btn color="info" @click="openAirlineCompanyDialog">
                <v-icon left>flight_takeoff</v-icon>
                  Add new airline company
              </v-btn>

              <v-dialog v-model="addHotelDialog" max-width="500px">
                <add-hotel-form  v-if="addHotelDialog"
                  v-on:finished="validateHotel($event)"
                  v-on:cancel  ="cancel">
                </add-hotel-form>
              </v-dialog>

              <v-dialog v-model="addRentACarDialog" max-width="500px">
                <add-rentacar-form>
                </add-rentacar-form>
              </v-dialog>
      </v-container>
    </div>   
</template>

<script>

import AddHotelForm from "@/components/AddHotelForm.vue";
import AddRentACarForm from "@/components/AddRentACarForm.vue";

export default {
    name: "sys-admin",
    components: {
    'add-hotel-form': AddHotelForm,
    'add-rentacar-form' : AddRentACarForm,
  },
  data: () => ({
    addHotelDialog: false,
    addRentACarDialog: false,
  }),
  methods: {
    validateHotel(){
    },
    cancel(){
      this.addHotelDialog = false;
    },
    openHotelDialog(){
      this.addRentACarDialog = false;
      this.addHotelDialog = true;
    },
    openRentACarDialog(){
      this.addHotelDialog = false;
      this.addRentACarDialog = true;
    },
  }
};
</script>

