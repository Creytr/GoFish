package ui;

import model.Game;
import persistance.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String PATH = "./data/game.txt";
    public static Game game;
    public static Scanner in = new Scanner(System.in);
    public static int inInt;
    public static String inString;

    //MODIFIES: this
    //EFFECTS: creates and runs the game
    public static void main(String[] args) {
        initializeGame();
        runGame();
        System.out.println("Thanks for playing!");
    }

    //MODIFIES: this
    //EFFECTS: creates a game with specified players
    public static void initializeGame() {
        boolean initialized = false;

        System.out.println("Would you like to load the previous game? y/n");
        inString = in.next();

        while (!initialized) {
            if (inString.equals("y")) {
                try {
                    game = Reader.readFile(new File(PATH));
                } catch (IOException e) {
                    System.out.println("Sorry, the file seems to be unreadable. Please create a new game.");
                }
                initialized = true;
            } else if (inString.equals("n")) {
                game = new Game(newPlayers(), true);
                initialized = true;
            }
        }
    }

    //EFFECTS: returns a list of names of the players
    private static List<String> newPlayers() {
        List<String> players = new ArrayList<>();
        System.out.println("How many players?");
        inInt = in.nextInt();

        for (int i = 0; i < inInt; i++) {
            System.out.println("What is the name of Player " + (i + 1));
            inString = in.next();
            players.add(inString);
        }
        return players;
    }

    //MODIFIES: this
    //EFFECTS: gives player options to do on their turn
    public static void runGame() {
        boolean end = false;
        while (!end) {
            System.out.println("It is " + game.getHand().getPlayer() + "'s turn:\nWhat will you do?"
                    + "\n1. View your hand\n2. Play a card\n3. Draw a card\n4. End turn\n5. Save Game\n6. End Game");
            end = menu();
        }
    }

    //MODIFIES: this
    //EFFECTS: does the action player picks
    private static boolean menu() {
        switch (in.nextInt()) {
            case 1:
                System.out.println(game.getHand());
                break;
            case 2:
                System.out.println("What card do you want to play?");
                game.playCard(in.nextInt());
                break;
            case 3:
                game.deal();
                break;
            case 4:
                game.nextTurn();
                break;
            case 5:
                save();
                break;
            case 6:
                return true;
        }
        return false;
    }

    //EFFECTS: saves the game
    private static void save() {
        try {
            game.save(PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + PATH);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }
}
