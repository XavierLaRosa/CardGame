

public class PenaltyCard implements Card{
    private String suitStr;
    private String faceStr;
    private int suitInt;
    private int faceInt;

    public PenaltyCard(){
        this.suitStr = "Penalty";
        this.faceStr = "Penalty";

        this.suitInt = -1;
        this.faceInt = -1;
    }

    public void setSuit(String suit){
        this.suitStr = "Penalty";
        this.suitInt = -1;
    }
    public int getSuit(){ // make sure each card returns an int 1 -> 4 with 4 as winner
        return suitInt;
    }

    public void setFace(String face){
        this.faceStr = "Penalty";
        this.faceInt = -1;
    }
    public int getFace(){ // make sure each card returns an int 2 - > 14 with 14 as winner
        return faceInt;
    }

    public boolean isRoyal(){ // returns true if card face is jack queen king or ace
        return false;
    }
    public boolean isPenalty(){ // returns true if card face is penalty
        return true;
    }

    public String toString(){ // make sure object prints suit and face or penalty
        return "Penalty! Deducting points by "+Player.deductPoints;
    }
}
