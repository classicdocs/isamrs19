<template>
  <div class="small">
    <p>Chart</p>
    <chart :data="datacollection"></chart>
  </div>
</template>

<script>
import ChartComponentVue from './Chart.component.vue';


export default {
  name: "SoldTickets",
  components: {
      'chart' : ChartComponentVue
  },
  props: ["datacollection"],
}
</script>

<style>
.small {
    max-width: auto;
    max-height: auto;
    margin: 0px 0px 0px 100px;
  }
</style>
