import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Boneyard boneyard;
    private Players players;
    private ArrayList<Domino> humanHand;
    private ArrayList<Domino> computerHand;




    @Override
    public void start(Stage stage) {


        Boneyard boneyard = new Boneyard();
        boneyard.initDominos();

        GridPane grid = new GridPane();

        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(grid);

        stage.setScene(new Scene(mainPane));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
