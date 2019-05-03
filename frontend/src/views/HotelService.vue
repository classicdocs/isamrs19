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
                  ></add-room-dialog><!-- v-bind:hotel="this.hotel" -->
            </v-layout>
          </v-container>
          
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
import HotelController from "@/controllers/hotels.controller"; 
import Hotel from "@/models/Hotel"; 
import store from "@/store";

export default {
  name: "HotelService",
  components: {
    'hotel-service-info': HotelServiceInfo,
    'add-room-dialog':AddRoomDialog,
},
  data: () => ({

    AddRoomDialog: false,

    admin: false,
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
  methods: {
    fetchData() {
      this.id = this.$route.params.id;

      HotelController.getHotel(this.id)
        .then((response) => {
          this.hotel = response.data;
        })
        .catch(() => {
          alert(error.response.data);
          this.$router.push({name: "home"});
        });
      
      if (store.getters.activeUserRole === 'Hotel Admin')
      if (this.id == store.getters.activeUser.idAdminOf)
        this.admin = true;
      else
        this.admin = false;

    },
    infoUpdate(obj) {
      this.hotel = obj;
    },
    cancel(){
      this.AddRoomDialog = false;
    },
    closeAddRoom(data){
      this.AddRoomDialog = false;
      this.showSnackbar(data);
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

