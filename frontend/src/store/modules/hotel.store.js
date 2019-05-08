const state = {
    newHotel: null,
};
  
const getters = {
    newHotel:(state) => state.newHotel,
};

const mutations = {
    newHotel(state, nh) {
        state.newHotel = nh;
    },
};

export {
    state,
    getters,
    mutations
};