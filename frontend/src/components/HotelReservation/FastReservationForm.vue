<template>
    <div>
        <v-dialog
            v-model="FastReservationForm"
            max-width="auto"
            persistent
        >
        <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">Rooms with discount</v-btn>
        </template>

            <v-card>
                <v-form
                    ref="form"
                    v-model="form"
                    lazy-validation
                >
                <v-card-text>
                    <v-container fluid grid-list-md v-if="hotel !== null">
                    <v-data-iterator
                        :items="discounts"
                        :pagination.sync="pagination"
                        content-tag="v-layout"
                        hide-actions
                        row
                        wrap
                        >
                    
                    <template v-slot:header>
                        <v-toolbar
                        class="mb-2"
                        color="indigo darken-5"
                        dark
                        flat
                        >
                        <v-toolbar-title v-if="hotel !== null"><h1>Rooms on discount in {{hotel.name}}:</h1></v-toolbar-title>
                        </v-toolbar>
                    </template>

                    <template v-slot:item="props">
                        <v-flex
                        xs12
                        sm6
                        md4
                        lg3
                        >
                        <v-card>
                            <v-card-title><h2>Room {{ props.item.room.roomNumber }} on {{props.item.room.hotelFloor.level}}. floor</h2></v-card-title>
                            <v-divider></v-divider>
                            <v-list dense>
                            <v-list-tile>
                                <v-list-tile-content>Number of beds:</v-list-tile-content>
                                <v-list-tile-content class="align-end"><h2>{{props.item.room.numberOfBeds}}</h2></v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile>
                                <v-list-tile-content>Check-in:</v-list-tile-content>
                                <v-list-tile-content class="align-end"><h2>{{ props.item.roomDiscount.startDate }}</h2></v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile>
                                <v-list-tile-content>Check-out:</v-list-tile-content>
                                <v-list-tile-content class="align-end"><h2>{{ props.item.roomDiscount.endDate }}</h2></v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile>
                                <v-list-tile-content>Price:</v-list-tile-content>
                                <v-list-tile-content class="align-end"><h2>{{ props.item.roomDiscount.price }}</h2> eur for the night</v-list-tile-content>
                            </v-list-tile>
                            </v-list>
                            <v-card-actions>
                            <v-btn color="success" @click="reserve(props.item)">reserve
                                <v-icon right>work</v-icon>
                            </v-btn>
                            </v-card-actions>
                        </v-card>
                        
                        </v-flex>
                    </template>

                    </v-data-iterator>
                    </v-container>
                </v-card-text>


                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken" flat @click="FastReservationForm = false">Close</v-btn> 
                </v-card-actions>
                </v-form>
                </v-card>                

        </v-dialog>

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
import Hotel from "@/models/Hotel.js";
import HotelFloor from "@/models/HotelFloor.js";
import Room from "@/models/Room.js";
import HotelReservation from "@/models/HotelReservation.js";

import RoomDiscount from "@/models/RoomDiscount.js";
import RoomDiscountBean from "@/models/RoomDiscountBean.js";

import HotelController from "@/controllers/hotels.controller";

import store from "@/store";

export default {
    name: "FastReservationForm",
    data: () => ({
        FastReservationForm: false,
        form: false,
        myHotel: new Hotel(),

        pagination: {
            rowsPerPage: 12
        },
        snackbar: {
            show: false,
            color: "",
            msg: "",
        },

    }),
    computed:{
        hotel: function(){
            this.myHotel = store.getters.newHotel;
            return this.myHotel;
        },
        discounts: function(){
            this.myHotel = store.getters.newHotel;
            var listOfDiscounts = [];
            if(this.myHotel != null){
                this.myHotel.floors.forEach(floor => {
                    floor.roomsOnFloor.forEach(room => {
                        var availableDiscounts = this.findAvailableDiscounts(room);
                        availableDiscounts.forEach(availableDiscount => {
                            var discountBean = new RoomDiscountBean();

                            room.hotelFloor = new HotelFloor();
                            room.hotelFloor.level = floor.level;
                            discountBean.room = room;
                            discountBean.roomDiscount = availableDiscount;


                            listOfDiscounts.push(discountBean);

                        })
                    })
                });
            }
            return listOfDiscounts;
        }
    },
    methods: {
        reserve(discountBean){
            var reservation = new HotelReservation();
            reservation.checkInDate = discountBean.roomDiscount.startDate;
            reservation.checkOutDate = discountBean.roomDiscount.endDate;
            reservation.totalPrice = discountBean.roomDiscount.price;
            reservation.numberOfPeople = discountBean.room.numberOfBeds;
            reservation.user = store.getters.activeUser.username;
            reservation.additionalServices = [];
            reservation.rooms = [];
            reservation.rooms.push(discountBean.room)
            var newHotel = this.hotel;
            newHotel.floors = []
            newHotel.admins = [];
            reservation.hotel = newHotel;

            HotelController.reserve(this.$route.params.id, reservation)
            .then(response => {
                
                if(typeof response.data.rooms == 'undefined'){
                    this.showSnackbar({msg: "Sorry, to late! Some of the rooms you chose are already reserved", color: "error"});
                    return;
                }
                this.showSnackbar({msg: "Reservation is successfull", color: "success"});
                

                var currentHotel = this.hotel;
                
                if(typeof currentHotel != 'undefined'){
                    currentHotel.floors.forEach(floor => {
                    floor.roomsOnFloor.forEach(room => {
                        response.data.rooms.forEach(responseRoom => {
                        if(room.id == responseRoom.id){
                            room.roomTaken = responseRoom.roomTaken.slice();
                        }
                        })
                    })
                    })  

                    store.commit('newHotel', currentHotel);
                    this.FastReservationForm = false;
                }

            })
        },
        findAvailableDiscounts(room){

            var listOfDiscounts = [];
            room.roomDiscounts.forEach(discount => {
                var startDiscountDate = new Date(discount.startDate);
                var endDiscountDate = new Date(discount.endDate);

                var discountAvailable = true;
                room.roomTaken.forEach(taken => {
                    
                    var startTakenDate = new Date(taken.startDate);
                    var endTakenDate = new Date(taken.endDate);


                    if((endDiscountDate < startTakenDate || startDiscountDate > endTakenDate)){
                        discountAvailable = false;
                    }
                })

                if(discountAvailable){
                    listOfDiscounts.push(discount);
                }
            })
            return listOfDiscounts;
        },
        showSnackbar(obj) {
            this.snackbar.color = obj.color;
            this.snackbar.msg = obj.msg;
            this.snackbar.show = true;
        },
    }
}
</script>
