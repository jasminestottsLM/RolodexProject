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



public class AddressApiControllerTests { 

    private CardRepository cardRepo;
    private AddressRepository addressRepo;
    private PhoneNumberRepository phoneRepo;
    private AddressApiController controller;

    @Before
    public void setUp() {
        cardRepo = mock(CardRepository.class);
        addressRepo = mock(AddressRepository.class);
        phoneRepo = mock(PhoneNumberRepository.class);
        controller = new AddressApiController(addressRepo, cardRepo);
    }
    
    @Test
    public void test_get_all_address() {
        ArrayList<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address());
        addresses.add(new Address());
        when(addressRepo.findAll()).thenReturn(addresses);
        List<Address> actualAddress = controller.getAll();
        assertThat(actualAddress.size()).isEqualTo(2);
        assertThat(actualAddress.get(0)).isSameAs(addresses.get(0));
        verify(addressRepo).findAll();
    }
    
    @Test
    public void test_getOne_returns_Address_from_repo() throws StuffNotFoundException {
        Address address = new Address();
        when(addressRepo.findOne(7l)).thenReturn(address);
        Address actual = controller.getOne(7l);
        verify(addressRepo).findOne(7l);
        assertThat(actual).isSameAs(address);
    }
    
    @Test
	public void test_getOne_throws_StuffNotFoundException_when_no_address_returned_from_repo() {
    	Address address = new Address();
    	when(addressRepo.findOne(2l)).thenReturn(null);
    	
    	Address actual;
		try {
			actual = controller.getOne(2l);
		} catch (StuffNotFoundException snfe) {
		
		}
	}
    
    @Test
    public void test_delete_throws_ERDA() throws StuffNotFoundException {
        when(addressRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0)); 
    }

    @Test
    public void test_delete_returns_address_deleted_when_found() {
     Card card = new Card();
     Address address = new Address();
     when(cardRepo.findOne(2l)).thenReturn(card);
     when(addressRepo.findOne(4l)).thenReturn(address);
     
     Address actual = controller.deleteAddress(2l, 4l);
     
     assertThat(actual).isSameAs(address);
     verify(addressRepo).delete(4l);
     verify(addressRepo).findOne(4l);
	
}
	
    @Test
    public void test_delete_address_throws_ERDA() throws StuffNotFoundException {
        when(addressRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
        Address actual = controller.deleteAddress(2l, 4l);
        assertThat(actual).isNull();
        verify(addressRepo).findOne(4l);
    }	
	
	@Test
	public void test_update_returns_address_with_changes_made() throws StuffNotFoundException {
		Address address = new Address();
		when(addressRepo.save(address)).thenReturn(address);
		
		// Act
		Address actual = controller.update(address, 3l);
		
		// Assert
		verify(addressRepo).save(address);
		assertThat(actual).isSameAs(address);
	}

	@Test
	public void test_create_saves_and_returns_a_card_with_address() throws StuffNotFoundException {
		
		//Arrange
		Card card = new Card();
		Address address = new Address();
		ArrayList<Address> addresses = new ArrayList<>();
		when(addressRepo.findOne(2l)).thenReturn(address);
		when(cardRepo.findOne(3l)).thenReturn(card);
		
		//Act
		addresses.add(address);
		controller.create(3l, address);
				
		//Assert 
		assertThat(card.getAddresses()).contains(address);
	}
}
