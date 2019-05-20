<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>You can invite your friends to fly with you if you want</h2>
      </v-card-title>
      <v-card-text>
        <v-data-table
        :headers="headers"
        :items="getFriends"
        hide-actions
        class="elevation-1"
      >
      <template v-slot:items="props">
        <tr :active="props.selected" @click=" check(props)">
          <td class="text-xs-left">{{ props.item.username}}</td>
          <td class="text-xs-left">{{ props.item.firstName}}</td>
          <td class="text-xs-left">{{ props.item.lastName }}</td>
          <td>
            <v-checkbox
              primary
              hide-details
              :input-value="props.selected"
              v-model="selected"
              :value="props.item"
            ></v-checkbox>
          </td>
        </tr>
      </template>
      </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import store from "@/store";
import UserController from "@/controllers/user.controller.js";

export default {
  name: "FriendsInvitation",
  data:() => ({
    friends: [],
    headers: [
      { text: 'Username', value: 'username' },
      { text: 'First name', value: 'firstName' },
      { text: 'Last name', value: 'lastName' },
      { text: 'Invite', value: 'action' },
    ],
    selected: [],
  }),
  beforeMount() {
    this.selected = []
  },
  computed: {
    getFriends() {
      this.friends = store.getters.friends;
      return this.friends;
    }
  },
  watch: {
    selected() {
      this.$emit("friendsInvited", this.selected);
    }
  },
  methods: {
    check(props) {

      let passengersNumber = store.getters.flightReservation.searchParams.passengersNumber;
      if (this.selected.length + 1 > passengersNumber) {
        store.commit("setSnack", {msg: "Maximal number of friends you can invite is " + (passengersNumber - 1), color: "error"});
        props.selected = false;
        this.selected.splice(this.selected.length - 1, 1);
      } else {
        props.selected = !props.selected;      
      }


    }
  }
}
</script>

<style>

</style>
