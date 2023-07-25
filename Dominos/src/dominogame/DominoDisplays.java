package dominogame;

import javafx.css.PseudoClass;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class DominoDisplays {
    private static final int DOTSIZE = 4;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 35;


    public Group dominoCreator(int leftValue, int rightValue, boolean isSelected) {
        Rectangle rectangle = new Rectangle(WIDTH,HEIGHT);
        Double height = rectangle.getHeight();
        Double width = rectangle.getWidth();
        if (isSelected) {
            rectangle.setFill(Color.GREY);
        } else {
            rectangle.setFill(Color.WHITE);
        }

        rectangle.setStroke(Color.BLACK);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);

        Line line = new Line(rectangle.getWidth()/2,rectangle.getY(),rectangle.getWidth()/2,rectangle.getHeight());
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1.5);

        Group leftDisplay = new Group(leftSide(leftValue,width,height));
        Group rightDisplay = new Group(rightSide(rightValue,width,height));
        Group domino = new Group(rectangle,line,leftDisplay,rightDisplay);

        return new Group(domino);
    }

    private Group rightSide(int value, double width, double height) {
        Group rightDominoDisplay = new Group();
        Color black = Color.BLACK;
        switch(value) {
            case 1:
                rightDominoDisplay.getChildren().addAll(new Circle(width*3/4, height/2,DOTSIZE,black));
                break;
            case 2:
                rightDominoDisplay.getChildren().addAll(new Circle(width*5/8, height/4,DOTSIZE,black),
                        new Circle(width*7/8,height*3/4,DOTSIZE,Color.BLACK));
                break;
            case 3:
                rightDominoDisplay.getChildren().addAll(new Circle(width*5/8, height/4,DOTSIZE,black),
                        new Circle(width*3/4,height*1/2,DOTSIZE,black),
                        new Circle(width*7/8,height*3/4,DOTSIZE,black));
                break;
            case 4:
                rightDominoDisplay.getChildren().addAll(new Circle(width*5/8, height/4,DOTSIZE,black),
                        new Circle(width*5/8,height*3/4,DOTSIZE,black),
                        new Circle(width*7/8,height/4,DOTSIZE,black),
                        new Circle(width*7/8,height*3/4,DOTSIZE,black));
                break;
            case 5:
                rightDominoDisplay.getChildren().addAll(new Circle(width*5/8, height/4,DOTSIZE,black),
                        new Circle(width*5/8,height*3/4,DOTSIZE,black),
                        new Circle(width*7/8,height/4,DOTSIZE,black),
                        new Circle(width*7/8,height*3/4,DOTSIZE,black),
                        new Circle(width*3/4,height/2,DOTSIZE,black));
                break;
            case 6:
                rightDominoDisplay.getChildren().addAll(new Circle(width*5/8, height/4,DOTSIZE,black),
                        new Circle(width*5/8,height*3/4,DOTSIZE,black),
                        new Circle(width*7/8,height/4,DOTSIZE,black),
                        new Circle(width*7/8,height*3/4,DOTSIZE,black),
                        new Circle(width*3/4,height*3/4,DOTSIZE,black),
                        new Circle(width*3/4,height*1/4,DOTSIZE,black));
                break;
        }
        return rightDominoDisplay;
    }

    private Group leftSide(int value, double width, double height) {
        Group leftDominoDisplay = new Group();
        Color black = Color.BLACK;
        switch(value) {
            case 1:
                leftDominoDisplay.getChildren().addAll(new Circle(width/4, height/2,DOTSIZE,black));
                break;
            case 2:
                leftDominoDisplay.getChildren().addAll(new Circle(width*1/8, height/4,DOTSIZE,black),
                        new Circle(width*3/8,height*3/4,DOTSIZE,Color.BLACK));
                break;
            case 3:
                leftDominoDisplay.getChildren().addAll(new Circle(width*1/8, height/4,DOTSIZE,black),
                        new Circle(width/4,height*1/2,DOTSIZE,black),
                        new Circle(width*3/8,height*3/4,DOTSIZE,black));
                break;
            case 4:
                leftDominoDisplay.getChildren().addAll(new Circle(width*1/8, height/4,DOTSIZE,black),
                        new Circle(width*1/8,height*3/4,DOTSIZE,black),
                        new Circle(width*3/8,height/4,DOTSIZE,black),
                        new Circle(width*3/8,height*3/4,DOTSIZE,black));
                break;
            case 5:
                leftDominoDisplay.getChildren().addAll(new Circle(width*1/8, height/4,DOTSIZE,black),
                        new Circle(width*1/8,height*3/4,DOTSIZE,black),
                        new Circle(width*3/8,height/4,DOTSIZE,black),
                        new Circle(width*3/8,height*3/4,DOTSIZE,black),
                        new Circle(width/4,height/2,DOTSIZE,black));
                break;
            case 6:
                leftDominoDisplay.getChildren().addAll(new Circle(width*1/8, height/4,DOTSIZE,black),
                        new Circle(width*1/8,height*3/4,DOTSIZE,black),
                        new Circle(width*3/8,height/4,DOTSIZE,black),
                        new Circle(width*3/8,height*3/4,DOTSIZE,black),
                        new Circle(width/4,height*3/4,DOTSIZE,black),
                        new Circle(width/4,height*1/4,DOTSIZE,black));
                break;
        }
        return leftDominoDisplay;
    }




}
