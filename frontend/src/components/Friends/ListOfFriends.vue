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
          <td class="text-xs-left">
            <a :href="getLink(props.item.id)">{{ props.item.username }}</a>
          </td>
          <td class="text-xs-left">{{ props.item.firstName }}</td>
          <td class="text-xs-left">{{ props.item.lastName }}</td>
          <v-btn color="primary" @click="remove(props.item.id)">Remove</v-btn>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import UserController from "@/controllers/user.controller.js";
import FriendshipController from "@/controllers/friendship.controller.js";
import store from "@/store";
import { mapGetters } from "vuex";

export default {
  name: "ListOfFriends",
  data: () => ({
    headers: [
      { text: "Username", value: "username" },
      { text: "First name", value: "firstName" },
      { text: "Last name", value: "lastName" },
      { text: "", value: "action" }
    ],
    friends: []
  }),
  watch: {
    newFriend(friend) {
      this.friends.push(friend);
    }
  },
  beforeMount() {
    let id = store.getters.activeUser.id;
    UserController.getFriends(id).then(response => {
      response.data.forEach(element => {
        this.friends.push(element);
      });
    });
  },
  computed: {
    newFriend() {
      return store.getters.newFriend;
    }
  },
  methods: {
    getLink(id) {
      return "http://localhost:8080/users/" + id + "/profil";
    },
    remove(id) {
      let data = {
        from: store.getters.activeUser.id,
        to: id
      };
      FriendshipController.removeFriend(data)
        .then(response => {
          store.commit("setSnack", {
            msg: "You have successfully removed user from friends list!",
            color: "success"
          });
          let ind = -1;
          this.friends.forEach(element => {
            if (element.id === response.data.id)
              ind = this.friends.indexOf(element);
          });

          if (ind !== -1) this.friends.splice(ind, 1);
        })
        .catch(error => {
          store.commit("setSnack", {
            msg: error.response.data,
            color: "error"
          });
        });
    }
  }
};
</script>
