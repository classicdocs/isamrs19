<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>My friends</h2>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="friends"
        hide-actions
        class="elevation-1"
      >
      <template v-slot:items="props">
        <td class="text-xs-left"><a :href="getLink(props.item.id)">{{ props.item.username }}</a></td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.lastName }}</td>
        <v-btn color="primary" >Remove</v-btn>
      </template>
      </v-data-table>
    </v-card>
    
  </div>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
import store from "@/store";
import {mapGetters} from "vuex";

export default {
  name: 'ListOfFriends',
  data:() => ({
    headers: [
      { text: 'Username', value: 'username' },
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: '', value: 'action' },
    ],
    friends: [],
  }),
  watch: {
    newFriend(friend) {
      this.friends.push(friend);
    }
  },
  beforeMount() {
      let id = store.getters.activeUser.id;
      UserController.getFriends(id)
        .then((response) => {
          response.data.forEach(element => {
            this.friends.push(element);
          });
        })
  },
  computed: {
    newFriend() {
      return store.getters.newFriend;
    }
  },
  methods: {
    getLink(id) {
      return "http://localhost:8080/users/" + id + "/profil";
    }
  }
  
}
</script>