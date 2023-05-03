package Quadterm2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BDura extends Application  {

    final BallsPane canvas = new BallsPane();
    List<Ball> balls= new ArrayList<>();
    Random rnd = new Random();

    Ball b;
    double bx, by;

    boolean play = true;


    @Override
    public void start(Stage stage){
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10),
                e -> {
                    canvas.update();
                    canvas.paint();
                }));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(canvas, 450, 450);

        scene.widthProperty().addListener(ov -> canvas.setW((int)scene.getWidth()));
        scene.heightProperty().addListener(ov -> canvas.setH((int)scene.getHeight()));

        canvas.setOnMouseMoved(event -> {
            bx = event.getX();
            by = event.getY();
        });

        canvas.setOnMouseClicked(event -> {
            int size = 2;
            int s = rnd.nextInt(3);
            if(s == 1) size = 4;
            else if(s == 2) size = 8;
            b = new Ball(bx, 20, 0, 0, size);
            b.drop = true;
            balls.add(b);
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                if(play) {
                    animation.stop();
                    play = false;
                }
                else{
                    animation.play();
                    play = true;
                }
            }
        });

        stage.setTitle("2048 Balls");
        stage.setScene(scene);
        stage.show();
    }

    class BallsPane extends Pane {
        private int w = 450, h = 450;

        public BallsPane() {
            b = new Ball(bx, by, 0, 0, 2);
        }
        public void update() {
            for (Ball ball : balls) {
                ball.update(w, h);
            }
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setW(int w) {
            this.w = w;
        }

        protected void paint() {
            getChildren().clear();

            Circle c = new Circle(bx, 20, b.size*5);
            if(b.size == 2) c.setFill(Color.GREEN);
            else if(b.size == 4) c.setFill(Color.BLUE);
            else if(b.size == 8) c.setFill(Color.RED);
            getChildren().add(c);

            for (Ball ball : balls) {
                Circle ba = new Circle(ball.x, ball.y, ball.size*5);
                if(ball.size == 2) ba.setFill(Color.GREEN);
                else if(ball.size == 4) ba.setFill(Color.BLUE);
                else if(ball.size == 8) ba.setFill(Color.RED);
                getChildren().add(ba);
            }
        }
    }

    static class Ball {
        double x, y;
        double dx, dy;
        int size;
        boolean drop = false;

        public Ball(double x, double y, double dx, double dy, int size) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.size = size;
        }
        public void update(int w, int h) {
            if(drop){
                dx *= 0.996;

                x += dx;
                y += dy;

                if (dy > 0) dy *= 1.005;
                else dy *= 0.96;

                if (x < size){
                    dx = -dx;
                    if (x + dx < size) x = size;
                }

                if (y < size){
                    dy = -dy;
                    if (y + dy < size) y = size;
                }

                if (x > w-size){
                    dx = -dx;
                    if (x + dx > w-size) x = w-size;
                }

                if (y > h-size){
                    dy = -dy;
                    if (y + dy > h-size) y = h-size;
                }

                dy += 0.05;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


