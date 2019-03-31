<template>
    <div>
        <v-toolbar dark>
          <v-toolbar-title> SYSTEM ADMIN PAGE</v-toolbar-title>
        </v-toolbar>
        <v-container grid-list-md text-xs-center>
              <v-btn color="info" @click="addHotelDialog = true">
                  Add new hotel
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
      </v-container>
    </div>   
</template>

<script>

import AddHotelForm from "@/components/AddHotelForm.vue";
import AddHotelAdminForm from "@/components/AddHotelAdminForm.vue"

export default {
    name: "sys-admin",
    components: {
    'add-hotel-form': AddHotelForm,
    'add-hotel-admin-form' : AddHotelAdminForm,
  },
  data: () => ({
    addHotelDialog: false,
    addHotelAdminDialog: false,
  }),
  methods: {
    activateHotelAdminForm(obj) {
      alert(obj)
      this.addHotelDialog = false;
    },
    cancel(){
      this.addHotelDialog = false;
    },
  }
};
</script>

