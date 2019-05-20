const state = {
  newDestination: null,
};

const getters = {
  newDestination:(state) => state.newDestination,
  
};

const mutations = {
  newDestination(state, nf) {
    state.newDestination = nf;
  },
};

export {
  state,
  getters,
  mutations
};