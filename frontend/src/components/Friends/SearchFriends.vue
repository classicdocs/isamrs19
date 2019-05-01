<template>
  <v-container fluid style="background-color: white">
    <v-layout row wrap >
      <v-flex lg11 md11 sm11 xs11 style="padding-bottom: 10px">
        <v-text-field
          v-model="searchParams"
          placeholder="Search friends"
          prepend-icon="search"
          hint="Search friends by username, first name or last name"
          @keyup.enter="search"
        ></v-text-field>
      </v-flex>
      <v-flex lg1 md1 sm1 xs1 >
        <v-btn color="primary" round @click="search"><v-icon>search</v-icon></v-btn>
      </v-flex>
      <v-flex lg12 md12 sm12 xs12>
        <v-data-table
          :headers="headers"
          :items="friends"
          class="elevation-1"
          hide-actions
          v-if="friends.length > 0"
        >
          <template v-slot:items="props">
            <td class="text-xs-left">{{ props.item.username }}</td>
            <td class="text-xs-left">{{ props.item.firstName }}</td>
            <td class="text-xs-left">{{ props.item.lastName }}</td>
            <v-btn color="primary" @click="viewProfil(props.item.id)">View profil</v-btn>
            <v-btn color="primary" @click="addFriend(props.item.id)">Send friend request</v-btn>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
import FriendshipController from "@/controllers/friendship.controller.js";
import store from "@/store";

export default {
  name: 'SearchFriends',
  data:() => ({
    searchParams: null,
    friends: [],
    headers: [
      { text: 'Username', value: 'username' },
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: '', value: 'action' },
    ],
    id: null,
  }),
  methods: {
    search() {
      if (this.searchParams === null) {
        store.commit('setSnack', {msg: 'You must input something', color:'error'});
        return;
      }
      this.id = store.getters.activeUser.id;
      UserController.searchFriends(this.id, {'friend' : this.searchParams})
        .then((response) => {
          if (response.data.length === 0)
            store.commit('setSnack', {msg: 'There is no search result!', color:'info'});
          this.friends = response.data;
          console.log(response.data);
        })
    },
    viewProfil(id) {
    this.$router.push("/users/" + id + "/profil");
    },
    addFriend(id) {
      let data = {
        "from": this.id,
        "to": id
      }
      FriendshipController.addFriend(data)
        .then((response) => {
          store.commit("setSnack", {msg: "You successfully sent friend request", color: "success"});
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color: "error"});
        })
    }
  },
  
  
}
</script>