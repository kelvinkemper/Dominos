import java.util.ArrayList;
import java.util.Collections;

public class Boneyard {

    private ArrayList<Domino> dominoList;

    public Boneyard() {
        dominoList = new ArrayList<>();
    }


    /**
     * Initializes all 28 dominos and shuffles them
     */
    public void initDominos() {
        for (int i = 7; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                dominoList.add(new Domino(i,j));
                //System.out.println(dominoList);
            }
        }
        Collections.shuffle(dominoList);
        //System.out.println(dominoList);
    }

    public ArrayList<Domino> getDominoList() {
        return dominoList;

    }

    public void removeDomino(int i) {
        dominoList.remove(i);
    }

    public Domino getDomino(int i) {
        return dominoList.get(i);

    }

  /**  public ArrayList<Domino> fillPlayerHands() {
        ArrayList<Domino> playerHands = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            playerHands.add(getDomino(i));
        }
        System.out.println(playerHands);
        System.out.println(dominoList);
        return playerHands;

    } */
}
