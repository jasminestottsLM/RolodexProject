package com.libertymutual.goforcode.rolodex.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.rolodex.api.AddressApiController;
import com.libertymutual.goforcode.rolodex.api.RolodexApiController;
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

public class CardModelTest {

    private CardRepository cardRepo;
    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;
    private AddressApiController controller;

    @Before
    public void setUp() {
        cardRepo = mock(CardRepository.class);
        addressRepo = mock(AddressRepository.class);
        phoneRepo = mock(PhoneNumberRepository.class);
    }
  	
	@Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Card.class);
    }

	@Test
	public void test_add_address_adds() {
		Address address = addressRepo.getOne(2l);
		Card card = new Card();
		
		card.addAddress(address);

		assertThat(card.getAddresses()).contains(address);
		
	}
	
	@Test
	public void test_add_phone_adds() {
		PhoneNumber phone = phoneRepo.getOne(3l);
		Card card = new Card();
		
		card.addPhoneNumber(phone);

		assertThat(card.getPhoneNumbers()).contains(phone);
		
	}

	
}

