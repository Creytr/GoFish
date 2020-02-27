package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TestCardPile {
    CardPile c = new CardPile();

    @Test
    void testGetCard() {
        c.addCard(new Card(1, "Spade"));
        assertEquals(1, c.getCard(0).getNumber());
        assertEquals("Spade", c.getCard(0).getSuit());
    }

    @Test
    void testAddCard() {
        c.addCard(new Card(1, "Spade"));
        assertEquals(1, c.getCard(0).getNumber());
        assertEquals("Spade", c.getCard(0).getSuit());
    }

    @Test
    void testRemoveCard() {
        c.addCard(new Card(1, "Spade"));
        c.removeCard(0);
        assertEquals(0, c.getPile().size());
    }
}
