package com.virtusa.houserentBackend.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.houserentBackend.entity.Location;
import com.virtusa.houserentBackend.repository.LocationRepository;
import com.virtusa.houserentBackend.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Location saveLocation(Location location) {
		
		return locationRepository.save(location);
	}

}
