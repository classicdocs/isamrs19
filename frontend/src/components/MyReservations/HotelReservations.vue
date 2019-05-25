<template>
    <div>
        <!-- <v-data-table
        :items="reservations"
        :headers="headers"
        class="elevation-1"
        >
        <template v-slot:items="props">
            <td>{{ props.item.vehicle.vehicleManufacturer}} {{ props.item.vehicle.vehicleModel }} </td>
            <td>{{ props.item.vehicle.vehicleType}} </td>
            <td>{{ props.item.reservedFrom | moment("dddd, MMMM Do YYYY, HH:mm")}} </td>
            <td>{{ props.item.reservedUntil | moment("dddd, MMMM Do YYYY, HH:mm")}} </td>
            <td>{{ props.item.vehicle.numberOfPassengers}} </td>
            <td>{{ props.item.totalPrice }} &euro; </td>
            <td>{{ props.item.rentACar.name  }} </td>
            <td>{{ props.item.pickupLocation }} </td>
            <td>{{ props.item.returnLocation }} </td>
        </template>
        </v-data-table> -->
    </div>
</template>

<script>

import VehicleReservationController from "@/controllers/vehicle.reservation.controller.js";
import store from '@/store';

export default {
    name: "HotelReservations",
    data: () => ({
        reservations: [],
    //     headers: [
    //     {
    //       text: 'Car',
    //       align: 'left',
    //       sortable: false,
    //       value: 'car'
    //     },
    //     { text: 'Car type', value: 'type',sortable: false, },
    //     { text: 'Reservation start', value: 'start',sortable: false, },
    //     { text: 'Reservation end', value: 'end' ,sortable: false, },
    //     { text: 'Passenger spaces', value: 'passengers',sortable: false, },
    //     { text: 'Reservation price', value: 'price' ,sortable: false, },
    //     { text: 'Rent A Car company', value: 'company' ,sortable: false, },
    //     { text: 'Pick up location', value: 'pickup' ,sortable: false, },
    //     { text: 'Return location', value: 'return' ,sortable: false, },
    //   ],
    }),
    // beforeMount() {
    //     VehicleReservationController.get(store.getters.activeUser.id).then((response) => {
    //     console.log(response.data);
    //     response.data.forEach(element => {
    //       this.reservations.push(element);
    //     });
    //     console.log(this.reservations);
    //   });
    // }
}
</script>

<style>

</style>
