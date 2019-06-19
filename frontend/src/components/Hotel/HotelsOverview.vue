<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot="header"><h1>Overview hotels</h1></div>
        <hotels-overview-form></hotels-overview-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>
<script>
import HotelsOverviewForm from "@/components/HotelsOverviewForm.vue";
export default {
  name: "HotelsOverview",
  components: {
    "hotels-overview-form": HotelsOverviewForm
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
