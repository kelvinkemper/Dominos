package dominogame;

import java.util.ArrayList;

public class Board {
    public ArrayList<Domino> board;


    Board() {
        this.board = new ArrayList<>();

    }

    /**
     * Gets the list of all dominos on the board
     * @return list of dominos on board
     */
    public ArrayList<Domino> getBoard() {
        return board;
    }

    public int getBoardSize() {
        return board.size();
    }

    /**
     * Checks if board is empty
     * @return boolean value
     */
    public boolean isEmpty() {
        return board.isEmpty();
    }

    public boolean isLegalMove(Domino dom, String side) {
        int sideValue;
        int checkLeft;
        int checkRight;
        //System.out.println("Side chosen: " + side);

        if (board.isEmpty()){
            return true;
        } else {
            while (!board.isEmpty()) {
               // System.out.println(dom);
              //  System.out.println(getBoard());
                if (side.equals("left")) {
                    sideValue = dom.getRight();
                    checkLeft = this.getLeftMostNum();
                   // System.out.println("left value: " + sideValue + " vs left list number: " + checkLeft);
                    if (sideValue == 0 || checkLeft == 0 || sideValue == checkLeft) {
                        return true;
                    }
                } else {
                    sideValue = dom.getLeft();
                    checkRight = this.getRightMostNum();
                   // System.out.println("right values: " + checkRight + " vs right list number: " + sideValue);
                    if (sideValue == 0 || checkRight == 0 || sideValue == checkRight) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }


    public int getLeftMostNum() {
        return getBoard().get(0).getLeft();
    }

    public int getRightMostNum() {
        return getBoard().get(getBoardSize()-1).getRight();
    }


    //TODO fix the flipping of sides
    /**
     * to string so the players can see the board
     * @return a printer representation of the board
     */
    @Override
    public String toString() {
        String str;
        String firstLine = "";
        String secondLine = "";

//    for (int i = 0; i < printBoard.size(); i++) {
//        if (isEven(i)) {
//            firstLine+= printBoard.get(i);
//        } else {
//            secondLine+= printBoard.get(i);
//        }
//    }
     for (int i = 0; i < board.size(); i += 2) {
             firstLine += board.get(i);
     }
     for (int i = 1; i < board.size(); i += 2) {
         secondLine += board.get(i);
     }
        str = firstLine + "\n" + "  " + secondLine;
        return str;
    }
}
