package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCard {
    Card ace;
    Card num;
    Card jack;
    Card queen;
    Card king;
    Card jokerRed;
    Card jokerBlack;

    @BeforeEach
    void runBefore() {
        ace = new Card(1, "Spade");
        num = new Card("2D");
        jack = new Card("11H");
        queen = new Card("12S");
        king = new Card("13C");
        jokerRed = new Card("14R");
        jokerBlack = new Card("14B");
    }

    @Test
    void testConstructor() {
        assertEquals(1, ace.getNumber());
        assertEquals("Spade", ace.getSuit());

        assertEquals(2, num.getNumber());
        assertEquals("Diamond", num.getSuit());
        assertEquals(11, jack.getNumber());
        assertEquals("Heart", jack.getSuit());
        assertEquals(12, queen.getNumber());
        assertEquals("Spade", queen.getSuit());
        assertEquals(13, king.getNumber());
        assertEquals("Clover", king.getSuit());
        assertEquals(14, jokerRed.getNumber());
        assertEquals("Red", jokerRed.getSuit());
        assertEquals(14, jokerBlack.getNumber());
        assertEquals("Black", jokerBlack.getSuit());
    }

    @Test
    void testRank() {
        assertEquals("A", ace.rank());
        assertEquals("2", num.rank());
        assertEquals("J", jack.rank());
        assertEquals("Q", queen.rank());
        assertEquals("K", king.rank());
        assertEquals("Joker", jokerRed.rank());
    }

    @Test
    void testShorten() {
        assertEquals("1S", ace.shorten());
        assertEquals("11H", jack.shorten());
        assertEquals("14B", jokerBlack.shorten());
    }

    @Test
    void testToString() {
        assertEquals("A of Spade", ace.toString());
        assertEquals("Red Joker", jokerRed.toString());
    }
}