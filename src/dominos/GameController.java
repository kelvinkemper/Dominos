package dominos;

import java.util.Scanner;


public class GameController {

    private Boneyard boneyard;
    private Players humanPlayer;
    private Players computerPlayer;
    private Board board;
    private Scanner sc = new Scanner(System.in);
    Domino dominoIndex;

    /**
     * Creates and initializes the board, boneyard, players and player's hands.
     */
    public GameController() {
        boneyard = new Boneyard();
        board = new Board();
        humanPlayer = new Players();
        computerPlayer = new Players();




    }

    public void start() {
        boneyard.initDominos();
        humanPlayer.setMyHand(boneyard.fillPlayerHands());
        computerPlayer.setMyHand(boneyard.fillPlayerHands());

        //TODO remove println
      //  System.out.println(humanPlayer.getMyHand());
       // System.out.println(computerPlayer.getMyHand());

        System.out.println("Dominos!");
        System.out.println("Computer has " + computerPlayer.getSize() + " dominos");
        System.out.println("Boneyard contains " + boneyard.getDominoListSize() + " domino");
        System.out.println("\n");
        gameLoop();


    }

    public void gameLoop() {
        for (int i =0; i < 3; i++) {
            humanPlayer.printHumanHand();
            System.out.println("Human's turn");
            System.out.println("[p] Play Domino");
            System.out.println("[d] Draw from Boneyard");
            System.out.println("[q] Quit Game");
            char c = sc.nextLine().charAt(0);
            switch (c) {
                case 'p':
                    if (board.isEmpty() || humanPlayer.playerHasMove(board)) {
                        System.out.println("Which domino?");
                        String chosenDomino = sc.nextLine();
                        dominoIndex = humanPlayer.getMyHand().get(Integer.parseInt(chosenDomino));
                        humanPlayer.getMyHand().remove(Integer.parseInt(chosenDomino));

                        System.out.println("Left or Right? (l/r)");
                        String side = sc.nextLine();
                        String sidePrint;
                        if (side.equals("l")) {
                            sidePrint = "left";
                        } else {
                            sidePrint = "right";
                        }

                        System.out.println("Rotate First? (y/n)");
                        String flip = sc.nextLine();
                        if (flip.equals("y")) {
                            dominoIndex.flipDomino();
                        }

                        if (side.equals("l")) {
                            board.getBoard().add(0, dominoIndex);
                        } else if (side.equals("r")) {
                            board.getBoard().add(dominoIndex);
                        }
                        System.out.println("Playing " + dominoIndex + " at " + sidePrint);
                        System.out.println("Computer has " + computerPlayer.getSize() + " dominos");
                        System.out.println("Boneyard contains " + boneyard.getDominoListSize() + " domino");

                    }
                    System.out.println(board);

            }
        }

    }



}
