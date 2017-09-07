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

import com.libertymutual.goforcode.rolodex.models.Address;
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.models.PhoneNumber;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards")
@Api(description = "Use this to get and create card data.")
@RestController
public class RolodexApiController {

    private CardRepository cardRepo;
    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;

    public RolodexApiController(CardRepository cardRepo, AddressRepository addressRepo, PhoneNumberRepository phoneRepo) {
        this.cardRepo = cardRepo;
        this.addressRepo = addressRepo;
        this.phoneRepo = phoneRepo;

        Card card = new Card();
        card.setFirstName("Ferg");
        card.setLastName("Fergusson");
        card.setTitle("FergsTitle");
        cardRepo.save(card);

        card = new Card();
        card.setFirstName("Walt");
        card.setLastName("Waltington");
        card.setTitle("WaltsTitle");
        cardRepo.save(card);

        card = new Card();
        card.setFirstName("Branch");
        card.setLastName("Brancherson");
        card.setTitle("BranchsTitle");
        cardRepo.save(card);

        card = new Card();
        card.setFirstName("Vic");
        card.setLastName("Victoria");
        card.setTitle("VicsTitle");
        cardRepo.save(card);
    }

    @GetMapping("")
    public List<Card> getAll() {
        return cardRepo.findAll();
    }

    @PostMapping("")
    public Card create(@RequestBody Card card) {
        return cardRepo.save(card); 
    }
    
//    @PostMapping("{id}/addresses")
//    public Address create(@PathVariable long id, @RequestBody Address address) {
//    	Card card = cardRepo.findOne(id);     
//        card.addAddress(address);
//        cardRepo.save(card);
//        return addressRepo.save(address); 
//    }

    @GetMapping("{id}")
    public Card getOne(@PathVariable long id) throws StuffNotFoundException {
        Card card = cardRepo.findOne(id);
        return card;
    }

    @PutMapping("{id}")
    public Card update(@RequestBody Card card, @PathVariable long id) throws StuffNotFoundException {
        card.setId(id);
        return cardRepo.save(card);
    }
    
    @PutMapping("{id}/phoneNumbers/{pho_id}")
    public PhoneNumber updatePhone(@RequestBody PhoneNumber phone, @PathVariable long id) throws StuffNotFoundException {
        phone.setId(id);
        return phoneRepo.save(phone);
    }

//    @PutMapping("{id}/addresses/{add_id}")
//    public Address updateAddress(@RequestBody Address address, @PathVariable long id) throws StuffNotFoundException {
//        address.setId(id);
//        return addressRepo.save(address);
//    }
    
    @DeleteMapping("{id}")
    public Card deleteOne(@PathVariable long id) {
        try {
            Card card = cardRepo.findOne(id);
            cardRepo.delete(id); 
            return card;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
    
    
    @DeleteMapping("{id}/phoneNumbers/{pho_id}")
    public PhoneNumber deletePhoneNumber(@PathVariable long id, @PathVariable long pho_id) {
        try { 
            PhoneNumber phoneNumber = phoneRepo.findOne(pho_id);
            phoneRepo.delete(pho_id);
            return phoneNumber;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

//    @PostMapping("{cardId}/addresses")
//    public Card associateAnAddress(@PathVariable long cardId, @RequestBody Address address) {
//        Card card = cardRepo.findOne(cardId);     
//        address = addressRepo.findOne(address.getId());
//        card.addAddress(address);
//        cardRepo.save(card);
//        return card;
//    }

    @PostMapping("{id}/phones")
    public Card associateAPhoneNumber(@PathVariable long cardId, @RequestBody PhoneNumber phoneNumber) {
        Card card = cardRepo.findOne(cardId);
        phoneNumber = phoneRepo.findOne(phoneNumber.getId());
        card.addPhoneNumber(phoneNumber);
        cardRepo.save(card);
        return card;
    }
}
