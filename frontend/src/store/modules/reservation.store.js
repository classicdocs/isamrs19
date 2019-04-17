
const state = {
  flightReservation: null,
};

const getters = {
  flightReservation: (state) => state.flightReservation,
};

const mutations = {
  flightReservation(state, data) {
    state.flightReservation = data;
  }
};

export {
  state,
  getters,
  mutations
};