const state = {
  newFriend: null,
};

const getters = {
  newFriend:(state) => state.newFriend
};

const mutations = {
  newFriend(state, nf) {
    state.newFriend = nf;
  }
};

export {
  state,
  getters,
  mutations
};