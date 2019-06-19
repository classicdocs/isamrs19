<template>
  <div>
    <v-expansion-panel v-model="expansion" expand>
      <v-expansion-panel-content>
        <div slot="header"><h1>Overview rent-a-car</h1></div>
        <rentacar-overview-form></rentacar-overview-form>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </div>
</template>
<script>
import RentACarOverviewForm from "@/components/RentACarOverviewForm.vue";
export default {
  name: "RentACarOverview",
  components: {
    "rentacar-overview-form": RentACarOverviewForm
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
