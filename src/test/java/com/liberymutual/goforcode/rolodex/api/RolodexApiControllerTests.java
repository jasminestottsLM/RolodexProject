package com.liberymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.libertymutual.goforcode.rolodex.api.StuffNotFoundException;
import com.liberymutual.goforcode.rolodex.models.Card;
import com.liberymutual.goforcode.rolodex.repositories.AddressRepository;
import com.liberymutual.goforcode.rolodex.repositories.CardRepository;
import com.liberymutual.goforcode.rolodex.repositories.PhoneNumberRepository;


public class RolodexApiControllerTests {

	private CardRepository cardRepo;
	private AddressRepository addressRepo;
	private PhoneNumberRepository phoneRepo;
	private RolodexApiController controller;
	
	@Before
	public void setUp() {
		cardRepo = mock(CardRepository.class);
		addressRepo = mock(AddressRepository.class);
		phoneRepo = mock(PhoneNumberRepository.class);
		controller = new RolodexApiController(cardRepo, addressRepo, phoneRepo);
	}
	
	@Test
	public void test_create_adds_a_card_record() {
		Card card = new Card();
		when(cardRepo.save(card)).thenReturn(card);
		
		Card actualCard = controller.create(card);
		
		verify(cardRepo).save(card);
		assertThat(controller.create(actualCard)).isSameAs(card);
	}

	@Test
	public void test_getAll() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card());
		cards.add(new Card());
		when(cardRepo.findAll()).thenReturn(cards);
		
		List<Card> actualCards = controller.getAll();
		
		assertThat(actualCards.size()).isEqualTo(2);
		assertThat(actualCards.get(0)).isSameAs(cards.get(0));
		assertThat(actualCards.get(1)).isSameAs(cards.get(1));
		verify(cardRepo).findAll();
	}
	
	@Test
	public void test_getOne() throws StuffNotFoundException {
		Card testCard = new Card();
		when(cardRepo.findOne(1l)).thenReturn(testCard);
		
		Card actual = controller.getOne(11);
		
		assertThat(actual).isSameAs(testCard);
		verify(cardRepo).findOne(1l);
	}
	
}

