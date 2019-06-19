<template>
  <div>
    <v-dialog v-model="pricelistForm" max-width="auto" persistent>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Define pricelist</v-btn>
      </template>
      <v-card>
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-text>
            <v-container fluid grid-list-md v-if="hotel !== null">
              <v-data-iterator
                :items="hotel.priceList"
                :pagination.sync="pagination"
                content-tag="v-layout"
                hide-actions
                row
                wrap
              >
                <template v-slot:header>
                  <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
                    <v-toolbar-title v-if="hotel !== null"
                      ><h1>
                        Defined prices for hotel {{ hotel.name }}:
                      </h1></v-toolbar-title
                    >
                    <v-spacer></v-spacer>
                    <v-btn color="success" @click="openAddNewPrice"
                      >ADD NEW PRICE <v-icon right>add</v-icon></v-btn
                    >
                  </v-toolbar>
                </template>

                <template v-slot:item="props">
                  <v-flex xs12 sm6 md4 lg3>
                    <v-card>
                      <v-card-title
                        ><h4>{{ props.item.type }}</h4></v-card-title
                      >
                      <v-divider></v-divider>
                      <v-list dense>
                        <v-list-tile>
                          <v-list-tile-content
                            >Description:</v-list-tile-content
                          >
                          <v-list-tile-content class="align-end"
                            ><h2>
                              {{ props.item.description }}
                            </h2></v-list-tile-content
                          >
                        </v-list-tile>
                        <v-list-tile>
                          <v-list-tile-content>Price:</v-list-tile-content>
                          <v-list-tile-content class="align-end"
                            ><h2>{{ props.item.price }}</h2>
                            eur for the night</v-list-tile-content
                          >
                        </v-list-tile>
                      </v-list>
                      <v-card-actions>
                        <v-btn color="success" @click="editPrice(props.item)"
                          >EDIT
                          <v-icon right>create</v-icon>
                        </v-btn>
                        <v-spacer></v-spacer>
                        <v-btn color="error" @click="deletePrice(props.item)"
                          >DELETE
                          <v-icon right>clear</v-icon>
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
            <v-btn color="blue darken" flat @click="closeForm">Close</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-dialog v-model="addNewPrice" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Offer details</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout row wrap align-center>
              <v-flex xs6 v-if="!isEdditing">
                <v-subheader>Type of service</v-subheader>
              </v-flex>
              <v-flex xs6 v-if="!isEdditing">
                <v-select
                  :items="getFreeTypes()"
                  label="type"
                  v-model="type"
                  required
                  :rules="typeRules"
                ></v-select>
              </v-flex>
              <v-flex xs6 v-if="isEdditing">
                <v-subheader>You are editing :</v-subheader>
              </v-flex>
              <v-flex xs6 v-if="isEdditing">
                <v-subheader>{{ type }} offer</v-subheader>
              </v-flex>

              <v-flex xs6>
                <v-subheader>Describe this service</v-subheader>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  v-model="description"
                  label="description"
                  :rules="[v => !!v || 'Description is required']"
                ></v-text-field>
              </v-flex>

              <v-flex xs6>
                <v-subheader>Choose the price for this service</v-subheader>
              </v-flex>
              <v-flex xs6>
                <number-input
                  v-model="price"
                  :min="0"
                  inline
                  center
                  controls
                  :rules="[v => !!v || 'Price is required']"
                >
                </number-input>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="closeOffer">Close</v-btn>
          <v-btn color="blue darken-1" flat @click="saveOffer">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="removeOffer" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">DELETE OFFER</span>
        </v-card-title>

        <v-card-text>
          <v-subheader
            >Are you sure you want to delete {{ type }} service?</v-subheader
          >
        </v-card-text>

        <v-card-actions>
          <v-btn color="error" flat @click="deleteOffer">DELETE</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" flat @click="closeDeleteOffer">CANCEL</v-btn>
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
import HotelController from "@/controllers/hotels.controller";

import store from "@/store";
import { returnStatement } from "@babel/types";

export default {
  name: "PriceListOverview",
  data: () => ({
    pricelistForm: false,
    form: true,
    addNewPrice: false,
    removeOffer: false,

    type: null,
    description: null,
    price: null,
    types: [
      "OneBed",
      "TwoBeds",
      "ThreeBeds",
      "FourBeds",
      "FiveBeds",
      "AdditionalService"
    ],
    isEdditing: false,

    pagination: {
      rowsPerPage: 12
    },
    typeRules: [v => !!v || "Service type is required"],
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
    getFreeTypes() {
      var h = this.hotel;
      var freeList = [];
      if (h != null) {
        this.types.forEach(type => {
          var found = false;
          h.priceList.forEach(offer => {
            if (offer.type == type) {
              found = true;
            }
          });
          if (!found) {
            freeList.push(type);
          }
        });
      }
      if (freeList.indexOf("AdditionalService") == -1) {
        freeList.push("AdditionalService");
      }
      return freeList;
    },
    openAddNewPrice() {
      this.addNewPrice = true;
    },
    editPrice(offer) {
      this.type = offer.type;
      this.description = offer.description;
      this.price = offer.price;
      this.isEdditing = true;
      this.addNewPrice = true;
    },
    deleteOffer() {
      var h = this.hotel;
      if (h != null) {
        h.priceList.forEach(offer => {
          if (
            offer.type == this.type &&
            offer.description == this.description &&
            offer.price == this.price
          ) {
            var exists = false;
            h.floors.forEach(floor => {
              floor.roomsOnFloor.forEach(room => {
                switch (room.numberOfBeds) {
                  case 1: {
                    if (offer.type === "OneBed") {
                      exists = true;
                    }
                    break;
                  }
                  case 2: {
                    if (offer.type === "TwoBeds") {
                      exists = true;
                    }
                    break;
                  }
                  case 3: {
                    if (offer.type === "ThreeBeds") {
                      exists = true;
                    }
                    break;
                  }
                  case 4: {
                    if (offer.type === "FourBeds") {
                      exists = true;
                    }
                    break;
                  }
                  case 5: {
                    if (offer.type === "FiveBeds") {
                      exists = true;
                    }
                    break;
                  }
                }
              });
            });

            if (exists) {
              this.showSnackbar({
                msg:
                  "You can not delete offer that is used to define some existing room price.",
                color: "error"
              });
              this.closeDeleteOffer();
              return;
            } else {
              h.priceList.splice(h.priceList.indexOf(offer), 1);
            }

            HotelController.updatePricelist(this.$route.params.id, h.priceList)
              .then(response => {
                var hotelForStore = this.hotel;

                if (hotelForStore !== null) {
                  hotelForStore.priceList = JSON.parse(
                    JSON.stringify(response.data)
                  );
                  store.commit("newHotel", hotelForStore);
                }
                this.showSnackbar({
                  msg: "Offer is successfully deleted",
                  color: "success"
                });
              })
              .catch(response => {
                this.showSnackbar({
                  msg: "Error! Something with update went wrong...",
                  color: "error"
                });
              });
          }
        });
        this.closeDeleteOffer();
      }
    },

    closeDeleteOffer() {
      this.type = null;
      this.description = null;
      this.price = null;
      this.removeOffer = false;
    },

    deletePrice(offer) {
      this.type = offer.type;
      this.description = offer.description;
      this.price = offer.price;

      this.removeOffer = true;
    },
    closeForm() {
      this.pricelistForm = false;
    },
    closeOffer() {
      this.type = null;
      this.description = null;
      this.price = null;
      this.isEdditing = false;
      this.addNewPrice = false;
    },
    saveOffer() {
      if (this.type != null && this.description != null) {
        var h = this.hotel;
        var edit = false;

        h.priceList.forEach(offer => {
          if (offer.type == this.type && this.isEdditing) {
            offer.description = this.description;
            offer.price = this.price;

            edit = true;
          }
        });
        if (!this.isEdditing) {
          var offer = new HotelsOffer();
          offer.type = this.type;
          offer.description = this.description;
          offer.price = this.price;
          h.priceList.push(offer);
        }

        HotelController.updatePricelist(this.$route.params.id, h.priceList)
          .then(response => {
            var hotelForStore = this.hotel;

            if (hotelForStore !== null) {
              hotelForStore.priceList = JSON.parse(
                JSON.stringify(response.data)
              );
              store.commit("newHotel", hotelForStore);
            }
            if (this.isEdditing) {
              this.showSnackbar({
                msg: "Hotels offer is successfully edited",
                color: "success"
              });
            } else {
              this.showSnackbar({
                msg: "Hotels offer is successfully added",
                color: "success"
              });
            }
          })
          .catch(response => {
            this.showSnackbar({
              msg: "Error! Something with update went wrong...",
              color: "error"
            });
          });

        this.closeOffer();
      }
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
