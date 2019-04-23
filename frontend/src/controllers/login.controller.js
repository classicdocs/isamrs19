import Vue from 'vue';
import * as _ from 'lodash';
import Axios from 'axios';
//import store from '../store';
import store from '@/store';
import LoginApiService from "@/api-services/login.service";
import { User } from '../models/User';

const AUTH_HEADER = 'Authorization';

export default {
  setLocalStorageAuthData(data) {
    localStorage.setItem('token',null);  
    localStorage.setItem('user', data ? data : null);
  },

  setAuthHeader(unset = false) {
    if (unset) {
      delete Axios.defaults.headers[AUTH_HEADER];
      return;
    }
    _.assign(
      Axios.defaults.headers,
      {'Authorization' : 'JWT' + localStorage.getItem('token')}
    );
  },

  initStoreAuth() {
    const userData = JSON.parse(localStorage.getItem('user'));
    if (userData) {
      this.setAuthHeader();
      store.commit('auth', userData);
      return LoginApiService.refreshUser().then((response) => {
        if (response.data) {
          this.updateUserInLocalStorage(response.data);
        }
      });
    }
    return this.setAuthHeader(true);
  },

  checkLocalStorage() {
    const userData = JSON.parse(localStorage.getItem('user'));

    if (userData) {
      this.setAuthHeader();
    }

    return userData;
  },
  
  updateUserInLocalStorage(newUserData) {
    const userData = JSON.parse(localStorage.getItem('user'));
    _.assign(userData, newUserData);
    localStorage.setItem('user', JSON.stringify(userData));
  },

  login(data) {
    return LoginApiService.login(data).then((response) => {
      var user = new User();
      Object.assign(user, response.data);
      this.setLocalStorageAuthData(JSON.stringify(user));
      this.setAuthHeader();
      store.commit('auth', response.data);
      let path = 'flights';
      let loggedFirstTime = store.getters.activeUser.loggedFirstTime;
      let role = store.getters.activeUserRole;
      console.log(role);
      if (loggedFirstTime === false && role !== "User" && role !== "System Admin")
        Vue.prototype.router.push("/change-password");
      else {
        switch(role) {
          case 'Airline Company Admin': {
            
            path = '/airline-company/' + store.getters.activeUser.idAdminOf;
            break;
          }
          case 'Hotel Admin': {
            path = '/hotel-service/' +  store.getters.activeUser.idAdminOf; // TREBA DODATI ruta  + store.getters.activeUser.idAdminOf;
            break;
          }
          case 'RentACar Admin': {
            path = '/rentacar-service/' + store.getters.activeUser.idAdminOf;
            break;
          }
          case 'System Admin': {
            path = '/sys-admin/';
            break;
          }
        }
        Vue.prototype.router.push(path);
      }
      
      return response;
    });
  },

  logout() {
    //LoginApiService.logout().then(() => {
      this.setLocalStorageAuthData({
        token: null,
        user: null,
      });
      this.setAuthHeader(true);
    //});
    store.commit('deauth');
    Vue.prototype.router.push( { name: 'login' });
  },

  changePassword(data) {
    return LoginApiService.changePassword(data);
  },

  checkAuthStatus() {
    const userData = this.checkLocalStorage();
    if (!userData) {
      return false;
    }
    store.commit('auth', userData);
    return store.getters.activeUser;
  }
};