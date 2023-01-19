package tdd_blackjack.model;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public String name() {
        return this.name;
    }

    public Hand hand() {
        return this.hand;
    }
}
