import {ENVIRONMENTS, BACKEND_HOSTNAMES, API_ROUTE} from "@/constants";

export default {
  getEnv() {
    console.log(window.location.hostname);
    return ENVIRONMENTS[window.location.hostname];
  },

  getHostName() {
    console.log(this.getEnv());
    return BACKEND_HOSTNAMES[this.getEnv()];
  },

  getApiUrl() {
    console.log(this.getHostName());
    return this.getHostName() + API_ROUTE;
  }
};