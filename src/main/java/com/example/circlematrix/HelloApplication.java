package com.example.circlematrix;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloApplication extends Application {
    public Double radius = 15.5;
    public Random randomColor = new Random();
    public float r ;
    public float g ;
    public float b ;
    public float ColorOpacity ;
    Color randomColorCircle ;
    //creating random Color object
    public CirlcleMatrix[][] matrixCircles ;
    @Override
    public void start(Stage stage) throws IOException {

        //Creating Flow pane for circles
        FlowPane rowColumn = new FlowPane();
        rowColumn.setVgap(5);
        rowColumn.setHgap(5);
        //Creating matrix of circles and putting them into the pane
        matrixCircles =  new CirlcleMatrix[20][10];

        for(int row =0; row< matrixCircles.length; row++){
            for (int column = 0;column < matrixCircles[row].length;column++){
                r = randomColor.nextFloat();
                g = randomColor.nextFloat();
                b = randomColor.nextFloat();
                ColorOpacity = randomColor.nextFloat();
                randomColorCircle = new Color(r,g,b,ColorOpacity);
                matrixCircles[row][column] = new CirlcleMatrix(randomColorCircle);
                matrixCircles[row][column].setRadius(radius);
                rowColumn.getChildren().add(matrixCircles[row][column]);
            }
        }


        //creating Enlarge & Shrink buttons for the row increase
        FlowPane buttonsPAne = new FlowPane();
        buttonsPAne.setVgap(20);
        buttonsPAne.setHgap(20);
        buttonsPAne.setAlignment(Pos.CENTER);
        Button PlusButton = new Button("Enlarge");
        PlusButton.setOnAction(new PlusButtonHandler());

        Button MinusButton = new Button("Shrink");
        MinusButton.setOnAction(new MinusButtonHnadler());
        buttonsPAne.getChildren().addAll(PlusButton, MinusButton);


        BorderPane pane = new BorderPane();
        pane.setCenter(rowColumn);
        pane.setBottom(buttonsPAne);
        Scene myScene = new Scene(pane, 600, 800);
        stage.setScene(myScene);
        stage.setTitle("Circles Matrix");
        stage.show();

    }

    class CirlcleMatrix extends Circle {

        double radius = 1.0;
        //Constructor with color
        CirlcleMatrix() {
            setStroke(Color.BLACK);
            setFill(Color.WHITE);
        }

        //constructor with black and white color
        CirlcleMatrix(Color newColor){
            setFill(newColor);
            setStroke(Color.BLACK);
        }
    }

    //Plus Button handler class
    class MinusButtonHnadler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            decreaseRadius(matrixCircles);
        }

        void decreaseRadius(CirlcleMatrix[][] matrix){
            for (int raw =0;raw < matrix.length;raw++){
                for (int column =0; column< matrix[raw].length;column++){
                    matrix[raw][column].setRadius((matrix[raw][column].getRadius() > 2 ? matrix[raw][column].getRadius() - 2 :
                            matrix[raw][column].getRadius()));
                }
            }

        }

    }

    //Minus Button handler class
    class PlusButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            increaseRadius(matrixCircles);

        }
        void increaseRadius(CirlcleMatrix[][] matrix){
            for (int raw =0;raw< matrix.length;raw++){
                for (int column =0;column<matrix[raw].length;column++){
                    matrix[raw][column].setRadius(matrix[raw][column].getRadius()+2);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}


