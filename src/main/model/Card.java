package model;

//A card
public class Card {
    private int number;
    private String suit;

    //REQUIRES: num in [1,13] and suit in {"Spade", "Clover", "Diamond", "Heart"}
    //          or num = 14, suit = "Red"
    //             num = 14, suit = "Black"
    public Card(int num, String suit) {
        number = num;
        this.suit = suit;
    }

    //REQUIRES: number at the start, first letter of suit at end
    //          Example: 14R or 2S
    public Card(String parts) {
        number = Integer.parseInt(parts.substring(0, parts.length() - 1));
        switch (parts.substring(parts.length() - 1)) {
            case "S":
                suit = "Spade";
                break;
            case "C":
                suit = "Clover";
                break;
            case "D":
                suit = "Diamond";
                break;
            case "H":
                suit = "Heart";
                break;
            case "R":
                suit = "Red";
                break;
            default:
                suit = "Black";
        }
    }

    public int getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }

    //EFFECTS: translates the card number to the card rank
    public String rank() {
        switch (number) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "Joker";
            default:
                return Integer.toString(number);
        }
    }

    //EFFECTS: returns the rank and suit of the card shortened
    public String shorten() {
        return number + suit.substring(0, 1);
    }

    //EFFECTS: returns the rank and suit of the card
    public String toString() {
        if (getNumber() == 14) {
            return getSuit() + " " + rank();
        }
        return rank() + " of " + getSuit();
    }
}