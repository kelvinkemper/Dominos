public class GameController {

    private Boneyard boneyard;
    private Players humanPlayer;
    private Players computerPlayer;
    private Board board;

    /**
     * Creates and initializes the board, boneyard, players and player's hands.
     */
    public GameController() {
        boneyard = new Boneyard();
        board = new Board();
        humanPlayer = new Players(boneyard);
        computerPlayer = new Players(boneyard);



    }

}
