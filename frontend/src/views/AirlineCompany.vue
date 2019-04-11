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
              <add-flight-form 
                v-on:snack="showSnackbar($event)"
              ></add-flight-form>
              <edit-airline-company-form 
                v-bind:airlineCompany="airlineCompany"
                v-on:info-update="infoUpdate($event)"
                v-on:snack="showSnackbar($event)"
              ></edit-airline-company-form>
              <add-airplane-form 
              v-bind:airlineCompany="airlineCompany"
              v-on:snack="showSnackbar($event)"
              ></add-airplane-form>
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
import AddFlightForm from "@/components/AirlineCompany/AddFlightForm.vue";
import AirlineCompanyController from "@/controllers/airline-company.controller";
import AirlineCompany from "@/models/AirlineCompany";
import EditAirlineCompanyForm from "@/components/AirlineCompany/EditAirlineCompanyForm.vue";
import AddAirplaneForm from "@/components/AirlineCompany/AddAirplaneForm.vue";

export default {
  name: "AirlineCompany",
  components: {
    'airline-company-info': AirlineCompanyInfo,
    'add-flight-form':AddFlightForm,
    'edit-airline-company-form': EditAirlineCompanyForm,
    'add-airplane-form': AddAirplaneForm,
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
          console.log("1");
          console.log(this.airlineCompany);
          this.airlineCompany = response.data;
          console.log("2");
          console.log(this.airlineCompany);
        })
        .catch(() => {
          console.log("redirect");
          // redirect
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

