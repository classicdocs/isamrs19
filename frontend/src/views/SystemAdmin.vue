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
                  Add rent-a-car
              </v-btn>

              <v-dialog v-model="addHotelDialog" max-width="500px">
                <add-hotel-form  v-if="addHotelDialog"
                  v-on:finished="activateHotelAdminForm($event)"
                  v-on:cancel  ="cancel">
                </add-hotel-form>
              </v-dialog>

              <v-dialog v-model="addHotelAdminDialog" max-width="500px">
                <add-hotel-admin-form>
                </add-hotel-admin-form>
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
import AddHotelAdminForm from "@/components/AddHotelAdminForm.vue";
import AddRentACarForm from "@/components/AddRentACarForm.vue";

export default {
    name: "sys-admin",
    components: {
    'add-hotel-form': AddHotelForm,
    'add-hotel-admin-form' : AddHotelAdminForm,
    'add-rentacar-form' : AddRentACarForm,
  },
  data: () => ({
    addHotelDialog: false,
    addHotelAdminDialog: false,
    AddRentACarDialog: false,
  }),
  methods: {
    activateHotelAdminForm(obj) {
      alert(obj)
      this.addHotelDialog = false;
    },
    cancel(){
      this.addHotelDialog = false;
    },
    openHotelDialog(){
      this.AddRentACarDialog = false;
      this.addHotelAdminDialog = false;
      this.addHotelDialog = true;
    },
    openHotelAdminDialog(){
      this.AddRentACarDialog = false;
      this.addHotelDialog = false;
      this.addHotelAdminDialog = true;
    },
    openRentACarDialog(){
      this.addHotelDialog = false;
      this.addHotelAdminDialog = false;
      this.AddRentACarDialog = true;
    },
  }
};
</script>

