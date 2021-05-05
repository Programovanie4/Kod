package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TanksInCanvas extends Application {
    final int TILE_SIZE = 48; // velkost jedneho policka a tanku (px)
    final int WORLD_WIDTH = 20; // sirka celeho sveta (pocet policok)
    final int WORLD_HEIGHT = 15; // vyska celeho sveta (pocet policok)

    final int SCENE_WIDTH = WORLD_WIDTH * TILE_SIZE; // sirka aplikacie
    final int SCENE_HEIGHT = WORLD_HEIGHT * TILE_SIZE; // vyska aplikacie

    final Image imgLeft = new Image("imgs/left.gif");
    final Image imgUp = new Image("imgs/up.gif");
    final Image imgRight = new Image("imgs/right.gif");
    final Image imgDown = new Image("imgs/down.gif");
    final Image imgBrick = new Image("imgs/brick.gif");
    final Image imgRock = new Image("imgs/rock.gif");

    final int MAX_BULLETS = 3; // maximalny pocet zivych nabojov

    Tile[][] world = World.generateWorld(WORLD_WIDTH, WORLD_HEIGHT);

    // vase premenne sem
    int tankX = WORLD_WIDTH / 2 * TILE_SIZE;
    int tankY = WORLD_HEIGHT / 2 * TILE_SIZE;
    int tankDirection = 1;    //0 - right, 1 - up, 2 - left, 3 - down
    boolean keyIsPressed = false;

    final int TANK_SPEED = 3;
    final int BULLET_SPEED = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        MyCanvas can = new MyCanvas();
        Pane root = new Pane(can);
        can.setFocusTraversable(true);
        primaryStage.setTitle("Java Quadterm 2: Tanky");
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        can.paint();
        can.requestFocus();
        // stlacenie klavesy
        can.setOnKeyPressed(e -> {
            //System.out.printf("stlacil si: %s\n", e.getCode());
            if(e.getCode().equals(KeyCode.RIGHT)){
                tankDirection = 0;
                keyIsPressed = true;
            }
            if(e.getCode().equals(KeyCode.UP)){
                tankDirection = 1;
                keyIsPressed = true;
            }
            if(e.getCode().equals(KeyCode.LEFT)){
                tankDirection = 2;
                keyIsPressed = true;
            }
            if(e.getCode().equals(KeyCode.DOWN)){
                tankDirection = 3;
                keyIsPressed = true;
            }
            if(e.getCode().equals(KeyCode.SPACE)){
                can.newBullet(root, tankDirection);
            }
            if(e.getCode().equals(KeyCode.R)){
                world = World.generateWorld(WORLD_WIDTH, WORLD_HEIGHT);
            }
        });
        can.setOnKeyReleased(e -> keyIsPressed = false);

        // tick kazdu sekundu
        Timeline timeline = new Timeline(new KeyFrame(new Duration(15), e -> {
            //tick();
            can.update(root);
            can.paint();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void tick() {
        System.out.println("tick");
    }

    //public int[2] getCoordinateFrom ....

    class MyCanvas extends Canvas {
        List<Bullet> bullets = new ArrayList<>();
        long start;
        public MyCanvas() {
            setWidth(SCENE_WIDTH);
            setHeight(SCENE_HEIGHT);

            start = System.currentTimeMillis();
        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);

            // plocha
            int brickCounter = 0;
            for(int x = 0; x < WORLD_WIDTH; x++){
                for(int y = 0; y < WORLD_HEIGHT; y++){
                    Tile tile = world[x][y];
                    if(tile == Tile.ROCK){
                        gc.drawImage(imgRock, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                    if(tile == Tile.BRICK){
                        gc.drawImage(imgBrick, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        brickCounter++;
                    }
                }
            }

            // tank
            Image tankImage = null;
            switch(tankDirection){
                case 0: tankImage = imgRight; break;
                case 1: tankImage = imgUp; break;
                case 2: tankImage = imgLeft; break;
                case 3: tankImage = imgDown; break;
            }
            gc.drawImage(tankImage, tankX, tankY, TILE_SIZE, TILE_SIZE);

            // text
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font ("Verdana", 30));
            long currentTime = System.currentTimeMillis();
            long seconds = (currentTime - start) / 1000;
            gc.fillText("Zvysne tehly: " + brickCounter + " Cas: " + seconds, 10, 30);

            if(brickCounter == 0){
                gc.fillText("!!! VYHRAL SI !!!", WORLD_WIDTH * TILE_SIZE / 2 - 100, WORLD_HEIGHT * TILE_SIZE - TILE_SIZE / 2);
            }
        }

        public void newBullet(Pane root, int direction){
            if(bullets.size() < MAX_BULLETS){
                Bullet bullet = new Bullet(tankX + TILE_SIZE / 2, tankY + TILE_SIZE / 2, 5, direction);

                root.getChildren().add(bullet);     //prida do pane, ktory si ich prekresluje sam
                bullets.add(bullet);
            }
        }

        public void update(Pane root) {
            // pohyb tanka po svete
            if (keyIsPressed) {
                int tankMoveX = 0, tankMoveY = 0;

                switch (tankDirection) {
                    case 0:
                        tankMoveX = TANK_SPEED;
                        break;
                    case 1:
                        tankMoveY = -1 * TANK_SPEED;
                        break;
                    case 2:
                        tankMoveX = -1 * TANK_SPEED;
                        break;
                    case 3:
                        tankMoveY = TANK_SPEED;
                        break;
                }

                // kolizia tanka (nic lepsie mi nenapadlo v rychlost :-))
                int x = getTileCoordinateFromCanvasCoordinate(tankX + TILE_SIZE / 2 + tankMoveX);   // stred tanku v X
                int y = getTileCoordinateFromCanvasCoordinate(tankY + TILE_SIZE / 2 + tankMoveY);   // stred tanku v Y

                if(world[x][y] != Tile.EMPTY){
                    tankMoveX = 0;
                    tankMoveY = 0;
                }

                tankX += tankMoveX;
                tankY += tankMoveY;
            }

            // update striel
            for(Bullet bullet: bullets){
                bullet.update();
            }

            // kolizia striel
            List<Bullet> toRemove = new ArrayList<>();
            for(Bullet bullet: bullets){
                int x = getTileCoordinateFromCanvasCoordinate((int) bullet.getCenterX());
                int y = getTileCoordinateFromCanvasCoordinate((int) bullet.getCenterY());

                Tile tile = world[x][y];
                if(tile != Tile.EMPTY){
                    if(tile == Tile.BRICK){
                        world[x][y] = Tile.EMPTY;
                    }

                    toRemove.add(bullet);
                }
            }
            bullets.removeAll(toRemove);
            root.getChildren().removeAll(toRemove);
        }

        public int getTileCoordinateFromCanvasCoordinate(int number){       //vrati suradnicu v poli 'world'
            return number / TILE_SIZE;
        }
    }

    class Bullet extends Circle{
        double x, y, r;
        int direction;
        Color color = Color.WHITE;

        public Bullet(double x, double y, double r, int direction){
            this.x = x; setCenterX(x);
            this.y = y; setCenterY(y);
            this.r = r; setRadius(r);

            this.direction = direction;

            setFill(color);
        }

        public void update(){
            switch(direction){
                case 0:
                    x += BULLET_SPEED;
                    break;
                case 1:
                    y -= BULLET_SPEED;
                    break;
                case 2:
                    x -= BULLET_SPEED;
                    break;
                case 3:
                    y += BULLET_SPEED;
                    break;
            }
            setCenterX(x);
            setCenterY(y);
        }
    }
}