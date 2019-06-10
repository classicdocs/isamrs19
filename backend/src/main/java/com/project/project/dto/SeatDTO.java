package com.project.project.dto;

import com.project.project.model.Seat;

public class SeatDTO {

    private Long id;

    private String seatClass;

    private Integer rowNum;

    private Integer colNum;

    private boolean taken;


    public SeatDTO() {

    }

    public SeatDTO(Seat s) {
        this.id = s.getId();
        this.seatClass = s.getSeatClass();
        this.rowNum = s.getRowNum();
        this.colNum = s.getColNum();
        this.taken = s.isTaken();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
