package com.virtusa.houserentBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "houserentrequest")
public class HouseRentRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="requestid")
	private Long requestId;

	@Column(nullable = true)
	private String request;

	@ManyToOne
	@JoinColumn(name = "propertyid", referencedColumnName = "propertyid")
	private ListProperty property;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;

	public HouseRentRequest() {
		super();
	}

	public HouseRentRequest(Long requestId, String request, ListProperty property, User user) {
		super();
		this.requestId = requestId;
		this.request = request;
		this.property = property;
		this.user = user;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public ListProperty getProperty() {
		return property;
	}

	public void setProperty(ListProperty property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
