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

    // if (to.meta.user && store.getters.activeUser.id != to.params.id)
    // {
    //     return next({name: 'flights'})
    // }
    // if (to.meta.guest && store.getters.isLogged) {
    //     return next({ name: 'flights' });
    // }

    // /*if (!to.meta.guest && !store.getters.isLogged) {
    //     return next({ name: 'login' });
    // }*/

    // if (to.meta.sysadmin && !store.getters.isSysAdmin) {
    //     return next({ name: 'flights' });
    // }

    // if (to.meta.airlineadmin && !store.getters.isAirlineAdmin) {
    //     return next({ name: 'flights' });
    // }

    // if (to.meta.hoteladmin && !store.getters.isHotelAdmin) {
    //     return next({ name: 'flights' });
    // }

    // if (to.meta.rentacaradmin && !store.getters.isRentacarAdmin) {
    //     return next({ name: 'flights' });
    // }

    return next();
});

export default router;