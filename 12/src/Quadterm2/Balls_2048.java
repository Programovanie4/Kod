package Quadterm2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Balls_2048 extends Application {

    final int width = 600;
    final int height = 800;
    int highestNum = 1;

    ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>(List.of(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PURPLE, Color.ORANGE, Color.CYAN, Color.AQUA));
    HashMap<Integer, Color> colorSize = new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        BallsPane pane = new BallsPane();
        Random rand  = new Random();
        for(int i = 1; i < colors.size()+1; i++) colorSize.put((int) Math.pow(2, i), colors.get(i-1));

        int initBnum = (int) Math.pow(2, rand.nextInt(8));

        int initBr = (int) Math.sqrt(initBnum) * 4;
        double initBx = initBr + rand.nextInt(pane.w-initBr*2);
        double initBy = 50;
        Ball initB = new Ball(initBx, initBy, initBr, 1, 1, initBnum, colorSize.get(initBnum));
        balls.add(initB);
        System.out.println("new ball" + balls.size());
        initB.start();

        Timeline animation2 = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            for (Ball b : balls) {
                if(b.moving) b.update(pane.w, pane.h);
            }
            pane.print();
        }));

        pane.setOnMouseClicked(event -> {
            //System.out.println(event.getX() + " " + event.getY());
            for (Ball b : balls) {
                if(!b.moving) b.moving = true;
            }

            int num = (int) Math.pow(2, rand.nextInt(8));

            int r = (int) Math.sqrt(num) * 4;
            int x = rand.nextInt(pane.w);
            int y = 50;
            Ball b = new Ball(x, y, r, 1, 1, num, colorSize.get(num));
            balls.add(b);
            System.out.println("new ball" + balls.size());
            b.start();

        });

        pane.setOnMouseMoved(event -> {
            Ball ball = balls.get(balls.size()-1);
            if(!ball.moving) ball.updateBeforeDrop(pane.w, pane.h, event.getX());
        });

        animation2.setCycleCount(Timeline.INDEFINITE);
        animation2.play();

        Scene scene = new Scene(pane);

        pane.setOnKeyReleased(e -> {
            if(e.getCode().equals(KeyCode.P)){ // P = pauza

            }
        });

        scene.widthProperty().addListener(ov -> pane.setW(scene.getWidth()));
        scene.heightProperty().addListener(ov -> pane.setH(scene.getHeight()));

        primaryStage.setTitle("BALLS_2048");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class BallsPane extends Pane{
        int h = height;
        int w = width;

        public BallsPane(){
            setPrefSize(w, h);
        }
        public void setH(double d) {
            this.h = (int) d;
        }
        public void setW(double d) {
            this.w = (int) d;
        }
        public void print(){
            getChildren().clear();
            for (Ball b : balls) {
                Circle c = new Circle(b.x, b.y, b.r, b.color);
                c.setStrokeWidth(3);
                c.setStroke(Color.BLACK);
                c.setFill(b.color);
                getChildren().add(c);
                Label l = new Label(b.num + "");
                l.setLayoutX(b.x);
                l.setLayoutY(b.y);
                getChildren().add(l);
            }
            //getChildren().add();
        }
    }

    class Ball extends Thread{
        double x;
        double y;
        double r;
        double dx;
        double dy;
        int num;
        Color color;
        boolean exists;
        boolean moving;

        public Ball(double x, double y, double r, double dx, double dy, int num, Color color){
            this.x = x;
            this.y = y;
            this.r = r;
            this.dx = dx;
            this.dy = dy;
            this.num = num;
            this.color = color;
            this.exists = true;
            this.moving = false;
        }
        public void updateBeforeDrop(int w, int h, double mouseX) {
            if(mouseX > 0) x = mouseX;
        }

        public void update(int w, int h) {

            // x += dx;
            y += dy;
            if (x < r)
                dx = -dx;
            if (y < r)
                dy = -dy;
            if (x > w-r)
                dx = -dx;
            if (x < r)
                dx = -dx;
            if (y > h-r){
                dy = -dy;
            }

            dy+= 0.015;

            /*
            // tu som chcel prechdzat vsetky a v pripade intersekcie spravit fyziku odrazu
            for(var b : balls){
                if(b.moving && b.exists && b != this){
                    if(Math.sqrt(Math.pow(this.x - b.x, 2) + Math.pow(this.y - b.y, 2)) <= this.x + this.r){
                        this.dx *= -1;
                        b.dx *= -1;
                        this.dy *= -1;
                        b.dy *= -1;
                    }
                }
            }
             */
        }

        public void run() {
            while (exists) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
