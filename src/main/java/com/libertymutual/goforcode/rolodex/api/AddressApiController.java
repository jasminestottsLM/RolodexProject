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
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards/{id}/addresses")
@Api(description = "Create, get, update, and delete addresses.")
@RestController
public class AddressApiController {

	private AddressRepository addressRepo;
	private CardRepository cardRepo;
	
	public AddressApiController(AddressRepository addressRepo, CardRepository cardRepo) {
		this.addressRepo = addressRepo;
		this.cardRepo = cardRepo;
	}


	@GetMapping("")
	public List<Address> getAll() {
		return addressRepo.findAll();
	}

	
	@GetMapping("{add_id}")
	public Address getOne(@PathVariable long id) throws StuffNotFoundException {
		Address address = addressRepo.findOne(id);
		if (address == null) { 
			throw new StuffNotFoundException();
		}

		return address;
	} 
	   
	@DeleteMapping("{add_id}")
    public Address deleteAddress(@PathVariable long id, @PathVariable long add_id) {
        try {
            Address address = addressRepo.findOne(add_id);
            addressRepo.delete(add_id);
            return address;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
	
	@PostMapping("")
	public Card create(@PathVariable long id, @RequestBody Address address) {
		Card card = cardRepo.findOne(id);     
        card.addAddress(address);
        address.setCard(card);
        addressRepo.save(address);
        cardRepo.save(card);
        return card; 
	}   
	 
	@PutMapping("{add_id}")
	public Address update(@RequestBody Address address, @PathVariable long id) {
		address.setId(id);
		return addressRepo.save(address);
	}
}