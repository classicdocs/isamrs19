package com.project.project.dto.Hotel_DTOs;

import java.util.Map;

public class HotelReportDTO {

    private Map<String, Integer> attendance;

    private  Map<String, Double> income;

    public HotelReportDTO() {}

    public Map<String, Integer> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<String, Integer> attendance) {
        this.attendance = attendance;
    }

    public Map<String, Double> getIncome() {
        return income;
    }

    public void setIncome(Map<String, Double> income) {
        this.income = income;
    }
}
