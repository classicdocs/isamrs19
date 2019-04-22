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
        store.commit('auth', null);
    }

    if (to.meta.guest && store.getters.isLogged) {
        return next({ name: 'home' });
    }

    /*if (!to.meta.guest && !store.getters.isLogged) {
        return next({ name: 'login' });
    }*/

    if (to.meta.sysadmin && !store.getters.isSysAdmin) {
        return next({ name: 'home' });
    }

    if (to.meta.airlineadmin && !store.getters.isAirlineAdmin) {
        return next({ name: 'home' });
    }

    if (to.meta.hoteladmin && !store.getters.isHotelAdmin) {
        return next({ name: 'home' });
    }

    if (to.meta.rentacaradmin && !store.getters.isRentacarAdmin) {
        return next({ name: 'home' });
    }

    return next();
});

export default router;