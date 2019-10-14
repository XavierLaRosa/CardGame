import java.util.Stack;
import java.util.Collections;

public class Deck {
    final static int officialDeckSize = 56;

    private static Stack<Card> deck = new Stack<>();
    private static int deckSize = 56;
    private static int penaltyCardsCap = 4;
    private static String[] suits = {"Club", "Diamond", "Heart", "Spade"};
    private static String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck(){
        constructDeck();
    }

    void constructDeck(){
        for(int i = 0; i<penaltyCardsCap; i++){
            deck.add(new PenaltyCard());
        }
        for(int i = 0; i<suits.length; i++){
            for(int j = 0; j<values.length; j++){
                // see if value is parsable to int
                if(isInteger(values[j])){
                    deck.add(new NumberCard(suits[i], values[j]));
                } else{
                    deck.add(new RoyalCard(suits[i], values[j]));
                }
            }
        }
        Collections.shuffle(deck);
    }

    Card draw(){ // take a card from top of shuffled deck and return card
        deckSize--;
        return deck.pop();
    }

    int getDeckSize(){
        return deckSize;
    }

    static boolean isInteger(String s) {
        if(s == null){
            return false;
        }
        int length = s.length();
        if(length == 0){
            return false;
        }
        int i = 0;
        if(s.charAt(0) == '-'){
            if(length == 1){
                return false;
            }
            i=1;
        }
        for(; i < length; i++){
            char c = s.charAt(i);
            if(c<'0'||c>'9'){
                return false;
            }
        }
        return true;
    }
}
