import FlightReservation from "@/models/FlightReservation";

const state = {
  flightReservation: null,
};

const getters = {
  flightReservation: (state) => new FlightReservation(),
  seatsPickedDeparture: (state) => state.flightReservation.seatsPickedDeparture,
  seatsPickedReturn: (state) => state.flightReservation.seatsPickedReturn,
};

const mutations = {
  flightReservation(state, data) {
    state.flightReservation = data;
  },
  seatsPickedDeparture(state, data) {
    state.flightReservation.seatsPickedDeparture = data;
  },
  seatsPickedReturn(state, data) {
    state.flightReservation.seatsPickedReturn = data;
  }
};

export {
  state,
  getters,
  mutations
};