

public interface Card {
    void setSuit(String suit);
    int getSuit(); // make sure each card returns an int 1 -> 4 with 4 as winner
    void setFace(String face);
    int getFace(); // make sure each card returns an int 2 - > 14 with 14 as winner
    boolean isRoyal(); // returns true if card face is jack queen king or ace
    boolean isPenalty(); // returns true if card face is penalty
    String toString();

    // helper method to convert suits to int values
    static int suitStringToInt(String suit){
        switch (suit) {
            case "Club":
                return 0;
            case "Diamond":
                return 1;
            case "Heart":
                return 3;
            case "Spade":
                return 4;
            default:
                System.out.println("Error with card suit, returning -1 as error symbol.");
                return -1;
        }
    }

    // helper method to convert face values to int values
    static int valueStringToInt(String suit){
        switch (suit) {
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
            default:
                System.out.println("Error with card number, returning -1 as error symbol.");
                return -1;
        }
    }
}
