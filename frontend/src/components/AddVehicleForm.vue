<template>
  <div>
    <v-layout row justify-center>
      <v-dialog v-model="addVehicle" persistent max-width="600px">
      <template v-slot:activator="{ on }">
          <v-btn color="#43A047" dark v-on="on">Add a vehicle</v-btn>
      </template>
      <v-card>
          <v-form
          ref="form"
          v-model="valid"
          lazy-validation
          >
          <v-card-title>
          <span class="headline">Add a new vehicle to the service</span>
          </v-card-title>
          <v-card-text>
          <v-container grid-list-md>
              <v-layout wrap>
              <v-flex xs12>
                  <v-text-field v-model="vehicle_name" :rules="vehicle_name_rules"  label="Vehicle name*" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                  <v-autocomplete
                      :items="cars"
                      label="Vehicle manufacturer*"
                      v-model="carManufacturer"
                      :rules="vehicle_manufacturer_rules"
                      required
                      @change="returnModels(carManufacturer)"
                  ></v-autocomplete>
              </v-flex>
              <v-flex xs12>
                  <v-autocomplete
                      :disabled="!modelSelected" 
                      :items="car_models_list"
                      v-model="carModel"
                      :rules="vehicle_model_rules"
                      label="Vehicle model*"
                      required
                  ></v-autocomplete>
              </v-flex>
              <v-flex xs12>
                  <v-select
                      :items="['Hatchback', 'Sedan', 'Station wagon', 'SUV', 'Coupe']"
                      v-model="carType"
                      :rules="vehicle_type_rules"
                      label="Vehicle type*"
                      required
                      @change="setPassengers(carType)"
                  ></v-select>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <span>Number of passengers:</span>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <span>Year of production:</span>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <span>Price per day (in Euros):</span>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <number-input :disabled="!passengers_enabled" v-model="passengers" :min="1" :max="max_passengers" inline center controls></number-input>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <number-input v-model="production" :min="1990" :max="2019" inline center controls></number-input>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <number-input v-model="price_per_day" :min="1" :max="1000" inline center controls></number-input>
              </v-flex>
              </v-layout>
          </v-container>
          <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="addVehicle = false">Close</v-btn>
          <v-btn :disabled="!valid" color="success" @click="validate">Add</v-btn>
          <v-btn color="error" @click="reset">Reset</v-btn>
          </v-card-actions>`
          </v-form>
      </v-card>
      </v-dialog>
    </v-layout>
    

    <!-- <v-list two-line>
        <v-list-tile id="listitem" v-for="vehicle in vehicle_list" v-bind:key="vehicle">
        <v-list-tile-content>
            <v-list-tile-title v-html="vehicle.vehicleManufacturer + ' ' + vehicle.vehicleModel "></v-list-tile-title>
            <v-list-tile-title v-html="'Vehicle name: ' + vehicle.name + ' | Vehicle type: ' + vehicle.vehicleType + ' | Number of pasengers: ' 
            + vehicle.numberOfPassengers + ' | Year of production: ' + vehicle.yearOfProduction + ' | Price per day: ' 
            + vehicle.pricePerDay + ' euros'"></v-list-tile-title>
        </v-list-tile-content>
        </v-list-tile>
    </v-list> -->

    <v-card id="listcard">
        <v-list two-line>
        <v-list-tile id="car" v-for="vehicle in vehicle_list" v-bind:key="vehicle">
        <v-list-tile-content>
            <v-list-tile-title id="cartitle" v-html="vehicle.vehicleManufacturer + ' ' + vehicle.vehicleModel "></v-list-tile-title>
            <v-layout>
            <v-flex>
              <img id="pic" src="../assets/car.jpg">
            </v-flex>
            <v-flex id="carinfo">
              Vehicle name: {{ vehicle.name }} <br/>
              Vehicle type: {{ vehicle.vehicleType }} <br/>
              Number of passengers: {{ vehicle.numberOfPassengers }} <br/>
              Year of production: {{ vehicle.yearOfProduction }} <br/>
              Price per day: {{ vehicle.pricePerDay }} euros <br/>
            </v-flex>
            </v-layout>
        </v-list-tile-content>
        </v-list-tile>
        </v-list>
        </v-card>
  </div>
</template>

<script>

import Vehicle from "@/models/Vehicle";
import VehicleController from "@/controllers/vehicle.controller";
import RentACarController from "@/controllers/rentacar.controller.js"

export default {
    name: "AddVehicleForm",
    data: () => ({
        addVehicle: false,
        valid: true,
        vehicle_name: '',
        cars: ["Alfa Romeo","Audi","BMW","Citroen","Fiat","Ford","Mercedes Benz","Opel","Peugeot","Renault","Volkswagen"],
        carManufacturer: '',
        carModel: '',
        modelSelected: false,
        car_models_list: [],
        vehicle_name_rules: [
            v => !!v || 'Name is required'
        ],
        vehicle_manufacturer_rules: [
            v => !!v || 'Vehicle manufacturer is required'
        ],
        vehicle_model_rules: [
            v => !!v || 'Vehicle model is required'
        ],
        vehicle_type_rules: [
            v => !!v || 'Vehicle type is required'
        ],
        carType: '',
        passengers: null,
        production: 2012,
        price_per_day: 25,
        max_passengers: 10,
        passengers_enabled: false,
        vehicle: null,
        vehicle_list: []
    }),
    created() {
        this.vehicle_list = [];

        RentACarController.getVehicles(this.$route.params.id).then((response) => {
        response.data.forEach(element => {
          this.vehicle_list.push(element);
        });
      })
    },
    methods: {
        onSubmit() {
            this.vehicle = new Vehicle(this.$route.params.id,this.vehicle_name,this.carManufacturer,this.carModel,this.carType,
            this.passengers,this.production,this.price_per_day);

            VehicleController.create(this.vehicle)
                .then((response) => {
                   this.vehicle_list.push(response.data);
            });
            this.reset();
            this.addVehicle = false;
        },
        validate () {
            if (this.$refs.form.validate()) {
                this.snackbar = true
                this.onSubmit()
            }
        },
        reset () {
            this.car_models_list = []
            this.modelSelected = false
            this.passengers = 1
            this.production = 2012
            this.price_per_day = 25
            this.$refs.form.reset()
            this.passengers_enabled = false;
            this.passengers = 1;
        },
        returnModels(carManufacturerParam) {
            this.modelSelected = true
            this.carModel = ''
            if (carManufacturerParam =='Alfa Romeo') {
                this.car_models_list = ['145','146','147','155','156','159','164','Brera','Giulia','Giulietta']
            } else if (carManufacturerParam == 'Audi') {
                this.car_models_list = ['A1','A2','A3','A4','A4','A5','A6','A7','A8','Q2','Q3','Q5','Q7','Q8','R8','TT']
            } else if (carManufacturerParam == 'BMW') {
                this.car_models_list = ['116','118','120','216','218','220','316','318','320','520','525','530']
            } else if (carManufacturerParam == 'Citroen') {
                this.car_models_list = ['C1','C2','C3','C4','C5']
            } else if (carManufacturerParam == 'Fiat') {
                this.car_models_list = ['Bravo','Croma','Grande Punto','Punto','Stilo','Seicento']
            } else if (carManufacturerParam == 'Ford') {
                this.car_models_list = ['Fiesta','Focus','Mondeo','Mustang']
            } else if (carManufacturerParam == 'Mercedes Benz') {
                this.car_models_list = ['A Class','B Class','C Class','E Class','S Class']
            } else if (carManufacturerParam == 'Opel') {
                this.car_models_list = ['Astra','Corsa','Insignia','Zafira']
            } else if (carManufacturerParam == 'Peugeot') {
                this.car_models_list = ['206','207','208','307','308','407','408','607','508']
            } else if (carManufacturerParam == 'Renault') {
                this.car_models_list = ['Clio','Kadjar','Laguna','Megane','Scenic','Talisman']
            } else if (carManufacturerParam == 'Volkswagen') {
                this.car_models_list = ['Golf','Passat','Polo','Tiguan','Touareg']
            } else {
                this.car_models_list = []
            }
        },
        setPassengers(carTypeParam) {
            this.passengers_enabled = true;
            this.passengers = 1;
            if (carTypeParam == 'Coupe') {
                this.max_passengers = 2;
            } else if (carTypeParam == 'Hatchback') {
                this.max_passengers = 4;
            } else if (carTypeParam == 'SUV') {
                this.max_passengers = 7;
            } else {
                this.max_passengers = 5;
            }
        }
    }
}
</script>

<style>

#listitem {
    padding-left: 2%;
    background-color: #f2f2f2;
    border: solid 0.1px  #dbdbdb;
}

#carinfo {
    padding-left: 10px;
}

#car {
    padding-left: 2%;
    
    border: solid 1px  #dbdbdb;
    min-height: 160px;
}

#listcard {
    width: 100%;
}

#cartitle {
    padding-bottom: 25px;
    padding-top: 5px;
}

#pic {
  max-width: 200px;
}

</style>
