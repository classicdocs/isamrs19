<template>
  <div>
    <v-layout row justify-center>
    <v-btn color="success" @click="showNewBranchDialog">
        Add a new branch
    </v-btn>
    </v-layout>
    <v-card>
    <v-card-text v-for="branch in branches" v-bind:key="branch"
      ><li>{{ branch }} <v-btn flat color="error" @click="removeBranch(branch)"
      >Remove branch</v-btn></li>
      </v-card-text
    >
    </v-card>
    <v-dialog width="300" v-model="showAddDialog">
      <v-card class="elevation-16 mx-auto">
        <v-form ref="addNewBranchForm" v-model="addNewBranchFormValid" lazy-validation>
        <v-card-text>
          <h2 id="yousure">Add a new branch</h2>
          <v-text-field
            v-model="newBranchName"
            :rules="branch_name_rules"
            label="Branch name*"
            required
          ></v-text-field>
        </v-card-text>
        </v-form>
        <v-divider></v-divider>
        <v-card-actions class="justify-space-between">
          <v-btn flat @click="cancelAddingNewBranch()">Cancel</v-btn>
          <v-btn color="success" flat @click="addNewBranch()">
            Add
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import BranchesController from "@/controllers/branches.controller.js";
import RentACarController from "@/controllers/rentacar.controller.js";
import store from "@/store";

export default {
  name: "BranchesList",
  data: () => ({
    branches: [],
    newBranchName: '',
    showAddDialog: false,
    addNewBranchFormValid: false,
    branch_name_rules: [v => !!v || "Name is required"]
  }),
  created() {
    this.getData();
  },
  methods: {
    removeBranch(branch) {
      let params = {
        branchName: branch
      };
      RentACarController.removeBranch(this.$route.params.id, params)
       .then(response => {
          store.commit("setSnack", {
            msg: "Branch successfully removed.",
            color: "success"
          });
          this.getData();
       });
    },
    cancelAddingNewBranch() {
      this.newBranchName = '';
      this.showAddDialog = false;
    },
    addNewBranch() {
      if(this.$refs.addNewBranchForm.validate()){
        let params = {
          branchName: this.newBranchName
        };
        RentACarController.addBranch(this.$route.params.id, params)
        .then(response => {
          store.commit("setSnack", {
            msg: "Branch successfully added.",
            color: "success"
          });
          this.showAddDialog = false;
          this.getData();
        });
      }
    },
    showNewBranchDialog() {
      this.showAddDialog = true;
    },
    getData() {
      this.branches = [];

      BranchesController.get(this.$route.params.id).then(response => {
        response.data.forEach(element => {
          this.branches.push(element);
        });
      });
    }
  }
};
</script>

<style></style>
