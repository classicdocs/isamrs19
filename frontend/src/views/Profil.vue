<template>
    <v-container >
      <v-layout align-center justify-center>
        <v-flex lg8 md8 sm6 xs12>
          <v-card>
            <v-card-title primary-title>
               <span class="headline" v-if="!isGuest">My Profil</span>
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
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
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
      UserController.getUser(this.id)
        .then((response) => {
          this.user = new User(response.data);
        })
        .catch((error) => {
          alert(error.response.data);
          this.$router.push({name: "home"});
        })
      if (this.id == store.getters.activeUser.id)
        this.isGuest = false;
      else
        this.isGuest = true;
    },
    update() {
      UserController.update(this.id, this.user)
        .then((response) => {
          this.btn = false;
          store.commit('setSnack', {msg:'Successfully updated!', color:'success'});
          console.log("succ");
        })
        .catch((error) => {
          store.commit('setSnack', {msg: error.response.data, color:'error'});
          console.log("error");
        })
    },
    enableBtn() {
      this.btn = true;
    },

  }
}
</script>

<style>

</style>
