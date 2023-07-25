package dominogame;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Display extends Application {

    private BorderPane root;
    public GridPane humanHandDisplay;
    public GameController gameController;
    public ArrayList<Domino> humanPlayerHand;
    private HBox hBox1;
    private HBox hBox2;
    private DominoDisplays dd;

    private Boneyard boneyard;
    private Players human;
    private Board board;
    private ArrayList<Domino> top;
    private ArrayList<Domino> bottom;
    private Domino baseCase;
    private int bCCounter =0;
    private int filler = 0;
    private Players computer;

    public SimpleIntegerProperty currentBoneyardDominos;
    public SimpleIntegerProperty currentComputerDominos;

    private boolean computerTurn;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dominos");
        root = new BorderPane();

        dd = new DominoDisplays();

        currentBoneyardDominos = new SimpleIntegerProperty(14);
        currentComputerDominos = new SimpleIntegerProperty(7);


        Scene scene = new Scene(root, 1200, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        top = new ArrayList<>();
        bottom = new ArrayList<>();
        hBox1 = new HBox();
        hBox2 = new HBox();

        gameController = new GameController();
        gameController.initializeGUIGame();
        humanPlayerHand = gameController.getHumanHand();
        boneyard = gameController.getBoneyard();
        human = gameController.getHuman();
        board = gameController.getBoard();
        computer = gameController.getComputerPlayer();



        humanDisplay();
        boardDisplay();
        boneyardAndComputerInfoDisplay();

        Alert gameNotOverDraw = new Alert(Alert.AlertType.WARNING);
        gameNotOverDraw.setHeaderText(null);
        gameNotOverDraw.setContentText("You must draw from the boneyard since you have no valid moves.");
        if ((human.isEmptyHand()) && !(boneyard.isDominoListEmpty())) {
            gameNotOverDraw.showAndWait();
        }

    }

    private void boardDisplay() {
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setAlignment(Pos.CENTER);

        vBox.setPadding(new Insets(0, 25, 0, dd.WIDTH/2));

        Background bg = new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY));
        vBox.setBackground(bg);

        root.setCenter(vBox);

    }



    /**
     * Displays information about how many dominos in the boneyard and hand of the computer
     */
    private void boneyardAndComputerInfoDisplay() {
        HBox dominoAmountsDisplay = new HBox();
        HBox boneyardy = new HBox();
        HBox computery = new HBox();
        dominoAmountsDisplay.setSpacing(600);
        dominoAmountsDisplay.setPrefHeight(50);
        dominoAmountsDisplay.setAlignment(Pos.TOP_CENTER);
        boneyardy.setAlignment(Pos.CENTER);
        computery.setAlignment(Pos.CENTER);


        Background bg = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        dominoAmountsDisplay.setBackground(bg);
        Label boneYardLabel = new Label();
        Label boneYardDominosAmount = new Label("Boneyard dominos: ");
        boneYardLabel.textProperty().bind(currentBoneyardDominos.asString());
        boneyardy.getChildren().addAll(boneYardDominosAmount,boneYardLabel);

        Label computerLabel = new Label();
        Text computerDominosAmount = new Text("Computer dominos: " );
        computerLabel.textProperty().bind(currentComputerDominos.asString());
        computery.getChildren().addAll(computerDominosAmount,computerLabel);
        dominoAmountsDisplay.getChildren().addAll(boneyardy,computery);
        root.setTop(dominoAmountsDisplay);

    }

    /**
     * Draws up the board for user to see
     */
    public void renderBoard(String prevSide) {
        System.out.println("board list == " + board.getBoard());
        System.out.println("base case index: " + board.getBoard().indexOf(baseCase) + "base case domino == " + baseCase);
        hBox1.getChildren().clear();
        hBox2.getChildren().clear();
        top.clear();
        bottom.clear();

        //helps determine what place the dominos will be based on the original inserted domino
        if (board.getBoard().indexOf(baseCase) % 2 == 0) {
            renderHelper(top, bottom);
        } else {
            renderHelper(bottom, top);
        }


        // creation of the dominos on the board based on 2 hboxes and 2 arrays
        for (int i = 0; i < top.size(); i++) {
            hBox1.getChildren().add(dd.dominoCreator(top.get(i).getLeft(), top.get(i).getRight(), false));
        }
        for (int i = 0; i < bottom.size(); i++) {
            hBox2.getChildren().add(dd.dominoCreator(bottom.get(i).getLeft(), bottom.get(i).getRight(), false));
        }

        fillerTester(prevSide);

        if (top.size() < bottom.size()) {
            filler = 1;
        }
        else {
            filler = 0;
        }

        if (gameController.checkWinCondition()) {
            announceWinner();
        }
    }


    public void computerPlay() {
        computer.computerPlays(board,boneyard);
        currentComputerDominos.setValue(computer.getHand().size());
        renderBoard(computer.lastSideComputerPlayers());
    }



      //  hBox1.getChildren().add(dd.dominoCreator(0,1,false));
      //  hBox1.getChildren().add(dd.dominoCreator(0,1,false));
      //  hBox2.getChildren().add(dd.dominoCreator(0,1,false));
      //  hBox2.getChildren().add(dd.dominoCreator(0,1,false));
      //  hBox2.getChildren().add(dd.dominoCreator(0,1,false));

    /**
     *  adds filler if even number of top and bottom dominos so the spacing is correct
     */
    private void fillerTester(String prevSide) {
        if (top.size() != 0 && top.size() == bottom.size() && prevSide.equals("r") && filler ==0) {
            hBox1.getChildren().add(fillRectangle());
        } else if (top.size() != 0 && top.size() == bottom.size() && prevSide.equals("l") && filler == 0) {
            System.out.println("left");
            hBox1.getChildren().add(0,fillRectangle());
        }

        if (top.size() != 0 && top.size() == bottom.size() && prevSide.equals("r") && filler ==1) {
            hBox1.getChildren().add(0,fillRectangle());
        } else if (top.size() != 0 && top.size() == bottom.size() && prevSide.equals("l") && filler == 1) {
            System.out.println("left");
            hBox1.getChildren().add(fillRectangle());

        }
    }

    /**
     * determines where each domino will be: top or bottom hboxes that display the dominos
     * @param top upper hbox that is within the board display vbox
     * @param bottom lower hbox that is within the board display vbox
     */
    private void renderHelper(ArrayList<Domino> top, ArrayList<Domino> bottom) {
        for (int i = 0; i < board.getBoardSize(); i+=2) {
            top.add(board.getBoard().get(i));
        }
        for (int i = 1; i < board.getBoardSize(); i+=2) {
            bottom.add(board.getBoard().get(i));
        }
    }

    /**
     * Invisible rectangle for spacing on the board
     * @return
     */
    private Rectangle fillRectangle() {
        Rectangle rectangle = new Rectangle(dd.WIDTH,dd.HEIGHT);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        return rectangle;
    }


    /**
     * Displays the bottom of the scene where the user can see their own dominos and
     * the player options
     */
    private void humanDisplay() {
        humanHandDisplay = new GridPane();
        HBox humanHandControls = new HBox();

        VBox humanDisplayView = new VBox(humanHandDisplay,humanHandControls);
        humanDisplayView.setSpacing(20);
        humanDisplayView.setMinHeight(150);
        Background light = new Background(new BackgroundFill(Color.ROYALBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        humanDisplayView.setBackground(light);
        humanDisplayView.setAlignment(Pos.CENTER);

        root.setBottom(humanDisplayView);

        Button draw = new Button("Draw");
        draw.setOnAction(event -> {
            drawButton();
        });
        Button rotateBtn = new Button("Rotate");
        rotateBtn.setOnAction(event -> {
            rotateGUIDomino();
        });
        Button placeLeft = new Button("Place Left");
        placeLeft.setOnAction(event -> {
            if (board.isLegalMove(humanPlayerHand.get(gameController.getHumanPlayerHandIndex()),"left")) {
                placeDominoOnLeft();
                computerPlay();
            } else {
                illegalMoveWarning();
            }
        });
        Button placeRight = new Button("Place Right");
        placeRight.setOnAction(event -> {
            if (board.isLegalMove(humanPlayerHand.get(gameController.getHumanPlayerHandIndex()),"right")) {
                placeDominoOnRight();
            } else {
           illegalMoveWarning();
        }

        });

        humanHandControls.getChildren().addAll(placeLeft,placeRight,rotateBtn,draw);
        humanHandControls.setSpacing(10);
        humanHandControls.setAlignment(Pos.CENTER);

        humanHandDisplay.setHgap(10);
        humanHandDisplay.setPadding(new Insets(0,0,0,10));
        humanHandDisplay.setAlignment(Pos.CENTER_LEFT);
        renderHumanHand();

        humanHandDisplay.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            setClickedIndex(x,y);
        });
    }

    /**
     * Places domino from hand to the board on the right side
     */
    private void placeDominoOnRight() {

        bCCounter++;
        if (bCCounter == 1) {
            baseCase = humanPlayerHand.get(gameController.getHumanPlayerHandIndex());
        }

        board.getBoard().add(humanPlayerHand.get(gameController.getHumanPlayerHandIndex()));
        humanPlayerHand.remove(gameController.getHumanPlayerHandIndex());

        renderHumanHand();
        renderBoard("r");
        computerPlay();

    }

    /**
     * Places domino from the hand to the left side of the board
     */
    private void placeDominoOnLeft() {
        bCCounter++;
        if (bCCounter == 1) {
            baseCase = humanPlayerHand.get(gameController.getHumanPlayerHandIndex());
        }

        board.getBoard().add(0, humanPlayerHand.get(gameController.getHumanPlayerHandIndex()));
        humanPlayerHand.remove(gameController.getHumanPlayerHandIndex());

        renderHumanHand();
        renderBoard("l");
    }

    /**
     * Visualization of the current dominos in the players hand
     */
    private void renderHumanHand() {
        humanHandDisplay.getChildren().clear();
        for (int i = 0; i < humanPlayerHand.size(); i++) {
            Node domino = dd.dominoCreator(humanPlayerHand.get(i).getLeft(), humanPlayerHand.get(i).getRight(),
                                            humanPlayerHand.get(i).isSelected());
            humanHandDisplay.add(domino,i,1);
        }
    }


    /**
     * sets the index of the array based on the domino that user clicks
     * uses location of the click to determine which index is used based on
     * how big the arraylist size is and if the y range is between 0 and 50
     * x range is between 10-100*count. Will also make the trigger highlightSelected()
     * @param x horizontal coordinate of mouse click
     * @param y vertical coordinate of mouse click
     */
    private void setClickedIndex(int x, int y) {
        boolean validIndex = false;
        if (y >= 0 && y <= dd.HEIGHT) {

            int count = 0;
            for (int i = 0; i < humanPlayerHand.size(); i++) {
           //     System.out.println("array size== " +humanPlayerHand.size());

                if ((x >= 10+count) && (x <= 10+count+dd.WIDTH)) {
                    for (int j = 0; j < humanPlayerHand.size(); j++) {
                        humanPlayerHand.get(j).setSelected(false);
                    }

                    gameController.setHumanPlayerHandIndex(i);
                    humanPlayerHand.get(i).setSelected(true);
                    renderHumanHand();
                    validIndex = true;
                    break;
                }
                count = 10+count + dd.WIDTH;

            }
        }
        if (!validIndex) {
            gameController.setHumanPlayerHandIndex(7);
        }
      //  System.out.println("current index== " + gameController.getHumanPlayerHandIndex());

    }

    /**
     * draws from the boneyard
     */
    private void drawButton() {
        if (!human.playerHasLegitMove(board)) {
            human.drawFromBoneyard(boneyard);
            currentBoneyardDominos.setValue(boneyard.getDominoListSize());
            renderHumanHand();
        } else {
            Alert cannotDraw = new Alert(Alert.AlertType.WARNING);
            cannotDraw.setHeaderText(null);
            cannotDraw.setContentText("You can't draw if you have a playable domino.");
            cannotDraw.showAndWait();
        }

    }

    private void rotateGUIDomino() {
        System.out.println(humanPlayerHand.get(gameController.getHumanPlayerHandIndex()));
        humanPlayerHand.get(gameController.getHumanPlayerHandIndex()).flipDomino();
        renderHumanHand();
    }

    public void announceWinner() {
        Alert winnerAlert = new Alert(Alert.AlertType.CONFIRMATION);
        winnerAlert.setHeaderText("Winner!");
        winnerAlert.setContentText(gameController.returnGUIWinner() + " won!");
        winnerAlert.showAndWait();
        System.exit(1);
    }

    public void illegalMoveWarning() {
        Alert illegalAlert = new Alert(Alert.AlertType.WARNING);
        illegalAlert.setHeaderText("Illegal Move!");
        illegalAlert.setContentText("That is not a legal move.");
        illegalAlert.showAndWait();

    }



}

