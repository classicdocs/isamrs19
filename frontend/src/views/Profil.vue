<template>
    <v-container >
      <v-layout align-center justify-center>
        <v-flex lg8 md8 sm6 xs12>
          <v-card>
            <v-card-title primary-title>
               <span class="headline">Profil</span>
            </v-card-title>
            <v-card-text>
              <v-layout justify-center justify-space-around>
                <v-flex lg5 md5 sm12 xs12>
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
              <v-btn color="primary" :disabled="!btn" @click="update">Update</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>

import UserController from "@/controllers/user.controller.js";
import store from "@/store";
import User from "@/models/User";

export default {
  name: "Profil",
  data:() => ({
    id: null,
    user: new User(),
    btn: false,
  }),
  watch: {
    '$route': 'getUser',
  },
  created() {
    this.getUser();
    console.log(this.user);
  },
  methods: {
    getUser() {
      this.id = this.$route.params.id;
      UserController.getUser(this.id)
        .then((response) => {
          Object.assign(this.user, response.data);
        })
        .catch((error) => {
          alert(error.response.data);
          this.$router.push({name: "home"});
        })
    },
    update() {
      UserController.update(this.id, this.user)
        .then((response) => {
          this.btn = false;
          store.commit('message', "Successfully updated!");
          store.commit('color', "success");
          store.commit("showSnackbar");
          console.log("succ");
        })
        .catch((error) => {
          console.log("error");
        })
    },
    enableBtn() {
      this.btn = true;
    }
  }
}
</script>

<style>

</style>
