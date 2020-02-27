package model;

import java.util.ArrayList;
import java.util.List;

//A card pile
public class CardPile {
    private List<Card> pile;

    public CardPile() {
        pile = new ArrayList<>();
    }

    public CardPile(List<Card> cards) {
        pile = cards;
    }

    public List<Card> getPile() {
        return pile;
    }

    //REQUIRES: i < size of pile
    //EFFECTS: returns the card at index i
    public Card getCard(int i) {
        return pile.get(i);
    }

    //MODIFIES: this
    //EFFECTS: adds the card to the pile
    public void addCard(Card c) {
        pile.add(c);
    }

    //REQUIRES: pile is not empty and i < size of pile
    //MODIFIES: this
    //EFFECTS: removes a card at index i from the pile
    public void removeCard(int i) {
        pile.remove(i);
    }
}