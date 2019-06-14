<template>
  <div>
    <v-card>
      <v-card-title><h3 style="color: grey">Hotel: <br></h3><h1> {{hotel.name}}</h1></v-card-title>
      <div><v-rating id="hotelStars" :readonly="true" v-model="hotel.average_rating" half-increments></v-rating></div>

      <v-card-text id="info">
        <div v-if="hotel.destination != null"><h3 style="color: gray;">Destination: <br></h3><h3> {{hotel.destination.name}}</h3></div>
        <div><h3 style="color: gray;">Address: <br></h3><h3> {{hotel.address}}</h3></div>
        <div><h3 style="color: gray;">Description: <br></h3><h3> {{hotel.description}}</h3></div>
        <div><h3 style="color: gray;">Floors: <br></h3><h3> {{hotel.numOfFloors}}</h3></div>
        <div><h3 style="color: gray;">Rooms by floor: <br></h3><h3> {{hotel.roomsByFloor}}</h3></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "HotelServiceInfo",
  props: ["hotel"],
  
};
</script>

<style>
h3 {
  
  display: inline-flex;
}

#hotelStars {
  padding-left: 20px;
}

</style>

