package com.liberymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.liberymutual.goforcode.rolodex.models.Card;
import com.liberymutual.goforcode.rolodex.repositories.CardRepository;

public class RolodexApiControllerTests {

	private CardRepository cardRepo;
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
