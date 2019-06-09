const state = {
    searchHotelParams: null,
};
  
const getters = {
    searchHotelParams:(state) => state.searchHotelParams,
};

const mutations = {
    searchHotelParams(state, shp) {
        state.searchHotelParams = shp;
    },
};

export {
    state,
    getters,
    mutations
};