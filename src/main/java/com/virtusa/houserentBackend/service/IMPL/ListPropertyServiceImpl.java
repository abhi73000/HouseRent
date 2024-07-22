package com.virtusa.houserentBackend.service.IMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtusa.houserentBackend.entity.ListProperty;
import com.virtusa.houserentBackend.repository.ListPropertyRepository;
import com.virtusa.houserentBackend.service.ListPropertyService;

@Service
public class ListPropertyServiceImpl implements ListPropertyService {

	@Autowired
	private ListPropertyRepository listPropertyRepository;

	@Override
	public ListProperty createProperty(ListProperty property) {
		ListProperty listProperty = listPropertyRepository.save(property);
		return listProperty;

	}

	@Override
	public ResponseEntity<List<ListProperty>> getAllListProperties(String searchKey) {
		List<ListProperty> listProperties;

		if (searchKey == null || searchKey.isEmpty()) {
			listProperties = this.listPropertyRepository.getAllVacentProperty();
		} else {
			listProperties = this.listPropertyRepository.getAllPropertyByCity(searchKey);
		}

		return new ResponseEntity<>(listProperties, HttpStatus.OK);
	}

	@Override
	public Optional<ListProperty> getPropertyById(Long propertyId) {
		return this.listPropertyRepository.findById(propertyId);

	}

	@Override
	public List<ListProperty> getAllPropertyByUserId(Long userId) {
		List<ListProperty> list = this.listPropertyRepository.findAll();
		List<ListProperty> result = new ArrayList<ListProperty>();
		for (ListProperty obj : list) {
			if (obj.getUser().getUserId() == userId) {
				result.add(obj);
			}
		}
		return result;
	}

	@Override
	public void deletePropertyById(Long propertyId) {
		this.listPropertyRepository.deleteById(propertyId);

	}

	@Override
	public ListProperty updatePropertyByPropertyId(Long id, ListProperty updatedlistProperty) {
		this.listPropertyRepository.deleteById(id);

		return this.listPropertyRepository.save(updatedlistProperty);
	}

	@Override
	public List<ListProperty> getAllPropertyByCity(String city) {

		return this.listPropertyRepository.getAllPropertyByCity(city);
	}

	@Override
	public ListProperty updateProperty(ListProperty updatedProperty) {
		return this.listPropertyRepository.save(updatedProperty);
	}

	@Override
	public ResponseEntity<List<ListProperty>> getAllProperties() {
		List<ListProperty> listProperties = listPropertyRepository.findAll();
		return ResponseEntity.ok(listProperties);
	}

}
