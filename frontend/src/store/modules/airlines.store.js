const state = {
  allAirlines: null,
  reload: true,
  newFlight: null
};

const getters = {
  allAirlines: state => state.allAirlines,
  reload: state => state.reload,
  newFlight: state => state.newFlight
};

const mutations = {
  allAirlines(state, nh) {
    state.allAirlines = nh;
  },
  reload(state, data) {
    state.reload = data;
  },
  newFlight(fl) {
    state.newFlight = fl;
  }
};

export { state, getters, mutations };
