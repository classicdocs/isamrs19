<template>
  <div>
    <v-card class="card">
      <v-form
          ref="form"
          v-model="form"
        >

        <v-toolbar flat color="white">
          <v-toolbar-title>Room configuration</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="primary" dark @click="expand = !expand">
            {{ expand ? 'Close' : 'Keep' }} other rows
          </v-btn>
           <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="search"
            label="Search rooms" 
            single-line
            hide-details
          ></v-text-field>

        </v-toolbar>

        <v-data-table :headers="headers" :items="rooms" :search="search"
          class="elevation-1" :expand="expand" item-key="name"
        >
          <template v-slot:items="props">
          <tr @click="props.expanded = !props.expanded">
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">
              <v-btn @click="goToPage(props.item.name)">
              hotel page
              <v-icon right>hotel</v-icon>
              </v-btn></td>

          </tr>
          </template>

          <template v-slot:expand="props">
            <v-card flat>
              <v-card-text>Hotel Description: {{props.item.description}}</v-card-text>
            </v-card>
          </template>
          <template v-slot:no-results>
            <v-alert :value="true" color="error" icon="warning">
              Your search for "{{ search }}" found no results.
            </v-alert>
          </template>
          <!-- <template v-slot:no-data>
            <v-alert :value="true" color="error" icon="warning">
              Sorry, nothing to display here :(
            </v-alert>
          </template> -->
          
        </v-data-table>
        <v-card-text>
        </v-card-text>

      </v-form>
    </v-card>
  </div>
</template>

<script>
import HotelsController from "@/controllers/hotels.controller.js"

export default {
  name: "RoomsOverview",
  components: {
    
  },
  data:() => ({
    search: '',
    expand: false,
    headers: [
          {
            text: 'Floor',
            align: 'center',
            value: 'floor'
          },
          {
            text: 'Number',
            align: 'center',
            value: 'number'
          },
          { text: 'Number of beds', value: 'number_of_beds' , align: 'center'},
          { text: 'Booked', value: 'booked' , align: 'center'},
          { text: 'Average rate', value: 'avgRate' , align: 'center'}
        ],
    rooms: [],
    form : true,
    
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },

  }),
  created() {
    HotelsController.getHotels()
      .then((response) => {
        response.data.forEach(element => {
          this.hotels.push(element);
        });
      });
  },
  methods: {
    goToPage(hotelName){
      var id = 0;
      this.hotels.forEach(hotel =>{
        if(hotel.name == hotelName){
          id = hotel.id;
        }
      });

      this.$router.push('/hotel-service/' + id); 
    }
  },
}
</script>
<style>
.text-xs-center{
  font-size: xx-large;
}
</style>

