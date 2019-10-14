import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
    private int pointCap = 21;
    Scanner readStr = new Scanner(System.in);
    Scanner readInt = new Scanner(System.in);

    // constructor
    public Game(){
        System.out.println("\nWelcome to Xavier's Card Game!\n------------------------------------------");
    }

    // call this method to start the game
    void start(){

        // get number of players
        int playerCount;
        while(true){
            try{
                System.out.println("Please enter number of players between 2 and "+Player.playerCountCap+": ");
                playerCount = readInt.nextInt();
                if(playerCount>Player.playerCountCap || playerCount<2){
                    throw new Exception();
                } else{
                    break;
                }
            } catch(Exception e){ // find a way to break this infinite loop properly
                System.out.println("Error: Make sure input is a whole number and max number of players is between 2 and "+Player.playerCountCap+".");
            }
        }

        // assign player names
        String[] players = new String[playerCount]; // list of players to draw cards
        List<Player> playersSorted = new ArrayList<Player>(); // list of player objects sorted by points descending
        for(int i = 0; i<playerCount; i++) { // add players by name
            System.out.println("Please enter name for Player " + (i + 1) + ": ");
            String playerName = readStr.nextLine(); //call a method to check if player doesnt exist already
            if(isUniqueIdentifier(playersSorted, playerName) == true){
                playersSorted.add(new Player(playerName));
                players[i] = playerName;
            } else{
                System.out.println("Error: Player already exists");
                i--;
            }
        }

        // go through rounds of drawing cards and comparing to add and deduct points
        boolean play = true;
        while(play && enoughCards(Player.deck, playerCount)){
            System.out.println("\nStarting a new round!\n------------------------------------------");

            // functions to apply per round
            playersSorted = distributeCards(players, playersSorted); // update players with new cards
            playersSorted = sortByCards(playersSorted); // sort players by cards descending
            distributePoints(playersSorted); // add and deduct points to first place and anybody who got penalty
            playersSorted = sortByPoints(playersSorted); // re-sort players by current points
            printLeaderboard(playersSorted); // print the leader board

            // check if game should continue or not
            if(playersSorted.get(1).getPoints() == playersSorted.get(0).getPoints() -1 && playersSorted.get(0).getPoints()>=pointCap){ // continue game with over time
                pointCap++;
                continue;
            } else if(playersSorted.get(1).getPoints() < playersSorted.get(0).getPoints() -1 && playersSorted.get(0).getPoints()>=pointCap){ // end over time
                play = false;
            } else if(playersSorted.get(0).getPoints()>=pointCap){ // end game normally
                play = false;
            } else{ // continue game normally
                continue;
            }
        }
        end();
    }

    // check for unique identifier per player name
    boolean isUniqueIdentifier(List<Player> playersSorted, String playerName){
        for(int i = 0; i<playersSorted.size(); i++){
            //System.out.println("Comparing names: "+playersSorted.get(i).getName() + ", "+playerName);
            if(playersSorted.get(i).getName().equals(playerName)){
                return false;
            }
        }
        return true;
    }

    // check if there are enough cards to distribute
    boolean enoughCards(Deck deck, int playerCount){
        if(deck.getDeckSize()/playerCount >= 1){
            System.out.println("Deck Size: "+deck.getDeckSize()+", Players: "+playerCount);
            return true;
        } else{
            System.out.println("Ending game, Deck Size: "+deck.getDeckSize()+", Players: "+playerCount);
            return false;
        }
    }

    // distribute cards to each player
    List<Player> distributeCards(String[] players, List<Player> playersSorted){
        for(int i = 0; i<players.length; i++){
            for(int j = 0; j<playersSorted.size(); j++){
                if(players[i] == playersSorted.get(j).getName()){
                    System.out.print(players[i] + "'s Turn:\n\tPress the enter key to draw a card...  ");
                    String enter = readStr.nextLine();
                    playersSorted.get(j).draw();
                }
            }
        }
        return playersSorted;
    }

    // sort players by cards descending
    List<Player> sortByCards(List<Player> players){
        //System.out.println("Before sort in reference to cards:\n"+players);
        Collections.sort(players, (Player p1, Player p2) -> {
            int result = p2.getCurrentCard().getFace() - p1.getCurrentCard().getFace();
            if(result == 0){
                result = p2.getCurrentCard().getSuit() - p1.getCurrentCard().getSuit();
            }
            return result;
        });
        //System.out.println("After sort in reference to cards:\n"+players);
        if(players.get(0).getCurrentCard().isPenalty() == false){
            System.out.println("********** WINNER OF ROUND **********\n\t"+players.get(0).getName()+" with the "+players.get(0).getCurrentCard());
        } else{
            System.out.println("****** NO WINNER FOR THIS ROUND ****"+players.get(0));
        }
        return players;
    }

    // distribute points to specific players
    void distributePoints(List<Player> playersSorted){
        //System.out.println("List to distribute points: "+ playersSorted);
        if(!playersSorted.get(0).getCurrentCard().isPenalty()){
            playersSorted.get(0).addPoints();
        }
        for(int i = 0; i< playersSorted.size(); i++){
            if(playersSorted.get(i).getCurrentCard().isPenalty()){
                playersSorted.get(i).deductPoints();
            }
        }
    }

    // sort players by points descending
    List<Player> sortByPoints(List<Player> playersSorted){
        Collections.sort(playersSorted, (Player p1, Player p2) -> {
            return p2.getPoints() - p1.getPoints();
        });
        return playersSorted;
    }

    // print current leader board
    void printLeaderboard(List<Player> playersSorted){
        System.out.println("\n------------------------------------------End of Round Leader Board------------------------------------------");
        for(int i = 0; i<playersSorted.size(); i++){
            System.out.println("Place "+(i+1)+":\n"+"\t"+playersSorted.get(i));
        }
    }

    // end the game
    void end(){
        System.out.println("\n------------------------------------------End of Game: Thank you for playing!------------------------------------------");
    }

}
