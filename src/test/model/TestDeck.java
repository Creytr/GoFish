package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDeck extends TestCardPile {
    Deck d1;
    Deck d2;
    Deck d3;

    @BeforeEach
    void runBefore() {
        d1 = new Deck(true);
        d2 = new Deck(false);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card("3D"));
        d3 = new Deck(cards);
    }

    @Test
    void testConstructor() {
        assertEquals(54, d1.getPile().size());
        assertEquals(1, d1.getCard(0).getNumber());
        assertEquals("Spade", d1.getCard(0).getSuit());
        assertEquals(1, d1.getCard(13).getNumber());
        assertEquals("Clover", d1.getCard(13).getSuit());
        assertEquals(14, d1.getCard(52).getNumber());
        assertEquals("Black", d1.getCard(52).getSuit());

        assertEquals(52, d2.getPile().size());
        assertEquals(2, d2.getCard(1).getNumber());
        assertEquals("Spade", d2.getCard(1).getSuit());
        assertEquals(2, d2.getCard(14).getNumber());
        assertEquals("Clover", d2.getCard(14).getSuit());

        assertEquals(1, d3.getPile().size());
        assertEquals(3, d3.getCard(0).getNumber());
        assertEquals("Diamond", d3.getCard(0).getSuit());
    }

    @Test
    void testDeal() {
        Hand h = new Hand("Player");
        d1.deal(h);
        assertEquals(1, h.getPile().size());
        assertEquals(1, h.getCard(0).getNumber());
        assertEquals("Spade", h.getCard(0).getSuit());

        assertEquals(53, d1.getPile().size());
        assertEquals(2, d1.getCard(0).getNumber());
        assertEquals("Spade", d1.getCard(0).getSuit());
    }
}