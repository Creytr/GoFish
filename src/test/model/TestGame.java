package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.Reader;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestGame {
    static final String TEST_FILE = "./data/testGame.txt";
    PrintWriter printWriter;
    Game g1;
    Game g2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        g1 = new Game (names, true);

        ArrayList<Hand> players = new ArrayList<>();
        players.add(new Hand("One"));
        players.add(new Hand("Two"));
        g2 = new Game(1, players, new Deck(false));

        printWriter = new PrintWriter(new File(TEST_FILE), "UTF-8");
    }

    @Test
    void testConstructor() {
        assertEquals(3, g1.getPlayers().size());
        assertEquals(54, g1.getDeck().getPile().size());
        assertEquals(0, g1.getTurn());

        assertEquals(2, g2.getPlayers().size());
        assertEquals(52, g2.getDeck().getPile().size());
        assertEquals(1, g2.getTurn());
    }

    @Test
    void testGetHand() {
        assertEquals("a", g1.getPlayers().get(0).getPlayer());
    }

    @Test
    void testNextTurn() {
        for (int i = 0; i < g1.getPlayers().size(); i++) {
            assertEquals(i, g1.getTurn());
            g1.nextTurn();
        }
        assertEquals(0, g1.getTurn());
    }

    @Test
    void testDeal() {
        assertEquals(0, g1.getHand().getPile().size());
        g1.deal();
        assertEquals(1, g1.getHand().getPile().size());
        assertEquals(53, g1.getDeck().getPile().size());
    }

    @Test
    void testPlayCard() {
        g1.deal();
        g1.deal();
        assertEquals("A of Spade", g1.getHand().getPile().get(0).toString());
        g1.playCard(1);
        assertEquals(1, g1.getHand().getPile().size());
        assertEquals("2 of Spade", g1.getHand().getPile().get(0).toString());
        g1.playCard(1);
        assertEquals(0, g1.getHand().getPile().size());
    }

    @Test
    void testSave() {
        try {
            g1.deal();
            g1.save(TEST_FILE);
            Game game = Reader.readFile(new File(TEST_FILE));
            assertEquals(3, game.getPlayers().size());
            assertEquals(53, game.getDeck().getPile().size());
            assertEquals(0, game.getTurn());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
