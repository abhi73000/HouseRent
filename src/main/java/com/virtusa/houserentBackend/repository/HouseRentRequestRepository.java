package com.virtusa.houserentBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.houserentBackend.entity.HouseRentRequest;

public interface HouseRentRequestRepository extends JpaRepository<HouseRentRequest, Long> {


	@Query(value="SELECT * FROM houserentrequest where propertyid IN (select propertyid from listproperty where userid = ?1)",  nativeQuery = true)
	public List<HouseRentRequest> getPropertyRequest(Long ownerId);
}
