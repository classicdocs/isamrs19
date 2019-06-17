<template>
  <div>
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-form ref="form" v-model="form">
          <v-dialog
            v-model="dialog"
            persistent
            max-width="500px"
          >
            <v-card >
            <v-card-title primary-title>
              <h3>Change password</h3>
            </v-card-title>
            <v-card-text>
              <v-text-field 
              label="Password*" 
              v-model="password" 
              :type="'password'" 
              :rules="password_rules"
              required
              ></v-text-field>
              <v-text-field 
              label="Confirm password*" 
              v-model="passwordConfirmation" 
              :error-messages='passwordMatchError()' 
              :type="'password'" 
              :rules="confirm_password_rules" 
              required
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" :disabled="!form" @click="submit">Submit</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        </v-form>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import LoginController from "@/controllers/login.controller.js";
import store from "@/store";

export default {
  name: 'ChangePassword',
  data:() => ({
    dialog: true,
    form: true,
    btn: true,
    password: null,
    passwordConfirmation: null,
    ok: false,
    password_rules:[
        v => !!v || 'Password is required',
        v => (v && v.length >= 5) || 'Password is too short'
    ],
    confirm_password_rules:[
        v => !!v || 'Password confirmation is required'
    ],
  }),
  methods: {
    passwordMatchError () {
      if (this.password === this.passwordConfirmation) {
        this.btn = false;
        return '';
      }
      else {
        this.btn = true;
        return 'Please enter a matching password';
      }
    },
    submit() {
      if (this.$refs.form.validate()) {
        let id = store.getters.activeUser.id;
        LoginController.changePassword({'id': id, 'password': this.password})
          .then((response) => {
            let path = '';
            switch(store.getters.activeUserRole) {
              case 'Airline Company Admin': {
                path = '/airline-company/' + store.getters.activeUser.idAdminOf;
                break;
              }
              case 'Hotel Admin': {
                path = '/hotel-service/' + store.getters.activeUser.idAdminOf;
                break;
              }
              case 'RentACar Admin': {
                path = '/rentacar-service/' + store.getters.activeUser.idAdminOf;
                break;
              }
            }
            store.commit("loggedFirstTime");
            console.log("USER: ");
            console.log(store.getters.activeUser);
            this.$router.push(path);
          })
          .catch()
      }
    }
  }
}
</script>

<style>

</style>
