<template>
  <div>
    <v-container >
      <v-layout row wrap>
        <v-flex lg4 md6 sm6 xs12>
          <airline-company-info v-bind:airlineCompany="airlineCompany"/>
        </v-flex>
        <v-flex lg8 md6 sm6 xs12>
          <v-container>
            <v-layout row wrap>
              <add-flight-dialog 
                v-on:snack="showSnackbar($event)"
              ></add-flight-dialog>
              <edit-airline-company-dialog 
                v-bind:airlineCompany="airlineCompany"
                v-on:info-update="infoUpdate($event)"
                v-on:snack="showSnackbar($event)"
              ></edit-airline-company-dialog>
              <add-airplane-dialog 
                v-bind:airlineCompany="airlineCompany"
                v-on:snack="showSnackbar($event)"
              ></add-airplane-dialog>
              <manage-seats-dialog
                v-on:snack="showSnackbar($event)"
              ></manage-seats-dialog>
            </v-layout>
          </v-container>
          
        </v-flex>
      </v-layout>
    </v-container>
    <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
    >
      {{snackbar.msg}}
      <v-btn
          dark
          flat
          @click="snackbar.show = false"
      >
      Close
      </v-btn>
    </v-snackbar>
  </div>
</template>

<script>

import AirlineCompanyInfo from "@/components/AirlineCompany/AirlineCompanyInfo.vue";
import AddFlightDialog from "@/components/AirlineCompany/AddFlightDialog.vue";
import AirlineCompanyController from "@/controllers/airline-company.controller";
import AirlineCompany from "@/models/AirlineCompany";
import EditAirlineCompanyDialog from "@/components/AirlineCompany/EditAirlineCompanyDialog.vue";
import AddAirplaneDialog from "@/components/AirlineCompany/AddAirplaneDialog.vue";
import ManageSeatsDialog from "@/components/AirlineCompany/ManageSeatsDialog.vue";

export default {
  name: "AirlineCompany",
  components: {
    'airline-company-info': AirlineCompanyInfo,
    'add-flight-dialog':AddFlightDialog,
    'edit-airline-company-dialog': EditAirlineCompanyDialog,
    'add-airplane-dialog': AddAirplaneDialog,
    'manage-seats-dialog': ManageSeatsDialog,
},
  data: () => ({
    id: null,
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },

    airlineCompany: new AirlineCompany()
  }),
  created() {
    this.fetchData();
  },
  watch: {
    '$route': 'fetchData'
  },
  methods: {
    fetchData() {
      this.id = this.$route.params.id;

      AirlineCompanyController.get(this.id)
        .then((response) => {
          this.airlineCompany = response.data;
        })
        .catch(() => {
          alert(error.response.data);
          this.$router.push({name: "home"});
        });
    },
    infoUpdate(obj) {
      this.airlineCompany = obj;
    },
    showSnackbar(obj) {
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    }
  }
};
</script>

<style>

</style>

