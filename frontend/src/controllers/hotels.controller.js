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
    },
    addOffer(hotelID, data){
      return HotelsService.addOffer(hotelID, data);
    },
    updatePricelist(hotelID, data){
      return HotelsService.updatePricelist(hotelID, data);
    },
    search(data){
      return HotelsService.search(data);
    },
    reserve(hotelID, data){
      return HotelsService.reserve(hotelID, data);
    },
    getReservations(user_id){
      return HotelsService.getReservations(user_id);
    },
    addSpecialPrice(hotel_id, room_id, specialPrice){
      return HotelsService.addSpecialPrice(hotel_id, room_id, specialPrice);
    },
    changeLocation(hotel_id, mapLocation){
      return HotelsService.changeLocation(hotel_id, mapLocation);
    },
    addRoomDiscount(hotel_id, room_id, roomDiscount){
      return HotelsService.addRoomDiscount(hotel_id, room_id, roomDiscount);
    }
}

