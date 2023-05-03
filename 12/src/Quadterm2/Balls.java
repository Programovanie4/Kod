package Quadterm2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Balls extends Application implements Runnable {
    PlayGround pg;
    Thread thread;
    boolean gameOver;
    double width;
    double height;
    double x, y;
    double dx, dy;
    double bsize = 20;
    int score = 0;
    Random rnd = new Random();
    //Lopticka[] lopticky;

    @Override
    public void start(Stage primaryStage) {
        pg = new PlayGround();

        pg.setOnMouseMoved(event -> {
            System.out.println(event.getX() + ", " + event.getY() + event.getButton());
            // co robit, ak MouseMoved
            if (event.getX() < width && event.getX() > 0)
                if (event.getY() < height && event.getY() > 0)
                    x = event.getX();
            if (pg != null) {
                pg.paint();
            }
        });

        pg.setOnMouseClicked(event -> {
            System.out.println(event.getX() + ", " + event.getY() + event.getButton());
            // co robit, ak MouseClicked
            while (y < height - bsize/2) {
                y += 1;
                if (pg != null) {
                    pg.paint();
                }
            }
        });

        Scene scene = new Scene(new Pane(pg));
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.show();
        // vytvor thread a spusti ho
        thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop() {
        gameOver = true;
        try {
            thread.join();
        } catch (InterruptedException e) { }
    }

    public boolean update() {
        if (x < bsize) {
            score++;
            x = width/2;
            y = height/10;
            return true;
        }

        if (y < bsize)
            dy = -dy;

        return false;
    }

    public void run() {
        System.out.println("started");
        while (!gameOver) {
            // simulacia
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // vykreslenie
            if (pg != null)
                pg.paint();
            if (update()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class PlayGround extends Canvas {
        public PlayGround() {
            setWidth(400);
            setHeight(600);
            //lopticky = new Lopticka[rnd.nextInt()];

            //plocha
            width = getWidth();
            height = getHeight();

            //lopticka
            x = width/2;
            y = height/10;
        }

        public void paint() {
            // zisti aktualnu velkost
            double width = getWidth();
            double height = getHeight();

            GraphicsContext gc = getGraphicsContext2D();
            // kreslenie do gc

            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, width, height);

            gc.setFill(Color.GREEN);
            gc.fillOval(x - bsize/2, y - bsize/2, bsize, bsize);

            gc.setFill(Color.BLACK);
            gc.setFont(new Font(10));
            gc.fillText("2", x - bsize/8, y + bsize/8);

            gc.setFill(Color.BLACK);
            gc.setFont(new Font(10));
            gc.fillText(String.valueOf(score), width/10, height/10);
        }
    }

    class Lopticka {
        int id, x, y, size;
        Color color;

        public Lopticka(int id, int x, int y, int size, Color color) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
        }
    }
}