package com.project.project.dto;

import java.util.List;

import com.project.project.model.Hotel;
import com.project.project.model.HotelsOffer;
import com.project.project.model.Room;

import javax.validation.constraints.NotNull;

public class HotelDTO {
	@NotNull
	private Long Id;

	@NotNull
    private String name;

    @NotNull
	private String address;

    @NotNull
    private String description;

    @NotNull
    private List<HotelsOffer> priceList;

    @NotNull
    private List<Room> roomConfiguration;
    
    public HotelDTO(){}

	public HotelDTO(Hotel hotel) {
		Id = hotel.getId();
		this.name = hotel.getName();
		this.address = hotel.getAddress();
		this.description = hotel.getDescription();
		this.priceList = hotel.getPriceList();
		this.roomConfiguration = hotel.getRoomConfiguration();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<HotelsOffer> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<HotelsOffer> priceList) {
		this.priceList = priceList;
	}

	public List<Room> getRoomConfiguration() {
		return roomConfiguration;
	}

	public void setRoomConfiguration(List<Room> roomConfiguration) {
		this.roomConfiguration = roomConfiguration;
	}
}