import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hra2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    BorderPane root;
    PlaygroundPane playground;
    Ball topBall;
    Label score = new Label("SCORE 000");
    boolean canMove = true;
    List<Ball> lopticky = new ArrayList<>();

    public class Ball extends Pane{
        private double positionX = 250;
        private double positionY = 40;
        private Integer hodnota;
        private double velkost = 12;
        private Random rnd = new Random();
        private double dy = 10;

        public Ball() {
            hodnota = (int) Math.pow(2,rnd.nextInt(3)+1);
            setVelkost();
        }

        private void setVelkost(){
            if(hodnota == null){
                return;
            }
            velkost = 15 + 2 * hodnota;
        }

        public void paint(){
            getChildren().clear();
            Circle k = new Circle();
            setColor(k);
            k.setRadius(velkost);
            k.setCenterX(positionX);
            k.setCenterY(positionY);
            Text t = new Text(hodnota + "");
            t.setFill(Color.BLACK);
            t.setX(positionX -4);
            t.setY(positionY +4);
            t.setStrokeWidth(5);
            t.setTextAlignment(TextAlignment.CENTER);
            getChildren().add(k);
            getChildren().add(t);
        }

        public void setPositionX(double pos){
            positionX = pos;
            paint();
        }

        private void setColor(Circle k){
            if(hodnota == 2){
                k.setFill(Color.YELLOW.darker());
            }else if(hodnota == 4){
                k.setFill(Color.GREEN);
            }
            else{
                k.setFill(Color.BLUE);
            }
        }

        public void fall(){
            if((positionY + dy) < playground.getHeight() - velkost){
                positionY += dy;
                dy += 3;
                paint();
            }
            else if(positionY < playground.getHeight() - velkost){
                positionY = playground.getHeight() - velkost + 6;
                paint();
            }
        }
    }
    public class PlaygroundPane extends Pane {
        public PlaygroundPane() {
            this.getChildren().add(topBall);
            topBall.paint();

            this.setOnMouseClicked(e -> {
                System.out.println("mouse click");
                Ball b = topBall;
                lopticky.add(b);
                Timeline pad = new Timeline(new KeyFrame(new Duration(100), v -> {
                    if (canMove)
                    b.fall();
                }));
                pad.setCycleCount(50);
                pad.play();
                topBall = new Ball();
                getChildren().add(topBall);
                topBall.paint();

            });

            setOnMouseMoved(e ->{
                if(canMove){
                    topBall.setPositionX(e.getX());
                }
            });

            setOnKeyPressed( e -> {
                System.out.println("key pressed");
                canMove = !canMove;
                if (canMove) {
                    System.out.println("Mozes hybat");
                } else {
                    System.out.println("Nemozes hybat");
                }
            });
        }
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        root.setPadding(new Insets(10));
        Color pozadie = Color.GRAY.darker().darker().darker();
        root.setBackground(new Background(new BackgroundFill(pozadie, new CornerRadii(0), Insets.EMPTY)));
        score.setTextFill(Color.WHITE);

        //GUI
        HBox topPanel = new HBox(score);
        topPanel.setAlignment(Pos.TOP_RIGHT);
        topPanel.setSpacing(40);
        root.setTop(topPanel);

        // playground
        topBall = new Ball();
        root.setCenter(playground = new PlaygroundPane());
        // Doladime okno:
        primaryStage.setTitle("Hra 2048 Ball");

        Scene scene = new Scene(root, 500, 700);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            System.out.println("key pressed");
            canMove = !canMove;
            if (canMove) {
                System.out.println("Mozes hybat");
            } else {
                System.out.println("Nemozes hybat");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        // playground.paint();

    }
}
