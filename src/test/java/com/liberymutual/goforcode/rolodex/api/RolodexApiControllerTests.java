package com.liberymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.liberymutual.goforcode.rolodex.models.Card;
import com.liberymutual.goforcode.rolodex.repositories.AddressRepository;
import com.liberymutual.goforcode.rolodex.repositories.CardRepository;
import com.liberymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

public class RolodexApiControllerTests {

	private CardRepository cardRepo;
	private AddressRepository addressRepo;
	private PhoneNumberRepository phonenoRepo;
	private RolodexApiController controller;
	
	@Before
	public void setUp() {
		cardRepo = mock(CardRepository.class);
		controller = new RolodexApiController(cardRepo);
	}
	
	@Test
	public void test_create_adds_a_card_record() {
		Card card = new Card();
		when(cardRepo.save(card)).thenReturn(card);
		
		Card actualCard = controller.create(card);
		
		verify(cardRepo).save(card);
		assertThat(controller.create(actualCard)).isSameAs(card);
	}

}

