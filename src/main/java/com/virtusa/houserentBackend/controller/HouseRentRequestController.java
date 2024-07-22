package com.virtusa.houserentBackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.houserentBackend.entity.HouseRentRequest;
import com.virtusa.houserentBackend.service.HouseRentRequestService;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseRentRequestController {

	@Autowired
	private HouseRentRequestService houseRentRequestService;

	@PostMapping("/create")
	public HouseRentRequest createRentRequest(@RequestBody HouseRentRequest houseRentRequest) {
		System.out.println(houseRentRequest.toString());
		return this.houseRentRequestService.createRequest(houseRentRequest);
	}

	@GetMapping("/getall")
	public List<HouseRentRequest> getAllRequests() {
		return this.houseRentRequestService.getAllRequests();
	}

	@DeleteMapping("/deletereq/{reqId}")
	public void deleteReq(@PathVariable Long reqId) {
		this.houseRentRequestService.deteteRequestById(reqId);
	}

	@GetMapping("/get/{id}")
	public Optional<HouseRentRequest> getAllPropertyRequests(@PathVariable Long id) {
		return this.houseRentRequestService.getAllPropertyByPropertyId(id);

	}

	@GetMapping("/getreq/{id}")
	public List<HouseRentRequest> getAllPropertyByRequestsId(@PathVariable Long id) {
		System.out.println(id);
		return this.houseRentRequestService.getAllPropertyRequest(id);

	}

}
