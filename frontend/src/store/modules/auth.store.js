import * as _ from 'lodash';
import { User } from '../../models/User';

const state = {
    activeUser: null
};

const getters = {
    activeUser: (state) => state.activeUser,
    isLogged: (state) => state.activeUser ? true : false,
    isAdmin: (state,getters) => getters.isLogged && state.activeUser.isAdmin(),
    activeUserRole: (state) => state.activeUser ? state.activeUserRole : 'guest'
};

const mutations = {
    deauth(state) {
        localStorage.clear();
        state.activeUser = null;
    },
    auth(state, activeUser) {
        state.activeUser = new User(activeUser);
        console.log(state.activeUser);
    }
};

export {
    state,
    getters,
    mutations
};