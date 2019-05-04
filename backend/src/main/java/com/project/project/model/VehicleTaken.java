package com.project.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class VehicleTaken {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date takenFrom;

    @Column
    private Date takenUntil;

    public VehicleTaken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTakenFrom() {
        return takenFrom;
    }

    public void setTakenFrom(Date takenFrom) {
        this.takenFrom = takenFrom;
    }

    public Date getTakenUntil() {
        return takenUntil;
    }

    public void setTakenUntil(Date takenUntil) {
        this.takenUntil = takenUntil;
    }
}
