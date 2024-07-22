package com.virtusa.houserentBackend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locationid")
	private Long locationId;

	@Column(name = "address", nullable = false, length = 50)
	private String address;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "state", nullable = false, length = 50)
	private String state;

	@Column(name = "country", nullable = false, length = 50)
	private String country;

	@Column(name = "pincode", nullable = false, length = 20)
	private String pincode;

	@OneToMany(mappedBy = "location")
	private List<ListProperty> properties;

	// Getters and setters for all fields, including properties

	public Location() {
		super();
	}

	public Location(Long locationId, String address, String city, String state, String country, String pincode,
			List<ListProperty> properties) {
		super();
		this.locationId = locationId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.properties = properties;
	}


	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}

	
	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@JsonIgnore
	public List<ListProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<ListProperty> properties) {
		this.properties = properties;
	}

}
