const state = {
    hotelReservations: null,
};
  
const getters = {
    hotelReservations:(state) => state.hotelReservations,
};

const mutations = {
    hotelReservations(state, hr) {
        state.hotelReservations = hr;
    },
};

export {
    state,
    getters,
    mutations
};