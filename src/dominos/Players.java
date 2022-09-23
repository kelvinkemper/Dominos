package dominos;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Players{
    private ArrayList<Domino> hand;
    private Board board;


    public void setMyHand(ArrayList<Domino> myHand) {
        hand = myHand;
    }

    public void drawFromBoneyard(Boneyard boneyard) {
        if (!boneyard.isEmpty()) {
            Domino drawDomino = boneyard.getDominoList().get(0);
            hand.add(drawDomino);
            boneyard.getDominoList().remove(drawDomino);
        }
        else {
            System.out.println("Boneyard is empty.");
        }
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


    /**
     * Boolean for if the player has a valid move or not
     * @param board
     * @return
     */
    public boolean playerHasMove(Board board) {
        for (Domino domInHand : getMyHand()) {
            if ((domInHand.containsLeft(board.getBoard().get(0))) ||
                    (domInHand.containsRight(board.getBoard().get(board.getBoardSize() - 1)))) {
                return true;
            } else {
                System.out.println("Not a valid move");
            }
        }
        return false;
    }

    /**
     * Prints out what is in the human player's current hand
     */
    public void printHumanHand() {
        System.out.println("Your dominos: " + getMyHand());
    }

    public void computerPlays() {
        int leftBoard = board.getLeftMostNum();
        int rightBoard = board.getRightMostNum();
        ArrayList<Domino> useableDominos = new ArrayList<>();

        for (Domino d : hand) {
            if(board.isLegalMove(hand)) {

        }
    }





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
