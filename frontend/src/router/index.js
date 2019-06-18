import Vue from 'vue';
import Router from 'vue-router';
import store from '../store';
import { routes } from './routes';

Vue.use(Router);

const routerConfig = {
    mode: 'history',
    routes,
};

const router = new Router(routerConfig);

router.isCurrentRoute = (routeName) => {
    return router.currentRoute.name === routeName;
};

router.beforeEach((to, from, next) => {
    if (localStorage.user) {
        store.commit('auth', JSON.parse(localStorage.user));
    }
    else {
        store.commit('deauth');
    }

    // must be logged in
    if (!store.getters.isLogged && !to.meta.guest) {
        return next({name:"login"});
    }

    // system admin can't go to that route
    if (store.getters.isSysAdmin && !to.meta.sysadmin && !to.meta.logged) {
        return next({name: "sys-admin"});
    }

    // user can't go to that route
    if (store.getters.isUser && !to.meta.logged && !to.meta.user) {
        return next({name: "flights"})
    }

    // user can't go to that route
    if (store.getters.isUser && !to.meta.user && to.meta.guest) {
        return next({name: "flights"});
    }

    // airline admin but can't go to that route
    if (store.getters.isAirlineAdmin && !to.meta.airlineAdmin && !to.meta.admin && !to.meta.logged) {
        return next({name: "airline-company", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // airline admin can't go to others service
    if (store.getters.isAirlineAdmin && to.meta.airlineAdmin && to.params.id !== store.getters.activeUser.idAdminOf) {
        return next({name: "airline-company", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // airline admin can't go to other user profiles
    if (store.getters.isAirlineAdmin && to.meta.logged) {
        if (to.params.id) {
            if (to.params.id != store.getters.activeUser.id)
                return next({name: "airline-company", params: {id: store.getters.activeUser.idAdminOf}});
        }
        return next();
    }
    
    // hotel admin but can't go to that route
    if (store.getters.isHotelAdmin && !to.meta.hotelAdmin && !to.meta.admin && !to.meta.logged) {
        return next({name: "hotel-service", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // hotel admin can't go to others service
    if (store.getters.isHotelAdmin && to.meta.hotelAdmin && to.params.id !== store.getters.activeUser.idAdminOf) {
        return next({name: "hotel-service", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // hotel admin can't go to other user profiles
    if (store.getters.isHotelAdmin && to.meta.logged) {
        if (to.params.id){
            if (to.params.id != store.getters.activeUser.id)
                return next({name: "hotel-service", params: {id: store.getters.activeUser.idAdminOf}});
        }
        return next();
    }

    // rentacar admin but can't go to that route
    if (store.getters.isRentacarAdmin && !to.meta.rentacarAdmin && !to.meta.admin && !to.meta.logged) {
        return next({name: "rentacar-service", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // rentacar admin can't go to others service
    if (store.getters.isRentacarAdmin && to.meta.rentacarAdmin && to.params.id !== store.getters.activeUser.idAdminOf) {
        return next({name: "rentacar-service", params: {id: store.getters.activeUser.idAdminOf}});
    }

    // rentacar admin can't go to other user profiles
    if (store.getters.isRentacarAdmin && to.meta.logged) {
        if (to.params.id) {
            if (to.params.id != store.getters.activeUser.id)
                return next({name: "rentacar-service", params: {id: store.getters.activeUser.idAdminOf}});
        }
        return next();
    }
    
    // airline admin can't go to change-password if isn't logged in for the first time
    if (store.getters.isAirlineAdmin && to.meta.admin) {
        if (store.getters.activeUser.loggedFirstTime)
            return next({name: "airline-company", params: {id: store.getters.activeUser.idAdminOf}});
        return next();
    }

    // hotel admin can't go to change-password if isn't logged in for the first time
    if (store.getters.isHotelAdmin && to.meta.admin) {
        if (to.params.id) {
            if (store.getters.activeUser.loggedFirstTime)
                return next({name: "hotel-service", params: {id: store.getters.activeUser.idAdminOf}});
        }
        return next();

    }

    // rentacar admin can't go to change-password if isn't logged in for the first time
    if (store.getters.isRentacarAdmin && to.meta.admin) {
        if (store.getters.activeUser.loggedFirstTime)
            return next({name: "rentacar-service", params: {id: store.getters.activeUser.idAdminOf}});
        return next();
    }

    // admin logged for the first time and must change password
    if (store.getters.isAdmin && !to.meta.admin) {
        if (!store.getters.activeUser.loggedFirstTime)
            return next({name: "change-password"});
    }



    return next();
});

export default router;