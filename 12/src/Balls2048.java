import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balls2048 extends  Application {
    final int CLASSSIZEX = 500;
    final int CLASSSIZEY = 900;
    Playground pg = new Playground("2048 Balls");
    boolean paused = false;
    int cas = 0;
    int sucet = 0;

    class Ball {
        double x, y;    // poloha
        double vx, vy;  // rychlost
        double ax, ay;  // zrychlenie
        double value;       // 2,4,8,16,...
        double size;
        Color color;
        public Ball(double x, double y, double vx, double vy, double ax, double ay, double value) {
            this.x = x; this.y = y;     // nahodna farba
            this.vx = vx; this.vy = vy; this.ax = ax; this.ay = ay;
            this.value = value;
            color = List.of(Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.ORANGE,
                    Color.AZURE, Color.DEEPSKYBLUE, Color.BEIGE)
                    .get((int)(Math.log(value) / Math.log(2))-1);
            //size = 20*Math.sqrt(Math.sqrt(value));
            size = 20*Math.sqrt(value);
        }
        public Ball(double x, double y, double value) {
            this(x,y,0,0,0,0,value);
        }

        public void update() {
            double newx = x+vx;
            double newy = y+vy;
            if (newy + size >= pg.getHeight()) {
                vy = -vy/2;
                vx += ax;
                x = newx;
                y = pg.getHeight() - size;
            } else if (newx < size) {
                vx = -vx;
                x = size;
                y = newy;
                vy += ay;
            } else if (x + size >= pg.getWidth()) {
                vx = -vx;
                x = pg.getWidth() - size;
                y = newy;
                vy += ay;
            } else {
                x = newx;
                y = newy;
                vx += ax;
                vy += ay;
            }
        }
        public void scaleUp(double factor) {
            x *= factor; y *= factor;
        }
        public boolean bounce(Ball z) {
            return Math.sqrt(Math.pow(x-z.x,2) + Math.pow(y-z.y,2)) <= size + z.size;
        }
        public Circle paint() {
            Circle c = new Circle(x,y, size);
            c.setStroke(Color.BLACK);
            c.setFill(color);
            return c;
        }
        public Text label() {
            Text t = new Text(""+(int)value);
            t.setFont(Font.font("dialog", 32.0*Math.sqrt(value)));
            var bounds = t.getBoundsInLocal();
            t.setX(x - bounds.getCenterX());
            t.setY(y - bounds.getCenterY());
            t.setStroke(Color.BLACK);
            return t;
        }
    }
    class Playground extends Pane {
        List<Ball> balls = new ArrayList<>();
        Ball topBall = new Ball(getWidth()/2, getHeight()/2, 2);
        String title;   // window title
        Random rnd = new Random();
        int nextValue = 2;
        public Playground(String title) {
            setWidth(CLASSSIZEX);
            setHeight(CLASSSIZEY);
            this.title = title;
            setOnMouseMoved(e -> {
                topBall.x = e.getX();
                topBall.y = topBall.size+1;
            });
            setOnMouseClicked(e -> {
                        topBall.ay = 0.05;
                        balls.add(topBall);
                        sucet += topBall.value;
                        nextValue = 1 << (1 + rnd.nextInt(3)); // 2,4,8
                        topBall = new Ball(e.getX(), e.getY(), nextValue);
            });
        }
        public void scaleUp(double factor) {
            for (Ball z : balls) z.scaleUp(factor);
        }
        public void update() {
            List<Ball> removed = new ArrayList<>();
            List<Pair<Ball,Ball>> collited = new ArrayList<>();
            List<Ball> news = new ArrayList<>();
            balls.forEach(Ball::update);
            for (Ball z1 : balls) {
                if (removed.contains(z1)) continue;
                for (Ball z2 : balls) {
                    if (removed.contains(z2)) continue;
                    if (collited.contains(new Pair(z2,z1))) continue;
                    if (z1 == z2) continue;
                    if (z1.bounce(z2))
                        if (z1.value == z2.value) {
                           removed.add(z1);
                           removed.add(z2);
                           news.add(new Ball(
                                   (z1.x+z2.x)/2, (z1.y+z2.y)/2,
                                   (z1.vx+z2.vx)/2, (z1.vy+z2.vy)/2,
                                   0,0.05,
                                   2*z1.value));
                        } else {
                            double slowdown = 0.7;
                            double smer = Math.atan2(z2.y - z1.y, z2.x - z1.x);
                            z1.vx = slowdown * (-Math.cos(smer) + z1.vx);
                            //z1.vx = -Math.cos(smer);
                            z1.vy = -Math.sin(smer)*slowdown*Math.abs(z1.vy);

                            smer = Math.atan2(z1.y - z2.y, z1.x - z2.x);
                            z2.vx = slowdown * (-Math.cos(smer) + z2.vx);
                            //z2.vx = -Math.cos(smer) + slowdown*Math.abs(z2.vx);
                            z2.vy = -Math.sin(smer)*slowdown*Math.abs(z2.vy);
                            collited.add(new Pair(z1,z2));
                        }
                }
            }
            balls.removeAll(removed);
            balls.addAll(news);
        }
        public void paint() {
            getChildren().clear();
            balls.forEach(b -> getChildren().addAll(b.paint(), b.label()));
            getChildren().addAll(topBall.paint(), topBall.label(),
                    new Text(10,10, "Čas: " + (cas/100)),
                    new Text(10,25, "Sum: " + sucet));
        }
    }
    @Override
    public void start(Stage primaryStage) {
            Stage stage = new Stage();
            Timeline tl = new Timeline(20);
            tl.setCycleCount(Timeline.INDEFINITE);
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(10), event -> {
                if (!paused) {
                    pg.update();
                    pg.paint();
                    cas++;
                }
            }));
            tl.play();
            Scene scene = new Scene(pg, CLASSSIZEX, CLASSSIZEY);
            scene.setOnKeyPressed(event -> paused = !paused);
            scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
                pg.scaleUp((double)newSceneWidth/(double)old);
                pg.prefWidth((double) newSceneWidth);
                pg.paint();
            });
            scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
                pg.scaleUp((double)newSceneHeight/(double)old);
                pg.prefHeight((double) newSceneHeight);
                pg.paint();
            });
            pg.setPrefHeight(scene.getHeight());
            pg.setPrefWidth(scene.getWidth());
            pg.paint();
            stage.setTitle(pg.title);
            stage.setScene(scene);
            stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
