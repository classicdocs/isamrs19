<template>
    <div>
    <v-card>
    <v-layout id="vehicleReservationLayout">
        <v-card id="contain">
            <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
            >            
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
                    <v-date-picker
                      v-model="vehicleSearch.pickupDate"
                      no-title
                      scrollable
                    >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="pickupDateMenu = false"
                        >Cancel</v-btn
                      >
                      <v-btn
                        color="primary"
                        @click="
                          $refs.pickupDateMenu.save(vehicleSearch.pickupDate)
                        "
                        >OK</v-btn
                      >
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
                      @click:minute="
                        $refs.pickupTimeMenu.save(vehicleSearch.pickupTime)
                      "
                    >
                      <v-btn
                        flat
                        color="primary"
                        @click="pickupTimeMenu = false"
                        >Cancel</v-btn
                      >
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
                    <v-date-picker
                      v-model="vehicleSearch.returnDate"
                      no-title
                      scrollable
                    >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="returnDateMenu = false"
                        >Cancel</v-btn
                      >
                      <v-btn
                        color="primary"
                        @click="
                          $refs.returnDateMenu.save(vehicleSearch.returnDate)
                        "
                        >OK</v-btn
                      >
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
                      @click:minute="
                        $refs.returnTimeMenu.save(vehicleSearch.returnTime)
                      "
                    >
                      <v-btn
                        flat
                        color="primary"
                        @click="returnTimeMenu = false"
                        >Cancel</v-btn
                      >
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
                    :items="[
                      'Hatchback',
                      'Sedan',
                      'Station wagon',
                      'SUV',
                      'Coupe'
                    ]"
                    v-model="vehicleSearch.carType"
                    :rules="[v => !!v || 'Vehicle type is required']"
                    label="Vehicle type"
                    required
                  ></v-select>
                </v-flex>
                <v-flex id="brojputnika">
                  <span>Number of passengers:</span>
                  <number-input
                    v-model="vehicleSearch.passengers"
                    :min="1"
                    :max="7"
                    inline
                    center
                    controls
                  ></number-input>
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
                  <number-input
                    v-model="vehicleSearch.minPrice"
                    :min="0"
                    :max="499"
                    inline
                    center
                    controls
                  ></number-input>
                </v-flex>
                <v-flex id="inputflex">
                  <number-input
                    v-model="vehicleSearch.maxPrice"
                    :min="1"
                    :max="500"
                    inline
                    center
                    controls
                  ></number-input>
                </v-flex>
                </v-layout>                
                <v-btn id="searchBN" color="primary" :disabled="!formValid" @click="validateSearch"> Search</v-btn>
            </v-container>
            </v-card-text>        
            </v-form>
        </v-card>

      <v-card id="listcard">
        <v-list two-line v-if="vehicle_list.length !== 0">
          <v-list-tile
            id="car"
            v-for="vehicle in vehicle_list"
            v-bind:key="vehicle"
          >
            <v-list-tile-content>
              <v-list-tile-title
                id="cartitle"
                v-html="
                  vehicle.vehicleManufacturer + ' ' + vehicle.vehicleModel
                "
              ></v-list-tile-title>
              <v-layout>
                <v-flex>
                  <img id="pic" src="../../assets/car.jpg" />
                </v-flex>
                <v-flex id="carresultinfo">
                  Vehicle name: {{ vehicle.name }} <br />
                  Vehicle type: {{ vehicle.vehicleType }} <br />
                  Number of passengers: {{ vehicle.numberOfPassengers }} <br />
                  Year of production: {{ vehicle.yearOfProduction }} <br />
                  Price per day: {{ vehicle.pricePerDay }} <br />
                </v-flex>
                <v-flex id="rating">
                  <v-rating
                    :readonly="true"
                    v-model="vehicle.averageRating"
                    large
                    half-increments
                  ></v-rating>
                </v-flex>
                <v-flex id="reserve">
                  <v-btn
                    color="primary"
                    @click="
                      showAdditionalServicesDialog(
                        vehicle.id,
                        vehicle.pricePerDay
                      )
                    "
                  >
                    Rent
                  </v-btn>
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
    </v-card>
    <v-card>
    <v-card-title><h2>Quick reservations</h2></v-card-title>
    <v-layout>
      <v-card id="listcard">
        <v-list two-line>
        <v-list-tile id="carQuick" v-for="quickReservation in quickReservations" v-bind:key="quickReservation">
        <v-list-tile-content>
            <v-list-tile-title id="cartitlequick" v-html="quickReservation.vehicle.vehicleManufacturer + ' ' + quickReservation.vehicle.vehicleModel "></v-list-tile-title>
            <v-layout>
            <v-flex>
              <img id="pic" src="../../assets/car.jpg">
            </v-flex>
            <v-flex id="carresultinfo">              
              Vehicle type: {{ quickReservation.vehicle.vehicleType }} <br/>
              Number of passengers: {{ quickReservation.vehicle.numberOfPassengers }} <br/>
              Year of production: {{ quickReservation.vehicle.yearOfProduction }} <br/> 
              <h4>Reservation start: {{ quickReservation.reservedFrom | moment("DD.MM.YYYY, HH:mm") }} <br/>
              Reservation end: {{ quickReservation.reservedUntil | moment("DD.MM.YYYY, HH:mm") }} </h4>     
                
            </v-flex>
            <v-flex id="servicesCheckboxes">
              <ul>
              <li v-if="quickReservation.childSeatIncluded">Child seat included</li>
              <li v-if="quickReservation.gpsIncluded">GPS included</li>
              <li v-if="quickReservation.collisionInsuranceIncluded">Collision insurance included</li>
              <li v-if="quickReservation.theftInsuranceIncluded">Theft insurance included</li>
              </ul>
            </v-flex>            
            <v-flex id="rating">
              <v-rating :readonly="true" v-model="quickReservation.vehicle.averageRating" large half-increments></v-rating>
            </v-flex>
            <v-flex id="totalPriceFlex">
              <h2>Total price: {{ quickReservation.totalPrice }} &euro; </h2>
              <p> Pickup location: {{ quickReservation.pickupLocation }} <br/>
              Return location: {{ quickReservation.returnLocation }} </p>
            </v-flex>
            <v-flex id="reserve">
              <v-btn color="primary" @click="quickReserve(quickReservation)"> Rent </v-btn>
            </v-flex>
            </v-layout>
        </v-list-tile-content>
        </v-list-tile>
        </v-list>        
        </v-card>
    </v-layout>   
    </v-card>
    <v-snackbar
      v-model="snackbar.show"
      :timeout="5000"
      :color="snackbar.color"
      :top="true"
    >
      {{ snackbar.msg }}
      <v-btn dark flat @click="snackbar.show = false">
        Close
      </v-btn>
    </v-snackbar>
    <v-dialog width="400" persistent v-model="additionalServicesDialog">
      <v-card class="elevation-16 mx-auto">
        <v-card-text>
          <h2>Choose additional services</h2>
        </v-card-text>
        <v-divider></v-divider>
        <v-layout>
          <v-flex>
            <v-checkbox
              class="additionalServiceItem"
              @change="calculateTotal"
              v-model="gps"
              :label="'GPS navigation'"
            ></v-checkbox>
            <v-checkbox
              class="additionalServiceItem"
              @change="calculateTotal"
              v-model="childSeat"
              :label="'Child seat'"
              >Child seat</v-checkbox
            >
            <v-checkbox
              class="additionalServiceItem"
              @change="calculateTotal"
              v-model="collision"
              :label="'Collision damage insurace'"
              >Collision damage insurace</v-checkbox
            >
            <v-checkbox
              class="additionalServiceItem"
              @change="calculateTotal"
              v-model="theft"
              :label="'Theft insurance'"
              >Theft insurance</v-checkbox
            >
          </v-flex>
          <v-flex>
            <h4 class="priceTag">50 &euro;</h4>
            <h4 class="priceTag">20 &euro;</h4>
            <h4 class="priceTag">100 &euro;</h4>
            <h4 class="priceTag">100 &euro;</h4>
          </v-flex>
        </v-layout>
        <v-divider></v-divider>
        <p id="allDaysPriceLabel">
          Car price for all days: {{ days * pricePerDay }} &euro;
        </p>
        <p id="allServicesPriceLabel">
          Price for all aditional services: {{ total }} &euro;
        </p>
        <h2 id="totalLabel">Total: {{ total + days * pricePerDay }} &euro;</h2>
        <v-card-actions class="justify-space-between">
          <v-btn flat @click="stopReservation()">Cancel</v-btn>
          <v-btn color="primary" @click="reserve()">
            Rent
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import VehicleSearch from "@/models/VehicleSearch.js";
import Vehicle from "@/models/Vehicle";
import VehicleController from "@/controllers/vehicle.controller";
import RentACarController from "@/controllers/rentacar.controller.js";
import BranchesController from "@/controllers/branches.controller.js";
import router from "../../router";
import VehicleReservation from "@/models/VehicleReservation.js";
import store from "@/store";
import VehicleReservationController from "@/controllers/vehicle.reservation.controller.js";
import QuickReservation from "@/models/QuickReservation.js";


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
      additionalServicesDialog : false,
      gps : false,
      childSeat : false,
      collision : false,
      theft : false,
      total : 0,
      days : 0,
      pricePerDay : 0,
      quickReservations: [],
      qr : new QuickReservation(),
  }),
  created() {
    this.vehicle_list = [];

    this.getBranches();
    this.getQuickReservations();
    
  },
  methods: {
    validateSearch() {
      if(this.$refs.form.validate()) {
        this.onSubmit();
      }
    },
    getQuickReservations() {
      RentACarController.getQuickReservations(this.$route.params.id)
        .then((response) => {
          response.data.forEach(element => {
            this.quickReservations.push(element);
          })
        })
    },
    getBranches() {
      BranchesController.get(this.$route.params.id)
      .then((response) => {
        response.data.forEach(element => {
          this.branches.push(element);
        });
      });
    },
    onSubmit() {
      this.vehicle_list = [];

    this.vehicleSearch.rentACar = this.$route.params.id;

    VehicleController.getSpecific(this.vehicleSearch)
      .then(response => {
        response.data.forEach(element => {
          this.vehicle_list.push(element);
        });
      })
      .catch(error => {
        this.showSnackbar(error.response.data, "error");
      });

    if (this.vehicle_list.length === 0) {
      this.noResults = true;
    }
  },
  showSnackbar(message, color) {
    this.snackbar.color = color;
    this.snackbar.msg = message;
    this.snackbar.show = true;
  },
  showAdditionalServicesDialog(id, daysPrice) {
    if (!store.getters.isLogged) {
      router.push({ name: "login" });
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

    const startDate = new Date(this.vr.pickupDate);
    const endDate = new Date(this.vr.returnDate);

    var pickupTimeString = this.vr.pickupTime;
    var pickupTimeArray = pickupTimeString.split(":");
    var pickupTimeHours = parseInt(pickupTimeArray[0]);
    var pickupTimeMinutes = parseInt(pickupTimeArray[1]);
    var pickupTimeAllToMinutes = pickupTimeHours * 60 + pickupTimeMinutes;

    var returnTimeString = this.vr.returnTime;
    var returnTimeArray = returnTimeString.split(":");
    var returnTimeHours = parseInt(returnTimeArray[0]);
    var returnTimeMinutes = parseInt(returnTimeArray[1]);
    var returnTimeAllToMinutes = returnTimeHours * 60 + returnTimeMinutes;

    this.days = (endDate - startDate) / (60 * 60 * 24 * 1000);
    this.pricePerDay = daysPrice;

    if (pickupTimeAllToMinutes < returnTimeAllToMinutes) {
      this.days += 1;
    }

    this.additionalServicesDialog = true;
  },
  reserve() {
    this.vr.gpsIncluded = this.gps;
    this.vr.childSeatIncluded = this.childSeat;
    this.vr.collisionInsuranceIncluded = this.collision;
    this.vr.theftInsuranceIncluded = this.theft;

    VehicleReservationController.reserve(this.vr)
      .then(response => {
        store.commit("setSnack", {
          msg: "You have successfully reserved a vehicle!",
          color: "success"
        });
      })
      .catch(error => {
        store.commit("setSnack", {
          msg: error.response.data,
          color: "error"
        });
      });

      this.vehicle_list = [];
      this.additionalServicesDialog = false;
    },
    calculateTotal() {
      this.total = 0;
      if(this.gps) {
        this.total += 50;
      }
      if(this.childSeat) {
        this.total += 20;
      }
      if(this.collision) {
        this.total += 100;
      }
      if(this.theft) {
        this.total += 100;
      }
    },
    stopReservation() {
      this.additionalServicesDialog = false;
      this.total = 0;
      this.gps = false;
      this.childSeat = false;
      this.collision = false;
      this.theft = false;
    },
    quickReserve(quickReservation) {
      this.qr.quickReservationId = quickReservation.id;
      this.qr.rentACarId = this.$route.params.id;
      this.qr.user = store.getters.activeUser.username;

      VehicleReservationController.quickReserve(this.qr)
        .then((response) => {
          store.commit("setSnack", {msg: "You have successfully rented a vehicle.", color:"success"})
          let idx = this.quickReservations.indexOf(quickReservation);
          if (idx != -1)
          this.quickReservations.splice(idx,1);
          })
        .catch((error) => {
          store.commit("setSnack", {msg: error.response.data, color:"error"})
        });       
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

#carresultinfo {
  padding-left: 10px;
  min-width: 250px;
}

#car {
  padding-left: 2%;

  border: solid 1px #dbdbdb;
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

#cartitlequick {
    padding-bottom: 25px;
    padding-top: 15px;
}

#mestouzimanja {
  padding-right: 20px;
}

#rating {
  padding-right: 20px;
}

.additionalServiceItem {
  padding-left: 20px;
}

#totalLabel {
  margin-left: 250px;
}

.priceTag {
  padding-top: 23px;
  padding-bottom: 23px;
}

#allDaysPriceLabel {
  padding-left: 15px;
}

#allServicesPriceLabel {
  padding-left: 15px;
}

#vehicleReservationLayout {
  max-height: 430px;
}

#vehicleReservationCard {
  padding-top: 0px;
}

#searchBN {
  margin-left: 270px;
}

#servicesCheckboxes {
  padding-right: 20px;
  padding-left: 10px;
}

#totalPriceFlex {
  padding-right: 20px;
}

#carQuick {
    padding-left: 2%;
    border: solid 1px  #dbdbdb;
    min-height: 170px;
}

</style>
