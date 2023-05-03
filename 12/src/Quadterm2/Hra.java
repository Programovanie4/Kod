package Quadterm2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class Hra extends Application {
    Board board ;
    int width = 800;
    int height = 600;
    HashMap<Integer, List<Integer>> colMap = new HashMap<>();
    //    Gula ball = new Gula(width);
    ArrayList<Gula> dieti = new ArrayList<Gula>();
    boolean start = true;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board();
        Random rnd  = new Random();
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if(board.getBall){
                board.getBall = false;
                Gula g = new Gula(board.w);
                if(colMap.containsKey(g.hodnota)){
                    g.color = colMap.get(g.hodnota);
                }else{
                    //colMap.put(g.hodnota, {
                    //})
                }

                dieti.add(g);
            }
 /*           for (Gula d : dieti) {
                d.update(width, height);
            }*/
            board.print();
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        board.setOnMouseMoved(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            board.getPoloha(x,y);
            if(!dieti.get(dieti.size() - 1).zije) {
                dieti.get(dieti.size() - 1).set(x);
            }
        });

        board.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            dieti.get(dieti.size() - 1).zije = true;
            dieti.get(dieti.size() - 1).set(x);
            dieti.get(dieti.size() - 1).start();

            board.getBall = true;


        });

        Scene scene = new Scene(board);


        scene.widthProperty().addListener(ov -> board.setW(scene.getWidth()));
        scene.heightProperty().addListener(ov -> board.setH(scene.getHeight()));

        primaryStage.setTitle("Hra");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class Board extends Pane {
        double h = height;
        double w = width;
        boolean getBall = true;
        double x = 0;
        double y = 0;

        public Board(){
            setPrefSize(w, h);
        }
        public void setH(double d) {
            this.h = d;
        }
        public void setW(double d) {
            this.w = d;
        }
        public void print(){
            getChildren().clear();
            for (Gula b : dieti) {
                Circle e = new Circle(b.x, b.y, b.r);
                e.setStroke(Color.BLACK);
                e.setFill(b.c);
                getChildren().add(e);
                Label l = new Label((int) b.hodnota + "");
                l.setLayoutX(b.x);
                l.setLayoutY(b.y);
                getChildren().add(l);
            }
            Text t = new Text("x = " + x + " y = " + y);
            t.setX(30);
            t.setY(30);
            getChildren().add(t);


        }

        public void getPoloha(double x, double y) {
            this.x = x;
            this.y= y;
        }
    }

    class Gula extends Thread {
        Random rnd = new Random();
        //       ArrayList<Color> colors = new Arrays.asList( Color.BLACK);
        double x;
        double y;
        double dx = 0.5;
        double dy = 0.5;
        double r;
        Color c;
        boolean zije = false;
        double hodnota = 0;
        List<Integer> color = new LinkedList<>();

        public Gula(double w){
            hodnota = Math.pow(2, rnd.nextInt(6));
            c = Color.PINK;
            this.y = 50;
            this.r = hodnota *2;
            this.x = rnd.nextInt((int) (w - r));

        }

        public void run() {
            if(zije) {
                while (y + r < board.h) {
                    //                  System.out.println(" y = " + y);
                    y += dy;
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void set(double x) {
            if(board.w > x && x > 0) {
                this.x = x;
            }
        }
    }


}
