<template>
  <div>
    <v-dialog
    v-model="dialog"
    persistent
  >
  <template v-slot:activator="{ on }">
    <v-btn color="primary" dark v-on="on">Reports</v-btn>
  </template>
  <v-card>
    <v-card-title primary-title>
     <span class="headline">Reports</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-layout row wrap >
          <v-flex lg2 md2 sm12 xs12>
            <v-select
              :items="years"
              v-model="year"
              @change="change"
              label="Year"
            ></v-select>
            <v-select
              :items="months"
              v-model="month"
              @change="change"
              label="Month"
            ></v-select>
            <v-btn flat @click="clearMonth">Clear month</v-btn>
            
          </v-flex>
          <v-flex lg8 md8 sm12 xs12>
            <chart v-if="loaded" :data="datacollection"></chart>
          </v-flex>
        </v-layout>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken" flat @click="dialog = false">Close</v-btn> 
    </v-card-actions>
  </v-card>
    
  </v-dialog>
  </div>
</template>

<script>
import CertainPeriodVue from './Reports/CertainPeriod.vue';
import SoldTicketsVue from './Reports/SoldTickets.vue';
import AirlineCompanyController from "@/controllers/airline-company.controller";
import ChartComponentVue from './Reports/Chart.component.vue';
export default {
  name: "Reports",
  components: {
    'certain-period' : CertainPeriodVue,
    'sold-tickets': SoldTicketsVue,
    'chart' : ChartComponentVue
  },
  data:() => ({
    dialog: false,
    years: ["2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"],
    months: ["January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"],
    year: "2019",
    month: "",
    datacollection: { labels: [],
      datasets: [
        {
          label: 'Number of sold tickets',
          backgroundColor: '#f87979',
          hoverBackgroundColor: '#f87979',
          borderWidth: 5,
          data: []
        },
        {
          label: 'Income of sold tickets',
          backgroundColor: 'blue',
          hoverBackgroundColor: 'blue',
          borderWidth: 5,
          data: []
        }
      ],
      
    } ,
    dataSoldTickets: [],
    dataIncome: [],
    loaded: false,
    
  }),
  created() {
    this.getData();
  },
  methods: {
    clearMonth() {
      this.month = "";
      this.getData();
    },
    getData() {
      this.loaded = false;
      let params = {
      'year': this.year,
      'month': this.month
      }
      AirlineCompanyController.getReports(this.$route.params.id, params)
        .then((response) => {
          console.log(response.data);
          this.dataSoldTickets = response.data.soldTickets;
          this.dataIncome = response.data.income;
          this.fillData();
       })
    },
    fillData() {
      this.datacollection.labels = [];
      this.datacollection.datasets[0].data = [];
      this.datacollection.datasets[1].data = [];
      for(var key in this.dataSoldTickets) {
        this.datacollection.labels.push(key);
        this.datacollection.datasets[0].data.push(this.dataSoldTickets[key]);
      }

      for(var key in this.dataIncome) {
        this.datacollection.datasets[1].data.push(this.dataIncome[key]);
      }

      console.log(this.datacollection);
      this.loaded = true;
    },
    change() {
      this.getData();
    }
  }
  
}
</script>

<style>

</style>
