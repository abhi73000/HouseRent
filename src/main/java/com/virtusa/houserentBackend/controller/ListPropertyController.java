package com.virtusa.houserentBackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.houserentBackend.entity.ListProperty;
import com.virtusa.houserentBackend.entity.Location;
import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.service.ListPropertyService;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:4200")
public class ListPropertyController {
	@Autowired
	private ListPropertyService listPropertyService;
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/create")
	public ResponseEntity<?> createProperty(@RequestParam(name = "photo2") MultipartFile f2,
			@RequestParam("propertyName") String propertyName,
			@RequestParam("bhkType") String bhkType,
			@RequestParam("price") int price,
			@RequestParam("area") int area,
			@RequestParam("furnishedType") String furnishedType,
			@RequestParam("status") String status,
			@RequestParam("country") String country,
			@RequestParam("state") String state,
			@RequestParam("city") String city, @RequestParam("address") String address,
			@RequestParam("pincode") String pincode) {
		try {
			System.out.println("log!!!!!!!!!");
			// Create a new ListProperty object
			ListProperty newProperty = new ListProperty();
			// Map property details from the DTO to the ListProperty object
			newProperty.setPropertyName(propertyName);
			newProperty.setBhkType(bhkType);
			newProperty.setPrice(price);
			newProperty.setArea(area);
			newProperty.setFurnishedType(furnishedType);
			newProperty.setStatus(status);
			// Decode the base64-encoded photo and set it in the ListProperty
			byte[] base64Photo = f2.getBytes();

			newProperty.setPhoto(base64Photo);

			Location location = new Location();
			location.setCountry(country);
			location.setState(state);
			location.setCity(city);
			location.setAddress(address);
			location.setPincode(pincode);

			// Set the Location object in the ListProperty
			newProperty.setLocation(location);

			// Assuming you have a user ID available, set it in the ListProperty
//			User user = new User();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			System.out.println(username);

//			user.setUserId(userDetailsService.loadUserByUsername(username));
			newProperty.setUser((User) userDetailsService.loadUserByUsername(username));
			ListProperty savedProperty = listPropertyService.createProperty(newProperty);
			System.out.println(savedProperty.getUser().getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@GetMapping({ "/getall" })
	public ResponseEntity<List<ListProperty>> getAllProperties(@RequestParam(defaultValue = "") String searchKey) {
		try {
			return listPropertyService.getAllListProperties(searchKey);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<ListProperty>> getPropertyById(@PathVariable Long id) {
		Optional<ListProperty> listProperty = this.listPropertyService.getPropertyById(id);
		System.out.println(listProperty);
		return ResponseEntity.ok(listProperty);
	}

	@GetMapping("/userproperty/{id}")
	public ResponseEntity<List<ListProperty>> getPropertyByUserId(@PathVariable Long id) {
		List<ListProperty> listProperty = this.listPropertyService.getAllPropertyByUserId(id);
		System.out.println(listProperty);
		return ResponseEntity.ok(listProperty);
	}

	@DeleteMapping("deleteProperty/{propertyId}")
	public void deletePropertyById(@PathVariable Long propertyId) {
		this.listPropertyService.deletePropertyById(propertyId);
	}

	@PutMapping("/updateProperty")
	public ListProperty updateProperty(@RequestBody ListProperty updatePropertr) {
		System.out.println(updatePropertr);
		return this.listPropertyService.updateProperty(updatePropertr);
	}

	@GetMapping("/getPropertyByCity/{city}")
	public List<ListProperty> getAllPropertyByCity(@PathVariable String city) {
		return this.listPropertyService.getAllPropertyByCity(city);
	}

//	@GetMapping("/{status}")
//	public List<ListProperty> getAllPropertyByStatus(@PathVariable String status) {
//		return this.listPropertyRepository.getAllVacentProperty(status);
//	}
//	
	@RequestMapping("/getAllListed")
	public ResponseEntity<List<ListProperty>> getAllProperties() {
		return this.listPropertyService.getAllProperties();

	}

}
