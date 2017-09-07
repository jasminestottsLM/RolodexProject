package com.libertymutual.goforcode.rolodex.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.rolodex.api.StuffNotFoundException;
import com.libertymutual.goforcode.rolodex.models.Address;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards")
@Api(description = "Create, get, update, and delete addresses.")
@RestController
public class AddressApiController {

	private AddressRepository addressRepo;
	
	public AddressApiController(AddressRepository addressRepo) {
		this.addressRepo = addressRepo;
	}


	@GetMapping("/addresses")
	public List<Address> getAll() {
		return addressRepo.findAll();
	}

	
	@GetMapping("/addresses/{id}")
	public Address getOne(@PathVariable long id) throws StuffNotFoundException {
		Address address = addressRepo.findOne(id);
		if (address == null) {
			throw new StuffNotFoundException();
		}

		return address;
	} 
	   
	@DeleteMapping("{id}/addresses/{add_id}")
    public Address deleteAddress(@PathVariable long id, @PathVariable long add_id) {
        try {
            Address address = addressRepo.findOne(add_id);
            addressRepo.delete(add_id);
            return address;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
	
	@PostMapping("/addresses")
	public Address create(@RequestBody Address address) {
		return addressRepo.save(address);
	}  
	 
	@PutMapping("{id}/addresses/{add_id}")
	public Address update(@RequestBody Address address, @PathVariable long id) {
		address.setId(id);
		return addressRepo.save(address);
	}
}