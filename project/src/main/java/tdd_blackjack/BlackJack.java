package tdd_blackjack;

import java.util.ArrayList;
import java.util.List;

import tdd_blackjack.model.*;

import static tdd_blackjack.model.Card.*;

public class BlackJack {
    public static void main(String[] args) {

        System.out.println("Example1:");

        List<Player> winners = getWinners(
                new Player("Player1", new Hand(Jack, Ace)),
                new Player("Player2", new Hand(_10, _5, _6)),
                new Player("Player3", new Hand(_3, _6, Ace, _3, Ace, King)),
                new Card[] { _9, _7 },
                new Card[] { _5, _4, King, _2 });

        for (Player player : winners)
            System.out.println(player.name());

        System.out.println("\nExample2:");

        winners = getWinners(
                new Player("Player1", new Hand(_10, King)),
                new Player("Player2", new Hand(_10, _2, _6)),
                new Player("Player3", new Hand(_8, _8, _5)),
                new Card[] { _5, _10 },
                new Card[] { Ace, _3, King, _2 });

        for (Player player : winners)
            System.out.println(player.name());

    }

    public static List<Player> getWinners(Player player1, Player player2, Player player3, Card[] crupierCards,
            Card[] deck) {

        Player crupier = new Player("Crupier", new Hand(crupierCards));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return new Game(crupier, players, new ArrayList<>(List.of(deck))).getWinners();
    }
}