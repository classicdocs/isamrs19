<template>
  <div>
    <v-card>
      <v-container>
        <v-stepper v-model="step" vertical>
          <v-stepper-header>
            <v-stepper-step step="1" :complete="step > 1"
              >Booking time</v-stepper-step
            >
            <v-divider></v-divider>
            <v-stepper-step step="2" :complete="step > 2"
              >Select rooms</v-stepper-step
            >
            <v-divider></v-divider>
            <v-stepper-step step="3" :complete="step > 3"
              >Additional service</v-stepper-step
            >
            <v-divider></v-divider>
            <v-stepper-step step="4" :complete="step > 4"
              >Overview</v-stepper-step
            >
            <v-divider></v-divider>
          </v-stepper-header>
          <v-stepper-items>
            <v-stepper-content step="1">
              <v-layout col span>
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
                    <v-date-picker v-model="checkInDate" no-title scrollable>
                      <v-btn
                        flat
                        color="primary"
                        @click="menuCheckInDate = false"
                        >Cancel</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="$refs.menuCheckInDate.save(checkInDate)"
                        >OK</v-btn
                      >
                    </v-date-picker>
                  </v-menu>
                </v-flex>
                <v-flex>
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
                    <v-date-picker v-model="checkOutDate" no-title scrollable>
                      <v-btn
                        flat
                        color="primary"
                        @click="menuCheckOutDate = false"
                        >Cancel</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="$refs.menuCheckOutDate.save(checkOutDate)"
                        >OK</v-btn
                      >
                    </v-date-picker>
                  </v-menu>
                </v-flex>
                <v-flex></v-flex>
              </v-layout>
              <v-layout>
                <v-flex>
                  <span><v-subheader>Number of people</v-subheader></span>
                  <number-input
                    v-model="numOfPeople"
                    :min="0"
                    :max="6"
                    inline
                    center
                    controls
                    :rules="[v => !!v || 'Num of people is required']"
                  ></number-input>
                </v-flex>
              </v-layout>

              <v-btn color="primary" @click.native="continueTo(2)"
                >Continue</v-btn
              >
            </v-stepper-content>

            <v-stepper-content step="2">
              <v-layout row wrap v-if="myHotel != null">
                <v-flex
                  lg12
                  md12
                  sm12
                  xs12
                  v-for="(floor, floorNUM) in myHotel.floors"
                  :key="floorNUM"
                >
                  <v-btn flat>Floor {{ floor.level }}</v-btn>

                  <v-btn
                    small
                    :disabled="checkDisabled(room)"
                    v-for="(room, roomNUM) in getRooms(floor)"
                    :key="roomNUM"
                    @click="pickRoom(room, floor)"
                  >
                    <v-icon large> airline_seat_individual_suite</v-icon>X
                    <v-icon v-if="room.numberOfBeds == 1"> looks_one</v-icon>
                    <v-icon v-if="room.numberOfBeds == 2"> looks_two</v-icon>
                    <v-icon v-if="room.numberOfBeds == 3"> looks_3</v-icon>
                    <v-icon v-if="room.numberOfBeds == 4"> looks_4</v-icon>
                    <v-icon v-if="room.numberOfBeds == 5"> looks_5</v-icon>
                  </v-btn>

                  <v-divider
                    v-if="floorNUM < myHotel.floors.length"
                    :key="`divider-${floorNUM}`"
                  ></v-divider>
                </v-flex>
              </v-layout>

              <v-layout row wrap>
                <v-flex>
                  <template>
                    <v-container
                      fluid
                      grid-list-md
                      v-if="pickedRooms.length !== 0"
                    >
                      <v-data-iterator
                        :items="pickedRooms"
                        :pagination.sync="pagination"
                        content-tag="v-layout"
                        row
                        wrap
                        hide-actions
                      >
                        <template v-slot:header>
                          <v-toolbar
                            class="mb-2"
                            color="indigo darken-5"
                            dark
                            flat
                          >
                            <v-toolbar-title
                              ><h1>Picked rooms</h1></v-toolbar-title
                            >
                          </v-toolbar>
                        </template>
                        <template v-slot:item="props">
                          <v-flex xs12 sm6 md4 lg3>
                            <v-card>
                              <v-card-title
                                ><h4>{{ props.item.numberOfBeds }}</h4>
                                <v-icon large>
                                  airline_seat_individual_suite</v-icon
                                ></v-card-title
                              >
                              <v-divider></v-divider>
                              <v-list dense>
                                <v-list-tile>
                                  <v-list-tile-content
                                    >Floor:</v-list-tile-content
                                  >
                                  <v-list-tile-content class="align-end">{{
                                    props.item.hotelFloor.level
                                  }}</v-list-tile-content>
                                </v-list-tile>
                                <v-list-tile>
                                  <v-list-tile-content
                                    >Room number:</v-list-tile-content
                                  >
                                  <v-list-tile-content class="align-end">{{
                                    props.item.roomNumber
                                  }}</v-list-tile-content>
                                </v-list-tile>
                              </v-list>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="error"
                                  @click="removeRoom('', props.item)"
                                  >REMOVE
                                  <v-icon right>clear</v-icon>
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-flex>
                        </template>
                        <template v-slot:footer>
                          <v-toolbar
                            class="mt-2"
                            color="indigo"
                            dark
                            dense
                            flat
                          >
                            <v-toolbar-title class="subheading"
                              ><h1>
                                Total price {{ totalPrice }} EUR for night
                              </h1></v-toolbar-title
                            >
                          </v-toolbar>
                        </template>
                      </v-data-iterator>
                    </v-container>
                  </template>
                </v-flex>
              </v-layout>

              <v-btn flat @click.native="step = 1">Back</v-btn>
              <v-btn color="primary" @click.native="continueTo(3)"
                >Continue</v-btn
              >
            </v-stepper-content>

            <v-stepper-content step="3">
              <v-subheader
                ><h3>
                  Choose additional service that you want to use.(None, one or
                  more)
                </h3></v-subheader
              >
              <v-data-table
                v-if="myHotel != null"
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
                      :class="[
                        'column sortable',
                        pagination2.descending ? 'desc' : 'asc',
                        header.value === pagination2.sortBy ? 'active' : ''
                      ]"
                      @click="changeSort(header.value)"
                    >
                      <v-icon small>arrow_upward</v-icon>
                      {{ header.text }}
                    </th>
                  </tr>
                </template>
                <template v-slot:items="props">
                  <tr
                    :active="props.selected"
                    @click="props.selected = !props.selected"
                    v-if="props.item.type == 'AdditionalService'"
                  >
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
              </v-data-table>
              <div class="text-xs-center pt-2">
                <h3>Total price: {{ additionalPrice }}</h3>
              </div>

              <v-btn flat @click.native="step = 2">Back</v-btn>
              <v-btn color="primary" @click.native="continueTo(4)"
                >Continue</v-btn
              >
            </v-stepper-content>

            <v-stepper-content step="4">
              <!-- <img src="@/assets/moskva.jpg"> -->
              <h3 class="headline mb-0">Confirm reservation</h3>

              <v-layout row wrap align-center>
                <v-flex xs6>
                  <v-subheader
                    ><span><h3>Check in date:</h3></span></v-subheader
                  >
                </v-flex>
                <v-flex xs6>
                  <v-subheader
                    ><span
                      ><h3>{{ checkInDate }}</h3></span
                    ></v-subheader
                  >
                </v-flex>
              </v-layout>

              <v-layout row wrap align-center>
                <v-flex xs6>
                  <v-subheader
                    ><span><h3>Check out date:</h3></span></v-subheader
                  >
                </v-flex>
                <v-flex xs6>
                  <v-subheader
                    ><span
                      ><h3>{{ checkOutDate }}</h3></span
                    ></v-subheader
                  >
                </v-flex>
              </v-layout>

              <v-layout row wrap align-center>
                <v-flex xs6>
                  <v-subheader
                    ><span><h3>Number of people:</h3></span></v-subheader
                  >
                </v-flex>
                <v-flex xs6>
                  <v-subheader
                    ><span
                      ><h3>{{ numOfPeople }}</h3></span
                    ></v-subheader
                  >
                </v-flex>
              </v-layout>
              <v-data-table
                :items="selected"
                class="elevation-1"
                hide-actions
                hide-headers
              >
                <template v-slot:header>
                  <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
                    <v-toolbar-title
                      ><h1>Additional services</h1></v-toolbar-title
                    >
                  </v-toolbar>
                </template>
                <template v-slot:items="props">
                  <td class="text-xs-center">{{ props.item.description }}</td>
                  <td class="text-xs-center">{{ props.item.price }}</td>
                </template>

                <!-- </template> -->
              </v-data-table>

              <v-container fluid grid-list-md v-if="pickedRooms.length !== 0">
                <v-data-iterator
                  :items="pickedRooms"
                  :pagination.sync="pagination"
                  content-tag="v-layout"
                  row
                  wrap
                  hide-actions
                >
                  <template v-slot:header>
                    <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
                      <v-toolbar-title><h1>Picked rooms</h1></v-toolbar-title>
                    </v-toolbar>
                  </template>
                  <template v-slot:item="props">
                    <v-flex xs12 sm6 md4 lg3>
                      <v-card>
                        <v-card-title
                          ><h4>{{ props.item.numberOfBeds }}</h4>
                          <v-icon large>
                            airline_seat_individual_suite</v-icon
                          ></v-card-title
                        >
                        <v-divider></v-divider>
                        <v-list dense>
                          <v-list-tile>
                            <v-list-tile-content>Floor:</v-list-tile-content>
                            <v-list-tile-content class="align-end">{{
                              props.item.hotelFloor.level
                            }}</v-list-tile-content>
                          </v-list-tile>
                          <v-list-tile>
                            <v-list-tile-content
                              >Room number:</v-list-tile-content
                            >
                            <v-list-tile-content class="align-end">{{
                              props.item.roomNumber
                            }}</v-list-tile-content>
                          </v-list-tile>
                          <v-list-tile>
                            <v-list-tile-content
                              >Room price:</v-list-tile-content
                            >
                            <v-list-tile-content class="align-end"
                              >Price</v-list-tile-content
                            >
                          </v-list-tile>
                        </v-list>
                      </v-card>
                    </v-flex>
                  </template>

                  <template v-slot:footer>
                    <v-toolbar class="mt-2" color="indigo" dark dense flat>
                      <v-toolbar-title class="subheading"
                        ><h1>
                          Total price {{ totalWithAdditional }} EUR for night
                        </h1></v-toolbar-title
                      >
                    </v-toolbar>
                  </template>
                </v-data-iterator>
              </v-container>

              <v-btn flat @click.native="step = 3">Back</v-btn>
              <v-btn flat color="success" @click="makeReservation"
                >MAKE RESERVATION</v-btn
              >
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>

        <v-dialog v-model="reserveRoom" max-width="500px">
          <reserve-room
            v-bind:room="this.pickedRoom"
            v-bind:picked="alreadyPicked"
            v-on:remove="removeRoom($event)"
            v-on:book="closeReserveRoom($event)"
            v-on:cancel="reserveRoom = false"
          >
          </reserve-room>
        </v-dialog>
      </v-container>

      <v-snackbar
        v-model="snackbar.show"
        :timeout="5000"
        :color="snackbar.color"
        :top="true"
      >
        {{ snackbar.msg }}
        <v-btn dark flat @click="snackbar.show = false">
          Close
        </v-btn>
      </v-snackbar>
    </v-card>
  </div>
</template>

<script>
import Hotel from "@/models/Hotel";
import store from "@/store";

import ReserveRoom from "@/components/HotelReservation/ReserveRoom.vue";
import SearchHotelParameters from "@/models/SearchHotelParameters.js";
import HotelReservation from "@/models/HotelReservation";
import Room from "@/models/Room";

import HotelController from "@/controllers/hotels.controller";
import { constants } from "fs";

export default {
  name: "ReservationForm",
  components: {
    "reserve-room": ReserveRoom
  },
  data: () => ({
    hotel: new Hotel(),

    menuCheckInDate: false,
    menuCheckOutDate: false,
    searchHotelParams: null,

    checkInDate: null,
    checkOutDate: null,
    numOfPeople: 0,
    totalPrice: 0,
    roomPrice: 0,

    step: 1,
    reserveRoom: false,

    pickedRooms: [],
    pickedRoom: new Room(),
    notAvailableRooms: [],

    enough: false,
    alreadyPicked: false,

    pagination: {
      rowsPerPage: 6
    },

    headers: [
      {
        text: "Floor",
        align: "center",
        value: "hotelFloor.level"
      },
      {
        text: "Room number",
        align: "center",
        value: "roomNumber"
      },
      { text: "Beds", value: "numberOfBeds", align: "center" },
      { text: "Price", value: "price", align: "center" }
    ],

    pagination2: {
      sortBy: "price"
    },
    selected: [],

    totalWithAdditional: 0,
    priceListHeaders: [
      {
        text: "Description",
        align: "center",
        sortable: false,
        value: "description"
      },
      { text: "Price", align: "center", sortable: true, value: "price" }
    ],

    hotelReservation: new HotelReservation(),

    snackbar: {
      show: false,
      color: "",
      msg: ""
    }
  }),
  watch: {
    checkInDate: function() {
      this.resetRooms();
    },
    checkOutDate: function() {
      this.resetRooms();
    },
    numOfPeople: function() {
      this.resetRooms();
    }
  },
  computed: {
    myHotel: function() {
      this.hotel = store.getters.newHotel;
      if (store.getters.newHotel != null) {
        this.hotel.floors.sort(function(a, b) {
          return a.level - b.level;
        });
        this.hotel.floors.forEach(floor => {
          floor.roomsOnFloor.sort(function(r1, r2) {
            return r1.roomNumber - r2.roomNumber;
          });
        });
      }
      return this.hotel;
    },
    searchParams: function() {
      this.searchHotelParams = store.getters.searchHotelParams;
      if (this.searchHotelParams == null) {
        this.searchHotelParams = new SearchHotelParameters();
      }
      return this.searchHotelParams;
    },
    additionalPrice: function() {
      var result = this.totalPrice;
      var str1 = this.totalPrice.toString();

      this.selected.forEach(service => {
        result += service.price;
        str1 += " + " + service.price.toString();
      });
      if (this.selected.length != 0) {
        str1 += " = " + result.toString();
      }
      str1 += " EUR";
      this.totalWithAdditional = result;
      return str1;
    }
  },
  created() {
    if (this.searchParams.checkInDate == null) {
      this.step = 1;
      return true;
    } else {
      this.checkInDate = this.searchParams.checkInDate;
      this.checkOutDate = this.searchParams.checkOutDate;
      this.numOfPeople = this.searchParams.numOfPeople;
      this.checkDates();
      this.step = 2;
      return true;
    }
  },
  methods: {
    pickRoom(room, floor) {
      var found = false;
      this.pickedRooms.forEach(pickRoom => {
        if (pickRoom.id == room.id) {
          found = true;
          this.alreadyPicked = true;
        }
      });
      if (!found) {
        this.alreadyPicked = false;
      }

      this.pickedRoom = JSON.parse(JSON.stringify(room));
      this.pickedRoom.hotelFloor = JSON.parse(JSON.stringify(floor));
      this.pickedRoom.hotelFloor.roomsOnFloor = [];
      this.reserveRoom = true;
    },
    closeReserveRoom(data) {
      this.reserveRoom = false;
      this.addRoom();
      this.showSnackbar(data);
    },
    removeRoom(data, room) {
      if (!(typeof room === "undefined")) {
        this.pickedRoom = room;
      }
      var idx = 0;
      var numOfPlaces = 0;
      this.pickedRooms.forEach(element => {
        if (element.id == this.pickedRoom.id) {
          idx = this.pickedRooms.indexOf(element);
          this.setRoomPrice(this.pickedRoom.numberOfBeds);
          this.totalPrice = this.totalPrice - this.roomPrice;
        } else {
          numOfPlaces += element.numberOfBeds;
        }
      });

      this.pickedRooms.splice(idx, 1);
      this.reserveRoom = false;

      if (numOfPlaces < this.numOfPeople) {
        this.enough = false;
      }

      this.showSnackbar({ msg: "Room is removed", color: "success" });
    },
    checkDisabled(room) {
      if (this.enough) {
        var found = true;
        this.pickedRooms.forEach(pickRoom => {
          if (pickRoom.id == room.id) {
            found = false;
          }
        });
        return found;
      } else {
        return false;
      }
    },
    getRooms(floor) {
      var availableRooms = [];
      floor.roomsOnFloor.forEach(room => {
        if (this.notAvailableRooms.indexOf(room) == -1) {
          availableRooms.push(room);
        }
      });
      return availableRooms;
    },
    addRoom() {
      var exists = false;
      if (this.pickedRooms.length != 0) {
        this.pickedRooms.forEach(room => {
          if (
            room.hotelFloor.level == this.pickedRoom.hotelFloor.level &&
            room.roomNumber == this.pickedRoom.roomNumber
          ) {
            exists = true;
          }
        });
        if (!exists) {
          this.pickedRooms.push(this.pickedRoom);
          this.setRoomPrice(this.pickedRoom.numberOfBeds);
          this.totalPrice += this.roomPrice;
        }
      } else {
        this.pickedRooms.push(this.pickedRoom);
        this.setRoomPrice(this.pickedRoom.numberOfBeds);
        this.totalPrice += this.roomPrice;
      }

      var sumOfPlaces = 0;
      this.pickedRooms.forEach(pickRoom => {
        sumOfPlaces += pickRoom.numberOfBeds;
      });

      if (sumOfPlaces >= this.numOfPeople) {
        this.enough = true;
      }
    },
    setRoomPrice(number) {
      var hotel = this.myHotel;
      if (hotel != null) {
        if (number == 1) {
          hotel.priceList.forEach(element => {
            if (element.type == "OneBed") {
              this.roomPrice = element.price;
            }
          });
        } else if (number == 2) {
          hotel.priceList.forEach(element => {
            if (element.type == "TwoBeds") {
              this.roomPrice = element.price;
            }
          });
        } else if (number == 3) {
          hotel.priceList.forEach(element => {
            if (element.type == "ThreeBeds") {
              this.roomPrice = element.price;
            }
          });
        } else if (number == 4) {
          hotel.priceList.forEach(element => {
            if (element.type == "FourBeds") {
              this.roomPrice = element.price;
            }
          });
        } else {
          hotel.priceList.forEach(element => {
            if (element.type == "FiveBeds") {
              this.roomPrice = element.price;
            }
          });
        }
      }
    },
    continueTo(number) {
      if (number == 2) {
        // if (this.$refs.form.validate()) {
        var startDate = new Date(this.checkInDate);
        var endDate = new Date(this.checkOutDate);
        var today = new Date();

        if (startDate <= today) {
          this.showSnackbar({
            msg: "Check in date can not be before today.",
            color: "error"
          });
          return;
        } else if (endDate <= startDate) {
          this.showSnackbar({
            msg: "End date must be after start date.",
            color: "error"
          });
          return;
        }

        if (
          this.checkInDate != null &&
          this.checkOutDate != null &&
          this.numOfPeople != 0
        ) {
          this.checkDates();
          this.step = 2;
        }
        // }
      } else if (number == 3) {
        if (this.enough) {
          this.step = 3;
        } else {
          this.showSnackbar({
            msg: "You dont have enough beds.",
            color: "error"
          });
        }
      } else if (number == 4) {
        this.step = 4;
      }
    },
    getMaxRooms(number) {
      var numOfRooms = 0;
      if (this.myHotel != null) {
        this.myHotel.floors.forEach(floor => {
          floor.roomsOnFloor.forEach(room => {
            if (room.numberOfBeds == number) {
              if (room.roomTaken.length == 0) {
                numOfRooms++;
              }
            }
          });
        });
      }
      return numOfRooms;
    },
    checkDates() {
      var startDate = new Date(this.checkInDate);
      var endDate = new Date(this.checkOutDate);

      if (this.myHotel != null) {
        this.myHotel.floors.forEach(floor => {
          floor.roomsOnFloor.forEach(room => {
            room.roomTaken.forEach(takenDate => {
              var startTakenDate = new Date(takenDate.startDate);
              var endTakenDate = new Date(takenDate.endDate);

              if (!(endDate < startTakenDate || startDate > endTakenDate)) {
                if (this.notAvailableRooms.indexOf(room) == -1) {
                  this.notAvailableRooms.push(room);
                }
              }
            });

            room.roomDiscounts.forEach(rd => {
              var startDiscountDate = new Date(rd.startDate);
              var endDiscountDate = new Date(rd.endDate);

              if (
                !(endDate < startDiscountDate || startDate > endDiscountDate)
              ) {
                if (this.notAvailableRooms.indexOf(room) == -1) {
                  this.notAvailableRooms.push(room);
                }
              }
            });
          });
        });
      }
    },
    resetRooms() {
      this.pickedRooms = [];
      this.pickedRoom = new Room();
      this.enough = false;
      this.alreadyPicked = false;
      this.notAvailableRooms = [];
    },
    isEnough() {
      return this.enough;
    },
    showSnackbar(obj) {
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    },
    toggleAll() {
      if (this.selected.length) this.selected = [];
      else this.selected = this.myHotel.priceList.slice();
    },
    changeSort(column) {
      if (this.pagination2.sortBy === column) {
        this.pagination2.descending = !this.pagination2.descending;
      } else {
        this.pagination2.sortBy = column;
        this.pagination2.descending = false;
      }
    },
    reset() {
      this.checkInDate = null;
      this.checkOutDate = null;
      this.numOfPeople = 0;
      this.searchHotelParams = null;
      this.totalPrice = 0;
      this.roomPrice = 0;
      this.reserveRoom = false;
      this.pickedRooms = [];
      this.pickedRoom = new Room();
      this.notAvailableRooms = [];
      this.enough = false;
      this.alreadyPicked = false;
      this.selected = [];
      this.totalWithAdditional = 0;
      this.hotelReservation = new HotelReservation();
      this.step = 1;
    },
    makeReservation() {
      this.hotelReservation.checkInDate = this.checkInDate;
      this.hotelReservation.checkOutDate = this.checkOutDate;
      this.hotelReservation.totalPrice = this.totalWithAdditional;
      this.hotelReservation.numberOfPeople = this.numOfPeople;
      this.hotelReservation.user = store.getters.activeUser.username;
      this.hotelReservation.additionalServices = this.selected;
      this.hotelReservation.rooms = this.pickedRooms;
      var newHotel = this.myHotel;
      newHotel.floors = [];
      newHotel.admins = [];
      this.hotelReservation.hotel = newHotel;

      HotelController.reserve(
        this.$route.params.id,
        this.hotelReservation
      ).then(response => {
        // var reservations = store.getters.hotelReservations;
        // reservations.push(response.data);
        // store.commit('hotelReservations', reservations);
        this.reset();

        if (typeof response.data.rooms == "undefined") {
          this.showSnackbar({
            msg:
              "Sorry, to late! Some of the rooms you chose are already reserved",
            color: "error"
          });
          return;
        }
        this.showSnackbar({
          msg: "Reservation is successfull",
          color: "success"
        });

        var currentHotel = this.myHotel;

        if (typeof currentHotel != "undefined") {
          currentHotel.floors.forEach(floor => {
            floor.roomsOnFloor.forEach(room => {
              response.data.rooms.forEach(responseRoom => {
                if (room.id == responseRoom.id) {
                  room.roomTaken = responseRoom.roomTaken.slice();
                }
              });
            });
          });

          store.commit("newHotel", currentHotel);
        }
      });
    }
  }
};
</script>
