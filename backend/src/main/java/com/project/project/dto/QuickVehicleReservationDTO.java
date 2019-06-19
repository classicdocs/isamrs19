package com.project.project.dto;

public class QuickVehicleReservationDTO {

    private Long quickReservationId;

    private Long rentACarId;

    private String user;

    public QuickVehicleReservationDTO() {
    }

    public Long getQuickReservationId() {
        return quickReservationId;
    }

    public void setQuickReservationId(Long quickReservationId) {
        this.quickReservationId = quickReservationId;
    }

    public Long getRentACarId() {
        return rentACarId;
    }

    public void setRentACarId(Long rentACarId) {
        this.rentACarId = rentACarId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
