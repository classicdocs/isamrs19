<template>
  <div>
    <v-card class="card">
      <v-form
          ref="form"
          v-model="form"
        >
        <!-- Toolbar sadrzi naslov i dugme za omogucavanje sirenja opisa kod jednog ili vise hotela -->


        <v-toolbar flat color="white">
          <v-toolbar-title>List of hotels</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="primary" dark @click="expand = !expand">
            {{ expand ? 'Close' : 'Keep' }} other rows
          </v-btn>
           <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="search"
            label="Search hotels" 
            single-line
            hide-details
          ></v-text-field>

        </v-toolbar>
        
        <!-- Forma koja se moze rasiriti u kojoj se prikazuje lista hotela -->


        <v-data-table :headers="headers" :items="hotels" :search="search"
          class="elevation-1" :expand="expand" item-key="name"
        >
          <template v-slot:items="props">
          <tr @click="props.expanded = !props.expanded">
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.address }}</td>
            <td class="text-xs-center">{{ "NO DATA" }}</td>
            <td class="text-xs-center">
              <v-btn @click="goToPage(props.item.name)">
              Go to page
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
  name: "HotelsOverviewForm",
  components: {
    
  },
  data:() => ({
    search: '',
    expand: false,
    headers: [
          {
            text: 'Name',
            align: 'center',
            value: 'name'
          },
          { text: 'Address', value: 'address' , align: 'center'},
          { text: 'Average rate', value: 'avgRate' , align: 'center'}
        ],
    hotels: [],
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
      alert("going to page for hotel: " + hotelName );
    }
  },
}
</script>
<style>
.text-xs-center{
  font-size: xx-large;
}
</style>

