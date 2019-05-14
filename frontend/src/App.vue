<template>
  <!-- <v-app id="app" v-bind:style="{'backgroundImage': image}"> -->
  <v-app id="app">
    <v-toolbar
     color="primary"
    >
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinksLeft"
          :key="index"
          @click="$router.push(item.computed ? item.path() : item.path)"
          color="primary"
        >{{item.name}}</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn 
          v-for="(item,index) in getNavBarLinksRight"
          :key="index"
          @click="$router.push(item.computed ?  item.path() : item.path)"
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
    navbar : null,
    image: "url('@/assets/wallpaper.jpg')",
  }),
  created() {
    let user = store.getters.activeUser;
    this.navbar =  {
      guest: {
        right : [
          {name:'Log in', path:'/login'},
          {name:'Sign up', path:'/registration'}
        ],
        left : [
          {name: 'Flights', path:'/flights'},
          {name: 'Hotels', path: '/hotels'},
          {name: 'Rent-a-cars', path: '/rent-a-cars'},
        ]
      },
      'User': { 
        right: [
        {name:'My Reservations', path:'/my-reservations'},
        {name:'Friends', path:'/friends'},
        {name:'Profile', path:() => this.getProfilPath , computed: true},
        {name:'Log out', path:'/logout'},
        ],
        left : [
          {name: 'Flights', path:'/flights'},
          {name: 'Hotels', path: '/hotels'},
          {name: 'Rent-a-cars', path: '/rent-a-cars'},]
      },
      'System Admin': { 
        right: [
        {name:'Profile', path:() => this.getProfilPath , computed: true},
        {name:'Log out', path:'/logout'},
        ],
        left : [
        {name: 'System admin panel', path: '/sys-admin'}
          ]
      },
      'Airline Company Admin': { 
        right: [
        {name:'Profile', path:() => this.getProfilPath , computed: true},
        {name:'Log out', path:'/logout'},
        ],
        left : [
        {name: 'My Airline company', path:() => this.getIdAdminOf, computed:true}
          ]
      },
      'RentACar Admin': { 
        right: [
        {name:'Profile', path:() => this.getProfilPath , computed: true},
        {name:'Log out', path:'/logout'},
        ],
        left : [
        {name: 'My Rent-A-Car Service', path:() => this.getIdAdminOf, computed:true}
          ]
      },
      'Hotel Admin': { 
        right: [
        {name:'Profile', path:() => this.getProfilPath , computed: true},
        {name:'Log out', path:'/logout'},
        ],
        left : [
        {name: 'My Hotel', path:() => this.getIdAdminOf, computed:true}
          ]
      }
    };
  },
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
    getProfilPath() {
      return '/users/' + (this.activeUser ? this.activeUser.id : -1) +  '/profil';
    },
    getIdAdminOf() {
      let path = '';
      switch(this.activeUserRole) {
        case 'Airline Company Admin': {
          path = '/airline-company/';
          break;
        }
        case 'Hotel Admin': {
          path = '/hotel-service/' // TREBA DODATI
          break;
        }
        case 'RentACar Admin': {
          path = '/rentacar-service/';
          break;
        }
      }
      console.log(path);
      return path + (this.activeUser ? this.activeUser.idAdminOf : -1);
    }
  },
}
</script>

<style>
#app {
  /* background-image: url("http://citaonica.com/wp-content/uploads/2018/11/travel-health.jpg"); */
  /* background-size: 100% ; */
  background-image: url('~@/assets/wallpaper.jpg');
  background-size: 100%  100%;
  background-color: lavender;
}
</style>

