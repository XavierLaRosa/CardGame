import java.lang.StringBuilder;

public class RoyalCard implements Card {
    private String suitStr;
    private String faceStr;
    private int suitInt;
    private int faceInt;

    public RoyalCard(String suit, String value){
        this.suitStr = suit;
        this.faceStr = value;

        this.suitInt = Card.suitStringToInt(suit);
        this.faceInt = Card.valueStringToInt(value);
    }

    public void setSuit(String suit){
        this.suitStr = suit;
        this.suitInt = Card.suitStringToInt(suit);
    }
    public int getSuit(){ // make sure each card returns an int 1 -> 4 with 4 as winner
        return suitInt;
    }

    public void setFace(String face){
        this.faceStr = face;
        this.faceInt = Integer.parseInt(face);
    }
    public int getFace(){ // make sure each card returns an int 2 - > 14 with 14 as winner
        return faceInt;
    }

    public boolean isRoyal(){ // returns true if card face is jack queen king or ace
        return true;
    }
    public boolean isPenalty(){ // returns true if card face is penalty
        return false;
    }

    public String toString(){ // make sure object prints suit and face or penalty
        return new StringBuilder(faceStr+" of "+suitStr).toString();
    }
}
