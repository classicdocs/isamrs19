import HotelsService from "@/api-services/hotels.service.js";

export default {
    getHotels() {
        return HotelsService.getHotels();
    },
    addRoom(id, data){
        return HotelsService.addRoom(id, data);
    },
    // getRooms(id){
    //     return HotelsService.getRooms(id);
    // },
    getHotel(id){
        return HotelsService.getHotel(id);
    }
}

