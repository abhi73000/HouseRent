package com.virtusa.houserentBackend.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.houserentBackend.entity.HouseRentRequest;


public interface HouseRentRequestService {
	
	public HouseRentRequest createRequest(HouseRentRequest rentRequest);
	
	public List<HouseRentRequest> getAllRequests();
	
	public void deteteRequestById(Long reqId);
	
	public List<HouseRentRequest> getAllPropertyRequest(Long ownerId);
	
	public Optional<HouseRentRequest> getAllPropertyByPropertyId(Long id);

	
	
	

}
