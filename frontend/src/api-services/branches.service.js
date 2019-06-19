import Axios from "axios";

const ENDPOINTS = {
  BRANCHES: "branches/"
};

export default {
  get(id) {
    return Axios.get(ENDPOINTS.BRANCHES + id);
  }
};
