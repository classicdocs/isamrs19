<template>
  <div>
    <v-card class="card">
      <v-form
          ref="form"
          v-model="form"
        >
        <v-toolbar flat color="white">
          <v-toolbar-title>List of rent-a-car companies</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="primary" dark @click="expand = !expand">
            {{ expand ? 'Close' : 'Keep' }} other rows
          </v-btn>
        </v-toolbar>
        <v-data-table :headers="headers" :items="rentACars"
          class="elevation-1" :expand="expand" item-key="name"
        >
          <template v-slot:items="props">
          <tr @click="props.expanded = !props.expanded">
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.address }}</td>
            <!-- <td class="text-xs-right">{{ props.item.promotionalDescription }}</td> -->
            <!-- <td class="text-xs-center">{{ "NO DATA" }}</td> -->
          </tr>
          </template>
          <template v-slot:expand="props">
            <v-card flat>
              <v-card-text>Rent-a-car company description: {{props.item.promotionalDescription}}</v-card-text>
            </v-card>
          </template>
          <template v-slot:no-data>
            <v-alert :value="true" color="error" icon="warning">
              Sorry, nothing to display here :(
            </v-alert>
          </template>
          
        </v-data-table>
        <v-card-text>
        </v-card-text>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import RentACarController from "@/controllers/rentacar.controller.js"

export default {
  name: "RentACarOverviewForm",
  components: {
    
  },
  data:() => ({
    expand: false,
    headers: [
          {
            text: 'Airline company name',
            align: 'center',
            value: 'airlineName'
          },
          { text: 'Address', value: 'address' , align: 'center'},
          // { text: 'Description', value: 'description' , align: 'center', sortable: false},
          //{ text: 'Average rate', value: 'avgRate' , align: 'center'}
        ],
    rentACars: [],
    form : true,
    
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
  }),
  created() {
    RentACarController.getAll()
      .then((response) => {
        response.data.forEach(element => {
          this.rentACars.push(element);
        });
      });
  },
}
</script>
<style>
.text-xs-center{
  font-size: xx-large;
}
</style>

