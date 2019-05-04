<template>
    <div>
        <v-card-text v-for="branch in branches" v-bind:key="branch"><li> {{ branch }} </li></v-card-text>
    </div>
</template>

<script>

import BranchesController from "@/controllers/branches.controller.js"

export default {
    name: "BranchesList",
    data: () => ({
        branches: []
    }),
    created() {
        BranchesController.get(this.$route.params.id)
        .then((response) => {
            response.data.forEach(element => {
                this.branches.push(element);
            });
        });
    }
}
</script>

<style>

</style>
