const state = {
  allRentACars: null
};

const getters = {
  allRentACars: state => state.allRentACars
};

const mutations = {
  allRentACars(state, nh) {
    state.allRentACars = nh;
  }
};

export { state, getters, mutations };
