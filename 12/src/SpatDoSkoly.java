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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class SpatDoSkoly extends  Application {
    Random rnd = new Random();
    final int CLASSSIZEX = 910;
    final int CLASSSIZEY = 460;
    Trieda pg0 = new Trieda("Materská škôlka - BUG, ale pekný", 0);
    Trieda pg1 = new Trieda("Gymnazisti",  1);
    Trieda pg2 = new Trieda("Tanečníci - BUG, ale pekný",  2);
    Trieda pg3 = new Trieda("Matfyzáci",  3);
    List<Trieda> pgs = List.of(pg0, pg1, pg2, pg3);
    boolean paused = false;
    final int RADIUS = 50;
    class Ziak {
        double x, y, z;
        double dx, dy;
        int speed;
        Color color;
        public Ziak(double x, double y, double z) {
            if (rnd != null) {
                this.x = x; this.y = y;     // nahodna farba
                color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                speed = 1+rnd.nextInt(3);   // nahodna rychlost
                do {
                    dx = rnd.nextDouble()*2-1;      // nahodny smer
                    dy = rnd.nextDouble()*2-1;
                } while (dx == 0 && dy == 0);       // ale musi sa hybat
            }
        }
        public void scaleUp(double factor) {
            x *= factor; y *= factor;
        }
        public double distance(Ziak z) {
            return Math.sqrt(Math.pow(x-z.x,2) + Math.pow(y-z.y,2));
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ziak ziak = (Ziak) o;
            return Double.compare(ziak.x, x) == 0 &&
                    Double.compare(ziak.y, y) == 0 &&
                    Double.compare(ziak.z, z) == 0 &&
                    Double.compare(ziak.dx, dx) == 0 &&
                    Double.compare(ziak.dy, dy) == 0 &&
                    speed == ziak.speed &&
                    Objects.equals(color, ziak.color);
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, dx, dy, speed, color);
        }
    }
    class Casovac extends Thread {
        Trieda p;
        public Casovac(Trieda p) {
            this.p = p;
        }
        @Override
        public void run() {
            for(;;) {
                long ms = 5000+rnd.nextInt(5000);       // nahodny cas medzi 5-10 s
                try {
                    sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                p.addNewOne(); // pribudne novy ziak
            }
        }
    }
    class Trieda extends Pane {
        List<Ziak> ziaci = new ArrayList<>();
        String title;   // window title
        int kind;       // simulation type
        public Trieda(String title, int kind) {
            setWidth(CLASSSIZEX);
            setHeight(CLASSSIZEY);
            this.title = title; this.kind = kind;
            addNewOne();        // first kid
            setOnMouseClicked(event ->
                ziaci.add(new Ziak(event.getX(),event.getY(), ziaci.size()))
            );
            Casovac c = new Casovac(this);
            c.start();
        }
        public void scaleUp(double factor) {
            for (Ziak z : ziaci) z.scaleUp(factor);
        }
        public void addNewOne() {
            ziaci.add(new Ziak(
                    RADIUS + rnd.nextDouble()*getWidth() - 2 * RADIUS,
                    RADIUS + rnd.nextDouble()*getHeight() - 2 * RADIUS, ziaci.size()));
        }
        public void update() {
            List<Ziak>updated = new ArrayList<>();
            for (Ziak z : ziaci) {
                switch (kind) {
                    case 2:
                    case 3:
                        int count = 0;
                        double sins = 0;
                        double coses = 0;
                        for (Ziak p : ziaci) {
                            if (p != z) {
                                double smer = Math.atan2(p.y - z.y, p.x - z.x);
                                //System.out.println(smer);
                                double dist = 500.0/z.distance(p);
                                sins += Math.sin(smer) * dist;
                                coses += Math.cos(smer) * dist;
                                count++;
                            }
                        }
                        if (count > 0) {
                            if (kind == 2) {
                                z.dx = -sins / count;
                                z.dy = coses / count;
                            } else {
                                z.dx = -coses / count;
                                z.dy = -sins / count;
                            }
                        }
                        break;
                    case 0:
                    case 1:
                        List<Ziak> cols = ziaci.stream().
                                filter(x -> x != z && !updated.contains(x) &&
                                        z.distance(x) < 2 * RADIUS).collect(Collectors.toList());
                        if (cols.size() > 0) {
                            Ziak p = cols.get(0);
                            int newSpeed = (int) (1 + 2 * RADIUS - z.distance(p));
                            if (newSpeed > 2)
                                newSpeed = 2; // hack
                            double smer = Math.atan2(p.y - z.y, p.x - z.x);
                            if (kind == 1) {
                                z.dx = -Math.cos(smer);
                                z.dy = -Math.sin(smer);
                            } else {
                                z.dx = Math.sin(smer);  //romantici
                                z.dy = -Math.cos(smer);
                            }
                            z.speed = newSpeed;
                            p.dx = -z.dx;
                            p.dy = -z.dy;
                            p.speed = newSpeed;
                            updated.add(z);
                            updated.add(p);
                        }
                }
                // get direction of z
                z.x += z.speed * z.dx;
                z.y += z.speed * z.dy;
                if (z.speed > 2)
                    z.speed--;
                if (z.x < RADIUS) {
                    z.dx = -z.dx;
                    z.x = RADIUS;
                }
                if (z.y < RADIUS) {
                    z.dy = -z.dy;
                    z.y = RADIUS;
                }
                if (z.x >= getWidth() - RADIUS) {
                    z.dx = -z.dx;
                    z.x = getWidth() - RADIUS;
                }
                if (z.y >= getHeight() - RADIUS) {
                    z.dy = -z.dy;
                    z.y = getHeight() - RADIUS;
                }
            }
        }
        public void paint() {
            getChildren().clear();
            for (Ziak z : ziaci) {
                Circle c = new Circle(z.x,z.y, RADIUS);
                c.setStroke(Color.BLACK);
                c.setFill(z.color);
                getChildren().add(c);
            }
            Text t = new Text(50,50,""+ziaci.size());
            t.setFont(new Font(20));
            getChildren().add(t);
        }
    }
    @Override
    public void start(Stage primaryStage) {
        for (Trieda pg:pgs) {
            Stage stage = new Stage();
            Timeline tl = new Timeline(1000);
            tl.setCycleCount(Timeline.INDEFINITE);
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(10), event -> {
                if (!paused) {
                    pg.update();
                    pg.paint();
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
    }
    public static void main(String[] args) {
        launch(args);
    }
}
