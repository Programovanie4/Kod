package org.example.cviceniea;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class RucickyFxCvicenie extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ClockPaneCvicenie3 clock = new ClockPaneCvicenie3();

        // Create a handler for animation
//        EventHandler<ActionEvent> eventHandler = e -> {
//            clock.setCurrentTime(); // Set a new clock time
//        };

        // Create an animation for a running clock
        //Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {clock.setCurrentTime();}));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation


//        AnimationTimer at = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                clock.setCurrentTime();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//        at.start();

//        Runnable r = () -> {
//            while (true) {
//                Platform.runLater(() -> clock.setCurrentTime());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//        new Thread(r).start();

        Scene scene = new Scene(clock);	// vytvor scenu

        // nastavena vazieb
        scene.widthProperty().addListener(ov -> clock.setW(scene.getWidth()));
        scene.heightProperty().addListener(ov -> clock.setH(scene.getHeight()));

        primaryStage.setTitle("Hodinky"); 	// pomenuj okno aplikacie, javisko
        primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
        primaryStage.show(); 					// zobraz javisko
    }
    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPaneCvicenie3 extends Pane {
    private int hour;
    private int minute;
    private int second;
    private boolean stopped = false;

    // Clock pane's width and height
    private double w = 450, h = 450;

    public ClockPaneCvicenie3() {
        setPrefSize(w, h);
        setCurrentTime();
        setOnMouseClicked((e) -> stopped = !stopped);
    }
    public void setH(double h) {
        this.h = h;
        paintClock();
    }
    public void setW(double w) {
        this.w = w;
        paintClock();
    }
    public void setCurrentTime() {
        if (stopped) return;
        Calendar calendar = new GregorianCalendar();
        System.out.println("setCurrentTime " + calendar.getTime());
        // nastav this.hour, minute, second podla aktualneho casu
        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE);
        hour = calendar.get(Calendar.HOUR);
        paintClock(); // Repaint the clock
    }

    protected void paintClock() {
        double clockRadius = Math.min(w, h) * 0.7/2;
        double centerX = w / 2;
        double centerY = h / 2;
        getChildren().clear();

        // kresli cifernik
        Circle c = new Circle(centerX, centerY, clockRadius);
        c.setStroke(Color.BLACK);
        c.setFill(Color.YELLOWGREEN);
        getChildren().add(c);

        // kresli sekudnovku
        getChildren().add(rucicka(second, 60, 0.6, Color.RED, 3, centerX, centerY, w, h));
        getChildren().add(rucicka(minute, 60, 0.5, Color.GREEN, 5, centerX, centerY, w, h));
        getChildren().add(rucicka(hour, 12, 0.3, Color.BLUE, 3, centerX, centerY, w, h));
        // kresli ciselnik
        // dorobit

    }
    private Line  rucicka(int val, int max, double rLen, Color col, int rWidth, double centerX, double centerY, double w, double h) {
        double alpha = val * 2* Math.PI/max;
        double len = Math.min(w, h) * rLen/2;
        Line line = new Line(centerX, centerY,
                centerX + len*Math.sin(alpha),
                centerY - len*Math.cos(alpha)
        );
        line.setStroke(col);
        line.setStrokeWidth(rWidth);
        line.setOnMouseClicked((e) -> { line.setStrokeWidth(0); e.consume(); });
        return line;
    }
}