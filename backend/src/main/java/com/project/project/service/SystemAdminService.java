package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.Hotel_DTOs.HotelFloorDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.dto.RentACarInfoDTO;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelDestination;
import com.project.project.model.Hotel_Model.HotelFloor;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class SystemAdminService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HotelDestinationsRepository hotelDestinationsRepository;

    @Autowired
    private MapLocationRepository mapLocationRepository;

    public HotelDTO createHotel(HotelDTO hotelDTO) throws HotelAlreadyExists, HotelNotFound {
        Optional<Hotel> hotel = hotelRepository.findOneByName(hotelDTO.getName());

        /*Check if hotel is already in database.*/
        if(hotel.isPresent()){
            throw new HotelAlreadyExists(hotelDTO.getName());
        }

        Hotel h = new Hotel();
        h.setAddress(hotelDTO.getAddress());
        h.setDescription(hotelDTO.getDescription());
        h.setName(hotelDTO.getName());
        h.setPriceList(hotelDTO.getPriceList());
        h.setFloors(hotelDTO.getFloors());

        h.setNumOfFloors(hotelDTO.getNumOfFloors());
        h.setRoomsByFloor(hotelDTO.getRoomsByFloor());

        MapLocation ml = mapLocationRepository.save(hotelDTO.getMapLocation());
        h.setMapLocation(ml);

        //h.setRoomConfiguration(hotelDTO.getRoomConfiguration());

        /*Save hotel admins. Not trough for loop because every iteration forms
         * new pool connection.*/
        //hotelAdminRepository.saveAdmins(hotelDTO.getAdmins());
        h.setAdmins(hotelDTO.getAdmins());

        h.setDestination(hotelDTO.getDestination());
        /*Save hotel.*/
        h = hotelRepository.save(h);

        for(int i = 1; i <= h.getNumOfFloors(); i++){
            HotelFloorDTO hf = new HotelFloorDTO();
            hf.setLevel(i);
            hf.setMaxRooms(h.getRoomsByFloor());
            hf.setHotel(h);
            hf.setRoomsOnFloor(new HashSet<Room>());
            addFloor(h.getId(), hf);
        }

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
            if(hotel.get().getFloors() == null){
                hotel.get().setFloors(new HashSet<HotelFloor>());
            }
            hotel.get().getFloors().add(hf);
            hotelRepository.save(hotel.get());

            return (new HotelFloorDTO(hf));
        } else {
            throw new HotelNotFound(hotelID);
        }
    }

    public RentACar createRentACar(RentACarInfoDTO rentACarInfoDTO) throws RentACarAlreadyExist {

        Optional<RentACar> service = rentACarRepository.findOneByName(rentACarInfoDTO.getName());

        if(service.isPresent()){
            throw new RentACarAlreadyExist(rentACarInfoDTO.getName());
        }
        RentACar rentACar = new RentACar();
        rentACar.setId(rentACarInfoDTO.getId());
        rentACar.setName(rentACarInfoDTO.getName());
        rentACar.setAddress(rentACarInfoDTO.getAddress());
        rentACar.setPromotionalDescription(rentACarInfoDTO.getPromotionalDescription());


        rentACar.setBranches(new HashSet<String>());
        rentACar.setVehicles(new HashSet<Vehicle>());
        rentACar.setAdmins(new HashSet<RentACarAdmin>());
        rentACar.setRating(0);
        rentACar.setTotal_rating(0);

        MapLocation ml = mapLocationRepository.save(rentACarInfoDTO.getMapLocation());
        rentACar.setMapLocation(ml);

        return rentACarRepository.save(rentACar);
    }

    public AirlineCompany createAirlineCompany(AirlineCompanyDTO airlineCompanyDTO) throws AirlineCompanyAlreadyExist {

        Optional<AirlineCompany> airline = airlineCompanyRepository.findOneByName(airlineCompanyDTO.getName());

        if(airline.isPresent()){
            throw new AirlineCompanyAlreadyExist(airlineCompanyDTO.getName());
        }
        AirlineCompany airlineCompany = new AirlineCompany();

        airlineCompany.setName(airlineCompanyDTO.getName());
        airlineCompany.setAddress(airlineCompanyDTO.getAddress());
        airlineCompany.setDescription(airlineCompanyDTO.getDescription());
        airlineCompany.setAverageRating(0);
        airlineCompany.setTotalRating(0);

        MapLocation ml = mapLocationRepository.save(airlineCompanyDTO.getMapLocation());
        airlineCompany.setMapLocation(ml);

        airlineCompany = airlineCompanyRepository.save(airlineCompany);

        return airlineCompany;
    }

    public HotelAdmin createHotelAdmin(HotelAdmin hotelAdmin) throws AdminAlreadyExists{

        hotelAdmin.setVerified(true);
        Optional<User> admin = userRepository.findOneByUsername(hotelAdmin.getUsername());

        if(admin.isPresent()){
            throw new AdminAlreadyExists(hotelAdmin.getUsername());
        }
        Role role = roleRepository.findOneById(4L);
        hotelAdmin.setRole(role);

        Optional<Hotel> hotel = hotelRepository.findOneById(hotelAdmin.getHotel().getId());
        if(hotel.isPresent()){
            hotel.get().getAdmins().add(hotelAdmin);
            hotelRepository.save(hotel.get());
        }

        return hotelAdmin;
    }


    public AirlineCompanyAdmin createAirlineAdmin(AirlineCompanyAdmin airlineCompanyAdmin) throws AdminAlreadyExists{

        airlineCompanyAdmin.setVerified(true);
        Optional<User> admin = userRepository.findOneByUsername(airlineCompanyAdmin.getUsername());

        if(admin.isPresent()){
            throw new AdminAlreadyExists(airlineCompanyAdmin.getUsername());
        }
        Role role = roleRepository.findOneById(3L);
        airlineCompanyAdmin.setRole(role);

        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findOneById(airlineCompanyAdmin.getAirlineCompany().getId());
        if(airlineCompany.isPresent()){
            airlineCompany.get().getAdmins().add(airlineCompanyAdmin);
            airlineCompanyRepository.save(airlineCompany.get());
        }

        return airlineCompanyAdmin;
    }

    public RentACarAdmin createRentACarAdmin(RentACarAdmin rentACarAdmin) throws AdminAlreadyExists{

        rentACarAdmin.setVerified(true);
        Optional<User> admin = userRepository.findOneByUsername(rentACarAdmin.getUsername());

        if(admin.isPresent()){
            throw new AdminAlreadyExists(rentACarAdmin.getUsername());
        }
        Role role = roleRepository.findOneById(2L);
        rentACarAdmin.setRole(role);

        RentACar rentACar = rentACarRepository.findOneById(rentACarAdmin.getRentACar().getId());
        rentACar.getAdmins().add(rentACarAdmin);
        rentACarRepository.save(rentACar);
        return rentACarAdmin;
    }
}
