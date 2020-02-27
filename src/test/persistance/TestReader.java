package persistance;

import model.Game;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestReader {
    Reader reader = new Reader();

    @Test
    void testParseAccountsFile1() {
        try {
            Game game = Reader.readFile(new File("./data/testGame1.txt"));
            assertEquals(1, game.getTurn());
            assertEquals(3, game.getPlayers().size());
            assertEquals("two", game.getHand().getPlayer());
            assertEquals(1, game.getHand().getPile().size());
            assertEquals("Spade", game.getHand().getPile().get(0).getSuit());
            assertEquals(3, game.getHand().getPile().get(0).getNumber());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readFile(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
