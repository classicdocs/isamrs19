package com.project.project.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class RoomTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDaTe", nullable = false)
    private Date endDaTe;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;

	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
    }
    
    public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
    }
    
    public Date getEndDaTe() {
		return this.endDaTe;
	}

	public void setEndDaTe(Date endDaTe) {
		this.endDaTe = endDaTe;
    }
    
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}