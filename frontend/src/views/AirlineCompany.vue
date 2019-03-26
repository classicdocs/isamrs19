<template>
  <div>
    <b-container fluid fill-height>
      <b-row>
        <b-col xl="4" md="6" sm="12"> 
          <AirlineCompanyInfo></AirlineCompanyInfo>
        </b-col>
        <b-col>
          <b-row>
            <b-btn variant="success" @click="modalShow = true">Add flight</b-btn>
            <AddFlightForm 
            v-if="modalShow"
            v-bind:modalShow="modalShow"
            v-on:modalClosed="closeModal()"/>
          </b-row>
          <!-- <b-row>fast reservations</b-row> -->
        </b-col>
      </b-row>
      <b-row>
        <!-- <p>Destination and flights</p> -->
      </b-row>
    </b-container>
    
  </div>
</template>

<script>

import AirlineCompanyInfo from "@/components/AirlineCompanyInfo.vue";
import AddFlightForm from "@/components/AddFlightForm.vue";

export default {
  name: "AirlineCompany",
  components: {
    AirlineCompanyInfo,
    AddFlightForm,
  },
  data: () => ({
    modalShow: false,
  }),
  methods: {
    closeModal() {
      this.modalShow = false;
      console.log(this.modalShow);
    }
  }
};
</script>

<style>

</style>

