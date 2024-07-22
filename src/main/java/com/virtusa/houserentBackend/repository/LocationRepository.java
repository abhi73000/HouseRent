package com.virtusa.houserentBackend.repository;

import org.springframework.data.repository.CrudRepository;

import com.virtusa.houserentBackend.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

	Location findByAddressAndCityAndStateAndCountryAndPincode(String address, String city, String state, String country,
			String pincode);

}
