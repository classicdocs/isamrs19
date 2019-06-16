<template>
    <div id="info">
        <v-layout id="lay">
            <v-card-text id="heading"><h2> {{ rentacar.name }} </h2></v-card-text>
            <v-container id="rate">
            <v-rating :readonly="true" id="stars" v-model="rentacar.rating" x-large half-increments></v-rating>
            </v-container>
        </v-layout>
        <v-card-text> {{ rentacar.address }} </v-card-text>
        <v-card-text> {{ rentacar.promotionalDescription }} </v-card-text>
    </div>
</template>

<script>

import store from "@/store";

export default {
    name: "RentACarInfo",
    props: ["rentacar"],
    data: () => ({
        name:"",
        address: "",
        promotionalDescription: "",
    }),
}
</script>

<style>

#heading {
    max-width: 70%;
}

#stars {
    padding-top: 2%;
}

#rate {
    max-width: 400px;
    min-width: 400px;
}

#lay {
    max-height: 100px;
}

</style>

