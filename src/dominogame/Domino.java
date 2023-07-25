package dominogame;

import java.util.ArrayList;

/**
 * The actual domino objects, basically contains the number that each
 * domino holds per side (left/right)
 */
public class Domino {

    private int left;
    private int right;
    private boolean isSelected;


    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
        this.isSelected = false;
    }

    /**
     * swaps left value with right value
     */
    public void flipDomino() {
        int temp = left;
        left = right;
        right = temp;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public String toString() {
        return "[" + left + "|" + right + "]";
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * @return if the domino is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

}
