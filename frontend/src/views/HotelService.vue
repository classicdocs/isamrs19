<template>
  <div>
    <v-container >
      <v-layout row wrap>
        <v-flex lg4 md6 sm6 xs12>
          <hotel-service-info v-bind:hotel="hotel"/>
        </v-flex>
        <v-flex lg8 md6 sm6 xs12 v-if="admin">
          <v-container>
            <v-layout row wrap> 
                <add-room-dialog 
                  v-bind:hotel="this.hotel"
                  v-on:finished="closeAddRoom($event)"
                  v-on:cancel  ="cancel"
                  ></add-room-dialog>
                  
                <change-room-dialog
                  v-bind:hotel="this.hotel"
                  v-on:finished="closeChangeRoom($event)"
                  v-on:cancel="cancel"
                ></change-room-dialog>

                <priceList-overview>
                </priceList-overview>

                <add-special-price></add-special-price>
            </v-layout>
            <v-layout col wrap>
                <rooms-overview
                v-bind:hotel="this.hotel">
                </rooms-overview>
            </v-layout>
          </v-container>
          
        </v-flex>
        <v-flex></v-flex>
        <v-flex lg8 md6 sm6 xs12 v-if="user"> 
            <reservation-form/>
        </v-flex>
      </v-layout>
    </v-container>
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

import HotelServiceInfo from "@/components/Hotel/HotelServiceInfo.vue"; 
import AddRoomDialog from "@/components/Hotel/AddRoomDialog.vue"; 
import ChangeRoomDialog from "@/components/Hotel/ChangeRoomDialog.vue"; 
import RoomsOverview from "@/components/Hotel/RoomsOverview.vue"
import ReservationForm from "@/components/HotelReservation/ReservationForm.vue";
import PriceListOverview from "@/components/Hotel/PriceListOverview.vue";
import AddSpecialPrice from "@/components/Hotel/AddSpecialPrice.vue";

import HotelController from "@/controllers/hotels.controller"; 
import Hotel from "@/models/Hotel"; 
import store from "@/store";


import MapsTest from "@/components/MapsTest/MapsTest.vue"; 

export default {
  name: "HotelService",
  components: {
    'hotel-service-info': HotelServiceInfo,
    'add-room-dialog':AddRoomDialog,
    'change-room-dialog':ChangeRoomDialog,
    'rooms-overview':RoomsOverview,
    'priceList-overview': PriceListOverview,
    'reservation-form':ReservationForm,
    'add-special-price': AddSpecialPrice,
},
  data: () => ({

    AddRoomDialog: false,
    ChangeRoomDialog: false,
    ReservationForm: false,

    admin: false,
    user: false,
    id: null,
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },

    hotel: new Hotel()  
  }),
  created() {
    this.fetchData();
  },
  watch: {
    '$route': 'fetchData'
  },
  computed(){

  },
  methods: {
    fetchData() {
      this.id = this.$route.params.id;

      var allRooms = [];
      HotelController.getRooms(this.id)
      .then((response) => {
        if(response.data != null){
          allRooms = response.data;
        }
        store.commit('allRooms', response.data);
        console.log("All rooms");
        console.log(response.data);
      })

      HotelController.getHotel(this.id)
        .then((response) => {
          this.hotel = response.data;
          store.commit('newHotel', this.hotel);
        })
        .catch((error) => {
          alert(error.data);
          this.$router.push({name: "home"});
        });
      
      if (store.getters.activeUserRole === 'Hotel Admin')
      if (this.id == store.getters.activeUser.idAdminOf)
        this.admin = true;
      else
        this.admin = false;

      if(store.getters.activeUserRole === "User"){
        this.user = true;
      }

    },
    infoUpdate(obj) {
      this.hotel = obj;
    },
    cancel(){
      this.AddRoomDialog = false;
      this.ChangeRoomDialog = false;
    },
    closeAddRoom(data){
      this.AddRoomDialog = false;
      this.showSnackbar(data);
    },
    closeChangeRoom(data){
      this.ChangeRoomDialog = false;
      this.showSnackbar(data);
    },
    showServiceExists(obj){
      this.showSnackbar(obj);
    },
    showSnackbar(obj) {
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    }
  }
};
</script>

<style>

</style>

