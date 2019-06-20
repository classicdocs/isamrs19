import Axios from "axios";

const ENDPOINTS = {
  RESERVATION_CANCEL: "cancel-hotel-reservations/"
};

export default {
  cancel(hotel_id, reservation_id) {
    return Axios.delete(ENDPOINTS.RESERVATION_CANCEL + hotel_id + "/" + reservation_id);
  }
};
