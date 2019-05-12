<template>
    <div>
        <v-layout row justify-center>
        <v-card max-width="500px">
            <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
            >
            <v-card-title>
            <span class="headline">Create new admin</span>
            </v-card-title>
            <v-card-text>
            <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6>
                    <v-text-field label="First name*" v-model=registration.firstname :rules="firstname_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                    <v-text-field label="Last name*" v-model=registration.lastname :rules="lastname_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                    <v-text-field label="Username*" v-model=registration.username :rules="username_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                    <v-text-field label="Password*" v-model="registration.password" :type="'password'" :rules="password_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                    <v-text-field label="Confirm password*" v-model="passwordConfirmation" :error-messages='passwordMatchError()' :type="'password'" :rules="confirm_password_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                    <v-text-field label="Email*" v-model=registration.email :rules="email_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                    <v-text-field label="Phone number*" v-model=registration.phone :rules="phone_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                    <v-text-field label="Address*" v-model=registration.address :rules="address_rules" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                    <p>Choose which kind of admin you want to create</p>
                    <v-radio-group v-model="radioGroup">
                    <v-radio label="Hotel admin" value="1"></v-radio>
                    <v-radio label="Airline admin" value="2"></v-radio>
                    <v-radio label="Rent-a-car admin" value="3"></v-radio>
                    </v-radio-group>
                </v-flex>
                <v-flex xs12>
                    <v-select v-if="radioGroup == 1" :items="hotels" v-model="selected" label="choose hotel" outline ></v-select>
                    <v-select v-if="radioGroup == 2" :items="airlines" v-model="selected" label="choose airline company" outline ></v-select>
                    <v-select v-if="radioGroup == 3" :items="rentACars" v-model="selected" label="choose rent-a-car" outline ></v-select>
                </v-flex>
                </v-layout>

            </v-container>
            <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken" @click="cancel" flat >Close</v-btn>
            <v-btn color="error" @click="reset">Reset</v-btn>
            <v-btn :disabled="!formValid" color="success" @click="validateUser"> Create</v-btn>
            </v-card-actions>`
            </v-form>
        </v-card>
    </v-layout>
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
import Registration from "@/models/Registration.js";
import HotelAdmin from "@/models/HotelAdmin.js";
import AirlineAdmin from "@/models/AirlineAdmin";
import RentACarAdmin from "@/models/RentACarAdmin";

import HotelController from "@/controllers/hotels.controller"; 
import AirlineController from "@/controllers/airline-company.controller"; 
import RentACarController from "@/controllers/rentacar.controller"; 
import SysAdminController from "@/controllers/system-admin.controller"; 


import Hotel from "@/models/Hotel";
import AirlineCompany from "@/models/AirlineCompany";
import RentACar from "@/models/RentACar";

export default {
    name: "AddAdminForm",
    props: ['hotels', 'airlines', 'rentACars'],
    data: () => ({
        formValid:true,
        passwordConfirmation:null,

        selected: "",

        firstname_rules:[
            v => !!v || 'First name is required'
        ],
        lastname_rules:[
            v => !!v || 'Last name is required'
        ],
        username_rules:[
            v => !!v || 'Username is required'
        ],
        password_rules:[
            v => !!v || 'Password is required',
            v => (v && v.length >= 5) || 'Password is too short'
        ],
        email_rules:[
            v => !!v || 'Email is required',
            v => /[0-9]*[A-Z]*[0-9]*[a-z]+[0-9]*[A-Z]*[0-9]*@[a-z]+\.[a-z]+/.test(v) || 'Please enter a valid email'
        ],
        phone_rules:[
            v => !!v || 'Phone number is required',
            v => /^[0-9]*$/.test(v) || 'Please enter a valid phone number'
        ],
        address_rules:[
            v => !!v || 'Address is required'
        ],
        confirm_password_rules:[
            v => !!v || 'Password confirmation is required'
        ],
        registration : new Registration(),

        hAdminEnabled: false,
        aAdminEnabled: false,
        racAdminEnabled: false,

        hotel: new Hotel(),
        airline: new AirlineCompany(),
        rentACar: new RentACar(),

        radioGroup: 0,

        snackbar: {
            show: false,
            color: "",
            msg: "",
        }
    }),
    methods: {
        passwordMatchError () {
            return (this.registration.password === this.passwordConfirmation) ? '' : 'Please enter a matching password'
        },
        validateUser() {
            console.log("validate user");
            if(this.$refs.form.validate()){
                this.onSubmit();
            }
        },
        onSubmit() {
            
            if(this.radioGroup == 1){
                
                var hotelAdmin = new HotelAdmin();
                hotelAdmin.username = this.registration.username;
                hotelAdmin.password = this.registration.password;
                hotelAdmin.firstname = this.registration.firstname;
                hotelAdmin.lastname = this.registration.lastname;
                hotelAdmin.email = this.registration.email;
                hotelAdmin.address = this.registration.address;
                hotelAdmin.phone = this.registration.phone;
                hotelAdmin.hotel = new Hotel();

                HotelController.getHotels()
                .then(response =>{
                    response.data.forEach(element => {
                        if(element.name == this.selected){
                            hotelAdmin.hotel = element;
                            SysAdminController.createHotelAdmin(hotelAdmin)
                            .then(response => {
                                console.log(response.data);
                            })
                            .catch((error) => {
                                this.showSnackbar(error.response.data, "error")
                            })
                        }
                    });
                });
                
            }else if(this.radioGroup == 2){
                var airlineAdmin = new AirlineAdmin();

                airlineAdmin.username = this.registration.username;
                airlineAdmin.password = this.registration.password;
                airlineAdmin.firstname = this.registration.firstname;
                airlineAdmin.lastname = this.registration.lastname;
                airlineAdmin.email = this.registration.email;
                airlineAdmin.address = this.registration.address;
                airlineAdmin.phone = this.registration.phone;
                airlineAdmin.airlineCompany = new AirlineCompany();
                AirlineController.findAllAirlines()
                .then(response => {
                    response.data.forEach(element => {
                        if(element.name == this.selected){
                            airlineAdmin.airlineCompany = element;
                            SysAdminController.createAirlineAdmin(airlineAdmin)
                            .then(response => {
                                console.log(response.data);
                            })
                            .catch((error) => {
                                this.showSnackbar(error.response.data, "error")
                            })
                        }
                    })
                });
            }else{
                var rentACarAdmin = new RentACarAdmin();

                rentACarAdmin.username = this.registration.username;
                rentACarAdmin.password = this.registration.password;
                rentACarAdmin.firstname = this.registration.firstname;
                rentACarAdmin.lastname = this.registration.lastname;
                rentACarAdmin.email = this.registration.email;
                rentACarAdmin.address = this.registration.address;
                rentACarAdmin.phone = this.registration.phone;
                rentACarAdmin.rentACar = new RentACar();

                RentACarController.getAll()
                .then(response => {
                    response.data.forEach(element => {
                        if(element.name == this.selected){
                            rentACarAdmin.rentACar = element;
                            SysAdminController.createRentACarAdmin(rentACarAdmin)
                            .then(response => {
                                console.log(response.data);
                            })
                            .catch((error) => {
                                this.showSnackbar(error.response.data, "error")
                            })
                        }
                    })
                });
            }
        },
        reset() {
            this.$refs.form.reset();
        },
        cancel(){
            this.$emit("cancel")
        },
        showSnackbar(message,color) {
            this.snackbar.color = color;
            this.snackbar.msg = message;
            this.snackbar.show = true;
        }
    }
}
</script>

<style>

#registrationdiv {
    height: 100%;
    /* background-color: #99bffc; */
}

#registrationform {
    padding-top: 2%;
}

.cac {
    visibility: hidden;
}
</style>
