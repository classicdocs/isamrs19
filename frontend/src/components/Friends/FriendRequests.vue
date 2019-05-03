<template>
  <div>
     <v-card>
      <v-card-title primary-title>
        <h2>Friend requests</h2>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="requestsFrom"
        hide-actions
        class="elevation-1"
      >
      <template v-slot:items="props">
        <td class="text-xs-left"><a :href="getLink(props.item.id)">{{ props.item.username }}</a></td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.lastName }}</td>
        <td>
        <v-btn color="primary" @click="accept(props.item.id)">Accept</v-btn>
        <v-btn color="primary" @click="cancel(props.item.id)">Cancel</v-btn>
        </td>
      </template>
      </v-data-table>
    </v-card>
    <v-card>
      <v-card-title primary-title>
         <h2>Friend requests that I sent</h2>
      </v-card-title>
       <v-data-table
        :headers="headers"
        :items="requestsTo"
        hide-actions
        class="elevation-1"
      >
      <template v-slot:items="props">
        <td class="text-xs-left"><a :href="getLink(props.item.id)">{{ props.item.username }}</a></td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.lastName }}</td>
        <v-btn color="primary" @click="withdraw(props.item.id)">Withdraw</v-btn>
      </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>

import FriendshipController from "@/controllers/friendship.controller.js";
import UserController from "@/controllers/user.controller.js";
import store from "@/store";

export default {
  name: 'FriendRequests',
  data:() => ({
    headers: [
      { text: 'Username', value: 'username' },
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: '', value: 'action' },
    ],
    requestsTo: [],
    requestsFrom: [],
    id: null,
  }),
  computed: {
    newRequest() {
      return store.getters.newRequest;
    }
  },
  watch: {
    newRequest(request) {
      this.requestsTo.push(request);
    }
  },
  beforeMount() {
      this.id = store.getters.activeUser.id;
      UserController.getFriendRequests(this.id)
        .then((response) => {
          response.data.requestsTo.forEach(element => {
            this.requestsTo.push(element);
          });
          response.data.requestsFrom.forEach(element => {
            this.requestsFrom.push(element);
          });
        })
  },
  methods: {
    accept(from) {
      var data = {
        "from": from,
        "to": this.id
      };
      FriendshipController.acceptFriendRequest(data)
        .then((response) => {
          let ind = -1;
          this.requestsFrom.forEach(element => {
            if (element.id === from)
              ind = this.requestsFrom.indexOf(element);
          });
          if (ind != -1)
            this.requestsFrom.splice(ind,1);
          store.commit("newFriend", response.data);
          store.commit("setSnack", {"msg": "Successfully accepted friend request", "color": "success"});
        });
    },
    cancel(from) {
       var data = {
        "from": from,
        "to": this.id
      };
      FriendshipController.cancelFriendRequest(data)
        .then((response) => {
          store.commit("setSnack", {msg: "You have successfully cancel friendship request", color:"success"});
          let ind = -1;
          this.requestsFrom.forEach(element => {
            if (element.id === from)
              ind = this.requestsFrom.indexOf(element);
          });
          if (ind != -1)
            this.requestsFrom.splice(ind,1);
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"});
        })
    },
    withdraw(toId) {
       var data = {
        "from": this.id,
        "to": toId
      };
      FriendshipController.cancelFriendRequest(data)
        .then((response) => {
          store.commit("setSnack", {msg: "You have successfully withdrawn your friendship request", color:"success"});
          let ind = -1;
          this.requestsTo.forEach(element => {
            if (element.id === toId)
              ind = this.requestsTo.indexOf(element);
          });
          if (ind != -1)
            this.requestsTo.splice(ind,1);
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"});
        })
    },
    getLink(id) {
      return "http://localhost:8080/users/" + id + "/profil";
    }
  }
  
}
</script>