package dominos;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        GameController gameController = new GameController();
        gameController.start();


      /**  GridPane grid = new GridPane();

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(grid);

        stage.setScene(new Scene(mainPane));
        stage.show(); */

    }

    public static void main(String[] args) {
        launch(args);
    }
}
