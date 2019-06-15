package com.project.project.model;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "row_num", nullable = false)
    private Integer rowNum;

    @Column(name = "col_num", nullable =  false)
    private Integer colNum;

    @Column(name = "taken", nullable = false)
    private boolean taken;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Passenger passenger;

    @Column(name = "class", nullable = false)
    private String seatClass;

    @Column(name = "discount", nullable = false)
    private int discount;

    @ManyToOne
    private SeatRow seatRow;

    public Seat() {
        this.taken = false;
        this.discount = 0;
    }

    public Seat(Integer rowNum, Integer colNum, boolean taken, String seatClass) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.taken = taken;
        this.seatClass = seatClass;
        this.discount = 0;
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public SeatRow getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(SeatRow seatRow) {
        this.seatRow = seatRow;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
