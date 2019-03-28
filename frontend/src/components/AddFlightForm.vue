<template>
  <div>
    <b-modal
      v-model="modal"
      hide-footer
      title="Add flight"
      header-bg-variant="dark"
      header-text-variant="light"
      scrollable
      no-close-on-backdrop
    >
      <b-form @submit.prevent="onSubmit" @reset.prevent="onReset">
        <b-form-group label="Start destination:" label-for="start-destination">
          <multiselect 
            v-model="startDestination" 
            :options="startDestinations" 
            placeholder="Select one"
            @select="error.startDestination = false"
          ></multiselect>
  
          <div v-if="error.startDestination" class="error-msg">You must select start destination!</div>
        </b-form-group>
        <b-form-group label="Final destination:" label-for="final-destination">
           <multiselect 
            v-model="finalDestination" 
            :options="finalDestinations" 
            placeholder="Select one"
            @select="error.finalDestination = false"
          ></multiselect>
          <div v-if="error.finalDestination" class="error-msg">You must select final destination!</div>
        </b-form-group>
        <b-container>
          <b-row>
            <b-col>
              <b-form-group label="Departure date:" label-for="departure-date">
                <datepicker
                  id="departure-date"
                  v-model="departureDate"
                  placeholder="Select Date"
                  :format="dateFormat"
                  @selected="error.departureDate = false"
                ></datepicker>
                <div v-if="error.departureDate" class="error-msg">You must select departure date!</div>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Departure time:" label-for="departure-time">
                <vue-timepicker 
                  id="departure-time" 
                  v-model="departureTime"
                  :format="timeFormat"
                  @change="error.departureTime = false"
                ></vue-timepicker>
                <div v-if="error.departureTime" class="error-msg">You must select departure time!</div>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-group label="Landing date:" label-for="landing-date">
                <datepicker
                  id="landing-date"
                  v-model="landingDate"
                  placeholder="Select Date"
                  :format="dateFormat"
                  @selected="error.landingDate = false"
                ></datepicker>
                <div v-if="error.landingDate" class="error-msg">You must select landing date!</div>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Landing time:" label-for="landing-time">
                <vue-timepicker 
                  id="landing-time" 
                  v-model="landingTime"
                  :format="timeFormat"
                  @change="error.landingTime = false"
                ></vue-timepicker>
                <div v-if="error.landingTime" class="error-msg">You must select landing time!</div>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-group label="Distance:" label-for="distance">
                <b-input
                  id="distance"
                  v-model="distance" 
                  type="number"
                  min="1"
                  placeholder="km"
                  @change="() => {error.distance = false; error.distanceNumber = false;}"
                />
                <div v-if="error.distance" class="error-msg">You must enter the distance!</div>
                <div v-else-if="error.distanceNumber" class="error-msg">Distance must be a positive number!</div>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Flight time: " label-for="flight-time">
                <vue-timepicker 
                  id="flight-time" 
                  v-model="flightTime"
                  @change="error.flightTime = false"
                ></vue-timepicker>
                <div v-if="error.flightTime" class="error-msg">You must select flight time!</div>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-group label="Ticket price:" label-for="ticket-price">
                <b-input
                  id="ticket-price"
                  v-model="ticketPrice" 
                  type="number"
                  min="1"
                  placeholder="€"
                  @change="error.ticketPrice = false"
                />
                <div v-if="error.ticketPrice" class="error-msg">You must enter the ticket price!</div>
                <div v-else-if="error.ticketPriceNumber" class="error-msg">Ticket price must be a positive number!</div>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <label label-for="transfer-destination">Transfer destination</label>
              <multiselect
                id="transfer-destination" 
                v-model="transferDestination" 
                :multiple="true" 
                :options="transferDestinations" 
                placeholder="Select one" 
              ></multiselect>
            </b-col>
          </b-row>
          <b-row>
          </b-row>
        </b-container>

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import VueTimepicker from "vue2-timepicker";
import Datepicker from "vuejs-datepicker";
import Multiselect from "vue-multiselect";

export default {
  name: "AddFlightForm",
  components: {
    VueTimepicker,
    Datepicker,
    Multiselect
  },
  data: () => ({
    modal: true,

    startDestinations: [{ text: "Select One", value: null }, '1'],
    finalDestinations: [{ text: "Select One", value: null }],
    transferDestinations: [],

    startDestination: null,
    finalDestination: null,
    transferDestination: [],
    departureDate: null,
    departureTime: {HH: 'HH', mm: "mm"},
    landingDate: null,
    landingTime: {HH: 'HH', mm: 'mm'},
    flightTime: {HH: 'HH', mm: 'mm'},
    ticketPrice: null,
    distance: null,
    dateFormat: "dd-MM-yyyy",
    timeFormat: "HH:mm",

    error: {
      startDestination: false,
      finalDestination: false,
      departureDate: false,
      departureTime: false,
      landingDate: false,
      landingTime: false,
      flightTime: false,
      ticketPrice: false,
      ticketPriceNumber: false,
      distance: false,
      distanceNumber: false,
    }
  }),
  watch: {
    modal(val) {
      if (val === false) this.$emit("modalClosed") && this.reset();
    }
  },
  methods: {
    onSubmit(e) {
      if (this.validateForm()) {
        alert("submit");
      }
      
    },
    onReset(e) {
      this.reset();
    },
    validateForm() {
      let retVal = true;
      if (this.startDestination === null) {
        this.error.startDestination = true;
        retVal = false;
      }
      if (this.finalDestination === null) {
        this.error.finalDestination = true;
        retVal = false;
      }
      if (this.departureDate === null) {
        this.error.departureDate = true;
        retVal = false;
      }
      if (this.departureTime.HH === "HH" || isNaN(this.departureTime.mm)) {
        this.error.departureTime = true;
        retVal = false;
      }
      if (this.landingDate === null) {
        this.error.landingDate = true;
        retVal = false;
      }
      if (this.landingTime.HH === "HH" || isNaN(this.landingTime.mm))  {
        this.error.landingTime = true;
        retVal = false;
      }
      if (this.flightTime.HH === "HH" || isNaN(this.flightTime.mm)) {
        this.error.flightTime = true;
        retVal = false;
      }
      if (this.ticketPrice === null) {
        this.error.ticketPrice = true;
        retVal = false;
      } else if (this.ticketPrice <= 0 || isNaN(this.ticketPrice)) {
        this.error.ticketPriceNumber = true;
        retVal = false;
      } 
      if (this.distance === null) {
        this.error.distance = true;
        retVal = false;
      } else if (this.distance <= 0 || isNaN(this.distance)) {
        this.error.distanceNumber = true;
        retVal = false;
      }

      return retVal;
    },
    reset() {
      /* Reset our form values */
      console.log(this.departureTime);

      this.startDestination = null;
      this.finalDestination = null;
      this.transferDestination = [];
      this.departureDate = null;
      this.departureTime = {HH:"HH",mm:"mm"};
      this.landingDate = null;
      this.landingTime = {HH:"HH",mm:"mm"};
      this.flightTime = {HH:"HH",mm:"mm"};
      this.ticketPrice = null;
      this.distance = null;

      this.error.startDestination = false;
      this.error.finalDestination = false;
      this.error.departureDate = false;
      this.error.departureTime = false;
      this.error.landingDate = false;
      this.error.landingTime = false;
      this.error.flightTime = false;
      this.error.ticketPrice = false;
      this.error.distance = false;

      /* Trick to reset/clear native browser form validation state */
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>

<style>

div {
  text-align: center;
}
.display-time, .vdp-datepicker div input {
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
}
.error-msg {
  color: red;
  text-align: center;
  font-size: 0.9rem;
}

button {
  margin: 10px;
}
</style>

