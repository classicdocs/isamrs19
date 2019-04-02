package com.project.project.dto;

import com.project.project.model.Room;
import com.project.project.model.RoomTaken;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public class RoomTakenDTO {

    @Positive
    private long ID;

    @NotNull
    private String startDate;

    @NotNull
    private String endDaTe;

    @NotNull
    private Set<Room> rooms;

    public RoomTakenDTO(RoomTaken roomTaken) {
        this.ID = roomTaken.getId();
        this.startDate = roomTaken.getStartDate();
        this.endDaTe = roomTaken.getEndDaTe();
        this.rooms = roomTaken.getRooms();
    }

    public RoomTakenDTO() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDaTe() {
        return endDaTe;
    }

    public void setEndDaTe(String endDaTe) {
        this.endDaTe = endDaTe;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
