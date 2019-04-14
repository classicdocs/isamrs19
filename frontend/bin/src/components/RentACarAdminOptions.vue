<template>
    <div>
        <AddVehicleForm></AddVehicleForm>
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

</style>
