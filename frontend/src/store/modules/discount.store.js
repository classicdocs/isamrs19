const state = {
  seats: [],
  discount: null
};

const getters = {
  seats: state => state.seats
};

const mutations = {
  seats(state, seats) {
    state.seats = seats;
  }
};

export { state, getters, mutations };
