<template>
  <div>
    <v-card max-width="auto">
      <v-form ref="form" v-model="form" lazy-validation>
        <v-card-title primary-title>
          <span class="headline">
            <h2 style="vertical-align:middle">
              Room details <v-icon x-large>weekend</v-icon>
            </h2>
            <br />
            <v-subheader
              ><h3>
                Floor {{ room.hotelFloor.level }}| Number {{ room.roomNumber }}|
                Beds {{ room.numberOfBeds }}
              </h3>
              <v-rating
                id="roomStars"
                :readonly="true"
                v-model="room.average_rating"
                half-increments
              ></v-rating>
            </v-subheader>
          </span>
        </v-card-title>

        <v-card-text>
          <v-layout row wrap align-center>
            <v-flex xs6>
              <v-subheader
                ><span><h3>Room image:</h3></span></v-subheader
              >
            </v-flex>
            <v-flex xs6>
              <img
                id="pic"
                src="@/assets/oneBed.jpg"
                v-if="room.numberOfBeds == 1"
              />
              <img
                id="pic"
                src="@/assets/twoBeds.jpg"
                v-if="room.numberOfBeds == 2"
              />
              <img
                id="pic"
                src="@/assets/threeBeds.jpg"
                v-if="room.numberOfBeds == 3"
              />
              <img
                id="pic"
                src="@/assets/fourBeds.jpg"
                v-if="room.numberOfBeds == 4"
              />
              <img
                id="pic"
                src="@/assets/fiveBeds.jpg"
                v-if="room.numberOfBeds == 5"
              />
            </v-flex>
            <v-flex xs6>
              <v-subheader
                ><span v-if="room.roomTaken.length > 0"
                  ><h3>Dates when room is reserved:</h3></span
                ></v-subheader
              >
            </v-flex>
            <v-flex xs6>
              <v-list>
                <v-list-tile
                  v-for="(item, index) in room.roomTaken"
                  :key="index"
                >
                  <v-list-tile-content>
                    <v-list-tile-title
                      v-text="getDates(item)"
                    ></v-list-tile-title>
                  </v-list-tile-content>
                </v-list-tile>
              </v-list>
            </v-flex>
            <v-spacer></v-spacer>

            <v-flex xs6>
              <v-subheader
                ><span v-if="room.specialPrices.length > 0"
                  ><h3>Dates when room has special prices:</h3></span
                ></v-subheader
              >
            </v-flex>
            <v-flex xs6>
              <v-list>
                <v-list-tile
                  two-line
                  v-for="(item, index) in room.specialPrices"
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
          </v-layout>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken" flat @click="cancel">Cancel</v-btn>
          <v-btn color="success" @click="bookRoom" v-if="!picked"
            >Book<v-icon right>card_travel</v-icon></v-btn
          >
          <v-btn color="error" @click="removeRoom" v-if="picked"
            >Remove<v-icon right>clear</v-icon></v-btn
          >
        </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import Hotel from "@/models/Hotel";
import { returnStatement } from "@babel/types";

export default {
  name: "ReserveRoom",
  props: ["room", "picked"],

  data: () => ({
    form: true,
    datesWhenTaken: []
  }),
  methods: {
    getDates(roomTaken) {
      return roomTaken.startDate + "-" + roomTaken.endDate + "\n";
    },
    bookRoom() {
      this.$emit("book", { msg: "Room successfully booked", color: "success" });
    },
    removeRoom() {
      this.$emit("remove", { msg: "Room is removed", color: "success" }, null);
    },
    cancel() {
      this.$emit("cancel");
    }
  },
  watch: {
    room: function() {
      this.room.roomTaken.forEach(element => {
        this.datesWhenTaken.push(element.startDate + " / " + element.endDate);
      });
    }
  }
};
</script>

<style>
.divider {
  border-width: 3px;
  border-color: dodgerblue;
}

#roomStars {
  padding-left: 35px;
}
</style>
