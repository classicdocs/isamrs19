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
    //console.log(store.activeUser);
    if (to.meta.guest && store.getters.isLogged) {
        return next({ name: 'home' });
    }

    /*if (!to.meta.guest && !store.getters.isLogged) {
        return next({ name: 'login' });
    }

    if (to.meta.admin && !store.getters.isAdmin) {
        return next({ name: 'home' });
    }*/

    return next();
});

export default router;