package tdd_blackjack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tdd_blackjack.model.Hand;
import static tdd_blackjack.model.Card.*;

public class HandTest {

    @Test
    public void value_with_one_card() {
        assertEquals(3, new Hand(_3).value());
        assertEquals(10, new Hand(_10).value());
        assertEquals(10, new Hand(Jack).value());
        assertEquals(10, new Hand(Queen).value());
        assertEquals(10, new Hand(King).value());
        assertEquals(11, new Hand(Ace).value());
    }

    @Test
    public void value_with_two_cards() {
        assertEquals(8, new Hand(_3, _5).value());
        assertEquals(15, new Hand(_5, Jack).value());
        assertEquals(12, new Hand(Ace, Ace).value());
    }

    @Test
    public void given_2_cards_determine_if_is_black_jack() {
        assertEquals(false, new Hand(_5, _6).isBlackJack());
        assertEquals(true, new Hand(Ace, King).isBlackJack());
    }

    @Test
    public void given_3_cards_determine_if_is_black_jack() {
        assertEquals(false, new Hand(_5, _6, Jack).isBlackJack());
    }

    @Test
    public void determine_if_is_bust() {
        assertEquals(false, new Hand(_5, _3).isBust());
        assertEquals(true, new Hand(_5, _5, King, _2).isBust());
    }
}
