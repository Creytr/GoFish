package model;

import java.util.List;

//A deck
public class Deck extends CardPile {

    //EFFECTS: creates a deck with all the cards possible with jokers if needed
    public Deck(Boolean joker) {
        super();

        for (int i = 0; i < 13; i++) {
            addCard(new Card(i + 1, "Spade"));
        }
        for (int i = 0; i < 13; i++) {
            addCard(new Card(i + 1, "Clover"));
        }
        for (int i = 0; i < 13; i++) {
            addCard(new Card(i + 1, "Heart"));
        }
        for (int i = 0; i < 13; i++) {
            addCard(new Card(i + 1, "Diamond"));
        }

        if (joker) {
            addCard(new Card(14, "Black"));
            addCard(new Card(14, "Red"));
        }
    }

    public Deck(List<Card> cards) {
        super(cards);
    }

    //REQUIRES: deck is not empty
    //MODIFIES: this
    //EFFECTS: removes a card from the deck and adds it to a hand
    public void deal(Hand h) {
        h.addCard(getCard(0));
        removeCard(0);
    }
}