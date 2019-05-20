package com.project.project.service;

import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.HotelAdmin;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelDestination;
import com.project.project.repository.HotelDestinationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelDestinationsService {

    @Autowired
    private HotelDestinationsRepository destinationRepository;


    public List<HotelDestination> findAll() {
        List<HotelDestination> destinations = destinationRepository.findAll();
        for (HotelDestination destination: destinations) {
            for(Hotel singleHotel: destination.getHotels()){
                for(HotelAdmin admin: singleHotel.getAdmins()){
                    admin.setHotel(null);
                }
            }
        }
        return destinations;
    }

    public HotelDestination findOne(String name) throws DestinationNotFound {
        return  destinationRepository.findOneByName(name).orElseThrow(() -> new DestinationNotFound(name, ""));
    }

}
