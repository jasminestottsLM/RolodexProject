package com.libertymutual.goforcode.rolodex.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.rolodex.api.RolodexApiController;
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

public class CardModelTest {

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
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Card.class);
    }

	
	@Test
    public void add_Address_sets_Address() {
    	Card card = new Card();
    	Address address = new Address();
    	when(cardRepo.findOne(4l)).thenReturn(card);
    	when(addressRepo.findOne(4l)).thenReturn(address);
    	
    	Card actualCard = controller.associateAnAddress(4l, address);
    	
    	verify(cardRepo).findOne(4l);
    	assertThat(card).isSameAs(actualCard);
    }
	
	@Test
    public void add_PhoneNumber_sets_PhoneNumber() {
    	Card card = new Card();
    	PhoneNumber phone = new PhoneNumber();
    	when(cardRepo.findOne(4l)).thenReturn(card);
    	when(phoneRepo.findOne(4l)).thenReturn(phone);
    	
    	Card actualCard = controller.associateAPhoneNumber(4l, phone);
    	
    	verify(cardRepo).findOne(4l);
    	assertThat(card).isSameAs(actualCard);
    }

	
}

