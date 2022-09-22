package dominos;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Players{
    private ArrayList<Domino> hand;





    public void setMyHand(ArrayList<Domino> myHand) {
        hand = myHand;
    }


    /**
     * @return returns arraylist of myHand
     */
    public ArrayList<Domino> getMyHand() {
        return hand;
    }

    public boolean emptyHand() {
        return hand.isEmpty();
    }

    public int getSize() {
        return hand.size();
    }


    public boolean playerHasMove(Board board) {
        for (Domino domInHand : getMyHand()) {
            if ((domInHand.containsLeft(board.getBoard().get(0))) ||
            (domInHand.containsRight(board.getBoard().get(board.getBoardSize()-1)))) {
                return true;
            }
        }
        return false;
    }

    public void printHumanHand() {
        System.out.println("Your dominos: " + getMyHand());
    }

   /** public boolean playerHasMove() {
        boolean hasMove = false;
        if (emptyHand()) {
            return false;
        }
        Stream<ArrayList> playerHand = Stream.of(hand);
        playerHand.anyMatch()
        // TODO
        //  maybe use streams to go through player hand to see if legal move , possible use streams.anyMatch(boolean value)
        //

        else {
            for (Domino domino : hand) {
                if (domino.contains)
            }

        }
    } */


}
