package tdd_blackjack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tdd_blackjack.model.Card;
import tdd_blackjack.model.Game;
import tdd_blackjack.model.Hand;
import tdd_blackjack.model.Player;
import static tdd_blackjack.model.Card.*;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
    public void crupier_have_blackjack() {

        Player player = new Player("Player1", new Hand(Ace, King));
        Player crupier = new Player("Crupier", new Hand(Ace, King));

        assertEquals(List.of(), new Game(crupier, List.of(player), List.of(_10, Ace, King)).getWinners());
    }

    @Test
    public void player_have_blackjack_and_crupier_dont() {
        Player player = new Player("Player1", new Hand(Ace, King));
        Player crupier = new Player("Crupier", new Hand(Ace, _5, _5));

        assertEquals(List.of(player), new Game(crupier, List.of(player), List.of(_10, Ace, King)).getWinners());
    }

    @Test
    public void crupier_have_more_points_than_player() {
        Player player = new Player("Player1", new Hand(_5, King));
        Player crupier = new Player("Crupier", new Hand(Queen, _6, Ace));

        assertEquals(List.of(),
                new Game(crupier, List.of(player), deckFrom(_10, Ace, King)).getWinners());
    }

    @Test
    public void player_have_more_points_than_crupier() {
        Player player = new Player("Player1", new Hand(_6, King, _3));
        Player crupier = new Player("Crupier", new Hand(Queen, _5, _2));

        assertEquals(List.of(player),
                new Game(crupier, List.of(player), deckFrom(_10, Ace, King)).getWinners());
    }

    @Test
    public void player_have_the_same_points_than_crupier() {
        Player player = new Player("Player1", new Hand(_6, King, _3));
        Player crupier = new Player("Crupier", new Hand(_6, King, _3));

        assertEquals(List.of(),
                new Game(crupier, List.of(player), deckFrom(_10, Ace, King)).getWinners());
    }

    @Test
    public void crupier_and_player_are_bust() {
        Player player = new Player("Player1", new Hand(King, Queen, Jack));
        Player crupier = new Player("Crupier", new Hand(King, Queen, Jack));

        assertEquals(List.of(),
                new Game(crupier, List.of(player), deckFrom(_10, Ace, King)).getWinners());
    }

    @Test
    public void crupier_is_busted_and_player_dont() {
        Player player = new Player("Player1", new Hand(_2));
        Player crupier = new Player("Crupier", new Hand(King, Queen, Jack));

        assertEquals(List.of(player),
                new Game(crupier, List.of(player), deckFrom(_10, Ace, King)).getWinners());
    }

    @Test
    public void crupier_game_adds_enought_cards() {
        Player crupier = new Player("Crupier", new Hand());
        new Game(crupier, List.of(), deckFrom(Ace, _10, Queen)).getWinners();
        assertEquals(21, crupier.hand().value());

        crupier = new Player("Crupier", new Hand());
        new Game(crupier, List.of(), deckFrom(_3, _10, _4, _2)).getWinners();
        assertEquals(17, crupier.hand().value());

        crupier = new Player("Crupier", new Hand());
        new Game(crupier, List.of(), deckFrom(_2, _10, _4, Ace)).getWinners();
        assertEquals(17, crupier.hand().value());

        crupier = new Player("Crupier", new Hand());
        new Game(crupier, List.of(), deckFrom(Queen, _6, King, _2)).getWinners();
        assertEquals(26, crupier.hand().value());
    }

    @Test
    public void multiple_players() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1", new Hand(Jack, Ace)));
        players.add(new Player("Player2", new Hand(_10, _5, _6)));
        players.add(new Player("Player3", new Hand(_3, _6, Ace, _3, Ace, King)));

        Player crupier = new Player("Crupier", new Hand(_9, _7));

        List<Card> deck = deckFrom(_5, _4, King, _2);

        assertEquals(List.of(players.get(0)), new Game(crupier, players, deck).getWinners());

        players.clear();
        players.add(new Player("Player1", new Hand(_10, King)));
        players.add(new Player("Player2", new Hand(_10, _2, _6)));
        players.add(new Player("Player3", new Hand(_8, _8, _5)));

        crupier = new Player("Crupier", new Hand(_5, _10));

        deck = deckFrom(Ace, _3, King, _2);

        assertEquals(List.of(players.get(0), players.get(2)), new Game(crupier, players, deck).getWinners());
    }

    public List<Card> deckFrom(Card... cards) {
        return new ArrayList<>(List.of(cards));
    }
}
