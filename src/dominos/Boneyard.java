package dominos;

import java.util.ArrayList;
import java.util.Collections;

public class Boneyard {

    private ArrayList<Domino> dominoList;
    public ArrayList<Domino> playerHands;

    public Boneyard() {
        dominoList = new ArrayList<>();
    }


    /**
     * Initializes all 28 dominos and shuffles them
     */
    public void initDominos() {
        for (int i = 6; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dominoList.add(new Domino(i,j));
            }
        }
        //System.out.println("all dominos pre shuffle:" + dominoList);
        Collections.shuffle(dominoList);
      //  System.out.println("all dominos post shuffle:" + dominoList);
    }

    public ArrayList<Domino> getDominoList() {
        return dominoList;
    }




    public int getDominoListSize() {
        return dominoList.size();
    }

    public boolean isEmpty() {
        return dominoList.isEmpty();
    }

    public ArrayList<Domino> fillPlayerHands() {
      playerHands = new ArrayList<>();

      for (int i = 0; i < 7; i++) {
          Domino domino = dominoList.get(i);
          playerHands.add(domino);
          dominoList.remove(domino);
         // TODO remove System.out.println(domino);
      }

      //System.out.println(dominoList);
      return playerHands;

  }
}
