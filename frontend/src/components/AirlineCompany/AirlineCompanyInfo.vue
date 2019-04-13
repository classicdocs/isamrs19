<template>
  <div>
    <v-card>
      <v-card-title><h1>{{airlineCompany.name}}</h1></v-card-title>
      <v-card-text>
        <div><h3>{{airlineCompany.address}}</h3></div>
        <div><h3>{{airlineCompany.description}}</h3></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "AirlineCompanyInfo",
  props: ["airlineCompany"],
  data: () => ({
    name: "",
    address: "",
    desctiption: "",
  })
};
</script>

<style>

</style>

