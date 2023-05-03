import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Point;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import java.util.List;
import java.util.Random;

public class Wurmi extends Application {

    private static int iconSize = 32;
    private static int sizeX = 30;	// 30 x 20
    private static int sizeY = 20;

    private static final int WIDTH = sizeX * iconSize;
    private static final int HEIGHT = sizeY * iconSize;
    private static final int row = iconSize;
    private static final int col = row;
    private static final int kocka = WIDTH / row;

    boolean goNorth, goSouth, goEast, goWest;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private List<Point> wurmi = new ArrayList();
    private Point wurmiH;
    private int act = 0;

    private Image foodImage;// = new Image("green.gif");
    private Image foodImage2;// = new Image("red.gif");
    private int foodX;
    private int foodY;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("WURMI");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);


        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = true;
                    act = UP;
                    break;
                case DOWN:
                    goSouth = true;
                    act = DOWN;
                    break;
                case LEFT:
                    goWest = true;
                    act = LEFT;
                    break;
                case RIGHT:
                    goEast = true;
                    act = RIGHT;
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = false;
                    break;
                case DOWN:
                    goSouth = false;
                    break;
                case LEFT:
                    goWest = false;
                    break;
                case RIGHT:
                    goEast = false;
                    break;
                default:
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();

        for (int i = 0; i < 3; i++) { wurmi.add(new Point(5, row / 2)); }
        wurmiH = wurmi.get(0);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        kresli(gc);
        for (int i = 0; i <50; i++)
            jablka();
        kresliWurmi(gc);

        for (int i = wurmi.size() - 1; i >= 1; i--) {
            wurmi.get(i).x = wurmi.get(i - 1).x;
            wurmi.get(i).y = wurmi.get(i - 1).y;
        }

        //if (goNorth) hore();
        //if (goSouth) dole();
        //if (goEast)  vpravo();
        //if (goWest)  vlavo();

        switch (act) {
            case RIGHT:
                vpravo();
                break;
            case LEFT:
                vlavo();
                break;
            case UP:
                hore();
                break;
            case DOWN:
                dole();
                break;
        }
        OtocSa();
        zjedz();
    }

    private void vpravo() { wurmiH.x++; }
    private void vlavo() { wurmiH.x--; }
    private void hore() { wurmiH.y--; }
    private void dole() { wurmiH.y++; }

    private void kresli(GraphicsContext gc) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gc.setFill(Color.AZURE);
                gc.fillRect(i * kocka, j * kocka, kocka, kocka);
            }
        }
    }

    private void jablka() {
//        foodX = 10;
//        foodY = 10;
        foodX = (int) (Math.random() * row - 1);
        foodY = (int) (Math.random() * col - 1);

        foodImage = new Image("green.gif");
        foodImage2 = new Image("red.gif");

        gc.drawImage(foodImage, foodX * kocka, foodY * kocka, kocka, kocka);
        gc.drawImage(foodImage2, foodX * kocka, foodY * kocka, kocka, kocka);
    }

    private void kresliWurmi(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(wurmiH.getX() * kocka, wurmiH.getY() * kocka, kocka - 1, kocka - 1, 35, 35);

        for (int i = 1; i < wurmi.size(); i++) {
            gc.fillRoundRect(wurmi.get(i).getX() * kocka, wurmi.get(i).getY() * kocka, kocka - 1,
                    kocka - 1, 20, 20);
        }
    }

    public void OtocSa() {
        Random rnd = new Random();
        int[] p = {RIGHT,LEFT};
        if (wurmiH.x < 1 || wurmiH.y < 1 || wurmiH.x * kocka >= WIDTH -1 || wurmiH.y * kocka >= HEIGHT +1) {
            act = p[rnd.nextInt(p.length)];
        }
    }


    private void zjedz() {
        if (wurmiH.getX() == foodX && wurmiH.getY() == foodY) {
            wurmi.add(new Point(-1, -1));
            jablka();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
