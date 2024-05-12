import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Ohnostroj2 extends Application {
    //    window size
    private static double width = 600;
    private static double height = 800;
    Random rnd = new Random();
    //    private Timeline tl;
    private Timeline tlMove;

    @Override
    public void start(Stage stage) throws Exception {
        Sky root = new Sky();
        stage.setTitle("Kaboom!");
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        root.setOnMouseClicked(e -> root.boom(e.getX(), e.getY()));
        stage.show();
//        tl = new Timeline(new KeyFrame(new Duration(1000), e -> root.boom()));
//        tl.setCycleCount(Timeline.INDEFINITE);
//        tl.play();
        tlMove = new Timeline(new KeyFrame(new Duration(20), e -> root.paint()));
        tlMove.setCycleCount(Timeline.INDEFINITE);
        tlMove.play();
    }


    private class Sky extends Pane {
        private class Firework {
            private class Particle {
                double x;
                double y;
                double dx;
                double dy;
                double r;
                public boolean dead = false;
                List<Circle> circles;
                long timeToLive;
                ObservableList<Node> sky;
                Color colour;
                public Particle(double x, double y, double dx, double dy, Pane sky, Color colour) {

                    this.x = x;
                    this.y = y;
                    this.dx = dx;
                    this.dy = dy;
                    this.sky = sky.getChildren();
                    this.timeToLive = 50;
//                    this.timeToLive = rnd.nextInt(50,80);
                    this.colour = colour;
                    r = 3.5;
                    circles = new ArrayList<>();
                }
                public void move() {
                    Circle dot = new Circle(x,y,r,colour);
                    circles.add(dot);
                    x += dx;
                    y += dy;
                    dx *= .99;
                    dy += .05;
                    sky.add(dot);
                    timeToLive--;
                    if (timeToLive < 0) {
                        sky.removeAll(circles);
                        dead = true;
                    }
                }

            }

            ConcurrentLinkedDeque<Particle> particles;
            Pane sky;
            boolean dead;
            public Firework(double x, double y, Pane sky) {
                dead = false;
                this.sky = sky;
                Color colour = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                particles = new ConcurrentLinkedDeque<>();
                int particleCount = 10+rnd.nextInt(51-10);
//                int particleCount = rnd.nextInt(10,501); // too much to handle for my ntb
                for (int i = 0; i < particleCount; i++) {
                    double angle = Math.random()*2*Math.PI;
                    particles.add(new Particle(x,y,Math.cos(angle)*3,Math.sin(angle)*3, sky, colour));
                }
            }


            public void paintParticles(){
                if (particles.isEmpty()){
                    dead = true;
                    return;
                }
                for (Particle particle : particles) {
                    particle.move();
                    if (particle.dead) {
                        particles.remove(particle);
                    }
                }
            }
        }

        ConcurrentLinkedDeque<Firework> fireworks;
        int ticksSinceBoom = 0;
        public Sky() {
            fireworks = new ConcurrentLinkedDeque<>();
            setPrefSize(width, height);
            Rectangle rectangle = new Rectangle(0,0,width,height);
            rectangle.setFill(Color.NAVY);
            getChildren().add(rectangle);
        }

        public void boom() {
            fireworks.add(new Firework(rnd.nextDouble()*width, rnd.nextDouble()*height/2, this));
        }
        public void boom(double x, double y) {
            fireworks.add(new Firework(x, y, this));
        }
        public void paint() {
            ticksSinceBoom++;
            ticksSinceBoom%=5;
            if (ticksSinceBoom == 0){
                boom();
            }
            for (Firework firework : fireworks) {
                firework.paintParticles();
                if (firework.dead) {
                    fireworks.remove(firework);
                }
            }
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}