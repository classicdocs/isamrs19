const state = {
  newFriend: null,
  newRequest: null
};

const getters = {
  newFriend: state => state.newFriend,
  newRequest: state => state.newRequest
};

const mutations = {
  newFriend(state, nf) {
    state.newFriend = nf;
  },
  newRequest(state, nr) {
    state.newRequest = nr;
  }
};

export { state, getters, mutations };
