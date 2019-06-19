<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <h2>List of destinations</h2>
      </v-card-title>
      <v-data-table
        :items="destinations"
        hide-headers
        hide-actions
        class="elevation-1"
        style="height: 300px; overflow: scroll;"
      >
        <template v-slot:items="props">
          <td class="text-xs-center">{{ props.item }}</td>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import AirlineCompanyController from "@/controllers/airline-company.controller";
import store from "@/store";

export default {
  name: "ListOfDestinations",
  data: () => ({
    destinations: []
  }),
  mounted() {
    this.getDestinations();
  },
  computed: {
    getNewDestination() {
      return store.getters.newDestination;
    }
  },
  watch: {
    getNewDestination(destination) {
      this.destinations.push(destination.name + " " + destination.zip);
    },
  },
  methods: {
    getDestinations() {
      AirlineCompanyController.getDestinations(this.$route.params.id)
        .then(response => {
          response.data.forEach(element => {
            this.destinations.push(element);
          });
        })
        .catch(error => {
          alert(error.response.data);
        });
    }
  }
};
</script>

<style></style>
