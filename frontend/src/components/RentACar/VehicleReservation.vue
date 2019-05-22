<template>
    <!--<v-expansion-panel
      v-model="expansion"
      expand
    >
    <v-expansion-panel-content> -->
    <v-card>
    <div slot='header'><h1>Search a vehicle</h1></div>
    <v-layout>
        <v-card id="contain">
            <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
            >
            <v-card-title>
            <span class="headline">Enter vehicle information</span>
            </v-card-title>
            <v-card-text>
            <v-container>
                <v-layout>
                <v-flex>    
                    <v-menu
                    ref="pickupDateMenu"
                    v-model="pickupDateMenu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="vehicleSearch.pickupDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="vehicleSearch.pickupDate"
                        label="Pickup date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Pick up date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="vehicleSearch.pickupDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="pickupDateMenu = false">Cancel</v-btn>
                        <v-btn color="primary" @click="$refs.pickupDateMenu.save(vehicleSearch.pickupDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="pickupTimeMenu"
                  v-model="pickupTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="vehicleSearch.pickupTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="vehicleSearch.pickupTime"
                      label="Pickup time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Pickup time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="pickupTimeMenu"
                    v-model="vehicleSearch.pickupTime"
                    full-width
                    @click:minute="$refs.pickupTimeMenu.save(vehicleSearch.pickupTime)"
                  >
                  <v-btn flat color="primary" @click="pickupTimeMenu = false">Cancel</v-btn>
                  </v-time-picker>
                </v-menu>
                </v-flex>
                </v-layout>
                <v-layout>
                    <v-flex>
                    <v-menu
                    ref="returnDateMenu"
                    v-model="returnDateMenu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="vehicleSearch.returnDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="vehicleSearch.returnDate"
                        label="Return date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Return date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="vehicleSearch.returnDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="returnDateMenu = false">Cancel</v-btn>
                        <v-btn color="primary" @click="$refs.returnDateMenu.save(vehicleSearch.returnDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="returnTimeMenu"
                  v-model="returnTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="vehicleSearch.returnTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="vehicleSearch.returnTime"
                      label="Return time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Return time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="returnTimeMenu"
                    v-model="vehicleSearch.returnTime"
                    full-width
                    @click:minute="$refs.returnTimeMenu.save(vehicleSearch.returnTime)"
                  >
                  <v-btn flat color="primary" @click="returnTimeMenu = false">Cancel</v-btn>
                  </v-time-picker>
                </v-menu>
                </v-flex>
                </v-layout>
                <v-layout>
                  <v-flex id="mestouzimanja">
                    <v-autocomplete
                      v-model="vehicleSearch.pickupPlace"
                      :items="branches"
                      :rules="[v => !!v || 'Return time is required']"
                      label="Pickup location"
                    ></v-autocomplete>
                  </v-flex>
                  <v-flex>
                    <v-autocomplete
                      v-model="vehicleSearch.returnPlace"
                      :items="branches"
                      :rules="[v => !!v || 'Return time is required']"
                      label="Return location"
                    ></v-autocomplete>
                  </v-flex>
                </v-layout>
                <v-layout>
                <v-flex id="tipvozila">
                  <v-select
                      :items="['Hatchback', 'Sedan', 'Station wagon', 'SUV', 'Coupe']"
                      v-model="vehicleSearch.carType"
                      :rules="[v => !!v || 'Vehicle type is required']"
                      label="Vehicle type"
                      required
                  ></v-select>
                </v-flex>
                <v-flex id="brojputnika">
                  <span>Number of passengers:</span>
                  <number-input v-model="vehicleSearch.passengers" :min="1" :max="7" inline center controls></number-input>
                </v-flex>
                </v-layout>
                <v-layout>
                  <v-flex>
                    <span>Minimum price: </span>
                  </v-flex>
                  <v-flex id="labelflex">
                    <span>Maximum price:</span>
                  </v-flex>
                </v-layout>
                <v-layout>
                <v-flex>
                  <number-input v-model="vehicleSearch.minPrice" :min="0" :max="499" inline center controls></number-input>
                </v-flex>
                <v-flex id="inputflex">
                  <number-input v-model="vehicleSearch.maxPrice" :min="1" :max="500" inline center controls></number-input>
                </v-flex>
                </v-layout>
            </v-container>
            </v-card-text>
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn :disabled="!formValid" @click="validateSearch"> Search</v-btn>
            </v-card-actions>`
            </v-form>
        </v-card>

        <v-card id="listcard">
        <v-list two-line v-if="vehicle_list.length !== 0">
        <v-list-tile id="car" v-for="vehicle in vehicle_list" v-bind:key="vehicle">
        <v-list-tile-content>
            <v-list-tile-title id="cartitle" v-html="vehicle.vehicleManufacturer + ' ' + vehicle.vehicleModel "></v-list-tile-title>
            <v-layout>
            <v-flex>
              <img id="pic" src="../../assets/car.jpg">
            </v-flex>
            <v-flex id="carinfo">
              Vehicle name: {{ vehicle.name }} <br/>
              Vehicle type: {{ vehicle.vehicleType }} <br/>
              Number of passengers: {{ vehicle.numberOfPassengers }} <br/>
              Year of production: {{ vehicle.yearOfProduction }} <br/>
              Price per day: {{ vehicle.pricePerDay }} <br/>
            </v-flex>
            <v-flex id="rating">

            </v-flex>
            <v-flex id="reserve">
              <v-btn  color="primary" @click="reserveVehicle(vehicle.id)"> Rent </v-btn>
            </v-flex>
            </v-layout>
        </v-list-tile-content>
        </v-list-tile>
        </v-list>
        <v-flex v-else-if="noResults">
        <h3>No results</h3>
        </v-flex>
        </v-card>
    </v-layout>
    <!--</v-expansion-panel-content> -->
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
    <!--</v-expansion-panel>-->
    </v-card>
</template>

<script>

import VehicleSearch from '@/models/VehicleSearch.js';
import Vehicle from "@/models/Vehicle";
import VehicleController from "@/controllers/vehicle.controller";
import RentACarController from "@/controllers/rentacar.controller.js";
import BranchesController from "@/controllers/branches.controller.js";
import router from '../../router';
import VehicleReservation from '@/models/VehicleReservation.js';  
import store from "@/store";
import VehicleReservationController from "@/controllers/vehicle.reservation.controller.js";

export default {
    name: 'VehicleReservation',
    data: () => ({
        expansion: [true],
        pickupDateMenu : false,
        pickupTimeMenu : false,
        returnDateMenu : false,
        returnTimeMenu : false,
        vehicleSearch : new VehicleSearch(),
        vr : new VehicleReservation(),
        vehicle_list : [],
        branches: [],
        noResults: false, 
        formValid: false,
        snackbar: {
          show: false,
          color: "",
          msg: "",
        },
    }),
    created() {
      this.vehicle_list = [];

      BranchesController.get(this.$route.params.id)
      .then((response) => {
        response.data.forEach(element => {
          this.branches.push(element);
        });
      });
    },
    methods: {
      validateSearch() {
        if(this.$refs.form.validate()) {
          this.onSubmit();
        }
      },
      onSubmit() {
        this.vehicle_list = [];

        this.vehicleSearch.rentACar = this.$route.params.id;

        VehicleController.getSpecific(this.vehicleSearch)
        .then((response) => {
          response.data.forEach(element => {
            this.vehicle_list.push(element);
            });
          })
        .catch((error) => {
          this.showSnackbar(error.response.data, "error")
        });

        if (this.vehicle_list.length === 0) {
          this.noResults = true;
        }
      },
      showSnackbar(message,color) {
        this.snackbar.color = color;
        this.snackbar.msg = message;
        this.snackbar.show = true;
      },
      reserveVehicle(id) {
        if(!store.getters.isLogged){
          router.push({ name: "login"});
        }
 
        this.vr.pickupDate = this.vehicleSearch.pickupDate;
        this.vr.pickupTime = this.vehicleSearch.pickupTime;
        this.vr.returnDate = this.vehicleSearch.returnDate;
        this.vr.returnTime = this.vehicleSearch.returnTime;
        this.vr.pickupLocation = this.vehicleSearch.pickupPlace;
        this.vr.returnLocation = this.vehicleSearch.returnPlace;
        this.vr.carId = id;
        this.vr.user = store.getters.activeUser.username;
        this.vr.rentACarId = this.$route.params.id;
        
        VehicleReservationController.reserve(this.vr);

        this.vehicle_list = [];

        store.commit('setSnack', {msg: "You have successfully reserved a vehicle!", color: "success"});
        this.$router.push({name: "my-reservations"});
      },
    }
}
</script>

<style>

#tipvozila {
    max-width: 180px;
    min-width: 180px;
}

.time {
    margin-left: 5%;
}

#brojputnika {
    padding-top: 1%;
    margin-left: 8%;
    padding-bottom: 3%;
}

#reserve {
    margin-top: 20px;
}

#carinfo {
    padding-left: 10px;
    min-width: 500px;
}

#car {
    padding-left: 2%;
    
    border: solid 1px  #dbdbdb;
    min-height: 160px;
}

#listcard {
    width: 100%;
}

#labelflex {
    margin-left: 19%;
}

#pic {
  max-width: 200px;
}

#inputflex {
    margin-left: 9%;
}

#cartitle {
    padding-bottom: 25px;
    padding-top: 5px;
}

#mestouzimanja {
  padding-right: 20px;
}

</style>
