package persistance;

import model.Card;
import model.Deck;
import model.Game;
import model.Hand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read data from a file
public class Reader {
    public static final String DELIMITER = ",";

    //EFFECTS: reads the information from the file and turns it into a game
    public static Game readFile(File file) throws IOException {
        return parseContent(Files.readAllLines(file.toPath()).get(0));
    }

    //EFFECTS: splits the information into lines and turns it into a game
    private static Game parseContent(String line) {
        ArrayList<String> lineComponents = splitString(line);
        return parseGame(lineComponents);
    }

    //EFFECTS: splits a line into multiple lines based on the DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: element 0 = turn number, element 1 = number of players, element 2 to be number of cards that player 1
    //          has, element 3-X be the cards that player 1 has, ..., element Z be number of cards the deck has, ...
    //EFFECTS: turns the lines into game data
    private static Game parseGame(ArrayList<String> components) {
        int turn = Integer.parseInt(components.get(0));
        int numPlayers = Integer.parseInt(components.get(1));
        ArrayList<Hand> players = new ArrayList<>();

        int count = 2;
        for (int i = 0; i < numPlayers; i++) {
            String name = components.get(count);
            int numCards = Integer.parseInt(components.get(count + 1));

            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < numCards; j++) {
                cards.add(new Card(components.get(count + j + 2)));
            }
            
            players.add(new Hand(name, cards));
            count += numCards + 2;
        }

        int numCards = Integer.parseInt(components.get(count));
        List<Card> cards = new ArrayList<>();
        for (int j = 0; j < numCards; j++) {
            cards.add(new Card(components.get(count + j + 1)));
        }
        Deck deck = new Deck(cards);
        
        return new Game(turn, players, deck);
    }


}
