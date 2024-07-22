package com.virtusa.houserentBackend.service.IMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.houserentBackend.entity.HouseRentRequest;
import com.virtusa.houserentBackend.repository.HouseRentRequestRepository;
import com.virtusa.houserentBackend.service.HouseRentRequestService;

@Service
public class HouseRentRequestServiceImpl implements HouseRentRequestService {

	@Autowired
	private HouseRentRequestRepository houseRentRequestRepository;

	@Override
	public HouseRentRequest createRequest(HouseRentRequest rentRequest) {
		return this.houseRentRequestRepository.save(rentRequest);
	}

	@Override
	public List<HouseRentRequest> getAllRequests() {
		
		return this.houseRentRequestRepository.findAll();
	}

	@Override
	public void deteteRequestById(Long reqId) {
		this.houseRentRequestRepository.deleteById(reqId);
	}

	@Override
	public Optional<HouseRentRequest> getAllPropertyByPropertyId(Long id) {
		return this.houseRentRequestRepository.findById(id);
	}

	@Override
	public List<HouseRentRequest> getAllPropertyRequest(Long ownerId) {
		return this.houseRentRequestRepository.getPropertyRequest(ownerId);
	}
}
