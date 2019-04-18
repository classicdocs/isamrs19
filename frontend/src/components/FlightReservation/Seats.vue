<template>
  <div>
    <v-card 
      class="mb-5"
      color="grey lighten-1"
    >
      <v-card-text>
        <v-container >
          <v-layout row wrap>
            <v-flex lg6 md6 sm12 xs12>
              <v-btn :color="item.color" v-for="(item, index)  in seatsDeparture" :key="index">{{item}}</v-btn>
            </v-flex>
            <v-flex lg6 md6 sm12 xs12>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "Seats",
  props: ['seatsDeparture', 'seatsReturn'],
  mounted() {
    console.log(this.seatsDeparture);
    console.log(this.seatsReturn);
    this.setColor();
  },
  methods: {
    setColor() {
      this.seatsDeparture.forEach(s => {
        if (s.taken === false)
          s["color"] = "error";
        else
          s["color"] = "success";
      });
    }
  }
}
</script>

<style>

</style>
