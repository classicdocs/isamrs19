
const state = {
  snackbar: false,
  message: "",
  color: "",
};

const getters = {
  snackbar: (state) => state.snackbar,
  message: (state) => state.message,
  color: (state) => state.color,
};

const mutations = {
  showSnackbar:(state) => state.snackbar = true,
  closeSnackbar:(state) => state.snackbar = false,
  message:(state, msg) => state.message = msg,
  color: (state, color) => state.color = color,
};

export {
  state,
  getters,
  mutations
};