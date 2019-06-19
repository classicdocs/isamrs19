<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot="header"><h1>Overview airline companies</h1></div>
        <airline-overview-form></airline-overview-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>
<script>
import AirlineOverviewForm from "@/components/AirlineOverviewForm.vue";
export default {
  name: "AirlineOverview",
  components: {
    "airline-overview-form": AirlineOverviewForm
  },
  data: () => ({
    expansion: [true]
  }),
  methods: {}
};
</script>
<style>
#containter {
  margin: 0px;
}
</style>
