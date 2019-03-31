<template>
    <div>
        <h1>Rent a car service profile</h1>
        <RentACarAdminOptions></RentACarAdminOptions>
        <RentACarInfo></RentACarInfo>
    </div>
</template>

<script>

import RentACarInfo from "@/components/RentACarInfo.vue";
import RentACarAdminOptions from "@/components/RentACarAdminOptions.vue";

export default {
    components: {
        RentACarInfo,
        RentACarAdminOptions
    }
}
</script>

<style>

h1 {
    text-align: center;
}

</style>

