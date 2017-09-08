package com.libertymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
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
    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;
    private PhoneNumberApiController controller;

    @Before
    public void setUp() {
        cardRepo = mock(CardRepository.class);
        addressRepo = mock(AddressRepository.class);
        phoneRepo = mock(PhoneNumberRepository.class);
        controller = new PhoneNumberApiController(phoneRepo, cardRepo);
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
	
	
	
}
