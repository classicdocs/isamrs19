const state = {
  allAirlines: null,
  reload: true
};

const getters = {
  allAirlines: state => state.allAirlines,
  reload: state => state.reload,
};

const mutations = {
  allAirlines(state, nh) {
    state.allAirlines = nh;
  },
  reload(state, data) {
    state.reload = data;
  }
};

export { state, getters, mutations };
