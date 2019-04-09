<template>
  <div>
    <v-expansion-panel
      v-model="expansion"
			expand
    >
      <v-expansion-panel-content>
        <div slot='header'><h1>Search a flight</h1></div>
        <search-form v-on:search-result="showSearchResult($event)"></search-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
    <v-container grid-list-lg>
      <v-layout row wrap>
        <v-flex lg3>
          <!-- filter -->
        </v-flex>
        <v-flex lg9 v-if="searchResult.length !== 0" id="result">
          <search-result v-for="(item, index) in searchResult"
          :key="index" 
          v-bind:data="item" 
          v-bind:searchParams="searchParams"></search-result>
        </v-flex>
        <v-flex v-else-if="zeroResult" lg9>
          <h3>No result found!</h3>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

import SearchForm from "@/components/Flights/SearchForm.vue";
import SearchResult from "@/components/Flights/SearchResult.vue";

export default {
  name: "SearchFlight",
  components: {
    'search-form': SearchForm,
    'search-result' : SearchResult
  },
  data:() => ({
    expansion: [true],
    resultDialog: true,
    searchResult: [],
    searchParams: null,
    zeroResult: false,
  }),
  methods: {
    showSearchResult(data) {
      this.searchResult = data.data;
      this.searchParams = {
        'seatClass': data.seatClass,
        'passengersNumber': data.passengersNumber
      }
      this.zeroResult = true;
    }
  }
}
</script>
<style>

#result {
  max-height: 700px;
  overflow: scroll;
}
</style>

