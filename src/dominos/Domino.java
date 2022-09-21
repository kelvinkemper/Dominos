package dominos;

import java.util.ArrayList;
import java.util.LinkedList;

public class Domino {

    private int left;
    private int right;
    private ArrayList<Integer> domino;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * returns the value of the left number of the domino
     * @return int value of left domino
     */
    public int getLeft() {
        return left;
    }

    public boolean containsLeft(Domino num) {
        return (this.left == num.left || this.right == num.left);
    }

    public boolean containsRight(Domino num) {
        return this.left == num.right || this.right == num.right;
    }

    /**
     * returns the value of the right number of the domino
     * @return int value of right domino
     */
    public int getRight() {
        return right;
    }

    /**
     * swaps left value with right value
     */
    public void flipDomino() {
        int temp = left;
        left = right;
        right = temp;
    }

    public ArrayList<Integer> getDomino() {
        return domino;
    }

    public String toString() {
        return "[" + left + "|" + right + "]";
    }


}
