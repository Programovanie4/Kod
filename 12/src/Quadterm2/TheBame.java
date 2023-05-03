package Quadterm2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TheBame extends Application {
    int initX = 400;
    int initY = 400;
    List<Circle> listOfCircles = new ArrayList<>();
    List<Text> listOfTexts = new ArrayList<>();
    Circle padajuci = new Circle();
    Text padajuciText = new Text();
    Circle cakajuci = new Circle();
    Text cakajuciText = new Text();
    List<Circle> waiting = new ArrayList<>();
    List<Text> waitingText = new ArrayList<>();
    int radius = 20;
    boolean pada = false;
    double gravitacia = 5;
    double vX = 0;
    double vY = 2;
    Double[] Xs = new Double[100];
    Double[] Ys = new Double[100];
    int index = 0;

    public int getRandom(){
        int x =(int) Math.floor(Math.random()* 4);
        if (x == 0){
            return 2;
        }
        if (x == 1){
            return 4;
        }
        if (x == 2){
            return 8;
        }
        if (x == 3){
            return 16;
        }
        return 32;
    }
    public Color getColor(int value){
        if (value == 2){
            return Color.RED;
        }
        if (value == 4){
            return Color.BROWN;
        }
        if (value == 8){
            return Color.BLUE;
        }
        if (value == 16){
            return Color.GREEN;
        }

        return Color.DARKORANGE;


    }
    public Circle generateRandomCircle(int value){
        int nasobok = value / 2;
        Circle c = new Circle(initX/2,
                radius+(2 * value) + 5,
                radius + (value * 2),
                getColor(value));
        return c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane m = new Pane();
        Scene scene = new Scene(m, initX, initY, Color.WHITE);
        int value = getRandom();
        cakajuci = generateRandomCircle(value);
        cakajuciText.setText(Integer.toString(value));
        cakajuciText.setX(cakajuci.getCenterX()-5);
        cakajuciText.setY(cakajuci.getCenterY()+5);

        scene.setOnMouseMoved(event -> {

                    cakajuci.setCenterX(event.getX());
                    cakajuciText.setX(event.getX() - 5);
                }






        );
        scene.setOnKeyPressed(event -> {
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
        scene.setOnMouseClicked(event -> {
            pada = true;



        });








        primaryStage.setTitle("wall");
        primaryStage.setScene(scene);
        primaryStage.show();
        Timeline tim =
                new Timeline(new KeyFrame(new Duration(10),e -> {
                    m.getChildren().clear();
                    if (pada){
                        System.out.println("ammm");
                        Circle newCircle =
                                new Circle(cakajuci.getCenterX()
                                        ,
                                        cakajuci.getCenterY(),
                                        cakajuci.getRadius(),
                                        cakajuci.getFill());
                        Text newText =
                                new Text(cakajuciText.getX(),
                                        cakajuciText.getY(),
                                        cakajuciText.getText()) ;


                        pada = false;


                        int v = getRandom();
                        cakajuci = new Circle();
                        cakajuci = generateRandomCircle(v);
                        cakajuciText = new Text();
                        cakajuciText.setText(Integer.toString(v));
                        cakajuciText.setX(cakajuci.getCenterX()-5);
                        cakajuciText.setY(cakajuci.getCenterY()+5);
                        Xs[index] = 0.0;
                        Ys[index] = 2.0;
                        index += 1;
                        listOfTexts.add(newText);
                        listOfCircles.add(newCircle);
                    }
                    for (int i = 0; i < listOfCircles.size(); i++) {
                        Circle circ = listOfCircles.get(i);




                        System.out.println(i);
                        double circleX = circ.getCenterX();
                        double circleY = circ.getCenterY();

                        if (circleY + circ.getRadius() >= 400 ){
                            Ys[i] = -Ys[i] ;
                            Ys[i] *= 0.5;

                        }
                        else{
                            if (Ys[i] < 0){
                                Ys[i] = -Ys[i];
                            }
                        }

                        circ.setCenterX(circleX + Xs[i]);
                        circ.setCenterY(circleY + Ys[i]);
                        Text concreteText =
                                listOfTexts.get(i);
                        concreteText.setX(circleX -5);
                        concreteText.setY(circleY + 5);

                        m.getChildren().add(circ);
                        m.getChildren().add(concreteText);

                    }

                    m.getChildren().addAll(cakajuci,
                            cakajuciText);
                }
                ));
        tim.setCycleCount(Timeline.INDEFINITE);
        tim.play();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop");
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

