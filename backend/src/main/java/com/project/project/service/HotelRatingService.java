package com.project.project.service;

import com.project.project.dto.RatingDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelReservation;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.repository.HotelRepository;
import com.project.project.repository.HotelReservationRepository;
import com.project.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class HotelRatingService {

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public HotelReservation rate(RatingDTO ratingDTO) throws AlreadyRated {

        HotelReservation hr = hotelReservationRepository.findOneById(ratingDTO.getId());

        if (hr.isRated())
            throw new AlreadyRated();

        Set<Room> rooms = hr.getRooms();

        for (Room r: rooms) {
            double averageRoomRating = r.getAverage_rating();
            double totalRoomRating = r.getTotal_rating();

            double newAverageRoomRating = ((averageRoomRating * totalRoomRating) + ratingDTO.getSpecific()) / (totalRoomRating + 1);
            r.setAverage_rating(newAverageRoomRating);
            r.setTotal_rating(totalRoomRating + 1);

            roomRepository.save(r);
        }

        Hotel h = hr.getHotel();

        double averageHotelRating = h.getAverage_rating();
        double totalHotelRating = h.getTotal_rating();

        double newAverageHotelRating = ((averageHotelRating * totalHotelRating) + ratingDTO.getService()) / (totalHotelRating+ 1);
        h.setAverage_rating(newAverageHotelRating);
        h.setTotal_rating(totalHotelRating + 1);

        hr.setRated(true);

        hotelRepository.save(h);

        hotelReservationRepository.save(hr);

        return hr;
    }
}