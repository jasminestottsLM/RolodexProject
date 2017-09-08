package com.libertymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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

import com.libertymutual.goforcode.rolodex.api.RolodexApiController;
import com.libertymutual.goforcode.rolodex.api.StuffNotFoundException;
import com.libertymutual.goforcode.rolodex.models.Address;
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.models.PhoneNumber;
import com.libertymutual.goforcode.rolodex.repositories.AddressRepository;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;
import com.libertymutual.goforcode.rolodex.repositories.PhoneNumberRepository;







public class PhoneNumberApiControllerTests { 

    private CardRepository cardRepo;
    private PhoneNumberRepository phoneRepo;
    private PhoneNumberApiController controller;

    @Before
    public void setUp() {
        cardRepo = mock(CardRepository.class);
        phoneRepo = mock(PhoneNumberRepository.class);
        controller = new PhoneNumberApiController(phoneRepo, cardRepo);
    }

    @Test
	public void test_getAll_returns_all_phoneNumbers_returned_by_the_repo() {
		// Arrange
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber());
		phoneNumbers.add(new PhoneNumber());
		phoneNumbers.add(new PhoneNumber());
		
		when(phoneRepo.findAll()).thenReturn(phoneNumbers);
		
		// Act
		List<PhoneNumber> actual = controller.getAll();
		
		// Assert 
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0)).isSameAs(phoneNumbers.get(0));
		verify(phoneRepo).findAll();
	}
    
    @Test
	public void test_getOne_returns_phoneNumber_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		PhoneNumber phone = new PhoneNumber();
		when(phoneRepo.findOne(2l)).thenReturn(phone);
		
		// Act
		PhoneNumber actual = controller.getOne(2l);
		
		//Assert
		assertThat(actual).isSameAs(phone);
		verify(phoneRepo).findOne(2l);
		
	}
       
    @Test
	public void test_getOne_throws_StuffNotFoundException_when_no_phone_returned_from_repo() {
    	PhoneNumber phone = new PhoneNumber();
    	when(phoneRepo.findOne(2l)).thenReturn(null);
    	
    	PhoneNumber actual;
		try {
			actual = controller.getOne(2l);
		} catch (StuffNotFoundException snfe) {
		
		}
	}
    
    @Test
	public void test_delete_returns_phoneNumber_deleted_when_found() {
	Card card = new Card();
	PhoneNumber phoneNumber = new PhoneNumber();
	when(cardRepo.findOne(2l)).thenReturn(card);
	when(phoneRepo.findOne(4l)).thenReturn(phoneNumber);
	
	PhoneNumber actual = controller.deletePhone(2l, 4l);
	
	assertThat(actual).isSameAs(phoneNumber);
	verify(phoneRepo).delete(4l);
	verify(phoneRepo).findOne(4l);
	
	}    
	
	@Test
    public void test_delete_phone_throws_ERDA() throws StuffNotFoundException {
    when(phoneRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
    PhoneNumber actual = controller.deletePhone(2l, 4l);
    assertThat(actual).isNull();
    verify(phoneRepo).findOne(4l);
	
	}
	
	@Test
	public void test_create_saves_and_returns_a_card_with_phone_number() throws StuffNotFoundException {
		
		//Arrange
		Card card = new Card();
		PhoneNumber phone = new PhoneNumber();
		ArrayList<PhoneNumber> phones = new ArrayList<>();
		when(phoneRepo.findOne(2l)).thenReturn(phone);
		when(cardRepo.findOne(3l)).thenReturn(card);
		
		//Act
		phones.add(phone);
		controller.create(3l, phone);
				
		//Assert 
		assertThat(card.getPhoneNumbers()).contains(phone);
	}
	
	
	@Test
	public void test_update_returns_phoneNumber_with_changes_made() throws StuffNotFoundException {
		// Arrange
		PhoneNumber phone = new PhoneNumber();
		when(phoneRepo.save(phone)).thenReturn(phone);
		
		// Act
		PhoneNumber actual = controller.update(phone, 3l);
		
		// Assert
		verify(phoneRepo).save(phone);
		assertThat(actual).isSameAs(phone);
	}
	
}
