package com.project.project.dto;

import java.util.List;
import com.project.project.model.Hotel;
import com.project.project.model.HotelsOffer;
import com.project.project.model.Room;

import javax.validation.constraints.NotNull;

public class HotelDTO {
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

    public HotelDTO(Hotel hotel){
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
        this.priceList = hotel.getPriceList();
        this.roomConfiguration = hotel.getRoomConfiguration();
    }

    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}