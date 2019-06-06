package com.project.project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.project.project.dto.Hotel_DTOs.*;
import com.project.project.exceptions.*;
import com.project.project.model.HotelAdmin;
import com.project.project.model.Hotel_Model.*;
import com.project.project.model.User;
import com.project.project.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private HotelDestinationsRepository hotelDestinationsRepository;

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Autowired
    private HotelsOfferRepository hotelsOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomTakenRepository roomTakenRepository;

    public Hotel findOneById(Long id) throws HotelNotFound{
        Optional<Hotel> h = hotelRepository.findOneById(id);
        if(h.isPresent()){
            for (HotelAdmin admin: h.get().getAdmins()) {
                admin.setHotel(null);
            }
            List<HotelDestination> destinations = hotelDestinationsRepository.findAll();
            for (HotelDestination dest: destinations) {
                if(dest.getHotels().contains(h.get())){
                    dest.setHotels(null);
                    h.get().setDestination(dest);
                    break;
            }
        }
            return h.get();
        }else{
            throw new HotelNotFound(id);
        }
    }

    /*Metoda koja za dati id pronadje hotel i vrati set njegovih ponuda koje u stvari cine cenovnik*/
    public Set<HotelsOffer> getPriceList(Long id) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if (hotel.isPresent()) {
            return hotel.get().getPriceList();
        } else {
            throw new HotelNotFound(id);
        }
    }

    public HotelDTO save(HotelDTO hotelDTO) throws HotelAlreadyExists, HotelNotFound {

        Optional<Hotel> hotel = hotelRepository.findOneByName(hotelDTO.getName());

        if(hotel.isPresent()){
            throw new HotelAlreadyExists(hotelDTO.getName());
        }

        Hotel h = new Hotel();
        h.setAddress(hotelDTO.getAddress());
        h.setDescription(hotelDTO.getDescription());
        h.setName(hotelDTO.getName());
        h.setPriceList(hotelDTO.getPriceList());

        h.setNumOfFloors(hotelDTO.getNumOfFloors());
        h.setRoomsByFloor(hotelDTO.getRoomsByFloor());

        h.setAdmins(hotelDTO.getAdmins());
        h = hotelRepository.save(h);

        for(int i = 1; i <= h.getNumOfFloors(); i++){
            HotelFloorDTO hf = new HotelFloorDTO();
            hf.setLevel(i);
            hf.setMaxRooms(h.getRoomsByFloor());
            hf.setHotel(h);
            addFloor(h.getId(), hf);
        }
        h = hotelRepository.save(h);
        h.setDestination(hotelDTO.getDestination());
        h = hotelRepository.save(h);
        return (new HotelDTO(h));
    }


    public HotelFloorDTO addFloor(Long hotelID, HotelFloorDTO hotelFloorDTO) throws HotelNotFound {

        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if (hotel.isPresent()) {
            HotelFloor hf = new HotelFloor();
            hf.setLevel(hotelFloorDTO.getLevel());
            hf.setMaxRooms(hotelFloorDTO.getMaxRooms());
            hf.setRoomsOnFloor(hotelFloorDTO.getRoomsOnFloor());

            hf = floorRepository.save(hf);

            hotel.get().getFloors().add(hf);
            hotelRepository.save(hotel.get());

            return (new HotelFloorDTO(hf));
        } else {
            throw new HotelNotFound(hotelID);
        }
    }

    public RoomDTO addRoom(RoomDTO roomDTO) throws FloorNotFound {

        Optional<HotelFloor> floor = floorRepository.findOneById(roomDTO.getHotelFloor().getId());

        if (floor.isPresent()) {

            Room room = new Room();
            room.setNumberOfBeds(roomDTO.getNumberOfBeds());
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setRoomTaken(roomDTO.getRoomTaken());
            room.setSpecialPrices(roomDTO.getSpecialPrices());

            room = roomRepository.save(room);

            floor.get().getRoomsOnFloor().add(room);
            floorRepository.save(floor.get());

            return (new RoomDTO(room));
        } else {
            throw new FloorNotFound(roomDTO.getHotelFloor().getId());
        }
    }

    public RoomDTO updateRoom(Long hotelID, RoomDTO roomDTO, Long floorID) throws FloorNotFound, HotelNotFound{

        Optional<HotelFloor> floor = floorRepository.findOneById(floorID);
        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if(hotel.isPresent()){
            if (floor.isPresent()) {
                for (Room room: floor.get().getRoomsOnFloor()) {
                    if(room.getRoomNumber() == roomDTO.getRoomNumber()){

                        room.setSpecialPrices(roomDTO.getSpecialPrices());
                        room.setRoomTaken(roomDTO.getRoomTaken());
                        room.setNumberOfBeds(roomDTO.getNumberOfBeds());

                        Hotel h = hotelRepository.save(hotel.get());
                        return (new RoomDTO(room));
                    }
                }
                return (new RoomDTO());
            } else {
                throw new FloorNotFound(floorID);
            }
        }else {
            throw new HotelNotFound(hotelID);
        }

    }

    public Set<HotelsOffer> updatePriceList(Long id, Set<HotelsOffer> offers) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);

        Set<HotelsOffer> offersToReturn = new HashSet<>();
        if(hotel.isPresent()){
            hotel.get().setPriceList(offers);
            hotelRepository.save(hotel.get());
//            for (HotelsOffer offer: offers) {
//                if(!hotel.get().getPriceList().contains(offer)){
//                    offersToReturn.add(this.addHotelsOffer(id, offer));
//                }
//            }

            return hotel.get().getPriceList();
        }else{
            throw new HotelNotFound(id);
        }
    }


    public HotelsOffer addHotelsOffer(Long id, HotelsOffer offer) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);

        if(hotel.isPresent()){
            Set<HotelsOffer> priceList = hotel.get().getPriceList();
            if(priceList.isEmpty()){
                priceList.add(offer);
                hotelRepository.save(hotel.get());
                return offer;
            }else{

                for (HotelsOffer hOffer : priceList) {
                    /*ako je bilo koja usluga i vec postoji onda treba da se izmeni*/
                    if(hOffer.getType() == offer.getType()){
                        if(offer.getType().getValue() == 5){
                            hotel.get().getPriceList().add(offer);
                            hotelRepository.save(hotel.get());
                            return offer;
                        }

                        hOffer.setPrice(offer.getPrice());
                        hOffer.setDescription(offer.getDescription());
                        hotelRepository.save(hotel.get());
                        return offer;
                    }
                }

                hotel.get().getPriceList().add(offer);
                hotelRepository.save(hotel.get());
                return offer;
            }
        }else{
            throw new HotelNotFound(id);
        }
    }

    public Set<HotelDTO> findAll() throws HotelNotFound, DestinationNotFound{
        Set<HotelDTO> hotels = hotelRepository.findAllHotels();
        for (HotelDTO hotelDTO: hotels) {
            for (HotelAdmin admin: hotelDTO.getAdmins()) {
                admin.setHotel(null);
            }
            HotelDestinationDTO destinationDTO = new HotelDestinationDTO(getDestination(hotelDTO.getId()));
            HotelDestination destination = new HotelDestination();
            destination.setName(destinationDTO.getName());
            hotelDTO.setDestination(destination);
        }
        return hotels;
    }

    public HotelDestination getDestination(Long id) throws HotelNotFound, DestinationNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if(hotel.isPresent()){
            Optional<HotelDestination> destination = hotelDestinationsRepository.findOneByHotels_id(id);
            if(destination.isPresent()){
                for(Hotel h: destination.get().getHotels()){
                    for(HotelAdmin ha : h.getAdmins()){
                        ha.setHotel(null);
                    }
                }
                return destination.get();
            }else{
                throw new DestinationNotFound(destination.get().getName(), "");
            }
        }else{
            throw new HotelNotFound(id);
        }
    }


    public Set<Room> getRooms(Long id) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if (hotel.isPresent()) {
            Set<Room> rooms = new HashSet<>();
            for (HotelFloor floor: hotel.get().getFloors()) {
                for (Room room: floor.getRoomsOnFloor()) {
                    HotelFloor newFloor = floor;
                    newFloor.setRoomsOnFloor(new HashSet<>());
                    room.setHotelFloor(newFloor);
                    rooms.add(room);
                }
            }
            return rooms;
        } else {
            throw new HotelNotFound(id);
        }
    }


    public Set<SearchHotelDTO> search(
            String destination,
            String checkInDate,
            String checkOutDate,
            int numOfPeople
    ) throws ParseException, HotelHasNoDestination {
        Set<HotelDTO> hotels = hotelRepository.findAllHotels();
        Set<SearchHotelDTO> searchResult = new HashSet<>();

        for (HotelDTO hotel: hotels) {
            // destination check
            List<HotelDestination> destinations = hotelDestinationsRepository.findAll();
            HotelDestination hotelDestination = getDestinationByHotel(destinations, hotel);
            if (hotelDestination.getName().equals(destination)){
                // date check
                if(checkDate(hotel, checkInDate, checkOutDate, numOfPeople)){

                    HotelDestinationDTO hotelDestinationDTO = new HotelDestinationDTO(hotelDestination);
                    SearchHotelDTO searchHotelDTO = new SearchHotelDTO(hotel);
                    searchHotelDTO.setDestination(hotelDestinationDTO);


                    searchResult.add(searchHotelDTO);
                }
            }
        }
        return searchResult;
    }


    public HotelReservation reserve(Long hotel_id, HotelReservationDTO reservation) throws UserNotFound, HotelNotFound {

        HotelReservation result = new HotelReservation();

        result.setCheckInDate(reservation.getCheckInDate());
        result.setCheckOutDate(reservation.getCheckOutDate());
        result.setNumberOfPeople(reservation.getNumberOfPeople());
        result.setTotalPrice(reservation.getTotalPrice());

        Room roomExample = reservation.getRooms().iterator().next();

        Optional<Hotel> hotel = hotelRepository.findOneById(hotel_id);

        if(hotel.isPresent()){

            hotel.get().setAdmins(null);
            result.setHotel(hotel.get());

            Set<HotelsOffer> additionalServices = new HashSet<>();
            for (HotelsOffer offer: reservation.getAdditionalServices()) {
                Optional<HotelsOffer> realOffer = hotelsOfferRepository.findById(offer.getId());
                if( realOffer.isPresent() ){
                    additionalServices.add(realOffer.get());
                }
            }
            result.setAdditionalServices(additionalServices);


            Set<Room> rooms = new HashSet<>();
            for(Room room : reservation.getRooms()){
                Optional<Room> realRoom = roomRepository.findOneById(room.getId());
                if (realRoom.isPresent()){

                    RoomTaken roomTaken = new RoomTaken();
                    roomTaken.setStartDate(reservation.getCheckInDate());
                    roomTaken.setEndDate(reservation.getCheckOutDate());

                    roomTaken = roomTakenRepository.save(roomTaken);

                    realRoom.get().getRoomTaken().add(roomTaken);

                    Room newRoom = roomRepository.save(realRoom.get());
                    rooms.add(newRoom);
                }
            }
            result.setRooms(rooms);
        }else{
            throw new HotelNotFound(hotel_id);
        }
        Optional<User> user = userRepository.findOneByUsername(reservation.getUser());

        if (user.isPresent()) {
            result.setUser(user.get());
        } else throw new UserNotFound(Long.parseLong(reservation.getUser()));

        result = hotelReservationRepository.save(result);
        return result;
    }


    public Set<HotelReservation> getReservations(Long user_id) throws HotelNotFound {
        List<HotelReservation> reservations = hotelReservationRepository.findAll();

        Set<HotelReservation> result = new HashSet<>();
        for (HotelReservation reservation : reservations) {
            if(reservation.getUser().getId() == user_id){

                Set<Room> rooms = getRooms(reservation.getHotel().getId());
                for (Room room: reservation.getRooms()) {
                    for (Room roomFromRepository: rooms) {
                        if(room.getId() == roomFromRepository.getId()){
                            room.setHotelFloor(roomFromRepository.getHotelFloor());
                        }
                    }
                }
                result.add(reservation);
            }
        }
        return result;
    }




    private HotelDestination getDestinationByHotel(List<HotelDestination> destinations, HotelDTO hotelDTO) throws HotelHasNoDestination {
        for (HotelDestination destination: destinations) {
            for(Hotel hotel : destination.getHotels()){
                if(hotel.getName().equals(hotelDTO.getName())){
                    return destination;
                }
            }
        }
        throw new HotelHasNoDestination(hotelDTO.getName());
    }

    private boolean checkDate(HotelDTO hotel, String checkIn, String CheckOut, int numOfPeople) throws ParseException {
        int foundPlaces = 0;
        // kroz svaki sprat
        for (HotelFloor floor: hotel.getFloors()) {
            // kroz svaku sobu
            for(Room room: floor.getRoomsOnFloor()){
                // ukoliko nema rezervacija
                if(room.getRoomTaken().isEmpty()){
                    foundPlaces += room.getNumberOfBeds();
                    // ukoliko sam nasao dovoljno kreveta
                    if(foundPlaces >= numOfPeople){
                        return true;
                    }
                // ukoliko ima rezervacija
                }else{
                    //2019-05-22
                    DateFormat format = new SimpleDateFormat("YYYY-mm-dd", Locale.ENGLISH);
//                    DateFormat format2 = new SimpleDateFormat("YYYY/mm/dd", Locale.ENGLISH);

                    Date checkInDate = format.parse(checkIn);
                    Date checkOutDate = format.parse(CheckOut);
                    boolean timeIsPossible = true;

                    // kroz svaku rezervaciju
                    for (RoomTaken roomTaken: room.getRoomTaken()) {
//                        Date startRoomTaken = format2.parse(roomTaken.getStartDate());
//                        Date endRoomTaken = format2.parse(roomTaken.getEndDate());

                        Date startRoomTaken = format.parse(roomTaken.getStartDate());
                        Date endRoomTaken = format.parse(roomTaken.getEndDate());

                        // ukoliko se neka preklapa sa datumom prijave i odjave
                        if(overlap(checkInDate,checkOutDate,startRoomTaken,endRoomTaken)){
                            timeIsPossible = false;
                            break;
                        }
                    }
                    // ukoliko se nijedna rezervacija ove sobe ne preklapa sa datumom prijave i odjave
                    if(timeIsPossible){
                        foundPlaces += room.getNumberOfBeds();
                        if(foundPlaces >= numOfPeople){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean overlap(Date start1, Date end1, Date start2, Date end2){
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
    }


}


