package dominos;

import java.util.ArrayList;


public class Players{
    private ArrayList<Domino> hand;

    public void setMyHand(ArrayList<Domino> myHand) {
        hand = myHand;
    }

    /**
     * method for drawing a domino from the boneyard, adding to a players hand
     * and removing domino from boneyard.
     * @param boneyard boneyard containing remaining 14 dominos
     */
    public void drawFromBoneyard(Boneyard boneyard) {
        if (!boneyard.isEmpty()) {
            Domino drawDomino = boneyard.getDominoList().get(0);
            hand.add(drawDomino);
            boneyard.getDominoList().remove(drawDomino);
            System.out.println("Boneyard has " + boneyard.getDominoListSize() + " dominos left");
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

    public boolean isEmptyHand() {
        return hand.isEmpty();
    }

    /**
     * @return size of hand
     */
    public int getSize() {
        return hand.size();
    }


    /**
     * Boolean for if the player has a valid move or not
     * @param board
     * @return
     */
    public boolean playerHasMove(Board board) {
        if (board.isEmpty()) {
            return true;
        }
        else {
            for (Domino domInHand : hand) {
                if ((domInHand.containsLeft(board.getBoard().get(0))) ||
                        (domInHand.containsRight(board.getBoard().get(board.getBoardSize() - 1)))
                        || (domInHand.containsLeft(board.getBoard().get(board.getBoardSize()-1)))
                        || (domInHand.containsRight(board.getBoard().get(0)))) {
                    return true;
                } else {
                    System.out.println("Not a valid move");
                    return false;
                }
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

    /**
     * Helper for computerPlays() allows adding domino to the right of board
     * @param board game board
     * @param index what domino in hand to add to board
     */
    public void computerAddtoEnd(Board board, int index) {
        System.out.println("Computer plays " + hand.get(index) + " at right");
        board.getBoard().add(hand.get(index));
        hand.remove(index);
    }

    /**
     * Helper for computerPlays() allows adding domino to the left of board
     * @param board game board
     * @param index what domino in hand to add to board
     */
    public void computerAddtoBeginning(Board board, int index) {
        System.out.println("Computer plays " + hand.get(index) + " at left");
        board.getBoard().add(0,hand.get(index));
        hand.remove(index);
    }

    /**
     * Computer player logic. Will choose the first legal move that it can play.
     * @param board checks and compares what dominos are in the hand and on the board
     * @param boneyard used for when drawing from boneyard
     */
    public void computerPlays(Board board,Boneyard boneyard) {
       //TODO REMOVE System.out.println("Computer hand: " + hand);
        System.out.println("Computer's turn");
        boolean search = true;
        while (search) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getLeft() == board.getRightMostNum()) {
                    computerAddtoEnd(board, i);
                    search = false;
                    break;
                } else if (hand.get(i).getLeft() == board.getLeftMostNum()) {
                    hand.get(i).flipDomino();
                    computerAddtoBeginning(board, i);
                    search = false;
                    break;
                } else if (hand.get(i).getRight() == board.getLeftMostNum()) {
                    computerAddtoBeginning(board, i);
                    search = false;
                    break;
                } else if (hand.get(i).getRight() == board.getRightMostNum()) {
                    hand.get(i).flipDomino();
                    computerAddtoEnd(board, i);
                    search = false;
                    break;
                }
            }
            if (search) {
                drawFromBoneyard(boneyard);
                System.out.println("Computer draws from the boneyard");
            }
        }
    }
}
