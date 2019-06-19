const state = {
  allAirlines: null
};

const getters = {
  allAirlines: state => state.allAirlines
};

const mutations = {
  allAirlines(state, nh) {
    state.allAirlines = nh;
  }
};

export { state, getters, mutations };
