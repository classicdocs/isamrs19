<template>
  <div>
    <v-dialog v-model="SpecialPriceForm" max-width="auto" persistent>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add special price</v-btn>
      </template>
      <v-card>
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-title primary-title>
            <span class="headline"
              >Pick a room for which you want to define special price</span
            >
          </v-card-title>
          <v-card-text>
            <v-layout col wrap v-if="hotel != null">
              <v-layout row wrap>
                <v-flex lg10 md10 sm12 xs12>
                  <v-layout row wrap>
                    <v-flex
                      lg12
                      md12
                      sm12
                      xs12
                      v-for="(floor, floorNUM) in hotel.floors"
                      :key="floorNUM"
                    >
                      <v-btn flat>Floor {{ floor.level }}</v-btn>

                      <v-btn
                        small
                        v-for="(room, roomNUM) in floor.roomsOnFloor"
                        :key="roomNUM"
                        @click="pickRoom(room, floor)"
                      >
                        {{ room.roomNumber }}</v-btn
                      >

                      <v-divider
                        v-if="floorNUM < hotel.floors.length"
                        :key="`divider-${floorNUM}`"
                      ></v-divider>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-layout row wrap>
                  <v-flex lg10 md10 sm12 xs12 v-if="selected">
                    <h4>
                      Choosen room is on {{ pickedRoom.hotelFloor.level }}.
                      floor and has room number {{ pickedRoom.roomNumber }}.
                    </h4>
                  </v-flex>
                </v-layout>
              </v-layout>
              <v-layout row wrap>
                <v-flex v-if="selected">
                  <h2 v-if="numberOfBeds == 1">
                    This room has {{ numberOfBeds }} bed.
                  </h2>
                  <h2 v-else>This room has {{ numberOfBeds }} beds.</h2>
                </v-flex>
                <v-flex v-if="selected">
                  <h2>New number of beds in room:</h2>
                  <number-input
                    v-model="pickedRoom.numberOfBeds"
                    :min="1"
                    :max="5"
                    inline
                    center
                    controls
                  ></number-input>
                </v-flex>
              </v-layout>
            </v-layout>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken" flat @click="closeForm">Close</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-dialog v-model="definePrice" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline"
            >Define special price for {{ pickedRoom.roomNumber }}</span
          >
        </v-card-title>

        <v-card-text>
          <v-layout row wrap>
            <v-flex xs6>
              <v-subheader
                ><span v-if="pickedRoom.specialPrices.length > 0"
                  ><h3>Dates when room has special prices:</h3></span
                ></v-subheader
              >
            </v-flex>
            <v-flex xs6>
              <v-list>
                <v-list-tile
                  two-line
                  v-for="(item, index) in pickedRoom.specialPrices"
                  :key="index"
                >
                  <v-list-tile-content>
                    <v-list-tile-title
                      >{{ item.startDate }} -
                      {{ item.endDate }}</v-list-tile-title
                    >
                    <v-list-tile-sub-title
                      >Price: {{ item.price }}</v-list-tile-sub-title
                    >
                  </v-list-tile-content>
                </v-list-tile>
              </v-list>
            </v-flex>

            <v-flex>
              <!-- <v-calendar :attributes='dates'>
                    </v-calendar>
                     -->

              <v-layout col span>
                <v-flex>
                  <v-menu
                    ref="menuStartDate"
                    v-model="menuStartDate"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="newPrice.startDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="newPrice.startDate"
                        label="Start date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Start date is required']"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="newPrice.startDate"
                      no-title
                      scrollable
                    >
                      <v-btn flat color="primary" @click="menuStartDate = false"
                        >Cancel</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="$refs.menuStartDate.save(newPrice.startDate)"
                        >OK</v-btn
                      >
                    </v-date-picker>
                  </v-menu>
                </v-flex>
                <v-flex>
                  <v-menu
                    ref="menuEndDate"
                    v-model="menuEndDate"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="newPrice.endDate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="newPrice.endDate"
                        label="End date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Check-out date is required']"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="newPrice.endDate"
                      no-title
                      scrollable
                    >
                      <v-btn flat color="primary" @click="menuEndDate = false"
                        >Cancel</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        flat
                        color="primary"
                        @click="$refs.menuEndDate.save(newPrice.endDate)"
                        >OK</v-btn
                      >
                    </v-date-picker>
                  </v-menu>
                </v-flex>

                <v-flex></v-flex>
              </v-layout>
            </v-flex>
          </v-layout>
          <v-layout col wrap>
            <v-flex align-center>
              <v-subheader> Price </v-subheader>
              <number-input
                v-model="newPrice.price"
                :min="1"
                inline
                center
                controls
              ></number-input>
            </v-flex>
          </v-layout>
        </v-card-text>

        <v-card-actions>
          <v-btn color="error" @click="closeDefinePrice">CANCEL</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="addPrice">ADD</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

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
  </div>
</template>

<script>
import Hotel from "@/models/Hotel";
import HotelsOffer from "@/models/HotelsOffer";
import Room from "@/models/Room";
import SpecialPrice from "@/models/SpecialPrice";
import HotelController from "@/controllers/hotels.controller";

import store from "@/store";
import { returnStatement } from "@babel/types";

export default {
  name: "AddSpecialPrice",
  data: () => ({
    SpecialPriceForm: false,
    form: false,
    selected: false,
    definePrice: false,

    pickedRoom: new Room(),
    price: 0,
    newPrice: new SpecialPrice(),

    menuStartDate: false,
    menuEndDate: false,

    startDate: null,
    endDate: null,

    snackbar: {
      show: false,
      color: "",
      msg: ""
    }
  }),
  computed: {
    hotel: function() {
      var h = store.getters.newHotel;
      if (h != null) {
        h.floors.forEach(floor => {
          floor.roomsOnFloor.forEach(singleRoom => {
            /*Treba popraviti da radi sa DTO*/
            singleRoom.hotelFloor = null;
          });
        });
      }
      return h;
    }
  },
  methods: {
    addPrice() {
      if (this.$refs.form.validate()) {
        var startDate = new Date(this.newPrice.startDate);
        var endDate = new Date(this.newPrice.endDate);
        var today = new Date();

        if (startDate < today) {
          this.showSnackbar({
            msg:
              "You can only chose date range which start is some day from tomorow.",
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

        HotelController.addSpecialPrice(
          this.$route.params.id,
          this.pickedRoom.id,
          this.newPrice
        )
          .then(response => {
            if (response.data.id != null) {
              // sve je okej
              if (this.hotel != null) {
                var h = this.hotel;
                h.floors.forEach(floor => {
                  floor.roomsOnFloor.forEach(room => {
                    if (room.id == response.data.id) {
                      room.specialPrices = response.data.specialPrices;
                    }
                  });
                });
                store.commit("newHotel", h);
              }
              this.definePrice = false;
              this.showSnackbar({
                msg: "Special price successfully added.",
                color: "success"
              });
            } else {
              // time overlaps
              this.showSnackbar({
                msg:
                  "Your date range overlaps with already defined date ranges. Please pick another one",
                color: "error"
              });
            }
          })
          .catch(response => {
            alert(response.data);
          });
      }
    },
    pickRoom(room, floor) {
      this.pickedRoom = room;
      this.definePrice = true;
    },
    closeDefinePrice() {
      this.definePrice = false;
    },
    closeForm() {
      this.SpecialPriceForm = false;
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
.divider {
  border-width: 7px;
  border-color: dodgerblue;
}
</style>
