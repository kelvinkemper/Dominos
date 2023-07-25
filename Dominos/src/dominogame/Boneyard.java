package dominogame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that initializes all dominos before passing them out to players
 * Holds the remaining dominos until needed for drawing
 */
public class Boneyard {

    public ArrayList<Domino> dominoList;
    public ArrayList<Domino> playerHands;

    private final int DOMINOUPPERBOUND = 6;

    public Boneyard() {
        dominoList = new ArrayList<>();
    }

    public void initalizeDominos() {
        for (int i = DOMINOUPPERBOUND; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dominoList.add(new Domino(i,j));
            }
        }
        shuffleDominos();
    }

    private void shuffleDominos() {
        Collections.shuffle(dominoList);
    }

    public ArrayList<Domino> getDominoList() {
        return dominoList;
    }

    public boolean isDominoListEmpty() {
        return dominoList.isEmpty();
    }

    public int getDominoListSize() {
        return dominoList.size();
    }

    public boolean isEmpty() {
        return dominoList.isEmpty();
    }

    public ArrayList<Domino> fillPlayerHands() {
        playerHands = new ArrayList<>();

        for (int i = 0; i <= 6; i++) {
            Domino domino = dominoList.get(i);
            playerHands.add(domino);
            dominoList.remove(domino);

        }

        //System.out.println(dominoList);
        return playerHands;

    }

}
