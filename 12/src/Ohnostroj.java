
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public class Ohnostroj extends Application implements Runnable {
    OhnostrojCanvas canvas;
    Thread thread;
    List<Castica> castice = Collections.synchronizedList(new ArrayList<Castica>());
    Svetlica mysiaSvetlica;

    class OhnostrojCanvas extends Canvas {
        int width = 800;
        int height = 600;

        public OhnostrojCanvas() {
            setWidth(width);
            setHeight(height);
            setFocusTraversable(true);

            this.setOnMousePressed((event) -> {
                mysiaSvetlica = new Svetlica(event.getX(), event.getY());
            });

            this.setOnMouseReleased((event) -> {
                mysiaSvetlica.setMysiu(event.getX(), event.getY());
            });
        }

        public void paint(){

            GraphicsContext gc = getGraphicsContext2D();
            gc.setGlobalAlpha(0.2);
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, width, height);


            gc.setGlobalAlpha(1);

            if (mysiaSvetlica != null && mysiaSvetlica.isSet) {
                gc.setFill(mysiaSvetlica.farba);
                gc.fillOval(mysiaSvetlica.x, mysiaSvetlica.y, mysiaSvetlica.velkost, mysiaSvetlica.velkost);
                mysiaSvetlica.mysiaUpdate();
                if (mysiaSvetlica.counter > 25){
                    mysiaSvetlica.run();
                    mysiaSvetlica = null;
                }
            }

            for (int i = 0; i < castice.size(); i++) {
                Castica c = castice.get(i);
                if (c == null) {
                    continue;
                }
                gc.setFill(c.farba);
                gc.fillOval(c.x, c.y, c.velkost, c.velkost);
                c.update();
                if (c.counter > 25){
                    castice.remove(c);
                    i--;
                }
            }

        }
    }

    class Svetlica{
        Color farba;
        int pocetCastic;
        double x,y;
        double vx, vy;
        int counter = 0;
        double velkost = 10;
        boolean isSet = false;

        public Svetlica() {
            farba = Color.rgb((int)(256*Math.random()),
                    (int)(256*Math.random()),
                    (int)(256*Math.random()));
            pocetCastic = (int)(Math.random()*490) + 10;
            x = Math.random()*800;
            y = Math.random()*300;

        }

        public Svetlica(double x, double y) {
            farba = Color.rgb((int)(256*Math.random()),
                    (int)(256*Math.random()),
                    (int)(256*Math.random()));
            pocetCastic = (int)(Math.random()*490) + 10;
            this.x = x;
            this.y = y;

        }

        public void setMysiu(double x, double y){
            vx = (x - this.x)/25;
            vy = (y - this.y)/25;
            isSet = true;
        }

        public void mysiaUpdate() {
            x += vx;
            y += vy;
            vx *= 0.99;
            vy += 0.3;
            counter++;
        }

        public void run() {
            for (int i = 0; i < pocetCastic; i++) {
                Castica c = new Castica(farba, x, y);
                castice.add(c);

            }
        }

    }

    class Castica{
        Color farba;
        double velkost = 7;
        double x, y, vx, vy;
        int counter = 0;
        double rychlost;

        public Castica(Color farba, double x, double y) {
            double smer = Math.random()*2*Math.PI;
            this.farba = farba;
            this.x = x;
            this.y = y;
            rychlost = Math.random()*6;
            vx = Math.cos(smer)*rychlost;
            vy = Math.sin(smer)*rychlost;
        }

        public void update() {
            x += vx;
            y += vy;
            vx *= 0.99;
            vy += 0.05;
            counter++;
            velkost *= 0.99;

        }
    }

    @Override
    public void start(Stage stage){
        canvas = new OhnostrojCanvas();

        Timeline t = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            Svetlica s = new Svetlica();
            s.run();
        }));
        // toto urci aby bolo 60 sekund
        t.setCycleCount(600);
        t.play();

        Scene scene = new Scene(new Pane(canvas));
        stage.setScene(scene);
        stage.setTitle("Ohnostroj");
        stage.show();
        // vytvor thread a spusti ho
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            // simulacia
            try {
                sleep(20);
            } catch (InterruptedException e) {

            }
            // vykreslenie
            if (canvas != null)
                canvas.paint();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}