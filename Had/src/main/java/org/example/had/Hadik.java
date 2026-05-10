package org.example.had;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hadik extends Application {
    Random rnd = new Random();
    int[][] plocha = new int[20][20];
    final int SIZE = 30;
    Had had;
    @Override
    public void start(Stage stage) throws IOException {
        Playground playground = new Playground();
        Scene scene = new Scene(playground, plocha.length*30, plocha[0].length*SIZE);
        stage.setTitle("Hadik");
        stage.setScene(scene);
        stage.show();
        had = new Had();
        Timeline tl = new Timeline(new KeyFrame(javafx.util.Duration.millis(200),
                e -> {
                    had.move();
                    playground.paint();

                }
        ));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play( );
        Timeline tl1 = new Timeline(new KeyFrame(javafx.util.Duration.millis(3000),
                e -> {
                    plocha[rnd.nextInt(plocha.length)][rnd.nextInt(plocha[0].length)] = 1;
                    playground.paint();
                }
        ));
        tl1.setCycleCount(Timeline.INDEFINITE);
        tl1.play( );

    }

    class Playground extends Pane {
        public Playground() {
            setStyle("-fx-background-color: black");
            setFocusTraversable(true);
            setOnKeyPressed( e -> {
                System.out.println(e.getCode());
                switch (e.getCode()) {
                    case LEFT -> had.rotateLeft();
                    case RIGHT -> had.rotateRight();
                }
            });
        }
        public void paint() {
                getChildren().clear();
                for (int i = 0; i < plocha.length; i++) {
                    for (int j = 0; j < plocha[0].length; j++) {
                        if (plocha[i][j] == 1) {
                            javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(i*SIZE, j*SIZE, SIZE, SIZE);
                            r.setFill(javafx.scene.paint.Color.GREEN);
                            getChildren().add(r);
                        }
                    }
                }
                for (Pair<Integer, Integer> p : had.body) {
                    javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(p.getKey()*SIZE, p.getValue()*SIZE, SIZE, SIZE);
                    r.setFill(javafx.scene.paint.Color.WHITE);
                    getChildren().add(r);
                }
        }

    }
    class Had {
        int dx;
        int dy;
        List<Pair<Integer, Integer>> body;
        public Had() {
            int x = plocha.length/2;
            int y = plocha[0].length/2;
            body = new ArrayList<>(List.of(new Pair<>(x, y)));
            dx = 1;
            dy = 0;
        }
        public void rotateLeft() {
            int temp = dx;
            dx = -dy;
            dy = temp;
        }
        public void rotateRight() {
            int temp = dx;
            dx = dy;
            dy = -temp;
        }
        public void move() {
            int headX = body.get(0).getKey();
            int headY = body.get(0).getValue();
            int newHeadX = headX + dx;
            int newHeadY = headY + dy;
            try {
                if (plocha[newHeadX][newHeadY] == 1) {
                    body.add(0, new Pair<>(newHeadX, newHeadY));
                    plocha[newHeadX][newHeadY] = 0;
                } else {
                    body.add(0, new Pair<>(newHeadX, newHeadY));
                    body.remove(body.size() - 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if(rnd.nextInt(2) < 1)
                    rotateLeft();
                else
                    rotateRight();
//                body.clear();
//                int x = plocha.length/2;
//                int y = plocha[0].length/2;
//                body.add(new Pair<>(x, y));
//                dx = 1;
//                dy = 0;
            }
        }

    }

}
