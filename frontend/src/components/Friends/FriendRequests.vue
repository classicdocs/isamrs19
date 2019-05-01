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
        <td class="text-xs-left">{{ props.item.username }}</td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.lastName }}</td>
        <td>
        <v-btn color="primary" @click="accept(props.item.id)">Accept</v-btn>
        <v-btn color="primary" >Cancel</v-btn>
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
        <td class="text-xs-left">{{ props.item.username }}</td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.lastName }}</td>
        <v-btn color="primary" >Cancel</v-btn>
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
          this.requestsFrom.splice(ind,1);
          store.commit("newFriend", response.data);
          store.commit("setSnack", {"msg": "Successfully accepted friend request", "color": "success"});
        });
    }
  }
  
}
</script>