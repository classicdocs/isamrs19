<template>
  <div>
    <v-card class="card">
      <v-form ref="form" v-model="form">
        <v-toolbar flat color="white">
          <v-toolbar-title>List of airline companies</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="primary" dark @click="expand = !expand">
            {{ expand ? "Close" : "Keep" }} other rows
          </v-btn>

          <v-text-field
            v-model="search"
            append-icon="search"
            label="Search airlines"
            single-line
            hide-details
          ></v-text-field>
        </v-toolbar>
        <v-data-table
          :headers="headers"
          :items="airlineCompanies"
          class="elevation-1"
          :expand="expand"
          item-key="name"
        >
          <template v-slot:items="props">
            <tr @click="props.expanded = !props.expanded">
              <td class="text-xs-center">{{ props.item.name }}</td>
              <td class="text-xs-center">{{ props.item.address }}</td>
              <!-- <td class="text-xs-right">{{ props.item.description }}</td> -->
              <td class="text-xs-center">
                <v-rating
                  :readonly="true"
                  v-model="props.item.averageRating"
                  half-increments
                ></v-rating>
              </td>
              <td class="text-xs-center">
                <v-btn @click="goToPage(props.item.id)">
                  {{ props.item.name }} page
                  <v-icon right>flight</v-icon>
                </v-btn>
              </td>
            </tr>
          </template>
          <template v-slot:expand="props">
            <v-card flat>
              <v-card-text
                >Airline company description:
                {{ props.item.description }}</v-card-text
              >
            </v-card>
          </template>
          <template v-slot:no-results>
            <v-alert :value="true" color="error" icon="warning">
              Your search for "{{ search }}" found no results.
            </v-alert>
          </template>
          <template v-slot:no-data>
            <v-alert :value="true" color="error" icon="warning">
              Sorry, nothing to display here :(
            </v-alert>
          </template>
        </v-data-table>
        <v-card-text> </v-card-text>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import AirlineCompanyController from "@/controllers/airline-company.controller.js";

export default {
  name: "AirlineOverviewForm",
  components: {},
  data: () => ({
    search: "",
    expand: false,
    headers: [
      {
        text: "Airline company name",
        align: "center",
        value: "name"
      },
      { text: "Address", value: "address", align: "center" },
      // { text: 'Description', value: 'description' , align: 'center', sortable: false},
      { text: "Average rate", value: "avgRate", align: "center" },
      { text: " ", value: "button", align: "center" }
    ],
    airlineCompanies: [],
    form: true,

    snackbar: {
      show: false,
      color: "",
      msg: ""
    }
  }),
  created() {
    // alert("tragam");
    AirlineCompanyController.findAllAirlines().then(response => {
      response.data.forEach(element => {
        this.airlineCompanies.push(element);
      });
    });
  },
  methods: {
    goToPage(id) {
      this.$router.push("/airline-company/" + id);
    }
  }
};
</script>
<style>
.text-xs-center {
  font-size: xx-large;
}
</style>
