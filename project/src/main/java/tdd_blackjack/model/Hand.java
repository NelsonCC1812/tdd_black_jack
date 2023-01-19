package tdd_blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand(Card... cards) {
        this.cards = Arrays.asList(cards);
        this.cards = new ArrayList<>(this.cards);
    }

    public int value() {
        return canUseAceExtendedValue() ? sum() + 10 : sum();
    }

    public boolean isBlackJack() {
        return value() == 21 && cards.size() == 2;
    }

    public boolean isBust() {
        return value() > 21;
    }

    public void add(Card... cards) {

        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    private boolean canUseAceExtendedValue() {
        return sum() <= 11 && containsAce();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(c -> c == Card.Ace);
    }

    private int sum() {
        return cards.stream().mapToInt(c -> c.value()).sum();
    }
}
