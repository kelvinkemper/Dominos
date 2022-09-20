import java.util.ArrayList;

public class Players{

    private Boneyard boneyard;

    private ArrayList<Domino> myHand;
    private ArrayList<Domino> computerHand;

    /**
     * Creates a new player for playing the game as well as taking 7 dominos from the boneyard
     * @param boneyard
     */
    public Players(Boneyard boneyard) {
        this.boneyard = boneyard;
        this.myHand = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            myHand.add(boneyard.getDomino(i));
            boneyard.removeDomino(i);
        }
    }


    /**
     * @return returns arraylist of myHand
     */
    public ArrayList<Domino> getMyHand() {
        return myHand;
    }


}
