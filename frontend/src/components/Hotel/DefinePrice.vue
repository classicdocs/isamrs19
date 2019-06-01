<template>
    <v-dialog v-model="form" max-width="500px">
                
        <template v-slot:activator="{ on }">
            <v-btn color="primary" dark class="mb-2" v-on="on">New service</v-btn>
        </template>
        
        <v-card>

            <v-card-text>
            <v-container grid-list-md>
                <v-layout row wrap align-center>

                <v-flex xs6>
                    <v-subheader >Defining price for {{type}}</v-subheader>
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

</template>

<script>


import HotelsOffer from "@/models/HotelsOffer";


export default {
  name: "DefinePrice",
  props: ['type'],
  data: () => ({
    form: true,
    newOffer: new HotelsOffer()
  }),
  methods: {
      close(){
          this.form = false;
      },
      save(){
          this.newOffer.type = type;
          
      }
  }
}
</script>