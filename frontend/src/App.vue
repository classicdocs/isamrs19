<template>
  <v-app>
    <v-snackbar
        v-model="showSnackbar"
        :timeout="5000"
        :color="getSanckbarColor"
        :top="true"
    >
      {{getSnackbarMsg}}
      <v-btn
          dark
          flat
          @click="closeSnackbar"
      >
      Close
      </v-btn>
    </v-snackbar>
    <v-toolbar
     color="primary"
    >
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinks1"
          :key="index"
          @click="this.$router.push(item.path)"
          color="primary"
        >{{item.name}}</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinks2"
          :key="index"
          @click="navigate(item.path)"
          color="primary"
        >{{item.name}}</v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <router-view/>
     <v-list dense>
        <v-list-tile
          v-if="isLogged"
          key="Logout"
          flat
          @click="logout">
          <v-list-tile-action>
            <v-icon>exit_to_app</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>Logout</v-list-tile-content>
        </v-list-tile>
      </v-list>
      <router-view/>
  </v-app>
</template>

<script>
import LoginController from './controllers/login.controller.js';
import { mapGetters } from 'vuex';

import store from "@/store";

export default {
  name: 'App',
  components: {
  },
  data:() => ({
    navbar: {
      guest: {
        'right' : [
          {name:'Log in', path:'login'},
          {name:'Sign up', path:'signup'}
        ],
        'left' : [
          {name: 'Flights', path:'flights'},
          {name: 'Hotels', path: 'hotels'},
          {name: 'Rent-a-cars', path: 'rent-a-cars'},
        ]
      },
      admin: [
        {name:'Log out', path:'/logout'},
      ],
      logged: [
        {name:'Log out', path:'/logout'},
      ]
    },
  }),
  computed: {
    getNavBarLinks() {
      // const role = store.getters.activeUserRole;
      // this.role = role;
      // return this.navbar[role];
    },
    showSnackbar() {
      return store.getters.snackbar;
    },
    
    getSnackbarMsg() {
      return store.getters.message;
    },
    getSanckbarColor() {
      return store.getters.color;
    }
  },
  methods: {
    closeSnackbar() {
      store.commit('closeSnackbar');
    },
  }
  
  },
  computed: {
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
  methods: {
    logout() {
      LoginController.logout();
    },
  },
}
</script>
