import java.util.ArrayList;

public class Players{

    private Boneyard boneyard;

    private ArrayList<Domino> humanHand;
    private ArrayList<Domino> computerHand;

    public Players(Boneyard boneyard) {
        this.boneyard = boneyard;
        this.humanHand = new ArrayList<>();
        this.computerHand = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            humanHand.add(boneyard.getDomino(i));
            computerHand.add(boneyard.getDomino(i+1));
            boneyard.removeDomino(i);
            boneyard.removeDomino(i+1);
        }
        System.out.println(humanHand);
        System.out.println(computerHand);
    }




    public ArrayList<Domino> getComputerHand() {
        return computerHand;
    }

    public ArrayList<Domino> getHumanHand() {
        return humanHand;
    }


}
