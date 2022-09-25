package dominos;


public class Main {

      /**  GridPane grid = new GridPane();

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(grid);

        stage.setScene(new Scene(mainPane));
        stage.show(); */


    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.start();

    }
}
