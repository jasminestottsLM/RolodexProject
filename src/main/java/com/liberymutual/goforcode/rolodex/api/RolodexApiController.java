package com.liberymutual.goforcode.rolodex.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberymutual.goforcode.rolodex.models.Card;
import com.liberymutual.goforcode.rolodex.repositories.CardRepository;

import io.swagger.annotations.Api;

@RequestMapping("/cards")
@Api(description = "Use this to get and create card data.")
@RestController
public class RolodexApiController {

	private CardRepository cardRepo;
	
	public RolodexApiController(CardRepository cardRepo) {
		this.cardRepo = cardRepo;
		
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
	
//	@GetMapping("{id}")
//	public Card getOne(@PathVariable long id) {
//		Card card = cardRepo.findOne(id);
//		return card;
//	}
//	
//	@PutMapping("{id}")
//	public Card update(@RequestBody Card card, @PathVariable long id) {
//		card.setId(id);
//		return cardRepo.save(card);
//	}
//	
//	@DeleteMapping("{id}")
//	public Card deleteOne(@PathVariable long id) {
//		try {
//			Card card = cardRepo.findOne(id);
//			cardRepo.delete(id);
//			return card;
//		} catch (EmptyResultDataAccessException erdae) {
//			return null;
//		}
//	}
}
