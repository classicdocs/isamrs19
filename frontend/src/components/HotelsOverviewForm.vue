<template>
  <div>
    <v-card class="card">
      <v-form
          ref="form"
          v-model="form"
        >
        <v-toolbar flat color="white">
          <v-toolbar-title>List of hotels</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="primary" dark @click="expand = !expand">
            {{ expand ? 'Close' : 'Keep' }} other rows
          </v-btn>
        </v-toolbar>
        <v-data-table
          :headers="headers"
          :items="hotels"
          class="elevation-1"
          :expand="expand"
          item-key="name"
        >
          <template v-slot:items="props">
          <tr @click="props.expanded = !props.expanded">
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.address }}</td>
            <!-- <td class="text-xs-right">{{ props.item.description }}</td> -->
            <td class="text-xs-center">{{ "NO DATA" }}</td>
          </tr>
          </template>
          <template v-slot:expand="props">
            <v-card flat>
              <v-card-text>Hotel Description: {{props.item.description}}</v-card-text>
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
    <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
    >
      {{snackbar.msg}}
      <v-btn
          dark
          flat
          @click="snackbar.show = false"
      >
      Close
      </v-btn>
    </v-snackbar>
  </div>
</template>

<script>
import HotelsController from "@/controllers/hotels.controller.js"

export default {
  name: "HotelsOverviewForm",
  components: {
    
  },
  data:() => ({
    expand: false,
    headers: [
          {
            text: 'Hotel name',
            align: 'center',
            value: 'hotelName'
          },
          { text: 'Address', value: 'address' , align: 'center'},
          // { text: 'Description', value: 'description' , align: 'center', sortable: false},
          { text: 'Average rate', value: 'avgRate' , align: 'center'}
        ],
    hotels: [],
    form : true,
    hotelNames: [],
    
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
  }),
  computed: {
  todos(){
    return store.tasks
  }
  },
  created() {
    HotelsController.getHotels()
      .then((response) => {
        response.data.forEach(element => {
          this.hotels.push(element);
          this.hotelNames.push(element.name);
        });
      });
  },
  methods: {
    oneWayBtnClicked() {
      this.roundBtnColor = "default";
      this.oneWayBtnColor = "primary";
    },
    roundBtnClicked() {
      this.roundBtnColor = "primary";
      this.oneWayBtnColor = "default";
    },
    
    validate() {
    }
  }
}
</script>
<style>
.text-xs-center{
  font-size: xx-large;
}
</style>

