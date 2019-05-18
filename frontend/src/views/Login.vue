<template>
    <div id="logindiv">
        <loading :active:sync="visible" :can-cancel="false"></loading>
        <!--<v-layout id="loginform" row justify-center>
            <v-card max-width="500px">
                <v-form
                ref="form"
                v-model="isValid"
                lazy-validation
                >
                    <v-card-title>
                        <span class="headline">Log in</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container grid-list-md>
                            <v-layout wrap>
                                <v-flex xs12>
                                    <v-text-field label="Username*" :rules="username_rules" required></v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field label="Password*" :rules="password_rules" :type="'password'" required></v-text-field>
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn :disabled="!isValid" color="success" @click="validateLogin">Log in</v-btn>
                    <v-btn color="warning" @click="reset">Sign up</v-btn>
                    </v-card-actions>`
                </v-form>
            </v-card>
        </v-layout>-->
        <v-content>
        <v-container id="loginform" fluid fill-height>
            <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                <v-toolbar dark color="primary">
                    <v-toolbar-title>Log in</v-toolbar-title>
                    <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                    <v-form
                    ref="form"
                    v-model="isValid"
                    lazy-validation>
                    <v-text-field prepend-icon="person" v-model=loginData.username :rules="username_rules" name="login" label="Username" type="text"></v-text-field>
                    <v-text-field prepend-icon="lock" 
                    v-model=loginData.password 
                    :rules="password_rules" 
                    name="password" label="Password" id="password" type="password" v-on:keyup.enter="validateLogin"></v-text-field>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn :disabled="!isValid" color="primary" @click="validateLogin" >Login</v-btn>
                    <v-btn color="warning" @click="signup">Sign up</v-btn>
                </v-card-actions>
                </v-card>
            </v-flex>
            </v-layout>
        </v-container>
    </v-content>
    <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
    >
      {{snackbar.msg}}
      <v-btn
          dark
          flat
          @click="snackbar.show = false"
      >
      Close
      </v-btn>
    </v-snackbar>
    </div>
</template>

<script>

import LoginData from "@/models/LoginData.js"
import LoginController from "@/controllers/login.controller.js"
import router from '../router'

export default {
    name: "Login",
    data: () => ({
        isValid:true,
        username_rules:[
            v => !!v || "Username field can't be empty"
        ],
        password_rules:[
            v => !!v || "Password field can't be empty",
        ],
        loginData: new LoginData(),
        snackbar: {
            show: false,
            color: "",
            msg: "",
        }
    }),
    methods: {
        validateLogin() {
            if(this.$refs.form.validate()){
                this.onSubmit();
            }
        },
        onSubmit() {
            LoginController.login(this.loginData);
        },
        showSnackbar(message,color) {
            this.snackbar.color = color;
            this.snackbar.msg = message;
            this.snackbar.show = true;
        },
        signup() {
            router.push({ name: "registration"});
        },
        logf() {
            console.log("FDS");
        }
    }
}
</script>

<style>

#logindiv {
    height: 100%;
    /* background-color: #99bffc; */
}

#loginform {
    padding-top: 5%;
}


</style>
