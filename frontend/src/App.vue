<template>
  <v-app>
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
      'isAdmin',
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
