<template>
  <div>
    <v-tabs centered color="#2b77f2" dark icons-and-text>
      <v-tabs-slider color="yellow"></v-tabs-slider>

      <v-tab href="#tab-1">
        Home
        <v-icon>home</v-icon>
      </v-tab>
    
      <v-tab href="#tab-2">
        Vehicles
        <i class="material-icons">directions_car</i>
      </v-tab>

      <v-tab href="#tab-3">
        Branches
        <i class="material-icons">business</i>
      </v-tab>

      <v-tab href="#tab-4" v-if="admin">
        Quick reservations
        <i class="material-icons">credit_card</i>
      </v-tab>

      <v-tab href="#tab-5" v-if="admin">
        Edit
        <i class="material-icons">border_color</i>
      </v-tab>

      <v-tab-item :value="'tab-1'">
        <v-card flat>
          <RentACarInfo :rentacar="rentacar"></RentACarInfo>
        </v-card>
      </v-tab-item>

      <v-tab-item :value="'tab-2'">
        <v-card flat>
          <RentACarAdminOptions
            id="rentacaradmin"
            v-if="admin"
          ></RentACarAdminOptions>
          <VehicleReservation v-if="!admin"></VehicleReservation>
        </v-card>
      </v-tab-item>

      <v-tab-item :value="'tab-3'">
        <v-card flat>
          <BranchesList :rentacar="rentacar"></BranchesList>
        </v-card>
      </v-tab-item>

      <v-tab-item :value="'tab-4'" v-if="admin">
        <v-card flat>
          <VehicleQuickReservations
            @update-rentacar="updateRentacar"
          ></VehicleQuickReservations>
        </v-card>
      </v-tab-item>

      <v-tab-item :value="'tab-5'" v-if="admin">
        <v-card flat>
           <EditRentacarForm
            @update-rentacar="updateRentacar"
          ></EditRentacarForm>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>        
</template>

<script>
import RentACarInfo from "@/components/RentACarInfo.vue";
import RentACarAdminOptions from "@/components/RentACarAdminOptions.vue";
import RentACarController from "@/controllers/rentacar.controller.js";
import RentACar from "@/models/RentACar.js";
import EditRentacarForm from "@/components/EditRentacarForm.vue";
import VehicleReservation from "@/components/RentACar/VehicleReservation.vue";
import BranchesList from "@/components/RentACar/BranchesList.vue";
import VehicleQuickReservations from "@/components/RentACar/VehicleQuickReservations.vue";
import store from "@/store";

export default {
  components: {
    RentACarInfo,
    RentACarAdminOptions,
    EditRentacarForm,
    VehicleReservation,
    BranchesList,
    VehicleQuickReservations
  },
  data: () => ({
    id: null,
    rentacar: new RentACar(),
    admin: false
  }),
  created() {
    this.getData();
  },
  watch: {
    $route: "getData"
  },
  methods: {
    getData() {
      this.id = this.$route.params.id;

      RentACarController.get(this.id).then(response => {
        console.log(response.data);
        this.rentacar = response.data;
      });

      if (store.getters.isRentacarAdmin)
        if (this.id == store.getters.activeUser.idAdminOf) this.admin = true;
        else this.admin = false;
    },
    updateRentacar(r) {
      this.rentacar = r;
    }
  }
};
</script>

<style></style>
