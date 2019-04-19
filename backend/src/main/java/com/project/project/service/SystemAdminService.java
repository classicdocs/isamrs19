package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.HotelDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.HotelAlreadyExists;
import com.project.project.exceptions.RentACarAlreadyExist;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.model.Hotel;
import com.project.project.model.RentACar;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.HotelAdminRepository;
import com.project.project.repository.HotelRepository;
import com.project.project.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public HotelDTO createHotel(HotelDTO hotelDTO) throws HotelAlreadyExists {
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
        h.setRoomConfiguration(hotelDTO.getRoomConfiguration());

        /*Save hotel admins. Not trough for loop because every iteration forms
        * new pool connection.*/
        //hotelAdminRepository.saveAdmins(hotelDTO.getAdmins());
        h.setAdmins(hotelDTO.getAdmins());

        /*Save hotel.*/
        h = hotelRepository.save(h);
        return (new HotelDTO(h));
    }

    public RentACar createRentACar(RentACarDTO rentACarDTO) throws RentACarAlreadyExist {

        Optional<RentACar> service = rentACarRepository.findOneByName(rentACarDTO.getName());

        if(service.isPresent()){
            throw new RentACarAlreadyExist(rentACarDTO.getName());
        }
        RentACar rentACar = new RentACar();
        rentACar.setId(rentACarDTO.getId());
        rentACar.setName(rentACarDTO.getName());
        rentACar.setAddress(rentACarDTO.getAddress());
        rentACar.setPromotionalDescription(rentACarDTO.getPromotionalDescription());
        rentACar.setBranches(rentACarDTO.getBranches());
        rentACar.setVehicles(rentACarDTO.getVehicles());
        rentACar.setAdmins(rentACarDTO.getAdmins());

        return rentACarRepository.save(rentACar);
    }

    public AirlineCompanyDTO createAirlineCompany(AirlineCompanyDTO airlineCompanyDTO) throws AirlineCompanyAlreadyExist {

        Optional<AirlineCompany> airline = airlineCompanyRepository.findOneByName(airlineCompanyDTO.getName());

        if(airline.isPresent()){
            throw new AirlineCompanyAlreadyExist(airlineCompanyDTO.getName());
        }
        AirlineCompany airlineCompany = new AirlineCompany();

        airlineCompany.setName(airlineCompanyDTO.getName());
        airlineCompany.setAddress(airlineCompanyDTO.getAddress());
        airlineCompany.setDescription(airlineCompanyDTO.getDescription());
        airlineCompany = airlineCompanyRepository.save(airlineCompany);

        return (new AirlineCompanyDTO(airlineCompany));
    }
}
