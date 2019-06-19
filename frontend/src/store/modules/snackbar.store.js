const state = {
  message: "",
  color: ""
};

const mutations = {
  setSnack(state, data) {
    state.message = data.msg;
    state.color = data.color;
  }
};

export { state, mutations };
