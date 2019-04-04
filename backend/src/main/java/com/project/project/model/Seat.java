package com.project.project.model;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "row_num", nullable = false)
    private Integer rowNum;

    @Column(name = "col_num", nullable =  false)
    private Integer colNum;

    @Column(name = "seatClass", nullable = false)
    private String seatClass;

    @Column(name = "taken", nullable = false)
    private boolean taken = false;

    public Seat() {

    }

    public Seat(Integer rowNum, Integer colNum, String seatClass, boolean taken) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.seatClass = seatClass;
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
}
