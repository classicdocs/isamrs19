import HotelsService from "@/api-services/hotels.service.js";

export default {
    getHotels() {
        return HotelsService.getHotels();
    },
    addRoom(id, data){
        return HotelsService.addRoom(id, data);
    },
    getHotel(id){
        return HotelsService.getHotel(id);
    },
    update(hotelID, room, floorID){
        return HotelsService.update(hotelID,room,floorID);
    },
    getRooms(hotelID){
      return HotelsService.getRooms(hotelID);
    }
}

