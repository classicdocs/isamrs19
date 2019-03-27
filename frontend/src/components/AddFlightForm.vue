<template>
  <div>
    <b-modal
      v-model="modal"
      hide-footer
      title="Add flight"
      header-bg-variant="dark"
      header-text-variant="light"
    >
      <b-form>
        <b-form-group label="Start destination:" label-for="start-destination">
          <b-form-select
            id="start-destination"
            :options="startDestinations"
            required
            v-model="startDestination"
          />
        </b-form-group>
        <b-form-group label="Final destination:" label-for="final-destination">
          <b-form-select
            id="final-destination"
            :options="finalDestinations"
            required
            v-model="finalDestination"
          />
        </b-form-group>

        <b-container>
          <b-row>
            <b-col>
              <b-form-group label="Departure date:" label-for="departure-date">
                <datepicker
                  id="departure-date"
                  v-model="departureDate"
                  placeholder="Select Date"
                  :format="format"
                ></datepicker>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Departure time:" label-for="departure-time">
                <vue-timepicker id="departure-time" v-model="departureTime"></vue-timepicker>
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
                  :format="format"
                ></datepicker>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Landing time:" label-for="landing-time">
                <vue-timepicker id="landing-time" v-model="landingTime"></vue-timepicker>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-group label="Flight time: " label-for="flight-time">
                <vue-timepicker id="flight-time" v-model="flightTime"></vue-timepicker>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Ticket price:" label-for="ticket-price">
                <b-input id="ticket-price" v-model="ticketPrice" required type="text"/>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Select transfer destinations:" label-for="transfer-destinations">
                <b-form-select
                  id="transfer-destinations"
                  class="selectpicker"
                  multiple
                  :options="transferDestinations"
                  required
                  v-model="transferDestination"
                />
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Selected: " label-for="selected-transfer-destinations">
                <b-form-select
                  id="transfer-destinations"
                  class="selectpicker"
                  multiple
                  readonly
                  :options="transferDestination"
                  v-model="transferDestination"
                />
              </b-form-group>
            </b-col>
          </b-row>
        </b-container>

        <b-button @click="submit" variant="primary">Submit</b-button>
        <b-button @click="reset" variant="danger">Reset</b-button>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import VueTimepicker from "vue2-timepicker";
import Datepicker from "vuejs-datepicker";

export default {
  name: "AddFlightForm",
  components: {
    VueTimepicker,
    Datepicker
  },
  data: () => ({
    modal: true,

    startDestinations: [{ text: "Select One", value: null }],
    finalDestinations: [{ text: "Select One", value: null }],
    transferDestinations: [],

    startDestination: null,
    finalDestination: null,
    transferDestination: [],
    departureDate: null,
    departureTime: null,
    landingDate: null,
    landingTime: null,
    flightTime: null,
    ticketPrice: null,
    format: "dd-MM-yyyy"
  }),
  watch: {
    modal(val) {
      if (val === false) this.$emit("modalClosed") && this.reset();
    }
  },
  methods: {
    submit() {
      alert(JSON.stringify(this.form));
    },
    reset() {
      /* Reset our form values */
      this.startDestination = null;
      this.finalDestination = null;
      this.transferDestination = [];
      this.departureDate = null;
      this.departureTime = { HH: "HH", mm: "mm" };
      this.landingDate = null;
      this.landingTime = { HH: "HH", mm: "mm" };
      this.flightTime = { HH: "HH", mm: "mm" };
      this.ticketPrice = null;
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
</style>

