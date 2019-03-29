package com.project.project.model;


import javax.persistence.*;

@Entity
public class RoomTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "endDaTe", nullable = false)
    private String endDaTe;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;

	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
    }
    
    public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
    }
    
    public String getEndDaTe() {
		return this.endDaTe;
	}

	public void setEndDaTe(String endDaTe) {
		this.endDaTe = endDaTe;
    }
    
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}