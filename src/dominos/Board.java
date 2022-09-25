package dominos;

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

    public boolean isLegalMove(Domino dom) {
        if (dom.getRight() == getLeftMostNum() || dom.getLeft() == getRightMostNum()){
            return true;
        }
        return false;
    }


    public int getLeftMostNum() {
        return board.get(0).getLeft();
    }

    public int getRightMostNum() {
        return board.get(getBoardSize()-1).getRight();
    }

  /**  public String toString() {
        String str = "";
        for (int i = 0; i < board.size(); i++) {
            str += board.get(i);
        }
        return str;
    } */

    public String toString() {
        String str;
        String firstLine = "";
        String secondLine = "";
        for (int i = 0; i < board.size(); i += 2) {
            firstLine += board.get(i);
        }
        for (int i = 1; i < board.size(); i += 2) {
            secondLine += board.get(i);
        }
        str = firstLine + "\n" + "   " + secondLine;
        return str;
    }

}
