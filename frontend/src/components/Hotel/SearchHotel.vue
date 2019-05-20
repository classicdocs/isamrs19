<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot='header'><h1>Search hotels</h1></div>
        <search-hotel-form v-on:search-result="showSearchResult($event)"></search-hotel-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>
<script>

import SearchHotelForm from "@/components/Hotel/SearchHotelForm.vue";

export default {
  name: "SearchFlight",
  components: {
    'search-hotel-form': SearchHotelForm,
  },
  data:() => ({
    expansion: [true],
    listOfHotels: []
  }),
  methods: {
    showSearchResult(data){
      // listOfHotels = data.data;
    }
  },
}
</script>
<style>

#containter {
  margin: 0px;
}
</style>

