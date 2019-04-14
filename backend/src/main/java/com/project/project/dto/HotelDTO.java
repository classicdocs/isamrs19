package com.project.project.dto;

import java.util.List;
import java.util.Set;

import com.project.project.model.Hotel;
import com.project.project.model.HotelAdmin;
import com.project.project.model.HotelsOffer;
import com.project.project.model.Room;

import javax.validation.constraints.NotNull;

public class HotelDTO {

	private Long id;

	@NotNull
    private String name;

    @NotNull
	private String address;

    @NotNull
    private String description;

    @NotNull
    private Set<HotelsOffer> priceList;

    @NotNull
    private Set<Room> roomConfiguration;

	@NotNull
	private Set<HotelAdmin> admins;

    public HotelDTO(){}

	public HotelDTO(Hotel hotel) {
		this.id = hotel.getId();
		this.name = hotel.getName();
		this.address = hotel.getAddress();
		this.description = hotel.getDescription();
		this.priceList = hotel.getPriceList();
		this.roomConfiguration = hotel.getRoomConfiguration();
		this.admins = hotel.getAdmins();
	}

	public Set<HotelAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<HotelAdmin> admins) {
		this.admins = admins;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<HotelsOffer> getPriceList() {
		return priceList;
	}

	public void setPriceList(Set<HotelsOffer> priceList) {
		this.priceList = priceList;
	}

	public Set<Room> getRoomConfiguration() {
		return roomConfiguration;
	}

	public void setRoomConfiguration(Set<Room> roomConfiguration) {
		this.roomConfiguration = roomConfiguration;
	}
}