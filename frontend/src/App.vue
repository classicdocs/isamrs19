<template>
  <v-app>
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

export default {
  name: 'App',
  components: {
  },
  data () {
    return {
      //
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
