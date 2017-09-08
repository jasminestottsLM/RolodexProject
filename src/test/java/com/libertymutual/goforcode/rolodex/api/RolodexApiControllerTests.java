package com.libertymutual.goforcode.rolodex.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.rolodex.api.RolodexApiController;
import com.libertymutual.goforcode.rolodex.api.StuffNotFoundException;
import com.libertymutual.goforcode.rolodex.models.Card;
import com.libertymutual.goforcode.rolodex.repositories.CardRepository;

public class RolodexApiControllerTests {

    private CardRepository cardRepo;

    private RolodexApiController controller;

    @Before
    public void setUp() {
        cardRepo = mock(CardRepository.class);
        controller = new RolodexApiController(cardRepo);
    }

    @Test
    public void test_getOne_returns_Card_from_repo() throws StuffNotFoundException {
        Card card = new Card();
        when(cardRepo.findOne(7l)).thenReturn(card);
        Card actual = controller.getOne(7l);
        assertThat(actual).isSameAs(card);
        verify(cardRepo).findOne(7l);
    }

    @Test
    public void test_delete_returns_card_deleted_when_found() {
        Card card = new Card();
        when(cardRepo.findOne(4l)).thenReturn(card);
        Card actual = controller.deleteOne(4l);
        assertThat(card).isSameAs(actual);
        verify(cardRepo).delete(4l);
        verify(cardRepo).findOne(4l);
    }

    @Test
    public void test_delete_throws_ERDA() throws StuffNotFoundException {
        when(cardRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
        Card actual = controller.deleteOne(4l);
        assertThat(actual).isNull();
        verify(cardRepo).findOne(4l);
    }

    @Test
    public void test_get_all() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card());
        cards.add(new Card());
        when(cardRepo.findAll()).thenReturn(cards);
        List<Card> actualCards = controller.getAll();
        assertThat(actualCards.size()).isEqualTo(2);
        assertThat(actualCards.get(0)).isSameAs(cards.get(0));
        verify(cardRepo).findAll();
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
    public void test_constructor_for_card() {
        Card card = new Card(1l, "Serena", "Zywicki", "Is Awesome");

        assertThat(card.getId()).isSameAs(1l);
        assertThat(card.getFirstName()).isSameAs("Serena");
        assertThat(card.getLastName()).isSameAs("Zywicki");
        assertThat(card.getTitle()).isSameAs("Is Awesome");
    }

    @Test
    public void test_update_returns_card_with_changes_made() throws StuffNotFoundException {
        Card card = new Card();
        when(cardRepo.save(card)).thenReturn(card);
        Card actual = controller.update(card, 3l);
        verify(cardRepo).save(card);
        assertThat(actual).isSameAs(card);
    }

}
