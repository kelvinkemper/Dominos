package dominos;

import java.util.Scanner;




public class GameController {

    private Boneyard boneyard;
    private Players humanPlayer;
    private Players computerPlayer;
    private Board board;
    private Scanner sc = new Scanner(System.in);
    Domino dominoIndex;
    private int turns;

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
       // System.out.println(humanPlayer.getMyHand());
       // System.out.println(computerPlayer.getMyHand());

        System.out.println("Dominos!");
        System.out.println("Computer has " + computerPlayer.getSize() + " dominos");
        System.out.println("Boneyard contains " + boneyard.getDominoListSize() + " domino");
        System.out.println("\n");
        gameLoop();


    }

    public void gameLoop() {
        for (int i =0; i < 15; i++) {
            humanPlayer.printHumanHand();
            System.out.println("Human's turn");
            System.out.println("[p] Play Domino");
            System.out.println("[d] Draw from Boneyard");
            System.out.println("[q] Quit Game");
            char c = sc.nextLine().charAt(0);
            switch (c) {
                case 'p':
                    playLoop();
                    break;
                case 'd':
                    drawLoop();
                    break;
                case 'q':
                    quit();
                    break;
                default:
                    System.out.println("Enter 'p' to Play, 'd' to Draw or 'q' to Quit the game.");
                    break;
            }
        }

    }

    /**
     * Game loop, contains switch statement with "p", "d", and "q" which allows
     * humans turn , then computer turns.
     */
    public void playLoop() {
        boolean play = true;
        while (play) {
            if (humanPlayer.playerHasLegitMove(board)) {
                System.out.println("Which domino?");
                String chosenDomino = sc.nextLine();
                while(!(Integer.parseInt(chosenDomino) < humanPlayer.getSize())) {
                    System.out.println("Please choose a valid index for your Domino");
                    System.out.println("Which domino?");
                    chosenDomino = sc.nextLine();
                }
                dominoIndex = humanPlayer.getMyHand().get(Integer.parseInt(chosenDomino));
            } else {
                System.out.println("You must draw from the boneyard since you have no valid moves.");
                break;
            }

            System.out.println("Left or Right? (l/r)");
            String side = sc.nextLine();
            String sidePrint;
            while (!(side.equals("l") ||side.equals("r"))) {
                System.out.println("Please enter 'l' or 'r'");
                System.out.println("Left or Right? (l/r)");
                side = sc.nextLine();
            }
            if (side.equals("l")) {
                sidePrint = "left";
            } else {
                sidePrint = "right";
            }


            System.out.println("Rotate First? (y/n)");
            String flip = sc.nextLine();
            while (!(flip.equals("y") || flip.equals("n"))) {
                System.out.println("Please only enter 'y' or 'n'");
                System.out.println("Rotate First? (y/n)");
                flip = sc.nextLine();
            }
            if (flip.equals("y")) {
                dominoIndex.flipDomino();
            }

            if (board.isLegalMove(dominoIndex)) {
                humanPlayer.getMyHand().remove(dominoIndex);
                if (side.equals("l")) {
                    board.getBoard().add(0, dominoIndex);
                } else if (side.equals("r")) {
                    board.getBoard().add(dominoIndex);
                }
                System.out.println("Playing " + dominoIndex + " at " + sidePrint);
            } else {
                System.out.println("Move is not legal");
                break;
            }
            checkWinCondition();

            printComputerandBoneyardSize();
            System.out.println(board.toString());
            humanPlayer.printHumanHand();

            computerPlayer.computerPlays(board, boneyard);
            printComputerandBoneyardSize();
            System.out.println(board.toString());
            play = false;
            turns++;
            checkWinCondition();
        }

    }
            //computer's turn

    /**
     * cleans up reused print statements which include size of computer hand and size of boneyard
     */
    public void printComputerandBoneyardSize() {
        System.out.println("Computer has " + computerPlayer.getSize() + " dominos");
        System.out.println("Boneyard contains " + boneyard.getDominoListSize() + " domino");
    }

    public void checkWinCondition() {
        if (boneyard.isEmpty() || humanPlayer.isEmptyHand() || computerPlayer.isEmptyHand()) {
            quit();
        }
    }

    /**
     * If d is typed during play(), this will draw from the boneyard into the players hand if possible
     */
    public void drawLoop() {
        if (!humanPlayer.playerHasLegitMove(board)) {
            humanPlayer.drawFromBoneyard(boneyard);
        } else {
            System.out.println("You can't draw if you have a playable domino.");
        }

    }

    /**
     * quits the game and counts up the score.
     */
    public void quit() {
        System.out.println("Game Over!");
        int humanScore = humanPlayer.countScores();
        int computerScore = computerPlayer.countScores();
        System.out.println("You have " + humanScore + " points");
        System.out.println("Computer has " + computerScore + " points");
       // System.out.println("You have taken " + turns + " turns");
       // System.out.println("Computer has taken " + computerPlayer.countTurns() + " turns");
        if (humanScore == computerScore) {
            if (turns > computerPlayer.countTurns()) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }
        }
        else if (humanScore < computerScore){
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");

        }
        System.exit(0);
    }

}
