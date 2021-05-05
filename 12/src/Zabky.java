import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Radovan Balog
 */
public class Zabky extends Application{
        final double rychlostSimulacie = 10;	// ms
        final double horizontalnyPosun = 1;		// pixel
        // brvno sa posunie o 1 pixel za 10 ms
        final double velkostZabky = 50;			// pixel
        final double sirkaBrvna = 15;			// pixel

        final double sirkaObrazovky = 800;
        final double vyskaObrazovky = 600;

        Brvno[][] brvna = {						// predpripravene brvna, nemusite generovat
                { new Brvno(10,80, true), new Brvno(200,90, true), new Brvno(400,80, true), new Brvno(600,90, true) },
                { new Brvno(10,120, false), new Brvno(250,110, false), new Brvno(470,80, false) },
                { new Brvno(25,60, true), new Brvno(220,40, true), new Brvno(430,50, true), new Brvno(620,60, true), new Brvno(720,40, true) },
                { new Brvno(10,80, false), new Brvno(400,50, false), new Brvno(600,80, false) },
                { new Brvno(30,80, true), new Brvno(170,30, true), new Brvno(330,40, true), new Brvno(500,60, true) },
                { new Brvno(20,60, false), new Brvno(220,40, false), new Brvno(400,40, false), new Brvno(500,50, false) }
        };
        Playground playground;
        Zabka zabka;
        int newGame = 0;

        int respawn = -1;

        public void start(Stage primaryStage) {
            try {
                playground = new Playground();
                Scene scene = new Scene(playground, sirkaObrazovky, vyskaObrazovky);
                zabka = new Zabka();
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.UP) {
                        zabka.skok(true);
                    }
                    else if (event.getCode() == KeyCode.DOWN) {
                        zabka.skok(false);
                    }
                });

                Timeline gameClock = new Timeline(new KeyFrame(Duration.millis(rychlostSimulacie), e -> {
                    playground.paint();
                    if (respawn >= 0) {
                        if (respawn == 0) {
                            zabka = new Zabka();
                        }
                        respawn--;
                    }
                }));
                gameClock.setCycleCount(Timeline.INDEFINITE);
                gameClock.play();

                primaryStage.setScene(scene);
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        //---------------------------------------------------
        public class Brvno {
            double x;			// lavy koniec brvna
            double dlzka;		// jeho dlzka
            boolean doPrava;	// ide doprava/dolava
            public Brvno(double x, double dlzka, boolean doPrava) {
                this.x = x; this.dlzka = dlzka; this.doPrava = doPrava;
            }
            public void paint(int riadok) {
                double y = (riadok * 80) + 120;
                Rectangle original = new Rectangle(x, y, dlzka, sirkaBrvna);
                Rectangle partial = new Rectangle(0, y, 0, sirkaBrvna);
                if (doPrava && x + dlzka > sirkaObrazovky) {
                    partial.setWidth((x + dlzka) - sirkaObrazovky);
                }
                else if (!doPrava && x < 0) {
                    double sirka = Math.abs(x);
                    partial.setWidth(sirka);
                    partial.setX(sirkaObrazovky - sirka);
                }
                playground.getChildren().add(original);
                playground.getChildren().add(partial);
            }
            public void update() {
                x = (doPrava) ? x + horizontalnyPosun : x - horizontalnyPosun;
                if (doPrava && x > sirkaObrazovky) {
                    x = 0;
                }
                else if (!doPrava && x + dlzka < 0) {
                    x = sirkaObrazovky - dlzka;
                }
            }
        }
        //---------------------------------------------------
        public class Zabka {
            int currentRow = brvna.length;
            double x = (sirkaObrazovky / 2) - (velkostZabky / 2);
            boolean doPrava = false;
            boolean daSaOvladat = true;
            String gameCondition;

            private void changeCondition(boolean win) {
                daSaOvladat = false;
                gameCondition = (win) ? "Výhra!!!" : "R. I. P.";
                respawn = 500;
            }

            public void paint() {
                if (daSaOvladat) {
                    ImageView zabkaImage = new ImageView("zaba.png");
                    zabkaImage.setX(x);
                    zabkaImage.setY((currentRow * 80) + (120 - (velkostZabky / 2) - sirkaBrvna));
                    zabkaImage.setFitHeight(velkostZabky);
                    zabkaImage.setFitWidth(velkostZabky);
                    playground.getChildren().add(zabkaImage);
                }
                else {
                    Text condition = new Text(gameCondition);
                    condition.setX((sirkaObrazovky / 2) - 130);
                    condition.setY(vyskaObrazovky / 2);
                    condition.setFont(new Font(72));
                    condition.setFill(Color.RED);
                    condition.setStroke(Color.BLACK);
                    condition.setStrokeWidth(2);
                    playground.getChildren().add(condition);
                }
            }
            public void update() {
                if (daSaOvladat) {
                    if (currentRow < brvna.length && currentRow >= 0) {
                        if (currentRow % 2 == 1) {
                            x -= horizontalnyPosun;
                        }
                        else {
                            x += horizontalnyPosun;
                        }
                    }
                    if (x + velkostZabky < 0 || x > sirkaObrazovky) {
                        changeCondition(false);
                    }
                }
            }

            private boolean naBrvne(int row) {
                if (row < brvna.length && row >= 0) {
                    for (Brvno b : brvna[row]) {
                        if (x + velkostZabky - 10 > b.x && x + 10 < b.x + b.dlzka) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }

            public void skok(boolean hore) {
                if (daSaOvladat) {
                    if (!hore && currentRow < brvna.length) {
                        currentRow++;
                    }
                    else if (hore) {
                        currentRow--;
                        if (currentRow == -1) {
                            changeCondition(true);
                            return;
                        }
                    }
                    if (!naBrvne(currentRow)) {
                        changeCondition(false);
                    }
                }
            }
        }
        //---------------------------------------------------
        public class Playground extends Pane {
            public void paint(){
                getChildren().clear();
                for (int i = 0; i < brvna.length; i++) {
                    for (Brvno b : brvna[i]) {
                        b.update();
                        b.paint(i);
                    }
                }
                zabka.update();
                zabka.paint();
            }
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
