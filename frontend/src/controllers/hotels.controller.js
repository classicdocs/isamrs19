import HotelsService from "@/api-services/hotels.service.js";

export default {
    getHotels() {
        return HotelsService.getHotels();
    }
}

