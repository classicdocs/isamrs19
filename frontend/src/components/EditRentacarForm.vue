<template>
    <div>
          <v-form
          ref="form"
          v-model="validEdit"
          lazy-validation
          id="EditForm"
          >
          <v-card-title>
          <span class="headline">Edit service information</span>
          </v-card-title>
          <v-card-text>
          <v-container grid-list-md>
              <v-layout wrap>
              <v-flex xs12>
                  <v-text-field :rules="service_name_rules" v-model="newRentacar.name" label="Service name*" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                  <v-text-field :rules="service_address_rules" v-model="newRentacar.address" label="Address*" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                  <v-textarea :rules="service_description_rules" v-model="newRentacar.promotionalDescription" label="Description*" required></v-textarea>
              </v-flex>              
              </v-layout>
          </v-container>
          <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!validEdit" color="success" @click="validateEdit">Save</v-btn>
          </v-card-actions>
          </v-form>
    </div>
</template>

<script>

import RentACarController from "@/controllers/rentacar.controller.js"
import RentACar from "@/models/RentACar.js"

export default {
    name: "EditRentacarForm",
    data: () => ({
        name:"",
        address: "",
        validEdit:true,
        promotionalDescription: "",
        service_name_rules: [
        v => !!v || 'Name is required'
        ],
        service_address_rules: [
            v => !!v || 'Address is required'
        ],
        service_description_rules: [
            v => !!v || 'Description is required'
        ],
        newRentacar: new RentACar()
    }),
    created() {
        this.id = this.$route.params.id;

        RentACarController.get(this.id)
            .then((response) => {
                this.newRentacar = response.data;
            })
    },
    methods: {
        onSubmit() {
            //this.newRentacar = new RentACar(this.$route.params.id,this.newName, this.newAddress, this.newDescription); 
            RentACarController.update(this.$route.params.id, this.newRentacar);
            this.$emit('update-rentacar', this.newRentacar);
        },
        validateEdit() {
            if (this.$refs.form.validate()) {
                this.onSubmit()
            }
        }
    }
}
</script>

<style>

#vcard {
    padding-top: 4%;
    padding-bottom: auto;
}

</style>
