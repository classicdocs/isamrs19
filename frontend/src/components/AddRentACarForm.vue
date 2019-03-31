<template>
  <div>
    <v-card>
      <v-form ref="form" v-model="form" lazy-validation>

        <!-- ====================================ISKOPIRAN HOTEL======================================== -->
          <v-card-title primary-title>
            <span class="headline">Add new hotel <v-icon x-large>hotel</v-icon></span>
          </v-card-title>

          <!-- LABELE -->

          <v-container>
            <v-text-field label="hotel name" :rules="[v => !!v || 'hotel name is required']">
            </v-text-field> 

            <v-layout v-bind="binding">
              <v-flex>
                <v-text-field label="country" :rules="[v => !!v || 'country name is required']">
                </v-text-field> 
              </v-flex>
              <v-flex>
                <v-text-field label="city" :rules="[v => !!v || 'city name is required']">
                </v-text-field> 
              </v-flex>
            </v-layout>

            <v-text-field label="street address" :rules="[v => !!v || 'street address is required']">
            </v-text-field> 

            <v-textarea name="promotionalDescription" label="promotional description" 
             hint="Say something good about hotel services and prices" box>
            </v-textarea>
          </v-container>

          <!-- DUGMAD -->

          <v-card-actions>
            <v-spacer></v-spacer>
              <v-btn v-on:click="validate" color="success">NEXT</v-btn>
              <v-btn v-on:click="reset" color="error">RESET</v-btn>
              <v-btn v-on:click="cancel" color="info">CANCEL</v-btn>
           </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>


export default {
  name: "AddRentACarForm",
  data: () => ({
    form: true,
  }),

  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        HotelContoller.create(this.hotel)
          .then((response) => {
            this.$emit("finished", {msg: "Hotel successfully added", color: "success"})
          })
          .catch((response) => {
            this.$emit("finished", {msg: "Error! Something went wrong...", color: "error"})
          })
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    cancel(){
      this.$emit("cancel")
    }
  }
};
</script>

<style>

</style>

