package com.project.project.repository;

import com.project.project.model.FlightWithDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FlightWithDiscountRepository  extends JpaRepository<FlightWithDiscount, Long> {

    @Transactional
    List<FlightWithDiscount> deleteBySeatId(Long seatId);


}