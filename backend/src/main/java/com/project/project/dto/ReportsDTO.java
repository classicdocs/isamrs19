package com.project.project.dto;

import java.util.Map;

public class ReportsDTO {

    private Map<String, Integer> soldTickets;

    private  Map<String, Double> income;

    public ReportsDTO() {}

    public ReportsDTO(Map<String, Integer> soldTickets, Map<String, Double> income) {
        this.soldTickets = soldTickets;
        this.income = income;
    }

    public Map<String, Integer> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Map<String, Integer> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Map<String, Double> getIncome() {
        return income;
    }

    public void setIncome(Map<String, Double> income) {
        this.income = income;
    }
}
