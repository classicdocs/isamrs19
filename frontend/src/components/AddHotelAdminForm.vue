<template>
  <div>
    <v-form ref="form" v-model="form" lazy-validation>
        <v-card-title primary-title>
            <span class="headline">NOW HOTEL ADMIN SHOULD BE REGISTRED <v-icon x-large>admin</v-icon></span>
          </v-card-title>
    </v-form>
  </div>
</template>

<script>

export default {
  name: "AddHotelAdminForm",
  data: () => ({
    form: true,
  }),

  methods: {
    
  }
};
</script>

<style>

</style>

