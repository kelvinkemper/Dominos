import java.util.ArrayList;
import java.util.Collections;

public class Boneyard {

    private ArrayList<Domino> dominoList;

    public Boneyard() {
        dominoList = new ArrayList<>();
    }



    public void initDominos() {
        for (int i = 6; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dominoList.add(new Domino(i,j));
                //System.out.println(dominoList);
            }
        }
        Collections.shuffle(dominoList);
        System.out.println(dominoList);
    }
}
