package com.project.project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

import com.project.project.dto.Hotel_DTOs.*;
import com.project.project.exceptions.*;
import com.project.project.model.HotelAdmin;
import com.project.project.model.Hotel_Model.*;
import com.project.project.model.MapLocation;
import com.project.project.model.User;
import com.project.project.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;


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

    @Autowired
    private SpecialPriceRepository specialPriceRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MapLocationRepository mapLocationRepository;

    @Autowired
    private RoomDiscountRepository roomDiscountRepository;

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

        MapLocation ml = mapLocationRepository.save(hotelDTO.getMapLocation());
        h.setMapLocation(ml);
//        h.setLongitude(hotelDTO.getLongitude());
//        h.setLatitude(hotelDTO.getLatitude());

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

            room.setRoomDiscounts(roomDTO.getRoomDiscounts());

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

    public HotelDestination getDestination(Long hotel_id) throws HotelNotFound, DestinationNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(hotel_id);
        if(hotel.isPresent()){
            return hotel.get().getDestination();
//            Optional<HotelDestination> destination = hotelDestinationsRepository.findById(hotel.get().getDestination().getId());
//            if(destination.isPresent()){
//                for(Hotel h: destination.get().getHotels()){
//                    for(HotelAdmin ha : h.getAdmins()){
//                        ha.setHotel(null);
//                    }
//                }
//                return destination.get();
//            }else{
//                throw new DestinationNotFound(destination.get().getName(), "");
//            }
        }else{
            throw new HotelNotFound(hotel_id);
        }
    }

    public Room findRoomById(Long id) throws RoomDoesntExist{
        Optional<Room> room = roomRepository.findOneById(id);
        if(room.isPresent()){
            return room.get();
        }else{
            throw new RoomDoesntExist(id);
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

    public Hotel changeLocation(Long id, MapLocation mapLocation) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if(hotel.isPresent()){
            Hotel newHotel = new Hotel();
//            newHotel.setMapLocation(mapLocation);
            MapLocation ml = mapLocationRepository.save(mapLocation);
            newHotel.setMapLocation(ml);

            newHotel.setId(hotel.get().getId());
            newHotel.setName(hotel.get().getName());
            newHotel.setAddress(hotel.get().getAddress());
            newHotel.setDescription(hotel.get().getDescription());

            newHotel.setDestination(hotel.get().getDestination());
            newHotel.setPriceList(hotel.get().getPriceList());
            newHotel.setAdmins(hotel.get().getAdmins());

            newHotel.setFloors(hotel.get().getFloors());
            newHotel.setNumOfFloors(hotel.get().getNumOfFloors());
            newHotel.setRoomsByFloor(hotel.get().getRoomsByFloor());

            newHotel = hotelRepository.save(newHotel);
            for (HotelAdmin admin: newHotel.getAdmins()) {
                admin.setHotel(null);
            }

            return newHotel;
        }else{
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
//            HotelDestination hotelDestination = getDestinationByHotel(destinations, hotel);
            HotelDestination hotelDestination = hotel.getDestination();
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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public HotelReservation reserve(Long hotel_id, HotelReservationDTO reservation) throws UserNotFound, HotelNotFound, ParseException {

        HotelReservation result = new HotelReservation();

        result.setCheckInDate(reservation.getCheckInDate());
        result.setCheckOutDate(reservation.getCheckOutDate());
        result.setNumberOfPeople(reservation.getNumberOfPeople());
        result.setTotalPrice(reservation.getTotalPrice());

        Room roomExample = reservation.getRooms().iterator().next();


        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date reservationStart = format.parse(reservation.getCheckInDate());
        Date reservationEnd = format.parse(reservation.getCheckOutDate());


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

                /*ENTITY MANAGER DOBAVLJA SOBU PO ID I ZAKLJUCAVA JE PRE MENJANJA*/
                Room realRoom = entityManager.find(Room.class, room.getId(), LockModeType.PESSIMISTIC_WRITE);
                entityManager.lock(realRoom, LockModeType.PESSIMISTIC_WRITE);
                //Optional<Room> realRoom = roomRepository.findOneById(room.getId());
                //if (realRoom.isPresent()){


                /* PROVERAVAM DA LI POSTOJI NEKI DATUM KADA JE SOBA REZERVISANA KOJI SE PREKLAPA SA NOVIM*/
                for(RoomTaken rt : realRoom.getRoomTaken()){
                    Date rtStart = format.parse(rt.getStartDate());
                    Date rtEnd = format.parse(rt.getEndDate());
                    if(overlap(rtStart, rtEnd, reservationStart, reservationEnd)){
                        return null;
                    }
                }
                RoomTaken roomTaken = new RoomTaken();

                roomTaken.setStartDate(reservation.getCheckInDate());
                roomTaken.setEndDate(reservation.getCheckOutDate());

                roomTaken = roomTakenRepository.save(roomTaken);

                //realRoom.get().getRoomTaken().add(roomTaken);
                realRoom.getRoomTaken().add(roomTaken);

                //Room newRoom = roomRepository.save(realRoom.get());
                Room newRoom = roomRepository.save(realRoom);
                rooms.add(newRoom);
                //}
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


    public Set<HotelReservation> getReservations(Long user_id) throws HotelNotFound, ParseException {
        List<HotelReservation> reservations = hotelReservationRepository.findAll();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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

                if(format.parse(reservation.getCheckOutDate()).before(new Date())){
                    reservation.setCompleted(true);
                } else {
                    reservation.setCompleted(false);
                }
                reservation.getHotel().setAdmins(new HashSet<HotelAdmin>());
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

    public Room addRoomPrice(Long hotelID, Long roomID, SpecialPrice specialPrice) throws HotelNotFound, ParseException {
        Optional<Hotel> h = hotelRepository.findOneById(hotelID);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date newStartDate = format.parse(specialPrice.getStartDate());
        Date newEndDate = format.parse(specialPrice.getEndDate());

        if(h.isPresent()){
            for (HotelFloor floor :h.get().getFloors()) {
                for (Room r : floor.getRoomsOnFloor()) {
                    if(r.getId() == roomID){
                        boolean overlaps = false;
                        for (SpecialPrice sp : r.getSpecialPrices()) {

                            Date startDate = format.parse(sp.getStartDate());
                            Date endDate = format.parse(sp.getEndDate());

                            if(overlap(startDate,endDate,newStartDate,newEndDate)){
                                // preklapaju se
                                overlaps = true;
                            }
                        }
                        if(!overlaps){

                            SpecialPrice newService = new SpecialPrice();
                            newService.setStartDate(specialPrice.getStartDate());
                            newService.setEndDate(specialPrice.getEndDate());
                            newService.setPrice(specialPrice.getPrice());

                            newService = specialPriceRepository.save(newService);

                            r.getSpecialPrices().add(newService);

                            r = roomRepository.save(r);
                            Hotel savedHotel = hotelRepository.save(h.get());

                            return r;
                        }
                    }
                }
            }
            return new Room();
        }else{
            throw new HotelNotFound(hotelID);
        }
    }

    public RoomDiscount addRoomDiscount(Long hotelID, Long roomID, RoomDiscount roomDiscount) throws  UnableToAddDiscount, HotelNotFound, ParseException{
        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);
        if(hotel.isPresent()){
            for (HotelFloor hf : hotel.get().getFloors()) {
                for (Room r: hf.getRoomsOnFloor()) {
                    if(r.getId() == roomID){

                        boolean possible = isDiscountPossible(r, roomDiscount);
                        if(possible){
                            Set<HotelsOffer> offers = new HashSet<>();
                            for (HotelsOffer offer: roomDiscount.getAdditionalServices()) {
                                offers.add(hotelsOfferRepository.save(offer));
                            }
                            roomDiscount.setAdditionalServices(offers);
                            roomDiscount = roomDiscountRepository.save(roomDiscount);
                            Set<RoomDiscount> roomDiscounts = r.getRoomDiscounts();
                            roomDiscounts.add(roomDiscount);
                            r.setRoomDiscounts(roomDiscounts);
                            Hotel h = hotelRepository.save(hotel.get());


                            return roomDiscount;
                        }else {
                            throw new UnableToAddDiscount();
                        }
                    }
                }
            }
            throw new UnableToAddDiscount();
        }else{
            throw new HotelNotFound(hotelID);
        }
    }


    public HotelReportDTO getReports(Long hotel_id, String year, String month) throws HotelNotFound, ParseException {
        Optional<Hotel> hotel = hotelRepository.findOneById(hotel_id);
        if (hotel.isPresent()) {

            HotelReportDTO result = new HotelReportDTO();
            Map<String, Integer> attendance = new LinkedHashMap<>();
            Map<String, Double> income = new LinkedHashMap<>();


            if (month.equals("")) {
                String[] months = new String[] {"January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"};
                for (String m: months) {
                    attendance.put(m,0);
                    income.put(m,0.0);
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
                Date pickedYear = sdfYear.parse(year);

                List<HotelReservation> reservations = hotelReservationRepository.findAll();

                for (HotelReservation reservation : reservations) {
                    if(reservation.getHotel().getId().equals(hotel_id)){
                        Date date = sdfYear.parse(reservation.getCheckInDate());
                        if (pickedYear.equals(date)) {
                            Date date1 = sdf.parse(reservation.getCheckInDate());

                            String m = String.valueOf(sdfMonth.format(date1));
                            String s = "";
                            switch (m) {
                                case "01": {
                                    s = "January"; break;
                                }
                                case "02": {
                                    s = "February"; break;
                                }
                                case "03": {
                                    s = "March"; break;
                                }
                                case "04": {
                                    s = "April"; break;
                                }
                                case "05": {
                                    s = "May"; break;
                                }
                                case "06": {
                                    s = "Jun"; break;
                                }
                                case "07": {
                                    s = "July"; break;
                                }
                                case "08": {
                                    s = "August"; break;
                                }
                                case "09": {
                                    s = "September"; break;
                                }
                                case "10": {
                                    s = "October"; break;
                                }
                                case "11": {
                                    s = "November"; break;
                                }
                                case "12": {
                                    s = "December"; break;
                                }
                            }

                            int count = attendance.getOrDefault(s,0);
                            attendance.put(s, count + reservation.getNumberOfPeople());

                            double price = reservation.getTotalPrice();
                            double count1 = income.getOrDefault(s,0.0);
                            income.put(s, count1 + price);
                        }
                    }
                }


                result.setAttendance(attendance);
                result.setIncome(income);
                return result;

            } else {
                SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
                SimpleDateFormat sdfMonthFromDate = new SimpleDateFormat("MM");
                Date dateMonth = sdfMonth.parse(month);
                Integer m = Integer.parseInt(sdfMonthFromDate.format(dateMonth));
                YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), m);
                int daysInMonth = yearMonthObject.lengthOfMonth();

                for(int i = 1; i <= daysInMonth; i++) {
                    if (i < 10) {
                        attendance.put("0" + i,0);
                        income.put("0" + i, 0.0);
                    }
                    else {
                        attendance.put(String.valueOf(i), 0);
                        income.put(String.valueOf(i), 0.0);
                    }
                }
                SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyy-MM");
                SimpleDateFormat sdfYearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
                Date pickedDate = sdfYearMonth.parse(year + "-" + m);


                List<HotelReservation> reservations = hotelReservationRepository.findAll();

                for (HotelReservation reservation : reservations) {
                    if(reservation.getHotel().getId().equals(hotel_id)){
                        Date date = sdfYearMonth.parse(reservation.getCheckInDate());

                        if (pickedDate.equals(date)) {

                            Date date1 = sdfYearMonthDay.parse(reservation.getCheckInDate());
                            String d = String.valueOf(sdfDay.format(date1));

                            int count = attendance.getOrDefault(d, 0);
                            attendance.put(d, count + reservation.getNumberOfPeople());

                            double price = reservation.getTotalPrice();
                            double count1 = income.getOrDefault(d,0.0);
                            income.put(d, count1 + price);
                        }
                    }
                }

                result.setAttendance(attendance);
                result.setIncome(income);
                return result;
            }

        } else {
            throw new HotelNotFound(hotel_id);
        }
    }




    public RoomDTO deleteRoom(Long hotelID, Long roomID) throws HotelNotFound, RoomDoesntExist, UnableToDeleteRoom{
        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if(hotel.isPresent()){
            Room roomToDelete = null;
            HotelFloor floorWithRoomToDelete = null;

            roomToDelete = this.getRoomToDelete(hotel.get(), roomID);
            floorWithRoomToDelete = this.getFloorWithRoomToDelete(hotel.get(), roomID);


            if(roomToDelete != null && floorWithRoomToDelete != null){
                if(roomToDelete.getRoomTaken().isEmpty()){

                    floorWithRoomToDelete.getRoomsOnFloor().remove(roomToDelete);

                    Set<RoomDiscount> discountsToRemove = new HashSet<>(roomToDelete.getRoomDiscounts());
                    roomToDelete.getRoomDiscounts().removeAll(discountsToRemove);

                    Set<SpecialPrice> specialPrices = new HashSet<>(roomToDelete.getSpecialPrices());
                    roomToDelete.getSpecialPrices().removeAll(specialPrices);

                    roomRepository.delete(roomToDelete);

                    this.removeDiscounts(discountsToRemove);
                    this.removeSpecialPrices(specialPrices);


                    return new RoomDTO(roomToDelete);
                }else{
                    throw new UnableToDeleteRoom(roomID);
                }
            }else{
                throw new RoomDoesntExist(roomID);
            }
        }else{
            throw new HotelNotFound(hotelID);
        }
    }

    private Room getRoomToDelete(Hotel hotel, Long roomID){
        for (HotelFloor floor : hotel.getFloors()) {
            for (Room room : floor.getRoomsOnFloor()) {
                if(room.getId().equals(roomID)){
                    return room;
                }
            }
        }
        return null;
    }
    private HotelFloor getFloorWithRoomToDelete(Hotel hotel, Long roomID){
        for (HotelFloor floor : hotel.getFloors()) {
            for (Room room : floor.getRoomsOnFloor()) {
                if(room.getId().equals(roomID)){
                    return floor;
                }
            }
        }
        return null;
    }

    private void removeSpecialPrices(Set<SpecialPrice> specialPrices){
        for(SpecialPrice specialPrice : specialPrices){
            SpecialPrice price = specialPriceRepository.getOne(specialPrice.getId());
            specialPriceRepository.delete(price);
        }
    }

    private void removeDiscounts(Set<RoomDiscount> discounts){
        for (RoomDiscount discount : discounts) {
            RoomDiscount discountToRemove = roomDiscountRepository.getOne(discount.getId());
            Set<HotelsOffer> hotelsOffers = new HashSet<>(discountToRemove.getAdditionalServices());
            discountToRemove.getAdditionalServices().removeAll(hotelsOffers);
            roomDiscountRepository.delete(discountToRemove);
        }
    }

    private Boolean isDiscountPossible(Room room, RoomDiscount roomDiscount) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date startDate = format.parse(roomDiscount.getStartDate());
        Date endDate = format.parse(roomDiscount.getEndDate());

        for (RoomDiscount rd : room.getRoomDiscounts()) {
            Date existingStart = format.parse(rd.getStartDate());
            Date existingEnd = format.parse(rd.getEndDate());

            if(overlap(startDate, endDate, existingStart, existingEnd)){
                return false;
            }
        }
        for (RoomTaken roomTaken : room.getRoomTaken()) {
            Date existingStart = format.parse(roomTaken.getStartDate());
            Date existingEnd = format.parse(roomTaken.getEndDate());

            if(overlap(startDate, endDate, existingStart, existingEnd)){
                return false;
            }
        }
        return true;

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
                    //DateFormat format = new SimpleDateFormat("YYYY-mm-dd", Locale.ENGLISH);
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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


