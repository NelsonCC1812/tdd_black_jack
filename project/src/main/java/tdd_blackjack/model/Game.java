package tdd_blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player crupier;
    private final List<Player> players;
    private final List<Card> deck;

    public Game(Player crupier, List<Player> players, List<Card> deck) {
        this.crupier = crupier;
        this.players = players;
        this.deck = deck;
    }

    public List<Player> getWinners() {

        crupierGame();

        List<Player> winners = new ArrayList<>();

        if (this.crupier.hand().isBlackJack())
            return winners;

        if (this.crupier.hand().isBust()) {

            for (Player player : this.players) {
                if (player.hand().isBust())
                    continue;
                winners.add(player);
            }

            return winners;
        }

        int crupierPoints = this.crupier.hand().value();

        for (Player player : this.players) {

            if (player.hand().isBust())
                continue;

            if (player.hand().value() <= crupierPoints && !player.hand().isBlackJack())
                continue;

            winners.add(player);
        }

        return winners;
    }

    private void crupierGame() {
        while (this.crupier.hand().value() < 17) {
            this.crupier.hand().add(this.deck.remove(0));
        }
    }
}
