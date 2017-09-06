package com.liberymutual.goforcode.rolodex.api;

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

import com.liberymutual.goforcode.rolodex.models.Address;
import com.liberymutual.goforcode.rolodex.models.Card;
import com.liberymutual.goforcode.rolodex.models.PhoneNumber;
import com.liberymutual.goforcode.rolodex.repositories.AddressRepository;
import com.liberymutual.goforcode.rolodex.repositories.CardRepository;
import com.liberymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards")
@Api(description = "Use this to get and create card data.")
@RestController
public class RolodexApiController {

    private CardRepository cardRepo;
    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;

    public RolodexApiController(CardRepository cardRepo) {
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

    @GetMapping("{id}")
    public Card getOne(@PathVariable long id) {
        Card card = cardRepo.findOne(id);
        return card;
    }

    @PutMapping("{id}")
    public Card update(@RequestBody Card card, @PathVariable long id) {
        card.setId(id);
        return cardRepo.save(card);
    }

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
    
    @DeleteMapping("{id}/addresses/{add_id}")
    public Address deleteAddress(@PathVariable long id, @PathVariable long add_id) {
        try {
            Address address = addressRepo.findOne(id);
            addressRepo.delete(id);
            return address;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
    
    @DeleteMapping("{id}/phoneNumbers/{pho_id}")
    public PhoneNumber deletePhoneNumber(@PathVariable long id, @PathVariable long pho_id) {
        try {
            PhoneNumber phoneNumber = phoneRepo.findOne(id);
            phoneRepo.delete(id);
            return phoneNumber;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    @PostMapping("{id}")
    public Card associateAnAddress(@PathVariable long cardId, @RequestBody Address address) {
        Card card = cardRepo.findOne(cardId);
        address = addressRepo.findOne(address.getId());
        card.addAddress(address);
        cardRepo.save(card);
        return card;
    }

    @PostMapping("{id}")
    public Card associateAPhoneNumber(@PathVariable long cardId, @RequestBody PhoneNumber phoneNumber) {
        Card card = cardRepo.findOne(cardId);
        phoneNumber = phoneRepo.findOne(phoneNumber.getId());
        card.addPhoneNumber(phoneNumber);
        cardRepo.save(card);
        return card;
    }
}
