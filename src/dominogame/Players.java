package dominogame;

import java.util.ArrayList;

/**
 * Player object that can create as many players as needed,
 * in this case we will only need the human and computer.
 * Holds computer logic
 */
public class Players {

    public ArrayList<Domino> hand;

    public void setMyHand(ArrayList<Domino> myHand) {
        hand = myHand;
    }

    private int turn;
    private String side;
    public Display displayClass;

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
           // System.out.println("Boneyard has " + boneyard.getDominoListSize() + " dominos left");
        }
        else {
            System.out.println("Boneyard is empty.");
        }
    }


    /**
     * @return returns arraylist of myHand
     */
    public ArrayList<Domino> getHand() {
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
     * when board is empty any move is valid else checks if
     * first or last elemnt of board matches any domino in hand
     * @param board
     * @return
     */
    public boolean playerHasLegitMove(Board board) {
        if (board.isEmpty()) {
            return true;
        }
        else {
            for (Domino d: hand) {
                if (d.getLeft() == 0 || d.getRight() == 0) {
                    return true;
                }
            }
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getLeft() == board.getRightMostNum() ||
                        hand.get(i).getLeft() == board.getLeftMostNum() ||
                        hand.get(i).getRight() == board.getRightMostNum() ||
                        hand.get(i).getRight() == board.getLeftMostNum()) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Prints out what is in the human player's current hand
     */
    public void printHumanHand() {
        System.out.println("Your dominos: " + getHand());
    }

    public int countScores() {
        int score = 0;

        for (Domino domino : hand) {
            score += domino.getRight() + domino.getLeft();
        }
        return score;
    }

    public int countTurns() {
        return turn;
    }

    /**
     * Helper for computerPlays() allows adding domino to the right of board
     * @param board game board
     * @param index what domino in hand to add to board
     */
    public void computerAddToEnd(Board board, int index) {
        //System.out.println("Computer plays " + hand.get(index) + " at right");
        board.getBoard().add(hand.get(index));
        hand.remove(index);
        side = "r";
    }

    /**
     * Helper for computerPlays() allows adding domino to the left of board
     * @param board game board
     * @param index what domino in hand to add to board
     */
    public void computerAddToBeginning(Board board, int index) {
       // System.out.println("Computer plays " + hand.get(index) + " at left");
        board.getBoard().add(0,hand.get(index));
        hand.remove(index);
        side = "l";
    }

    public String lastSideComputerPlayers() {
        return side;
    }

    /**
     * Computer player logic. Will choose the first legal move that it can play.
     * @param board checks and compares what dominos are in the hand and on the board
     * @param boneyard used for when drawing from boneyard
     */
    public void computerPlays(Board board,Boneyard boneyard) {
       // System.out.println("Computer's turn");
        boolean search = true;
        while (search) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getLeft() == board.getRightMostNum() ||
                        hand.get(i).getLeft() == 0 || board.getRightMostNum() == 0) {
                    computerAddToEnd(board, i);
                    search = false;
                    break;

                } else if (hand.get(i).getLeft() == board.getLeftMostNum() ||
                        hand.get(i).getLeft() == 0 || board.getLeftMostNum() == 0) {
                    hand.get(i).flipDomino();
                    computerAddToBeginning(board, i);
                    search = false;
                    break;

                } else if (hand.get(i).getRight() == board.getLeftMostNum() ||
                        hand.get(i).getRight() == 0 || board.getLeftMostNum() == 0) {
                    computerAddToBeginning(board, i);
                    search = false;
                    break;

                } else if (hand.get(i).getRight() == board.getRightMostNum() ||
                        hand.get(i).getRight() == 0 || board.getRightMostNum() == 0) {
                    hand.get(i).flipDomino();
                    computerAddToEnd(board, i);
                    search = false;
                    break;
                }
            }
            turn++;
            if (search) {
                drawFromBoneyard(boneyard);
                System.out.println("Computer draws from the boneyard");
            }
            if (boneyard.isEmpty()){
                break;
            }
        }


    }
}
