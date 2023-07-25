package dominogame;


import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        //Console start
        GameController gameController = new GameController();
        gameController.startGame();

        // GUI start
        //Application.launch(Display.class,args);
    }

}
