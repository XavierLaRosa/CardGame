import java.lang.StringBuilder;

public class Player {
    static int addPoints = 2;
    static int deductPoints = 1;
    static int playerCountCap = 4;
    static int playerCount = 0;
    static Deck deck = new Deck();

    private String name;
    private int points = 0;
    private int position; // 1 = 1st place
    private Card currentCard;

    public Player(String name){
        this.name = name;
        this.position = 0;
        playerCount++;
    }

    void setName(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }

    void setPosition(int position){
        this.position = position;
    }
    int getPosition(){
        return position;
    }

    void setCurrentCard(Card currentCard){
        this.currentCard = currentCard;
    }

    Card getCurrentCard(){
        return this.currentCard;
    }

    void addPoints(){
        points+=addPoints;
    }
    void deductPoints(){
        points-=deductPoints;
    }
    int getPoints(){
        return points;
    }

    void draw(){
        setCurrentCard(deck.draw());
        System.out.println("\t"+name + " drew: "+currentCard + "\n");
    }

    public String toString() {
        return new StringBuilder("Name: "+getName()+"\n\tPoints: "+getPoints()+"\n\tCurrent Card: "+currentCard).toString();
    }
}
