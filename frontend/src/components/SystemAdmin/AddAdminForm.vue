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
                    <v-radio-group v-model="radioGroup" :rules="radio_rules" required>
                    <v-radio label="Hotel admin" value="1"></v-radio>
                    <v-radio label="Airline admin" value="2"></v-radio>
                    <v-radio label="Rent-a-car admin" value="3"></v-radio>
                    </v-radio-group>
                </v-flex>
                <v-flex xs12>
                    <v-select v-if="radioGroup == 1" :items="myHotels" v-model="selected" label="choose hotel" outline :rules="hotel_rules" required></v-select>
                    <v-select v-if="radioGroup == 2" :items="myAirlines" v-model="selected" label="choose airline company" outline :rules="airline_rules" required></v-select>
                    <v-select v-if="radioGroup == 3" :items="myRentACars" v-model="selected" label="choose rent-a-car" outline :rules="rentacar_rules" required></v-select>
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

import store from "@/store";
export default {
    name: "AddAdminForm",
    // props: ['hotels', 'airlines', 'rentACars'],
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
        radio_rules:[
            v => !!v || 'You need to choose type of admin'
        ],
        hotel_rules:[
            v => !!v || 'You need to choose existing hotel from list'
        ],
        airline_rules:[
            v => !!v || 'You need to choose existing airline company from list'
        ],
        rentacar_rules:[
            v => !!v || 'You need to choose existing rent-a-car from list'
        ],
        registration : new Registration(),

        hAdminEnabled: false,
        aAdminEnabled: false,
        racAdminEnabled: false,

        hotel: new Hotel(),
        airline: new AirlineCompany(),
        rentACar: new RentACar(),

        radioGroup: 0,

    }),
    computed: {
      myHotels: function() {
        var storeHotels = store.getters.allHotels;
        var hotelsNames = [];
        if(store.getters.allHotels != null){
            storeHotels.forEach(singleHotel => {
                hotelsNames.push(singleHotel.name);
            })
        }
        return hotelsNames;
      },
      myRentACars: function(){
        var storeRAC = store.getters.allRentACars;
        var RACNames = [];
        if(store.getters.allRentACars != null){
            storeRAC.forEach(singleRAC => {
                RACNames.push(singleRAC.name);
            })
        }
        return RACNames;
      },
      myAirlines: function(){
        var storeAirlines = store.getters.allAirlines;
        var AirlinesNames = [];
        if(store.getters.allAirlines != null){
            storeAirlines.forEach(singleAirlines => {
                AirlinesNames.push(singleAirlines.name);
            })
        }
        return AirlinesNames;
      }
    }, 
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
                                this.$emit("finished", {msg: "Hotel administrator successfully added", color: "success"})
                            })
                            .catch((error) => {
                                console.log(error);
                                this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
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
                                this.$emit("finished", {msg: "Airline company administrator successfully added", color: "success"})
                            })
                            .catch((error) => {
                                this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
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
                                this.$emit("finished", {msg: "Rent-a-car administrator successfully added", color: "success"})
                            })
                            .catch((error) => {
                                this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
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
