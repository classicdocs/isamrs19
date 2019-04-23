<template>
  <div>
    <v-container>
      <v-layout row wrap>
        <v-flex lg11 md11 sm11 xs11>
          <v-text-field
            v-model="searchParams"
            placeholder="Search friends"
            prepend-icon="search"
            hint="Search friends by username, first name or last name"
            @keyup.enter="search"
          ></v-text-field>
        </v-flex>
        <v-flex lg1 md1 sm1 xs1>
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
            </template>
          </v-data-table>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
import store from "@/store";

export default {
  name: 'Friends',
  data:() => ({
    searchParams: null,
    friends: [],
    headers: [
      { text: 'Username', value: 'username' },
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: '', value: 'action' },
    ],
  }),
  methods: {
    search() {
      if (this.searchParams === null) {
        store.commit('setSnack', {msg: 'You must input something', color:'error'});
        return;
      }
      let id = store.getters.activeUser.id;
      UserController.searchFriends(id, {'friend' : this.searchParams})
        .then((response) => {
          if (response.data.length === 0)
            store.commit('setSnack', {msg: 'There is no search result!', color:'info'});
          this.friends = response.data;
          console.log(response.data);
        })
    },
    viewProfil(id) {
    this.$router.push("/users/" + id + "/profil");
    }
  },
  
  
}
</script>

<style>

</style>
