<template>
    <div>
        <v-dialog
            v-model="AddDiscountForm"
            max-width="auto"
            persistent
        >
        <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">Add discount</v-btn>
        </template>
        <v-stepper v-model="step">
            <v-stepper-header>
                <v-stepper-step :complete="step > 1" step="1">Date, price, offer</v-stepper-step>

                <v-divider></v-divider>

                <v-stepper-step :complete="step > 2" step="2">for room</v-stepper-step>
            </v-stepper-header>      

            <v-stepper-items>

                <v-stepper-content step="1">

                <v-layout row wrap>
                    <v-flex>
                        <v-layout row wrap>

                            <v-flex>
                                <v-menu
                                    ref="menuCheckInDate"
                                    v-model="menuCheckInDate"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    :return-value.sync="checkInDate"
                                    lazy
                                    transition="scale-transition"
                                    offset-y
                                    full-width
                                    min-width="290px"
                                >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                    v-model="checkInDate"
                                    label="Check-in date"
                                    prepend-icon="event"
                                    readonly
                                    required
                                    :rules="[v => !!v || 'Check-in date is required']"
                                    v-on="on"
                                    ></v-text-field>
                                </template>

                                <v-date-picker v-model="checkInDate" no-title scrollable >
                                    <v-btn flat color="primary" @click="menuCheckInDate = false" >Cancel</v-btn>
                                    <v-spacer></v-spacer>
                                    <v-btn flat color="primary" @click="$refs.menuCheckInDate.save(checkInDate)">OK</v-btn>
                                </v-date-picker>
                                </v-menu>

                                <v-menu
                                    ref="menuCheckOutDate"
                                    v-model="menuCheckOutDate"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    :return-value.sync="checkOutDate"
                                    lazy
                                    transition="scale-transition"
                                    offset-y
                                    full-width
                                    min-width="290px"
                                >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                    v-model="checkOutDate"
                                    label="Check-out date"
                                    prepend-icon="event"
                                    readonly
                                    required
                                    :rules="[v => !!v || 'Check-out date is required']"
                                    v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-date-picker v-model="checkOutDate" no-title scrollable >
                                    <v-btn flat color="primary" @click="menuCheckOutDate = false" >Cancel</v-btn>
                                    <v-spacer></v-spacer>
                                    <v-btn flat color="primary" @click="$refs.menuCheckOutDate.save(checkOutDate)">OK</v-btn>
                                </v-date-picker>
                                </v-menu> 
                            </v-flex>

                            
                        </v-layout>
                        
                        <!-- <v-subheader><h3>Choose additional services</h3></v-subheader>
                        <v-data-table v-if="myHotel != null"
                            v-model="selected"
                            :headers="priceListHeaders"
                            :items="myHotel.priceList"
                            :pagination.sync="pagination2"
                            select-all
                            item-key="description"
                            class="elevation-1"
                            hide-actions
                        >
                        <template v-slot:headers="props">
                            <tr>
                            <th>
                                <v-checkbox
                                :input-value="props.all"
                                :indeterminate="props.indeterminate"
                                primary
                                hide-details
                                @click.stop="toggleAll"
                                ></v-checkbox>
                            </th>
                            <th
                                v-for="header in props.headers"
                                :key="header.text"
                                :class="['column sortable', pagination2.descending ? 'desc' : 'asc', header.value === pagination2.sortBy ? 'active' : '']"
                                @click="changeSort(header.value)"
                            >
                                <v-icon small>arrow_upward</v-icon>
                                {{ header.text }}
                            </th>
                            </tr>
                        </template>
                        <template v-slot:items="props">
                            <tr :active="props.selected" @click="props.selected = !props.selected" v-if="props.item.type == 'AdditionalService'">
                            <td>
                                <v-checkbox
                                :input-value="props.selected"
                                primary
                                hide-details
                                ></v-checkbox>
                            </td>
                            <td class="text-xs-center">{{ props.item.description }}</td>
                            <td class="text-xs-center">{{ props.item.price }}</td>
                            </tr>
                        </template>
                        
                        </v-data-table> -->

                        <span><v-subheader>Discount price</v-subheader></span>
                        <number-input v-if="myHotel != null" v-model="discountPrice" :min="0" inline center controls  :rules="[v => !!v || 'Discount price is required']"></number-input>
                    

                    </v-flex>
                    
                </v-layout>

                <v-layout col wrap>
                    <v-flex>
                        <v-btn @click="AddDiscountForm = false">CANCEL</v-btn>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex>
                        <v-btn color="primary" @click.native="continueTo(2)">Continue</v-btn>
                    </v-flex>
                </v-layout>

            </v-stepper-content>
            <v-stepper-content step="2">
            
                <v-layout row wrap v-if=" myHotel != null">

                    <v-flex lg12 md12 sm12 xs12 
                    v-for="(floor,floorNUM) in myHotel.floors" :key="floorNUM">
                    
                    <v-btn flat >Floor {{ floor.level }}</v-btn>
                    
                    
                    <v-btn small 
                        v-for="(room,roomNUM) in getRooms(floor)" :key="roomNUM"
                        @click="pickRoom(room, floor)" 
                    >
                    
                    <v-icon large> airline_seat_individual_suite</v-icon>X
                    <v-icon v-if="room.numberOfBeds == 1"> looks_one</v-icon>
                    <v-icon v-if="room.numberOfBeds == 2"> looks_two</v-icon>
                    <v-icon v-if="room.numberOfBeds == 3"> looks_3</v-icon>
                    <v-icon v-if="room.numberOfBeds == 4"> looks_4</v-icon>
                    <v-icon v-if="room.numberOfBeds == 5"> looks_5</v-icon>
                    </v-btn>
                    
                    <v-divider v-if="floorNUM < myHotel.floors.length" :key="`divider-${floorNUM}`"></v-divider>
                    </v-flex>
                </v-layout>

                <v-layout row wrap>
                    <v-flex>
                        <h1>number: {{pickedRoom.roomNumber}} floor: {{pickedRoom.hotelFloor.level}}</h1>
                    </v-flex>
                    <v-flex>
                        <h1>discount price: {{discountPrice}} eur</h1>
                    </v-flex>
                </v-layout>

                <v-layout col wrap>
                     <v-flex>
                        <v-btn @click.native="step = 1">BACK</v-btn>
                    </v-flex>
                     <v-flex>
                        <v-btn @click="AddDiscountForm = false">CANCEL</v-btn>
                    </v-flex>
                    <v-spacer></v-spacer>
                     <v-flex>
                        <v-btn color="primary" @click.native="finish()">FINISH</v-btn>
                    </v-flex>
                </v-layout>
            </v-stepper-content>
            
            </v-stepper-items>         
        </v-stepper>
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
import RoomDiscount from "@/models/RoomDiscount.js";
import HotelController from "@/controllers/hotels.controller";

import store from "@/store";


export default {
    name: "AddDiscount",
    data: () => ({
        AddDiscountForm: false,
        step: 1,

        menuCheckInDate: false,
        checkInDate: null,

        menuCheckOutDate: false,
        checkOutDate: null,

        discountPrice: 0,

        hotel: new Hotel(),

        selected: [],
        priceListHeaders: 
        [
          { text: 'Description', 
            align: 'center',
            sortable: false, 
            value: 'description' 
          },
          { text: 'Price', 
            align: 'center', 
            sortable: true, 
            value: 'price' 
          }
        ],
        pagination2: {
            sortBy: 'price'
        },

        notAvailableRooms: [],
        pickedRooms: [],
        pickedRoom: new Room(),
        
        snackbar: {
            show: false,
            color: "",
            msg: "",
        },
    }),
    computed: {
        myHotel: function(){
            this.hotel = store.getters.newHotel;
            console.log("hotel from store")
            console.log(this.hotel)
            return this.hotel;
        }
    },
    methods: {
        finish(){
            var roomDiscount = new RoomDiscount();
            roomDiscount.startDate = this.checkInDate;
            roomDiscount.endDate = this.checkOutDate;
            roomDiscount.price = this.discountPrice;
            roomDiscount.additionalServices = this.selected;

            var pr = 0;
            this.hotel = store.getters.newHotel;
            if(this.hotel != null && this.pickedRoom.numberOfBeds != null){
                this.hotel.priceList.forEach(offer => {
                    if(offer.type == 'OneBed' && this.pickedRoom.numberOfBeds == 1){
                        pr =  offer.price;
                    }else if(offer.type == 'TwoBeds' && this.pickedRoom.numberOfBeds == 2){
                        pr =  offer.price;
                    }else if(offer.type == 'ThreeBeds' && this.pickedRoom.numberOfBeds == 3){
                        pr =  offer.price;
                    }else if(offer.type == 'FourBeds' && this.pickedRoom.numberOfBeds == 4){
                        pr =  offer.price;
                    }else if(offer.type == 'FiveBeds' && this.pickedRoom.numberOfBeds == 5){
                        pr =  offer.price;
                    }
                })
            }

            if(pr < roomDiscount.price){
                this.showSnackbar({msg: "Discount price cant be higher than hotel room price", color: "error"});
                return;
            }



            var id = 0;
            if(typeof this.pickedRoom.id == 'undefined'){
                id = this.checkID();
            }else{
                id = this.pickedRoom.id ;
            }

            HotelController.addRoomDiscount(this.$route.params.id, id, roomDiscount)
            .then(response => {
                this.hotel = this.myHotel
                
                console.log("hotel iz this.myHotel, pre dodavanja response data")
                console.log(this.hotel)
                this.hotel.floors.forEach(floor => {
                    floor.roomsOnFloor.forEach(room => {
                        if(room.id = this.pickedRoom.id){

                            room.roomDiscounts.push(response.data);
                            this.AddDiscountForm = false;
                            this.showSnackbar({msg: "Discount successfully defined", color: "success"});

                        }
                    })
                })
                console.log("hotel for push")
                console.log(this.hotel)
                store.commit('newHotel', this.hotel);
                this.reset();
            })
        },
        checkID(){
            this.myHotel.floors.forEach(floor => {
            if(floor.level == this.pickedRoom.hotelFloor.level){
                floor.roomsOnFloor.forEach(room => {
                    if(room.roomNumber == this.pickedRoom.roomNumber){
                        return room.id;
                    }
                })
            }
            })
        },
        reset(){
            this.checkInDate = null;
            this.checkOutDate = null;

            this.discountPrice = 0;
            this.selected = [];
            this.notAvailableRooms = [];
            this.pickedRooms = [];
            this.pickedRoom = new Room();
            this.step = 1;

        },
        continueTo(number){
            if(number == 2){
                var startDate = new Date(this.checkInDate); 
                var endDate = new Date(this.checkOutDate); 
                var today = new Date();

                if(startDate <= today) {
                    this.showSnackbar({msg: "Check in date can not be before today.", color: "error"});
                    return;
                }else if (endDate <= startDate){
                    this.showSnackbar({msg: "End date must be after start date.", color: "error"});
                    return;
                }

                if(this.checkInDate != null && this.checkOutDate != null && this.numOfPeople != 0){
                    this.checkDates();
                    this.step = 2;
                }
            }
        },
        pickRoom(room, floor){
            var found = false;
            this.pickedRooms.forEach(pickRoom => {
                if(pickRoom.id == room.id){
                    found = true;
                    this.alreadyPicked = true;
                }
            })

            if(!found){
                this.alreadyPicked = false;
            }

            this.pickedRoom = new Room();
            this.pickedRoom.id = room.id;
            this.pickedRoom.roomNumber = room.roomNumber;
            this.pickedRoom.numberOfBeds = room.numberOfBeds;
            this.pickedRoom.hotelFloor = new HotelFloor();
            this.pickedRoom.hotelFloor.level = room.hotelFloor.level;  
            console.log("picked room")
            console.log(this.pickedRoom);
        },
        getRooms(floor){
            var availableRooms = [];
            floor.roomsOnFloor.forEach(room => {
            if(this.notAvailableRooms.indexOf(room) == -1){
                availableRooms.push(room);
            }
            })
            console.log("available rooms");
            console.log(availableRooms);
            return availableRooms;
        },
        checkDates(){
            var startDate = new Date(this.checkInDate);
            var endDate = new Date(this.checkOutDate);

            if(this.myHotel != null){

                this.myHotel.floors.forEach(floor => {
                    floor.roomsOnFloor.forEach(room => {
                        room.roomTaken.forEach(takenDate => {

                            var startTakenDate = new Date(takenDate.startDate);
                            var endTakenDate = new Date(takenDate.endDate);

                            if(!(endDate < startTakenDate || startDate > endTakenDate)){
                                if(this.notAvailableRooms.indexOf(room) == -1){
                                    this.notAvailableRooms.push(room);
                                }
                            }
                        })

                        room.roomDiscounts.forEach(rd => {
                            var startDiscountDate = new Date(rd.startDate);
                            var endDiscountDate = new Date(rd.endDate);

                            if(!(endDate < startDiscountDate || startDate > endDiscountDate)){
                                if(this.notAvailableRooms.indexOf(room) == -1){
                                    this.notAvailableRooms.push(room);
                                }
                            }
                        })
                    })
                })
            }

            console.log("Theese rooms are not available");
            console.log(this.notAvailableRooms);
        },
        toggleAll () {
            if (this.selected.length) this.selected = []
            else this.selected = this.myHotel.priceList.slice()
        },
        changeSort (column) {
            if (this.pagination2.sortBy === column) {
                this.pagination2.descending = !this.pagination2.descending
            } else {
                this.pagination2.sortBy = column
                this.pagination2.descending = false
            }
        },
        showSnackbar(obj) {
            this.snackbar.color = obj.color;
            this.snackbar.msg = obj.msg;
            this.snackbar.show = true;
        },
    }
}
</script>
