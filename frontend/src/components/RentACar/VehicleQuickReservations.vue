<template>
    <div>
     <v-layout row justify-center>
      <v-dialog v-model="addQuickReservation" persistent max-width="600px">
      <template v-slot:activator="{ on }">
          <v-btn @click="initializeBranchesAndVehicles" color="#43A047" dark v-on="on">Add a quick reservation</v-btn>
      </template>
      <v-card>
          <v-form
          ref="addQuickVehicleReservationForm"
          v-model="addQuickVehicleReservationFormvalid"
          lazy-validation
          >
          <v-card-title>
          <span class="headline">Add a new quick vehicle reservation to the service</span>
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
                    :return-value.sync="newVehicleQuickReservation.pickupDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="newVehicleQuickReservation.pickupDate"
                        label="Pickup date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Pick up date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="newVehicleQuickReservation.pickupDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="pickupDateMenu = false">Cancel</v-btn>
                        <v-btn color="primary" @click="$refs.pickupDateMenu.save(newVehicleQuickReservation.pickupDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="pickupTimeMenu"
                  v-model="pickupTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="newVehicleQuickReservation.pickupTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="newVehicleQuickReservation.pickupTime"
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
                    v-model="newVehicleQuickReservation.pickupTime"
                    full-width
                    @click:minute="$refs.pickupTimeMenu.save(newVehicleQuickReservation.pickupTime)"
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
                    :return-value.sync="newVehicleQuickReservation.returnDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="newVehicleQuickReservation.returnDate"
                        label="Return date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Return date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="newVehicleQuickReservation.returnDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="returnDateMenu = false">Cancel</v-btn>
                        <v-btn color="primary" @click="$refs.returnDateMenu.save(newVehicleQuickReservation.returnDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="returnTimeMenu"
                  v-model="returnTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="newVehicleQuickReservation.returnTime"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="newVehicleQuickReservation.returnTime"
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
                    v-model="newVehicleQuickReservation.returnTime"
                    full-width
                    @click:minute="$refs.returnTimeMenu.save(newVehicleQuickReservation.returnTime)"
                  >
                  <v-btn flat color="primary" @click="returnTimeMenu = false">Cancel</v-btn>
                  </v-time-picker>
                </v-menu>
                </v-flex>
                </v-layout>
                <v-layout>
                  <v-flex id="mestouzimanja">
                    <v-autocomplete
                      v-model="newVehicleQuickReservation.pickupLocation"
                      :items="branches"
                      :rules="[v => !!v || 'Pickup location is required']"
                      label="Pickup location"
                    ></v-autocomplete>
                  </v-flex>
                  <v-flex>
                    <v-autocomplete
                      v-model="newVehicleQuickReservation.returnLocation"
                      :items="branches"
                      :rules="[v => !!v || 'Return location is required']"
                      label="Return location"
                    ></v-autocomplete>
                  </v-flex>
                </v-layout>            
                <v-layout>
                  <v-flex>
                    <span>Total price in euros: </span>
                  </v-flex>                  
                </v-layout>
                <v-layout>
                <v-flex id="totalPriceLabel">
                  <number-input v-model="newVehicleQuickReservation.totalPrice" :min="1" :max="49999" inline center></number-input>
                </v-flex>
                <v-flex>
                    <v-autocomplete
                      v-model="newVehicleQuickReservation.vehicle"
                      :items="vehicles"
                      :rules="[v => !!v || 'Vehicle is required']"
                      item-value="id"
                      item-text="name"
                      label="Vehicle"
                    >
                    <template slot='selection' slot-scope='{ item }'>
                        {{ item.name }} - {{ item.vehicleManufacturer }} - {{ item.vehicleModel }} - {{ item.vehicleType }}
                    </template>
                    <template slot='item' slot-scope='{ item }'>
                        {{ item.name }} - {{ item.vehicleManufacturer }} - {{ item.vehicleModel }} - {{ item.vehicleType }}
                    </template>
                    </v-autocomplete>
                  </v-flex>
                </v-layout>
                <v-layout>
                    <v-checkbox v-model="newVehicleQuickReservation.collisionInsuranceIncluded" :label="'Collision insurance'"></v-checkbox>
                    <v-checkbox v-model="newVehicleQuickReservation.gpsIncluded" :label="'GPS navigation'"></v-checkbox>
                </v-layout>
                <v-layout>
                    <v-checkbox v-model="newVehicleQuickReservation.theftInsuranceIncluded" :label="'Theft insurance'"></v-checkbox>                
                    <v-checkbox v-model="newVehicleQuickReservation.childSeatIncluded" :label="'Child seat'"></v-checkbox>
                </v-layout>                
          </v-container>
          </v-card-text>
          <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="cancelAddNewQuickReservation">Close</v-btn>
          <v-btn :disabled="!addQuickVehicleReservationFormvalid" color="success" @click="validateAddQuickVehicleReservation">Add</v-btn>
          </v-card-actions>`
          </v-form>
      </v-card>
      </v-dialog>
     </v-layout>
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
            </v-layout>
        </v-list-tile-content>
        </v-list-tile>
        </v-list>        
        </v-card>
    </div>
</template>

<script>
import NewVehicleQuickReservation from "@/models/NewVehicleQuickReservation.js";
import BranchesController from "@/controllers/branches.controller.js";
import RentACarController from "@/controllers/rentacar.controller.js";
import store from '@/store';

export default {
    name: 'VehicleQuickReservations',
     data: () => ({
        addQuickReservation: false,
        newVehicleQuickReservation : new NewVehicleQuickReservation(),
        pickupDateMenu : false,
        pickupTimeMenu : false,
        returnDateMenu : false,
        returnTimeMenu : false,
        addQuickVehicleReservationFormvalid : false,
        branches: [],
        vehicles: [],
        quickReservations: [],
     }),
     created() {
        this.getQuickReservations();
        this.getBranches();
        this.getVehicles();
     },
     methods: {
         validateAddQuickVehicleReservation(){
             if(this.$refs.addQuickVehicleReservationForm.validate()) {
                this.newVehicleQuickReservation.rentACarId = this.$route.params.id;
                RentACarController.createNewQuickReservation(this.newVehicleQuickReservation)
                 .then((response) => {
                    store.commit("setSnack", {msg: "Quick reservation successfully added.", color:"success"});
                    this.refreshQR();
                    this.cancelAddNewQuickReservation();
                 })
                 .catch((error) => {
                    store.commit("setSnack", {msg: error.response.data, color:"error"});                   
                 });                         
             }
         },
         getBranches() {
            BranchesController.get(this.$route.params.id)
             .then((response) => {
                response.data.forEach(element => {
                this.branches.push(element);
             });
            });
         },
         getVehicles() {
            RentACarController.getVehicles(this.$route.params.id)
             .then((response) => {
                response.data.forEach(element => {
                this.vehicles.push(element);
             });
            });
         },
         getQuickReservations() {
            RentACarController.getQuickReservationsAdmin(this.$route.params.id)
             .then((response) => {
                response.data.forEach(element => {
                this.quickReservations.push(element);
             });
            });
         },
         initializeBranchesAndVehicles() {
            this.getBranches();
            this.getVehicles(); 
         },
         cancelAddNewQuickReservation() {
            this.addQuickReservation = false;
            this.newVehicleQuickReservation = new NewVehicleQuickReservation();
            this.$refs.addQuickVehicleReservationForm.resetValidation()
         },
         refreshQR() {
            this.quickReservations = [];
            this.getQuickReservations();
         }
     }
}
</script>

<style>

#totalPriceLabel {
    padding-top: 20px;
    padding-right: 10px;
}

#servicesCheckboxes {
    min-width: 250px;
}

</style>
