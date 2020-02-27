package model;

import java.util.List;

//A player's hand
public class Hand extends CardPile {
    private String player;

    public Hand(String player) {
        super();

        this.player = player;
    }

    public Hand(String player, List<Card> cards) {
        super(cards);
        this.player = player;
    }

    public  String getPlayer() {
        return player;
    }

    //EFFECTS: returns all cards in the hand
    public String toString() {
        StringBuilder cards = new StringBuilder();
        cards.append("Player: ").append(getPlayer()).append("\n");

        int i = 1;
        for (Card c: getPile()) {
            cards.append(i).append(": ").append(c).append("\n");
            i++;
        }
        return cards.toString();
    }
}