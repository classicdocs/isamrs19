<template>
    <v-container >
      <v-layout align-center justify-center>
        <v-flex lg8 md8 sm6 xs12>
          <v-card>
            <v-card-title primary-title>
               <span class="headline" v-if="!isGuest">My Profile</span>
               <span class="headline" v-else>{{user.username}}</span>
            </v-card-title>
            <v-card-text>
              <v-layout justify-center justify-space-around  >
                <v-flex lg5 md5 sm12 xs12 v-if="!isGuest">
                  <span>Username: </span>
                  <v-text-field solo label="label" v-model="user.username"  prepend-icon="assignment_ind" @input="enableBtn"></v-text-field>
                  <span>Password: </span>
                  <v-text-field solo label="label" v-model="user.password"  prepend-icon="lock" @input="enableBtn"></v-text-field>
                </v-flex>
                <v-flex lg5 md5 sm12 xs12>
                  <span>First name: </span>
                  <v-text-field solo label="label" v-model="user.firstname"  prepend-icon="assignment_ind"  @input="enableBtn"></v-text-field>
                  <span>Last name: </span>
                  <v-text-field solo label="label" v-model="user.lastname"  prepend-icon="assignment_ind"  @input="enableBtn"></v-text-field>
                  <span>Email: </span>
                  <v-text-field solo label="label" v-model="user.email"  prepend-icon="email"  @input="enableBtn"></v-text-field>
                  <span>Phone number: </span>
                  <v-text-field solo label="label" v-model="user.phone"  prepend-icon="phone"  @input="enableBtn"></v-text-field>
                  <span>Address: </span>
                  <v-text-field solo label="label" v-model="user.address"  prepend-icon="location_city"  @input="enableBtn"></v-text-field>
                </v-flex>
              </v-layout>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" :disabled="!btn" v-if="!isGuest" @click="update">Update</v-btn>
              <v-btn color="primary" v-if="isGuest && isUser && !show && !isFriend" @click="addFriend">Send friend request</v-btn>
              <v-btn color="primary" v-if="isGuest && isUser && show && !isFriend" @click="withdraw">Withdraw friend request</v-btn>
              <v-btn color="primary" v-if="isGuest && isUser && isFriend" @click="remove">Remove friend</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
import FriendshipController from "@/controllers/friendship.controller.js";
import store from "@/store";
import {User} from "@/models/User";
import * as _ from "lodash";
import { mapMutations } from 'vuex';

export default {
  name: "Profil",

  data:() => ({
    id: null,
    user: new User(),
    btn: false,
    isGuest: true,
    isUser: false,
    show: false,
    isFriend: false,
    progress: false,
  }),
  watch: {
    '$route': 'getUser',
  },
  beforeMount() {
    this.getUser();
  },
  methods: {
    getUser() {
      this.id = this.$route.params.id;
      let loader = this.$loading.show()
      UserController.getUser(this.id)
        .then((response) => {
          loader.hide();
          this.user = new User(response.data);
          if (this.user.role !== "User")
            this.$router.push({name: "flights"});
        })
        .catch((error) => {
          alert(error.response.data);
          this.$router.push({name: "home"});
        })
      if (this.id == store.getters.activeUser.id)
        this.isGuest = false;
      else
        this.isGuest = true;

      this.isUser = store.getters.isUser;
      this.checkRequest();
      this.checkIsFriend();
    },
    update() {
      UserController.update(this.id, this.user)
        .then((response) => {
          this.btn = false;
          store.commit('setSnack', {msg:'Successfully updated!', color:'success'});
        })
        .catch((error) => {
          store.commit('setSnack', {msg: error.response.data, color:'error'});
        })
    },
    enableBtn() {
      this.btn = true;
    },
    addFriend() {
      let data = {
        "from": store.getters.activeUser.id,
        "to": this.id
      }
      FriendshipController.addFriend(data)
        .then((response) => {
          store.commit("setSnack", {msg: "You successfully sent friend request", color: "success"});
          this.show = true;
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color: "error"});
        })
    },
    checkRequest() {
      let data = {
        "from": store.getters.activeUser.id,
        "to": this.id
      }
      FriendshipController.isRequestAlreadySent(data)
        .then((response) => {
          this.show = response.data;
        })
        .catch((error) => {
        })
      return this.show;
    },
    checkIsFriend() {
      let data = {
        "from": store.getters.activeUser.id,
        "to": this.id
      }
      FriendshipController.isFriend(data)
        .then((response) => {
          this.isFriend = response.data;
        })
        .catch((error) => {
        })
    },
    withdraw() {
       var data = {
        "from": store.getters.activeUser.id,
        "to": this.id
      };
      FriendshipController.cancelFriendRequest(data)
        .then((response) => {
          store.commit("setSnack", {msg: "You have successfully withdrawn your friendship request", color:"success"});
          this.show = false;
        })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"});
          this.show = true;
        })
      this.isFriend = false;
    },
    remove() {
      let data = {
        "from": store.getters.activeUser.id,
        "to": this.id
      }
      FriendshipController.removeFriend(data)
        .then((response) => {
          store.commit('setSnack', {msg:'You have successfully removed user from friends list!', color:'success'});
        })
        .catch((error) => {
          store.commit('setSnack', {msg: error.response.data, color:'error'});
        })
      this.isFriend = false;
      this.show = false;
    }
    
  }
}
</script>

<style>

</style>
