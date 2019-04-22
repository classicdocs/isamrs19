<template>
  <v-app id="app">
    <v-toolbar
     color="primary"
    >
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinksLeft"
          :key="index"
          @click="$router.push({name: item.path})"
          color="primary"
        >{{item.name}}</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinksRight"
          :key="index"
          @click="$router.push({name: item.path})"
          color="primary"
        >{{item.name}}</v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <snackbar/>
    <router-view/>

  </v-app>
</template>

<script>

import { mapGetters } from 'vuex';
import Snackbar from "@/components/Snackbar.vue";
import store from "@/store";

export default {
  name: 'App',
  components: {
    'snackbar': Snackbar
  },
  data:() => ({
    role: 'guest',
    navbar: {
      guest: {
        right : [
          {name:'Log in', path:'login'},
          {name:'Sign up', path:'registration'}
        ],
        left : [
          {name: 'Flights', path:'flights'},
          {name: 'Hotels', path: 'hotels'},
          {name: 'Rent-a-cars', path: 'rent-a-cars'},
        ]
      },
      admin: {
        right: [
        {name:'Log out', path:'logout'},
        ],
        left: []
      },
      User: { 
        right: [
        {name:'Log out', path:'logout'},
        ],
        left : [
          {name: 'Flights', path:'flights'},
          {name: 'Hotels', path: 'hotels'},
          {name: 'Rent-a-cars', path: 'rent-a-cars'},]
      },
    },
  }),
  computed: {
    getNavBarLinksRight() {
      const role = store.getters.activeUserRole;
      this.role = role;
      return this.navbar[role].right;
    },
    getNavBarLinksLeft() {
      const role = store.getters.activeUserRole;
      this.role = role;
      return this.navbar[role].left;
    },
    ...mapGetters([
      'activeUser',
      'isSysAdmin',
      'isAirlineAdmin',
      'isHotelAdmin',
      'isRentacarAdmin',
      'isLogged',
      'activeUserRole',
    ]),
  },
}
</script>

<style>
#app {
  /* background-image: url("http://citaonica.com/wp-content/uploads/2018/11/travel-health.jpg"); */
  /* background-size: 100% ; */
  background-color: lavender;
}
</style>

