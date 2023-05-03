package Quadterm2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;


public class Game2048 extends Application {
    public static final double windowWidth = 640;
    public static final double windowHeight = 480;

    PlaygroundPane playground;
    GameState state = new GameState();

    public class PlaygroundPane extends Pane {

        public PlaygroundPane() {
            setWidth(windowWidth);
            setHeight(windowHeight);
        }

        public void paint() {  // updating game scene (visual)
            getChildren().clear();
            Circle topBall = new Circle(state.topBall.x, state.topBall.y, state.topBall.radius, state.colors.get(state.topBall.value));

            Label label = new Label(String.valueOf(state.topBall.value));
            label.setTextFill(Color.BLACK);
            label.setStyle("-fx-font-size: 2.5em;");
            label.setTranslateX(state.topBall.x);  // TODO: center
            label.setTranslateY(state.topBall.y);

            getChildren().addAll(topBall, label);

            for (GameState.Ball ball: state.balls) {
                Circle c = new Circle(ball.x, ball.y, ball.radius, state.colors.get(ball.value));
                Label labelC = new Label(String.valueOf(ball.value));
                labelC.setTextFill(Color.BLACK);
                labelC.setStyle("-fx-font-size: 2.5em;");
                labelC.setTranslateX(ball.x);  // TODO: center
                labelC.setTranslateY(ball.y);
                getChildren().addAll(c, labelC);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // playground
        playground = new PlaygroundPane();

        Scene scene = new Scene(playground, 640, 480);

        // events
        scene.setOnMouseMoved(e -> {
            // System.out.println(e.getX() + ", " + e.getY());
            if (!state.paused) state.updateTopBall(e.getX());
        });

        scene.setOnMouseClicked(e -> {
            if (!state.paused) state.fall();
        });

        scene.setOnKeyPressed(e -> {
            state.paused = !state.paused;
        });

        new Thread(() -> {
            while (!state.gameOver) {
                // System.out.println("updating state");
                System.out.println(state.paused);
                if (!state.paused) {
                    state.updateGameState();
                    Platform.runLater(() -> playground.paint());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
