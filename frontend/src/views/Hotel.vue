<template>
    <div>
        <v-container grid-list-md text-xs-center>
        <v-layout row wrap>
          <v-flex xs12>

            <v-btn color="success" @click="addFormDialog = true">
                Add new hotel
            </v-btn>

            <v-dialog v-model="addFormDialog" max-width="500px">
              <add-hotel-form v-on:operation="showSnackbar($event)" >

              </add-hotel-form>
            </v-dialog>
          </v-flex>
        </v-layout>
      </v-container>

      <v-snackbar
          v-model="snackbar.show"
          :timeout="5000"
          :color="snackbar.color"
          :top="true"
      >
        {{snackbar.msg}}
        <v-btn dark flat @click="snackbar.show = false">
          Close
        </v-btn>
      </v-snackbar>
    </div>   
</template>

<script>

import AddHotelForm from "@/components/AddHotelForm.vue";

export default {
    name: "sys-admin",
    components: {
    'add-hotel-form': AddHotelForm,
  },
  data: () => ({
    id: null,
    addFormDialog: false,
    snackbar: {
      show: false,
      color: "",
      msg: "",
    },
  }),
  beforeMount() {
    this.id = this.$route.params.id;
  },
  methods: {
    showSnackbar(obj) {
      this.addFormDialog = false,
      this.snackbar.color = obj.color;
      this.snackbar.msg = obj.msg;
      this.snackbar.show = true;
    }
  }
};
</script>

