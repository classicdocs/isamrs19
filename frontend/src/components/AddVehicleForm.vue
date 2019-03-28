<template>
  <div id="app">
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-2" label="Vehicle name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          type="name"
          required
          placeholder="Enter name"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Vehicle manufacturer:" label-for="input-3">
        <b-form-select id="input-3" v-model="selectedManufacturer" @change="selectManufacturer">
            <option v-bind:key="manufacturer.label" v-for="(manufacturer,index) in cars" :value="index">{{ manufacturer.label }}</option>
        </b-form-select>
      </b-form-group>    

      <b-form-group id="input-group-4" label="Vehicle model:" label-for="input-4">
        <b-form-select id="input-4" v-model="selectedModel" v-if="selectedManufacturer != -1">
            <option v-bind:key="model" v-for="model in cars[selectedManufacturer].options">{{ model }}</option>
        </b-form-select>
        <b-form-select v-else>
            
        </b-form-select>
      </b-form-group>    

      <p>Number of passengers:</p>
      <number-input v-model="passengers" :min="1" :max="10" inline center controls></number-input>

      <p>Year of production:</p>
      <number-input v-model="production" :min="1990" :max="2019" inline center controls></number-input>

      <b-form-group id="input-group-1" label="Vehicle type:" label-for="input-1">
        <b-form-select id="input-1" v-model="form.type" :options="types" required></b-form-select>
      </b-form-group>    


      <b-button type="submit" variant="primary">Add</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
  export default {
    name: "AddVehicleForm",
    data() {
      return {
        form: {
          name: ' ',
          type: null,
          car: null,
          option: null,
          passengerNumber: null,
          productionYear: null
        },
        passengers: 1,
        production: 2012,
        types: ['Hatchback', 'Sedan', 'Station wagon', 'SUV', 'Coupe'],
        show: true,
        cars:[
        {
            label:"Mercedes",
            options:["S Class","A Class","C Class"]
        },
        {
            label:"BMW",
            options:["3 Series","5 Series","7 Series"]
        },
        {
            label:"Audi",
            options:["A3","A4","A6"]
        }
        ],
        selectedManufacturer:-1,
        selectedModel:''
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        this.form.car = this.selectedManufacturer
        this.form.option = this.selectedModel
        this.form.passengerNumber = this.passengers
        this.form.productionYear = this.production
        alert(JSON.stringify(this.form))
      },
      selectManufacturer:function() {
        this.selectedModel = '';
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.name = ' '
        this.form.type = null
        this.selectedManufacturer = -1
        this.selectedModel = ' '
        this.production = 2012
        this.passengers = 1
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
</script>