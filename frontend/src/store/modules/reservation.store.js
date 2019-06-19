import FlightReservation from "@/models/FlightReservation";
import Friend from "@/models/Friend.js";

const state = {
  flightReservation: null,
  friends: [],
  myInfo: new Friend(),
  passengers: []
};

const getters = {
  flightReservation: state => state.flightReservation,
  seatsPickedDeparture: state => state.flightReservation.seatsPickedDeparture,
  seatsPickedReturn: state => state.flightReservation.seatsPickedReturn,
  friends: state => state.friends,
  myInfo: state => state.myInfo,
  passengers: state => state.passengers
};

const mutations = {
  flightReservation(state, data) {
    state.flightReservation = data;
  },
  seatsPickedDeparture(state, data) {
    state.flightReservation.seatsPickedDeparture = data;
  },
  seatsPickedReturn(state, data) {
    state.flightReservation.seatsPickedReturn = data;
  },
  setFriends(state, data) {
    state.friends = data;
  },
  friendsInvited(state, data) {
    for (let element of data) {
      for (let i = 0; i < state.passengers.length; i++) {
        if (state.passengers[i].id === null) {
          state.passengers[i] = element;
          break;
        }
      }
    }
  },
  myInfo(state, data) {
    state.myInfo = data;
  },
  passengers(state, data) {
    state.passengers = data;
  },
  setPassengersDefault(state, number) {
    state.passengers = [];
    for (let index = 1; index < number; index++) {
      let friend = new Friend();
      state.passengers.push(friend);
    }
  }
};

export { state, getters, mutations };
