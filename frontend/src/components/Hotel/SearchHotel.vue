<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot='header'><h1>Search hotels</h1></div>
        <search-hotel-form v-on:search-result="showSearchResult($event)"></search-hotel-form>
      </v-expansion-panel-content>
    </v-expansion-panel>


    <!--  -->
    
    <template>
      <v-container fluid grid-list-md>
        <v-data-iterator
          :items="listOfHotels"
          :pagination.sync="pagination"
          content-tag="v-layout"
          row
          wrap
          v-if="listOfHotels.length !== 0"
        >
          <template v-slot:item="props">
            <v-flex
              xs12
              sm6
              md4
              lg3
            >
              <v-card>
                <v-card-title><h4>{{ props.item.name }}</h4></v-card-title>
                <v-divider></v-divider>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>Description:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.description }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Location:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.destination.name }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Address:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.address }}</v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-card>
            </v-flex>
          </template>
        </v-data-iterator>
      </v-container>
    </template>
    <!--  -->

    <!-- <v-container grid-list-xl id="container" fluid>
      <v-layout row wrap >
        <v-flex lg8 md8 sm12 xs12 v-if="listOfHotels.length !== 0">
          <search-result v-for="(hotel, index) in listOfHotels"
            :key="index" 
            v-bind:data="hotel" 
            >
          </search-result>
        </v-flex>
        <v-flex v-else-if="listOfHotels.length == 0" lg8 md8 sm6 xs12>
          <h1>There are no hotels with rooms that can satisfy this parameters.</h1>
        </v-flex>
      </v-layout>
    </v-container> -->
  </div>
</template>
<script>

import SearchHotelForm from "@/components/Hotel/SearchHotelForm.vue";
import SearchHotelResult from "@/components/Hotel/SearchHotelResult.vue";

export default {
  name: "SearchHotel",
  components: {
    'search-hotel-form': SearchHotelForm,
    'search-hotel-result': SearchHotelResult
  },
  data:() => ({
    expansion: [true],
    listOfHotels: [],
    pagination: {
      rowsPerPage: 4
    },
  }),
  methods: {
    showSearchResult(data){
      console.log(data);
      this.listOfHotels = data;
    }
  },
}
</script>
<style>

#containter {
  margin: 0px;
}
</style>

