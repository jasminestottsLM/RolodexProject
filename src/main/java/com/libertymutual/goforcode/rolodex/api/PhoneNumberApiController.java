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
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.models.PhoneNumber;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards/{id}/phones")
@Api(description = "Create, get, update, and delete phone numbers.")
@RestController
public class PhoneNumberApiController {

    private PhoneNumberRepository phoneRepo;
    private CardRepository cardRepo;

    public PhoneNumberApiController(PhoneNumberRepository phoneRepo, CardRepository cardRepo) {
        this.phoneRepo = phoneRepo;
        this.cardRepo = cardRepo;
    }

    @GetMapping("")
    public List<PhoneNumber> getAll() {
        return phoneRepo.findAll();
    }

    @GetMapping("{add_id}")
    public PhoneNumber getOne(@PathVariable long id, @PathVariable long pho_id) throws StuffNotFoundException {
        PhoneNumber phone = phoneRepo.findOne(pho_id);
        if (phone == null) {
            throw new StuffNotFoundException();
        }

        return phone;
    }

    @DeleteMapping("{pho_id}")
    public PhoneNumber deletePhone(@PathVariable long id, @PathVariable long pho_id) {
        try {
            PhoneNumber phone = phoneRepo.findOne(pho_id);
            phoneRepo.delete(pho_id);
            return phone;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    @PostMapping("")
    public Card create(@PathVariable long id, @RequestBody PhoneNumber phone) {
        Card card = cardRepo.findOne(id);
        card.addPhoneNumber(phone);
        phone.setCard(card);
        phoneRepo.save(phone);
        cardRepo.save(card);
        return card;
    }

    @PutMapping("{pho_id}")
    public PhoneNumber update(@RequestBody PhoneNumber phone, @PathVariable long id, @PathVariable long pho_id) {
        Card card = cardRepo.findOne(id);
        phone.setCard(card);
    	phone.setId(pho_id);
        return phoneRepo.save(phone);
    }
}