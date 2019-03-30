<template>
  <div>
    <v-layout row justify-center>
      <v-dialog v-model="addVehicle" persistent max-width="600px">
      <template v-slot:activator="{ on }">
          <v-btn color="primary" dark v-on="on">Add a vehicle</v-btn>
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
                  ></v-select>
              </v-flex>
              <v-flex xs12 sm6 md8>
                  <span>Number of passengers:</span>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <span>Year of production:</span>
              </v-flex>
              <v-flex xs12 sm6 md8>
                  <number-input v-model="passengers" :min="1" :max="10" inline center controls></number-input>
              </v-flex>
              <v-flex xs12 sm6 md4>
                  <number-input v-model="production" :min="1990" :max="2019" inline center controls></number-input>
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
          </v-card-actions>
          </v-form>
      </v-card>
      </v-dialog>
    </v-layout>
  </div>
</template>

<script>
export default {
    name: "AddVehicleForm",
    data: () => ({
        form: {
            vehicleName: '',
            vehicleManufacturer: '',
            vehicleModel: '',
            vehicleType: '',
            passengerNumber: null,
            productionYear: null
        },
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
        passengers: 1,
        production: 2012
    }),
    methods: {
        onSubmit() {
            this.form.vehicleName = this.vehicle_name
            this.form.vehicleManufacturer = this.carManufacturer
            this.form.vehicleModel = this.carModel
            this.form.vehicleType = this.carType
            this.form.passengerNumber = this.passengers
            this.form.productionYear = this.production
            alert(JSON.stringify(this.form))
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
            this.$refs.form.reset()
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
        selectManufacturer:function() {
            this.selectedModel = '';
       }
    }
}
</script>
