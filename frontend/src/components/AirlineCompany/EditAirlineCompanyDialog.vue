<template>
  <div>
    <v-dialog v-model="editDialog" max-width="500px" persistent>
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on" @click="getProp"
          >Edit information</v-btn
        >
      </template>
      <v-card>
        <v-form ref="form" v-model="form" lazy-validation>
          <v-card-title primary-title>
            <span class="headline">Edit airline company information</span>
          </v-card-title>
          <v-card-text>
            <v-text-field
              label="Name"
              v-model="newAirlineCompany.name"
              :rules="[v => !!v || 'Name is required']"
              @input="changed = true"
            ></v-text-field>
            <v-text-field
              label="Address"
              v-model="newAirlineCompany.address"
              @input="changed = true"
              :rules="[v => !!v || 'Address is required']"
            ></v-text-field>
            <v-textarea
              label="Description"
              auto-grow
              @input="changed = true"
              v-model="newAirlineCompany.description"
            ></v-textarea>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken" flat @click="editDialog = false"
              >Close</v-btn
            >
            <v-btn @click="resetValidation">Reset Validation</v-btn>
            <v-btn @click="reset">Reset Form</v-btn>
            <v-btn
              :disabled="!form || !changed"
              color="success"
              @click="validate"
              >Update</v-btn
            >
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import AirlineCompany from "@/models/AirlineCompany";
import AirlineCompanyContoller from "@/controllers/airline-company.controller";

export default {
  name: "EditAirlineCompanyDialog",
  props: ["airlineCompany"],
  data: () => ({
    editDialog: false,
    form: true,
    changed: false,
    newAirlineCompany: new AirlineCompany()
  }),
  watch: {
    editDialog(val) {
      !val && this.reset();
    }
  },
  methods: {
    getProp() {
      this.newAirlineCompany = JSON.parse(JSON.stringify(this.airlineCompany));
    },
    validate() {
      if (this.$refs.form.validate()) {
        AirlineCompanyContoller.update(
          this.airlineCompany.id,
          this.newAirlineCompany
        )
          .then(response => {
            this.editDialog = false;
            this.$emit("info-update", response.data);
            this.$emit("snack", {
              color: "success",
              msg: "Successfully updated!"
            });
          })
          .catch(error => {
            this.editDialog = false;
            this.$emit("snack", { color: "error", msg: error.response.data });
          });
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    }
  }
};
</script>
