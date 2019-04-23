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
                  v-on:finished="closeAddHotel($event)"
                  v-on:cancel  ="cancel">
                </add-hotel-form>
              </v-dialog>

              <v-dialog v-model="addRentACarDialog" max-width="500px">
                <add-rentacar-form
                v-on:finished="closeAddRentACar($event)"
                v-on:cancel  ="cancel">
                </add-rentacar-form>
              </v-dialog>

              <v-dialog v-model="addAirlineDialog" max-width="500px">
                <add-airline-form  v-if="addAirlineDialog"
                  v-on:finished="closeAddAirline($event)"
                  v-on:cancel  ="cancel">
                </add-airline-form>
              </v-dialog>
      </v-container>
    </div>   
</template>

<script>

import AddHotelForm from "@/components/SystemAdmin/AddHotelForm.vue";
import AddRentACarForm from "@/components/SystemAdmin/AddRentACarForm.vue";
import AddAirlineForm from "@/components/SystemAdmin/AddAirlineForm.vue";

export default {
    name: "sys-admin",
    components: {
    'add-hotel-form': AddHotelForm,
    'add-rentacar-form' : AddRentACarForm,
    'add-airline-form' : AddAirlineForm,
  },
  data: () => ({
    addHotelDialog: false,
    addRentACarDialog: false,
    addAirlineDialog: false,

    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
  }),
  methods: {
    closeAddHotel(data){
      this.addHotelDialog = false;
      this.showSnackbar(data);
    },
    closeAddRentACar(data){
      this.addRentACarDialog = false;
      this.showSnackbar(data);
    },
    closeAddAirline(data){
      this.addAirlineDialog = false;
      this.showSnackbar(data);
    },
    cancel(){
      this.addHotelDialog = false;
      this.addRentACarDialog = false;
      this.addAirlineDialog = false;
    },
    openHotelDialog(){
      this.addRentACarDialog = false;
      this.addAirlineDialog = false;
      this.addHotelDialog = true;
    },
    openRentACarDialog(){
      this.addHotelDialog = false;
      this.addAirlineDialog = false;
      this.addRentACarDialog = true;
    },
    openAirlineCompanyDialog(){
      this.addHotelDialog = false;
      this.addRentACarDialog = false;
      this.addAirlineDialog = true;
    },
    showSnackbar(obj) {
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    }
  }
};
</script>

