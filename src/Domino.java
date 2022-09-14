public class Domino {

    private int left;
    private int right;

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

    public String toString() {
        return "[" + left + "|" + right + "]";
    }


}
