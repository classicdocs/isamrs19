package com.project.project.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RoomTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "endDaTe", nullable = false)
    private String endDaTe;

    //@ManyToMany(fetch = FetchType.LAZY)
	@ManyToMany(fetch = FetchType.LAZY,
			mappedBy = "roomTaken")
    private Set<Room> rooms;


	public RoomTaken() {
	}

	public long getId() {
		return this.ID;
	}

	public void setId(long Id) {
		this.ID = Id;
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

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
}