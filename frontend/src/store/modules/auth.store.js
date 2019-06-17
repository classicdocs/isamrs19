import * as _ from 'lodash';
import { User } from '../../models/User';
import { stat } from 'fs';

const state = {
    activeUser: null
};

const getters = {
    activeUser: (state) => state.activeUser,
    isLogged: (state) => !_.isEmpty(state.activeUser),
    isUser: (state, getters) => getters.isLogged && state.activeUser.isUser(),
    isAdmin: (state, getters) => getters.isLogged &&  getters.isAirlineAdmin 
    || getters.isHotelAdmin || getters.isRentacarAdmin,
    isSysAdmin: (state,getters) => getters.isLogged && state.activeUser.isSysAdmin(),
    isAirlineAdmin: (state,getters) => getters.isLogged && state.activeUser.isAirlineAdmin(),
    isHotelAdmin: (state,getters) => getters.isLogged && state.activeUser.isHotelAdmin(),
    isRentacarAdmin: (state,getters) => getters.isLogged && state.activeUser.isRentacarAdmin(),
    activeUserRole: (state) => state.activeUser ? state.activeUser.role : 'guest',
};

const mutations = {
    deauth(state) {
        localStorage.clear();
        state.activeUser = null;
    },
    auth(state, activeUser) {
        state.activeUser = new User(activeUser);
    },
    loggedFirstTime(state) {
        console.log("SET LOGGED");
        state.activeUser.loggedFirstTime = true;
        localStorage.setItem('user', JSON.stringify(state.activeUser));
    }
};

export {
    state,
    getters,
    mutations
};