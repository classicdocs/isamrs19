<template>
    <div class="options">
        <b-button v-b-toggle.collapse-1 variant="primary">Add a vehicle</b-button>
        <b-button v-b-toggle.collapse-2 variant="primary">Change a vehicle</b-button>
        <b-button v-b-toggle.collapse-3 variant="primary">Remove a vehicle</b-button>
        <b-collapse id="collapse-1" class="mt-2">
            <b-card>
                <AddVehicleForm></AddVehicleForm>
            </b-card>
        </b-collapse>
        <b-collapse id="collapse-2" class="mt-2">
            <b-card>
                <ChangeVehicleForm></ChangeVehicleForm>
            </b-card>
        </b-collapse>
        <b-collapse id="collapse-3" class="mt-2">
            <b-card>
                <RemoveVehicleForm></RemoveVehicleForm>
            </b-card>
        </b-collapse>
    </div>
</template>

<script>

import AddVehicleForm from "@/components/AddVehicleForm.vue"
import ChangeVehicleForm from "@/components/ChangeVehicleForm.vue";
import RemoveVehicleForm from "@/components/RemoveVehicleForm.vue";

export default {
    name: "RentACarAdminOptions",
    components: {
        AddVehicleForm,
        ChangeVehicleForm,
        RemoveVehicleForm
    }
}
</script>

<style>
.options {
  width: 35%;
  padding: 15px;
  margin: 25px;
  text-align: center;
  margin-left: 33%;
}

.btn {
    margin: 5px;
}
</style>
