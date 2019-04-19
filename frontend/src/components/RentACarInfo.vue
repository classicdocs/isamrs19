<template>
    <div id="info">
        <v-card-text><h2> {{ rentacar.name }} </h2></v-card-text>

        <v-card-text> {{ rentacar.address }} </v-card-text>
        <v-card-text> {{ rentacar.promotionalDescription }} </v-card-text>
    </div>
</template>

<script>
export default {
    name: "RentACarInfo",
    props: ["rentacar"],
    data: () => ({
        name:"",
        address: "",
        promotionalDescription: ""
    })
}
</script>

<style>

</style>

