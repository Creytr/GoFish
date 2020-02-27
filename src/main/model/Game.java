package model;


import persistance.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//A game
public class Game {
    private ArrayList<Hand> players;
    private Deck deck;
    private int turn;

    //REQUIRES: size of names is = to numPlayers
    public Game(List<String> names, boolean jokers) {
        players = new ArrayList<>();
        for (String name : names) {
            players.add(new Hand(name));
        }

        deck = new Deck(jokers);

        turn = 0;
    }

    public Game(int turn, ArrayList<Hand> players, Deck deck) {
        this.turn = turn;
        this.players = players;
        this.deck = deck;
    }

    public ArrayList<Hand> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getTurn() {
        return turn;
    }

    //EFFECTS: returns the current player's hand
    public Hand getHand() {
        return players.get(turn);
    }

    //REQUIRES: i - 1 < getHand().size()
    //MODIFIES: this
    //EFFECTS: plays specified card
    public void playCard(int i) {
        getHand().removeCard(i - 1);
    }

    //EFFECTS: passes the turn to the next player
    public void nextTurn() {
        turn = (turn + 1) % players.size();
    }

    //REQUIRES: deck is not empty
    //MODIFIES: this
    //EFFECTS: deals a card to the current hand
    public void deal() {
        deck.deal(getHand());
    }

    //EFFECTS: saves the game to a file
    public void save(String path) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter(new File(path), "UTF-8");
        writeGame(printWriter);
        printWriter.close();
        System.out.println("Game saved to file " + path);
    }

    //EFFECTS: helps save the game to a file
    private void writeGame(PrintWriter printWriter) {
        printWriter.print(turn);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(players.size());
        printWriter.print(Reader.DELIMITER);
        writeHands(printWriter);
        writeDeck(printWriter);
    }

    //EFFECTS: saves the player's data
    private void writeHands(PrintWriter printWriter) {
        for (Hand hand: players) {
            printWriter.print(hand.getPlayer());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(hand.getPile().size());
            printWriter.print(Reader.DELIMITER);
            for (Card card: hand.getPile()) {
                printWriter.print(card.shorten());
                printWriter.print(Reader.DELIMITER);
            }
        }
    }

    //EFFECTS: saves the deck data
    private void writeDeck(PrintWriter printWriter) {
        printWriter.print(deck.getPile().size());
        printWriter.print(Reader.DELIMITER);
        for (Card card: deck.getPile()) {
            printWriter.print(card.shorten());
            printWriter.print(Reader.DELIMITER);
        }
    }
}
