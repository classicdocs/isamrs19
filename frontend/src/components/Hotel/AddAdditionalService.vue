<template>
  <div>
    <v-dialog
      v-model="addFormDialog"
      max-width="auto"
      persistent
    >
    <template v-slot:activator="{ on }">
      <v-btn color="primary" dark v-on="on">Additional services and prices</v-btn>
    </template>
    <v-card>
      <v-form
        ref="form"
        v-model="form"
        lazy-validation
      >

      <v-card-text>
          
          
            <v-toolbar flat color="white">
              <v-toolbar-title>PRICELIST</v-toolbar-title>
              <v-divider
                class="mx-2"
                inset
                vertical
              ></v-divider>

              <v-spacer></v-spacer>

              <v-dialog v-model="dialog" max-width="500px">
                
                <template v-slot:activator="{ on }">
                  <v-btn color="primary" dark class="mb-2" v-on="on">New service</v-btn>
                </template>
                
                <v-card>

                  <v-card-text>
                    <v-container grid-list-md>
                      <v-layout row wrap align-center>

                        <v-flex xs6>
                            <v-subheader >Choose type of service</v-subheader>
                        </v-flex>
                        <v-flex xs6>
                          <v-select :items="freeTypes" label="type" v-model="newOffer.type" required :rules="typeRules"></v-select>
                        </v-flex>


                        <v-flex xs6>
                            <v-subheader >Describe this service</v-subheader>
                        </v-flex>
                        <v-flex xs6>
                            <v-text-field v-model="newOffer.description" label="description" :rules="[v => !!v || 'Description is required']"></v-text-field>
                        </v-flex>

                        <v-flex xs6>
                            <v-subheader >Choose the price for this service</v-subheader>
                        </v-flex>
                        <v-flex xs6>
                          <number-input v-model="newOffer.price" :min="0" inline center controls :rules="[v => !!v || 'New price is required']">
                          </number-input>
                        </v-flex>
                      </v-layout>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
                    <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
                  </v-card-actions>
                </v-card>
              
              </v-dialog>
            
            </v-toolbar>
            <v-data-table
              :headers="headers"
              :items="hotel.priceList"
              class="elevation-1"
              hide-actions
            >
              <template v-slot:items="props">
                <td>{{ props.item.type }}</td>
                <td class="text-xs-right">{{ props.item.description }}</td>
                <td class="text-xs-right">{{ props.item.price }}</td>
                <td class="justify-center layout px-0">

                  <v-icon small class="mr-2"
                    @click="editItem(props.item)" > edit </v-icon>

                  <v-icon small @click="deleteItem(props.item)"> delete </v-icon>

                </td>
              </template>
              <template v-slot:no-data>
                NO DATA
              </template>
            </v-data-table>

      </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="closeForm">Close</v-btn> 
        </v-card-actions>
      </v-form>
    </v-card>
    </v-dialog>
  </div>
</template>

<script>

import Hotel from "@/models/Hotel";
import HotelsOffer from "@/models/HotelsOffer";
import HotelController from "@/controllers/hotels.controller";

import store from "@/store";
import { returnStatement } from '@babel/types';


export default {
  name: "AddAdditionalService",
  props: ['hotel'],
  data: () => ({
    form: true,
    addFormDialog: false,
    dialog: false,
    types: ['OneBed', 'TwoBeds','ThreeBeds', 'FourBeds','FiveBeds', 'AdditionalService'],

    headers: [
       {
            text: 'Type of service',
            align: 'center',
            sortable: false,
            value: 'type'
          },
          { text: 'Description', align: 'center', sortable: false, value: 'description' },
          { text: 'Price', align: 'center', sortable: false, value: 'price' }
     ],
     typeRules: [
            v => !!v || 'Service type is required'
        ],

    editedIndex: -1,
    newOffer: new HotelsOffer(),
    deffaultOffer: {
      price: 0,
      type : 0,
      description: ""
    }
  }),
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
    freeTypes: function(){
      var lista = [];
      this.types.forEach(t => {
        this.hotel.priceList.forEach(offer => {
          if(offer.type == t){
            lista.push(t);
          }
        })
      })

      var returnLista = [];
      this.types.forEach(singleType => {
        var idx = lista.indexOf(singleType);
        if(idx == -1){
          returnLista.push(singleType);
        }
      })

      return returnLista;
    }
  },
  methods: {
    update(){
      HotelController.updatePricelist(this.$route.params.id, this.hotel.priceList)
      .then(response => {
        console.log(response);
         this.$emit("finished", {msg: "Additional service successfully added", color: "success"})
      })
      .catch((response) => {
          this.$emit("finished", {msg: "Error! Something with update went wrong...", color: "error"})
      })
    },
    alreadyExists(){
         this.$emit("serviceExists", {msg: "Service already exists", color: "error"})
    },
    save(){
      if (this.$refs.form.validate()) {
      
      if (this.editedIndex > -1) {

        Object.assign(this.hotel.priceList[this.editedIndex], this.newOffer)
      } else {
        var exists = false;
        
        this.hotel.priceList.forEach(offer => {
          console.log(offer.type != "AdditionalService");
          if(offer.type == this.newOffer.type && offer.type != "AdditionalService"){
            this.alreadyExists();
            exists = true;
          }
        })
        if(!exists){
          this.hotel.priceList.push(this.newOffer)
        }
      }
      this.close();
      this.update();
      }
    },
    isEmpty(str) {
      return (!str || 0 === str.length);
    },
    editItem (item) {
      this.newOffer = new HotelsOffer();
      this.editedIndex = this.hotel.priceList.indexOf(item)
      this.newOffer = Object.assign({}, item)
      this.dialog = true
    },
    deleteItem (item) {
      alert("We are working on it");
      // const index = this.hotel.priceList.indexOf(item);
      // alert("Brisem " + index);
      // confirm('Are you sure you want to delete this item?') &&  this.hotel.priceList.splice(index,1)
      // this.update();
    },
    closeForm(){
      this.addFormDialog = false;
    },
    close () {
      this.newOffer = new HotelsOffer();
      this.dialog = false
      setTimeout(() => {
        this.newOffer = Object.assign({}, this.deffaultOffer)
        this.editedIndex = -1
      }, 300)
    },
  }
};
</script>
<style>

.divider {
  border-width: 7px;
  border-color: dodgerblue;
}

</style>
