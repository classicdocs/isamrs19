<template>
    <div>
        
        <v-app id="inspire">
            <v-tabs
            centered
            color="#2E7D32"
            dark
            icons-and-text
            >
            <v-tabs-slider color="yellow"></v-tabs-slider>
        
            <v-tab href="#tab-1">
                Home
                <v-icon>home</v-icon>
            </v-tab>
        
            <v-tab href="#tab-2">
                Vehicles
                <i class="material-icons">directions_car</i>
            </v-tab>
        
            <v-tab href="#tab-3">
                Branches
                <i class="material-icons">business</i>
            </v-tab>

            <v-tab href="#tab-4">
                Edit
                <i class="material-icons">border_color</i>
            </v-tab>
        
            <v-tab-item :value="'tab-1'" >
                <v-card flat>
                    <RentACarInfo :rentacar="rentacar"></RentACarInfo>
                </v-card>
            </v-tab-item>

            <v-tab-item :value="'tab-2'" >
                <v-card flat>
                    <RentACarAdminOptions></RentACarAdminOptions>
                </v-card>
            </v-tab-item>

            <v-tab-item :value="'tab-3'" >
                <v-card flat>
                <v-card-text>tab 3</v-card-text>
                </v-card>
            </v-tab-item>

            <v-tab-item :value="'tab-4'" >
                <v-card flat>
                <EditRentacarForm @update-rentacar="updateRentacar"></EditRentacarForm>
                </v-card>
            </v-tab-item>
            </v-tabs>
        </v-app>
        
        
    </div>
</template>

<script>

import RentACarInfo from "@/components/RentACarInfo.vue";
import RentACarAdminOptions from "@/components/RentACarAdminOptions.vue";
import RentACarController from "@/controllers/rentacar.controller.js"
import RentACar from "@/models/RentACar.js"
import EditRentacarForm from "@/components/EditRentacarForm.vue"

export default {
    components: {
        RentACarInfo,
        RentACarAdminOptions,
        EditRentacarForm
    },
    data: () => ({
        id: null,
        rentacar : new RentACar()
    }),
    created() {
        this.getData();
    },
    methods: {
        getData() {
            this.id = this.$route.params.id;

            RentACarController.get(this.id)
                .then((response) => {
                    this.rentacar = response.data;
                })
        },
        updateRentacar(r){
            this.rentacar = r;
        }
    }
}
</script>

<style>

h1 {
    text-align: center;
}

</style>

