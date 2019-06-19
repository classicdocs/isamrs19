const state = {
  allRooms: null
};

const getters = {
  allRooms: state => state.allRooms
};

const mutations = {
  allRooms(state, nh) {
    state.allRooms = nh;
  }
};

export { state, getters, mutations };
