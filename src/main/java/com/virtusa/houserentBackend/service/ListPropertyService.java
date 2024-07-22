package com.virtusa.houserentBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.virtusa.houserentBackend.entity.ListProperty;

public interface ListPropertyService {

	public ListProperty createProperty(ListProperty property);

	public ResponseEntity<List<ListProperty>> getAllListProperties(String searchKey);

	public Optional<ListProperty> getPropertyById(Long propertyId);

	public List<ListProperty> getAllPropertyByUserId(Long userId);

	public void deletePropertyById(Long propertyId);

	public ListProperty updatePropertyByPropertyId(Long id, ListProperty listProperty);
	
	public List<ListProperty> getAllPropertyByCity(String city);
	
	public ListProperty updateProperty(ListProperty updatedProperty) ;
	
	public ResponseEntity<List<ListProperty>> getAllProperties();
	

}
