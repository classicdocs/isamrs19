<template>
  <div>
    <v-card>
      <v-card-title><h3 style="color: grey">Hotel: <br></h3><h1> {{hotel.name}}</h1></v-card-title>
      <v-card-text id="info">
        <div><h3 style="color: gray;">Address: <br></h3><h3> {{hotel.address}}</h3></div>
        <div><h3 style="color: gray;">Description: <br></h3><h3> {{hotel.description}}</h3></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "HotelServiceInfo",
  props: ["hotel"],
  data: () => ({
    name: "",
    address: "",
    desctiption: "",
  })
};
</script>

<style>
h3 {
  
  display: inline-flex;
}
</style>

