<template>
  <div>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs6>
          <airline-company-info v-bind:airlineCompany="airlineCompany"/>
        </v-flex>
        <v-flex xs6>
          <v-btn 
            color="primary"
            @click="addFormDialog = true"
          >Add flight</v-btn>
          <v-dialog
            v-model="addFormDialog"
            max-width="500px"
          >
            <add-flight-form v-if="addFormDialog"
              v-on:operation="closeAddForm($event)"
              v-on:destination-error="showSnackbar($event)"
            ></add-flight-form>
          </v-dialog>

          <v-btn 
            color="primary"
            @click="editDialog = true"
          >Edit information</v-btn>
          <v-dialog
            v-model="editDialog"
            max-width="500px"
          >
            <edit-airline-company-form 
              v-if="editDialog"
              v-bind:airlineCompany="airlineCompany"
              v-on:info-update="infoUpdate($event)"
              v-on:update-error="updateError($event)"
            ></edit-airline-company-form>
          </v-dialog>

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

import AirlineCompanyInfo from "@/components/AirlineCompanyInfo.vue";
import AddFlightForm from "@/components/AddFlightForm.vue";
import AirlineCompanyController from "@/controllers/airline-company.controller";
import AirlineCompany from "@/models/AirlineCompany";
import EditAirlineCompanyForm from "@/components/EditAirlineCompanyForm.vue";

export default {
  name: "AirlineCompany",
  components: {
    'airline-company-info': AirlineCompanyInfo,
    'add-flight-form':AddFlightForm,
    'edit-airline-company-form': EditAirlineCompanyForm,
  },
  data: () => ({
    id: null,
    editDialog: false,
    addFormDialog: false,
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
          console.log(response);
          this.airlineCompany = response.data;
          console.log(this.airlineCompany);
        })
        .catch(() => {
          console.log("redirect");
          // redirect
        });
    },
    closeAddForm(obj) {
      this.addFormDialog = false;
      this.showSnackbar(obj);
    },
    infoUpdate(obj) {
      this.airlineCompany = obj;
      this.editDialog = false;
      this.showSnackbar({color: "success", msg: "Successfully updated!"});
    },
    updateError(obj) {
      this.showSnackbar(obj);
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

