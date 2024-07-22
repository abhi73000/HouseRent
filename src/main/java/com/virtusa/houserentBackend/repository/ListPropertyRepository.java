package com.virtusa.houserentBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.houserentBackend.entity.ListProperty;

public interface ListPropertyRepository extends JpaRepository<ListProperty, Long> {
	
//	@Query("SELECT p FROM ListProperty p WHERE p.propertyId IN (SELECT l.locationId FROM Location l WHERE l.city = ?1) AND p.status = 'vacant'")
//	@Query("SELECT p FROM ListProperty p WHERE p.propertyId IN (SELECT l.locationId FROM Location l WHERE l.city = ?1) AND p.status = 'vacant'")
//	public List<ListProperty> getAllPropertyByCity(String city);
//	
	@Query("SELECT p FROM ListProperty p WHERE p.propertyId IN (SELECT l.locationId FROM Location l WHERE l.city = ?1) AND p.status = 'vacent'")
	public List<ListProperty> getAllPropertyByCity(String city);

	@Query("FROM ListProperty lp WHERE lp.status = 'vacent'")
	public List<ListProperty> getAllVacentProperty();
	
	

}
