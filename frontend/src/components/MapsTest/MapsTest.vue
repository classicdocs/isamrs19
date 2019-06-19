<template>
  <div>
    <gmap-map
      v-bind:center="{ lat: 10, lng: 10 }"
      v-bind:zoom="7"
      style="height: 225px"
    >
      <gmap-marker
        v-bind:key="index"
        v-for="(m, index) in markers"
        v-bind:position="m.position"
        v-bind:clickable="true"
      />
    </gmap-map>
  </div>
</template>

<script>
import Map from "ol/Map.js";
import View from "ol/View.js";
import TileLayer from "ol/layer/Tile.js";
import OSM from "ol/source/OSM";

export default {
  // name: "map-openlayers",
  // mounted() {
  //   let map = new Map({
  //     target: 'mapOL',
  //     layers: [
  //       new TileLayer({source: new OSM()})
  //     ],
  //     view: new View({
  //       center: [0, 0],
  //       zoom: 2
  //     })
  //   })
  // }
  data() {
    return {
      center: {
        lat: 10.0,
        lng: 10.0
      },
      markers: [
        {
          position: { lat: 10.0, lng: 10.0 }
        },
        {
          position: { lat: 11.0, lng: 11.0 }
        }
      ]
    };
  }
};
</script>

<style scoped lang="stylus">
@import "~ol/ol.css"
#mapOL
  height 300px
</style>
