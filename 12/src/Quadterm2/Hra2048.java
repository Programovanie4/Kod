package Quadterm2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// inspiroval som sa svojim delom (DU)
// odrazanie od inych guliciek ani ich spajanie nefunguje (kod som zakomentoval v triede Gula)

public class Hra2048 extends Application {
    PlayGround pg;
    double widthTotal = 400;
    double heightTotal = 600;
    Random rnd = new Random();
    //double pozX;
    double gulaMoved;
    List<Gula> gule = new ArrayList<>();
    boolean genarateNew = true;
    boolean stopped = false;
    double gulaStartY = 30;
    double gulaNajmensiPolomer = 30;
    List<Color> colors = List.of(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.ORANGE,
            Color.YELLOW, Color.CHOCOLATE, Color.DARKORANGE);
    int counterId = 1;


    @Override
    public void start(Stage primaryStage) {
        pg = new PlayGround();

        pg.setOnMouseMoved(event -> {
            //System.out.println(event.getX() + ", " + event.getY() + " " +event.getButton());

            for (Gula g : gule) {
                if (!stopped) {
                    if (g.mouseMove) {
                        //System.out.println(event.getX());
                        if (event.getX() < widthTotal - g.priemerG / 2 && event.getX() > g.priemerG / 2) {
                            g.pozX = event.getX();
                        }

                    }
                }
                if (pg != null) {
                    pg.paint();
                }
            }
        });

        pg.setOnMouseClicked(event -> {

            synchronized (gule) {
                for (Gula g : gule) {
                    if (g.mouseMove) {
                        g.mouseMove = false;
                        g.moveDown = true;
                        genarateNew = true;
                    }
                }
            }
        });

        pg.setOnKeyPressed(
                event -> {
                    stopped = !stopped;
                    System.out.println(stopped);
                });

        Scene scene = new Scene(new Pane(pg));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hra 2048");
        primaryStage.show();


        Thread th = new Thread(() -> {
            while (true) {
                if (!stopped) {
                    if (genarateNew) {
                        int mocnina = 1 + rnd.nextInt(3);
                        double polomer = gulaNajmensiPolomer * mocnina;
                        double x = (int) ((Math.random() * (widthTotal - polomer / 2 - polomer / 2)) + polomer / 2);
                        double y = polomer / 2;
                        Gula g = new Gula(counterId,polomer, colors.get(mocnina - 1), mocnina, x, y);
                        counterId++;
                        g.mouseMove = true;
                        gule.add(g);
                        genarateNew = false;

                    }
                    for (Gula gulicka : gule) {
                        if (!gulicka.mouseMove && gulicka.moveDown) {

                            gulicka.start();
                        }
                    }
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (pg != null) {
                    pg.paint();
                }
            }
        });
        th.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Gula {
        int id;
        double priemerG;
        Color color;
        int hodnota;
        double pozX;
        double pozY;
        boolean mouseMove;
        boolean moveDown;

        double spomalovacY = -0.8;
        double spomalovacX = -0.8; // ked narazi
        int counter = 0;
        int counterTolerance = 100;
        double faktorSpomaleniaX = 0.8, faktorSpomaleniaY = 0.4;
        double rychlostX = 1, rychlostY = -0.7;
        double zrychlenieX = 0.1, zrychlenieY = 1;
        boolean hybeSaX = true, hybeSaY = true;
        boolean destroyed = false;

        public Gula(int id, double polomer, Color color, int hodnota, double pozX, double pozY) {
            this.id = id;
            this.priemerG = polomer;
            this.color = color;
            this.hodnota = hodnota;
            this.pozX = pozX;
            this.pozY = pozY;
        }

        public void start() {
            if (Math.abs(rychlostX) < 0.2) {
                hybeSaX = false;
            }
            if (!hybeSaX) {
                counter++;
            }
            if (!hybeSaX && pozY >= heightTotal - priemerG/2 && counter > counterTolerance){
                hybeSaY = false;
            }
            // odrazanie od guliciek (nefunguje):
//            synchronized (gule) {
//                for (Gula g : gule) {
//                    if (g.id != this.id  && this.hybeSaY && !g.mouseMove && !g.hybeSaY) {
//                        //System.out.println(g.id);
//                        if ((g.pozX >= this.pozX  && g.pozX < this.pozX + priemerG) && g.pozY -g.priemerG/2 < this.pozY) {
//                            rychlostY *= spomalovacY;
//                            pozY += rychlostY;
//                            rychlostX *= spomalovacX;
//                            pozX += rychlostX;
//                            if (!hybeSaX && counter > counterTolerance){
//                                hybeSaY = false;
//                            }
//                            System.out.println("Kolizia");
//                        }
//                    }
//                }
//            }
            if (rychlostY < 0) {
                rychlostY += faktorSpomaleniaY;
            } else {
                rychlostY -= faktorSpomaleniaY;
            }

            if (!hybeSaY) {
                if (rychlostX < 0) {
                    rychlostX += faktorSpomaleniaX;
                }
                else {
                    rychlostX -= faktorSpomaleniaX;
                }
            }

            if (pozX >= widthTotal - priemerG/2) {
                rychlostX *= spomalovacX;
                pozX += rychlostX;
            }

            if (pozX <= priemerG/2) {
                rychlostX *= spomalovacX;
                pozX += rychlostX;
            }

            if (pozY >= heightTotal - priemerG/2) {
                rychlostY *= spomalovacY;
                pozY += rychlostY;
            }

            if (hybeSaX||hybeSaY) {
                pozX += rychlostX;
                pozY += rychlostY;
            }

            if (rychlostX > 0) {
                rychlostX -= zrychlenieX;
            }
            else {
                rychlostX += zrychlenieX;
            }

            rychlostY += zrychlenieY;
        }


    }

    class PlayGround extends Canvas {

        public PlayGround() {
            setWidth(widthTotal);
            setHeight(heightTotal);
            setFocusTraversable(true);


        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();

            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, widthTotal, heightTotal);

            synchronized (gule) {
                for (Gula g : gule) {
                    if (!g.destroyed) {
                        gc.setFill(g.color);
                        gc.fillOval(g.pozX - g.priemerG / 2, g.pozY - g.priemerG / 2, g.priemerG, g.priemerG);
                        int n = (int) Math.pow(2,  g.hodnota);
                        gc.setFill(Color.BLACK);
                        int fontSize = 5;
                        gc.fillText(String.valueOf(n), g.pozX, g.pozY);
                    }
                }
            }


        }

    }
}
