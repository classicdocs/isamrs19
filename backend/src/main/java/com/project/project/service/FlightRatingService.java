package com.project.project.service;

import com.project.project.dto.FlightReservationResultDTO;
import com.project.project.dto.RatingDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.model.AirlineCompany;
import com.project.project.model.Flight;
import com.project.project.model.FlightReservation;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.FlightRepository;
import com.project.project.repository.FlightReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightRatingService {

    @Autowired
    FlightReservationRepository flightReservationRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    public FlightReservationResultDTO rate(RatingDTO ratingDTO) throws AlreadyRated {

        FlightReservation fr = flightReservationRepository.findOneById(ratingDTO.getId());


        if (fr.isRated())
            throw new AlreadyRated();

        return doRating(fr,ratingDTO);
    }

    public FlightReservationResultDTO rateWithReturn(RatingDTO ratingDTO) throws AlreadyRated {

        FlightReservation fr = flightReservationRepository.findOneById(ratingDTO.getId());


        if (fr.isRated())
            throw new AlreadyRated();

        rateReturnFlight(fr, ratingDTO);

        return doRating(fr,ratingDTO);
    }

    public FlightReservationResultDTO rateDifferentCompanies(RatingDTO ratingDTO) throws AlreadyRated {

        FlightReservation fr = flightReservationRepository.findOneById(ratingDTO.getId());


        if (fr.isRated())
            throw new AlreadyRated();

        rateReturnFlight(fr, ratingDTO);

        AirlineCompany returnCompany = fr.getReturnFlight().getAirlineCompany();

        rateACompany(returnCompany, ratingDTO.getReturnCompanyRating());

        return doRating(fr,ratingDTO);
    }

    private void rateReturnFlight(FlightReservation fr, RatingDTO ratingDTO) {
        Flight return_flight = fr.getReturnFlight();

        double average_return_flight_rating = return_flight.getAverageRating();
        double total_return_flight_rating = return_flight.getTotalRating();

        double new_average_return_flight_rating = ((average_return_flight_rating * total_return_flight_rating) + ratingDTO.getReturnFlightRating()) / (total_return_flight_rating + 1);
        return_flight.setAverageRating(new_average_return_flight_rating);
        return_flight.setTotalRating(total_return_flight_rating + 1);

        flightRepository.save(return_flight);
    }

    private FlightReservationResultDTO doRating(FlightReservation fr, RatingDTO ratingDTO) {
        Flight departure_flight = fr.getDepartureFlight();

        double average_flight_rating = departure_flight.getAverageRating();
        double total_flight_rating = departure_flight.getTotalRating();

        double new_average_flight_rating = ((average_flight_rating * total_flight_rating) + ratingDTO.getSpecific()) / (total_flight_rating + 1);
        departure_flight.setAverageRating(new_average_flight_rating);
        departure_flight.setTotalRating(total_flight_rating + 1);

        AirlineCompany ac = departure_flight.getAirlineCompany();

        rateACompany(ac, ratingDTO.getService());

        fr.setRated(true);

        flightRepository.save(departure_flight);

        flightReservationRepository.save(fr);

        return new FlightReservationResultDTO();
    }

    private void rateACompany(AirlineCompany ac, double companyRating) {
        double average_airline_company_rating = ac.getAverageRating();
        double total_airline_company_rating = ac.getTotalRating();

        double new_average_airline_company_rating = ((average_airline_company_rating * total_airline_company_rating) + companyRating) / (total_airline_company_rating+ 1);
        ac.setAverageRating(new_average_airline_company_rating);
        ac.setTotalRating(total_airline_company_rating + 1);

        airlineCompanyRepository.save(ac);
    }
}