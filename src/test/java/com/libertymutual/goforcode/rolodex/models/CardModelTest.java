package com.libertymutual.goforcode.rolodex.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;

public class CardModelTest {

    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;
    
    @Before
    public void setUp() {
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

