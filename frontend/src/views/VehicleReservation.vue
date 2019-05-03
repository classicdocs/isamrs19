<template>
    <v-layout>
        <v-card>
            <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
            >
            <v-card-title>
            <span class="headline">Enter vehicle information</span>
            </v-card-title>
            <v-card-text>
            <v-container>
                <v-layout>
                <v-flex>    
                    <v-menu
                    ref="pickupDateMenu"
                    v-model="pickupDateMenu"
                    :close-on-content-click="false"
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="vehicleSearch.pickupDate"
                        label="Pickup date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Pick up date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="vehicleSearch.pickupDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="pickupDateMenu = false">Cancel</v-btn>
                        <v-btn flat color="primary" @click="$refs.pickupDateMenu.save(vehicleSearch.pickupDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="pickupTimeMenu"
                  v-model="pickupTimeMenu"
                  :close-on-content-click="false"
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="vehicleSearch.pickupTime"
                      label="Pickup time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Pickup time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="pickupTimeMenu"
                    v-model="vehicleSearch.pickupTime"
                    full-width
                    @click:minute="$refs.pickupTimeMenu.save(vehicleSearch.pickupTime)"
                  ></v-time-picker>
                </v-menu>
                </v-flex>
                </v-layout>
                <v-layout>
                    <v-flex>
                    <v-menu
                    ref="returnDateMenu"
                    v-model="returnDateMenu"
                    :close-on-content-click="false"
                    full-width
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                        v-model="vehicleSearch.returnDate"
                        label="Return date"
                        prepend-icon="event"
                        readonly
                        required
                        :rules="[v => !!v || 'Return date is required']"
                        v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker v-model="vehicleSearch.returnDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="returnDateMenu = false">Cancel</v-btn>
                        <v-btn flat color="primary" @click="$refs.returnDateMenu.save(vehicleSearch.returnDate)">OK</v-btn>
                    </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex class="time">
                <v-menu
                  ref="returnTimeMenu"
                  v-model="returnTimeMenu"
                  :close-on-content-click="false"
                  full-width
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="vehicleSearch.returnTime"
                      label="Return time"
                      prepend-icon="access_time"
                      readonly
                      :rules="[v => !!v || 'Return time is required']"
                      required
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="returnTimeMenu"
                    v-model="vehicleSearch.returnTime"
                    full-width
                    @click:minute="$refs.returnTimeMenu.save(vehicleSearch.returnTime)"
                  ></v-time-picker>
                </v-menu>
                </v-flex>
                </v-layout>
                <v-layout>
                <v-flex id="tipvozila">
                  <v-select
                      :items="['Hatchback', 'Sedan', 'Station wagon', 'SUV', 'Coupe']"
                      v-model="vehicleSearch.carType"
                      :rules="vehicle_type_rules"
                      label="Vehicle type"
                      required
                  ></v-select>
                </v-flex>
                <v-flex id="brojputnika">
                  <number-input v-model="vehicleSearch.passengers" :min="1" :max="7" inline center controls></number-input>
                </v-flex>
                </v-layout>
            </v-container>
            </v-card-text>
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="validateSearch"> Search</v-btn>
            </v-card-actions>`
            </v-form>
        </v-card>
    </v-layout>
</template>

<script>

import VehicleSearch from '@/models/VehicleSearch.js';

export default {
    name: 'VehicleReservation',
    data: () => ({
        pickupDateMenu : false,
        pickupTimeMenu : false,
        returnDateMenu : false,
        returnTimeMenu : false,
        vehicleSearch : new VehicleSearch(),
    }),
    methods: {
      validateSearch() {
        if(this.$refs.form.validate) {
          this.onSubmit();
        }
      },
      onSubmit() {
        VehicleSearchController.get(this.vehicleSearch);
      }
    },
}
</script>

<style>


#tipvozila {
    max-width: 230px;
}

.time {
    margin-left: 5%;
}

#brojputnika {
    padding-top: 4%;
    margin-left: 7%;
}

</style>
