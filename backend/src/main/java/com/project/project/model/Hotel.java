package com.project.project.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priceList", nullable = false)
    private List<HotelsOffer> priceList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> roomConfiguration;

    
    public Hotel(){}

    public Hotel(String name, String  address, String description, List<HotelsOffer> priceList, List<Room> roomConfiguration){
        this.name = name;
        this.address = address;
        this.description = description;
        this.priceList = priceList;
        this.roomConfiguration = roomConfiguration;
    }
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<HotelsOffer> getPriceList() {
		return this.priceList;
	}

	public void setPriceList(List<HotelsOffer> priceList) {
		this.priceList = priceList;
	}

	public List<Room> getRoomConfiguration() {
		return this.roomConfiguration;
	}

	public void setRoomConfiguration(List<Room> roomConfiguration) {
		this.roomConfiguration = roomConfiguration;
	}
    
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
