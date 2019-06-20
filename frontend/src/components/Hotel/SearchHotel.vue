<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot="header"><h1>Search hotels</h1></div>
        <search-hotel-form
          v-on:search-result="showSearchResult($event)"
          v-on:search-input-error="showInputError($event)"
        ></search-hotel-form>
      </v-expansion-panel-content>
    </v-expansion-panel>

    <template>
      <v-container fluid grid-list-md v-if="listOfHotels.length !== 0">
        <v-data-iterator
          :items="listOfHotels"
          :pagination.sync="pagination"
          content-tag="v-layout"
          row
          wrap
        >
          <template v-slot:header>
            <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
              <v-toolbar-title
                ><h1>
                  Choose some of the listed hotels to continue reservation
                </h1></v-toolbar-title
              >
            </v-toolbar>
          </template>
          <template v-slot:item="props">
            <v-flex xs12 sm6 md4 lg3>
              <v-card>
                <v-card-title
                  ><h4>{{ props.item.name }}</h4></v-card-title
                >
                <v-divider></v-divider>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>Description:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{
                      getDescription(props.item.description)
                    }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Location:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{
                      props.item.destination.name
                    }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Address:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{
                      props.item.address
                    }}</v-list-tile-content>
                  </v-list-tile>
                </v-list>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="success" @click="goToPage(props.item)"
                    >MAKE RESERVATION
                    <v-icon right>hotel</v-icon>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
          </template>
        </v-data-iterator>
      </v-container>
      <v-container
        grid-list-xl
        id="container"
        fluid
        v-else-if="listOfHotels.length == 0 && searchHapened"
      >
        <v-layout row wrap>
          <v-flex lg8 md8 sm6 xs12>
            <h1>
              There are no hotels with rooms that can satisfy this parameters.
            </h1>
          </v-flex>
        </v-layout>
      </v-container>
    </template>
    <!--  -->

    <!--  -->
  </div>
</template>
<script>
import SearchHotelForm from "@/components/Hotel/SearchHotelForm.vue";
import SearchHotelParameters from "@/models/SearchHotelParameters.js";
import store from "@/store";

export default {
  name: "SearchHotel",
  components: {
    "search-hotel-form": SearchHotelForm
  },
  data: () => ({
    expansion: [true],
    listOfHotels: [],
    searchHapened: false,
    searchParams: new SearchHotelParameters(),

    pagination: {
      rowsPerPage: 4
    }
  }),
  created() {
    store.commit("searchHotelParams", new SearchHotelParameters());
  },
  methods: {
    getDescription(description){
      description = description.replace(/(.{45})..+/, "$1…");
      return description;
    },
    showSearchResult(result) {
      this.listOfHotels = result.data;
      this.searchParams = result.searchHotelParams;

      this.searchHapened = true;
    },
    showInputError(result) {
      store.commit("setSnack", result);
      this.listOfHotels = [];
      this.searchParams = new SearchHotelParameters();
      this.searchHapened = false;
    },
    goToPage(hotel) {
      let user = store.getters.isUser;
      if (!user) {
        store.commit("setSnack", { msg: "To reserve you must first sign up" });
        return;
      }
      store.commit("searchHotelParams", this.searchParams);
      this.$router.push("/hotel-service/" + hotel.id);
    }
  }
};
</script>

<style>
#containter {
  margin: 0px;
}
</style>
