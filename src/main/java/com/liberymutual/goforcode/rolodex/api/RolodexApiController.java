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
	}
	
	@GetMapping("")
	public List<Card> getAll() {
		return cardRepo.findAll();
	}
	
	@PostMapping("")
	public Card create(@RequestBody Card card) {
		return cardRepo.save(card);
	}  
	
}
