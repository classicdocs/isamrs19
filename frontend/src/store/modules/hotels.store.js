const state = {
  allHotels: null
};

const getters = {
  allHotels: state => state.allHotels
};

const mutations = {
  allHotels(state, nh) {
    state.allHotels = nh;
  }
};

export { state, getters, mutations };
