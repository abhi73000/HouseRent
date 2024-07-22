package com.virtusa.houserentBackend.entity;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "listproperty")
public class ListProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "propertyid")
	private Long propertyId;

	@Column(name = "bhktype", nullable = false, length = 100)
	private String bhkType;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locationid", referencedColumnName = "locationid")
	private Location location;

	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private int price;

	@Column(name = "area", nullable = false, precision = 10, scale = 2)
	private int area;

	@Column(name = "furnishedtype", nullable = false, length = 100)
	private String furnishedType;

	@Column(name = "propertyname", nullable = false, length = 50)
	private String propertyName;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;

	@OneToMany(mappedBy = "property")
	private List<HouseRentRequest> rentalRequests;

	public ListProperty() {
		super();
	}



	public ListProperty(Long propertyId, String bhkType, User user, Location location, int price, int area,
			String furnishedType, String propertyName, String status, byte[] photo) {
		super();
		this.propertyId = propertyId;
		this.bhkType = bhkType;
		this.user = user;
		this.location = location;
		this.price = price;
		this.area = area;
		this.furnishedType = furnishedType;
		this.propertyName = propertyName;
		this.status = status;
		this.photo = photo;
	}



	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public String getBhkType() {
		return bhkType;
	}

	public void setBhkType(String bhkType) {
		this.bhkType = bhkType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getFurnishedType() {
		return furnishedType;
	}

	public void setFurnishedType(String furnishedType) {
		this.furnishedType = furnishedType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
