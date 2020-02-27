package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestHand extends TestCardPile {
    Hand h1;
    Hand h2;

    @BeforeEach
    void runBefore() {
        h1 = new Hand("One");

        List<Card> cards = new ArrayList<>();
        cards.add(new Card("14R"));
        h2 = new Hand("Two", cards);
    }

    @Test
    void testConstructor() {
        assertEquals(0, h1.getPile().size());
        assertEquals("One", h1.getPlayer());

        assertEquals(1, h2.getPile().size());
        assertEquals("Two", h2.getPlayer());
        assertEquals(14, h2.getPile().get(0).getNumber());
        assertEquals("Red", h2.getPile().get(0).getSuit());
    }

    @Test
    void testToString() {
        assertEquals("Player: One\n", h1.toString());

        Card c1 = new Card(1, "Spade");
        Card c2 = new Card(14, "Black");
        h1.addCard(c1);
        h1.addCard(c2);

        assertEquals("Player: One\n1: " + c1.toString() + "\n2: " + c2.toString() + "\n", h1.toString());
    }
}