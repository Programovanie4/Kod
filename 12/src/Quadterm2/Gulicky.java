package Quadterm2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Gulicky extends Application {
    private ObservableList<Gula> children = FXCollections.observableArrayList();
    //private List<Gula> children = new ArrayList<>();
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int scale = 5;
    private static final int speed[] = {30, 60, 90};
    private static Random rnd = new Random();
    private boolean pause = false;

    double x = 0;
    Gula g = new Gula(WIDTH/2, children);


    @Override
    public void start(Stage stage) {
        final TriedaPane pane = new TriedaPane();
        pane.setOnMouseClicked(event -> {
            g.setYSpeed(10);
            g = new Gula(WIDTH/2, children);

        });

        pane.setOnMouseMoved(e ->{
            x += e.getSceneX();
            g.setCenterX(e.getX());
        });

        final Scene scene = new Scene(pane, WIDTH, HEIGHT);

        scene.setOnKeyPressed(event -> {

            pause = !pause;

        });

        stage.setScene(scene);
        stage.show();
        pane.paint();

        zacniAnimaciu(pane);
    }

    private void zacniAnimaciu(final  TriedaPane pane) {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Text pauseText = new Text(pane.getWidth() / 2, pane.getHeight() / 2, "PAUSED");
                if (!pause) {
                    checkForCollisions(pane);
                    pane.paint();
                    pane.update();
                } else {
                    pauseText.setFont(new Font(50));
                    pane.getChildren().add(pauseText);
                }
            }
        };
        timer.start();
    }

    private void checkForCollisions(TriedaPane pane) {
        for (Gula g1 : children) {

            if ((g1.getCenterY() - (g1.getRadius() * scale) <= 0)
                    || (g1.getCenterY() + (g1.getRadius() * scale) >= pane.getHeight())) {
                g1.setYSpeed(0);
            }
        }

    }

    class TriedaPane extends Pane {
        protected void paint() {
            getChildren().clear();
            for (Gula g : children) {
                getChildren().add(g.getView());
                getChildren().add(new Text(g.getCenterX(), g.getCenterY(), String.valueOf(g.value)));
            }
        }

        private void update() {
            for (Gula g : children) {
                g.setYSpeed(g.getYSpeed() * 1.02);
                g.setCenterY(g.getCenterY() + 0.1 * g.getYSpeed());
            }
        }

    }

    private static class Gula {
        private final int radius = rnd.nextInt(11) + 1;
        private Color colour;
        private double spdX;
        private double spdY;

        int value = (int) Math.pow(2, radius);

        private final Circle view;

        public Gula(double centerX, ObservableList<Gula> children) {
        //public Gula(double centerX, List<Gula> children) {

            this.view = new Circle(centerX, 80, radius * scale);
            String c;
            if (radius == 2){
                c = "blue";
            }
            else if (radius == 3){
                c = "pink";
            }
            else if (radius == 4){
                c = "cyan";
            }
            else if (radius == 5){
                c = "magenta";
            }
            else if (radius == 6){
                c = "purple";
            }
            else if (radius == 7){
                c = "grey";
            }
            else if (radius == 8){
                c = "yellow";
            }
            else if (radius == 9){
                c = "green";
            }
            else if (radius == 10){
                c = "red";
            }
            else{
                c = "brown";
            }
            this.view.setFill(Paint.valueOf(c));

            this.spdX = 0;
            this.spdY = 0;
            children.add(this);
        }

        public int getRadius() { return radius; }

        public Circle getView() { return view; }

        public final double getCenterX() {
            return view.getCenterX();
        }

        public final void setCenterX(double centerX) { view.setCenterX(centerX); }

        public final double getCenterY() {
            return view.getCenterY();
        }

        public final void setCenterY(double centerY) {
            view.setCenterY(centerY);
        }

        public final double getYSpeed() { return spdY; }

        public final void setYSpeed(double ySpeed) { this.spdY = ySpeed; }
    }

    public static void main(String[] args) { launch(args); }
}
