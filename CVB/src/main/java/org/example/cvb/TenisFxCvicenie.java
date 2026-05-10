package org.example.cvb;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TenisFxCvicenie extends Application implements Runnable {
	PlayGround pg;
	int OFFSET=5;
	int GAMEWIDTH = 800;
	int GAMEHEIGHT = 600;
	int PLAYERWIDTH = 100;
	int BALLSIZE = 5;
	int BALLSPEED = 5;
	int PLAYERSPEED = 5;
	boolean gameOver;
	Thread thread;
	int p1x = OFFSET;
	int p1y = GAMEHEIGHT/2;
	int p2x = GAMEWIDTH-OFFSET;
	int p2y = GAMEHEIGHT/2;

	int ballx=p1x;
	int bally=p1x+PLAYERWIDTH/2;
	int dx=1;
	int dy=1;

	int p1score = 0;
	int p2score = 0;

	@Override
	public void start(Stage primaryStage) {
		pg = new PlayGround();

		pg.setOnKeyPressed(event -> {
			System.out.println(event.getCode());
			if(event.getCode().equals(KeyCode.W)){
				p1y -= 5*PLAYERSPEED;
				if(dx==0){
					dx=1;
					dy=-1;
				}
			}
			if(event.getCode().equals(KeyCode.S)){
				p1y += 5*PLAYERSPEED;
				if(dx==0){
					dx=1;
					dy=1;
				}
			}

			if(event.getCode().equals(KeyCode.UP)){
				p2y -= 5*PLAYERSPEED;
				if(dx==0){
					dx=-1;
					dy=-1;
				}
			}
			if(event.getCode().equals(KeyCode.DOWN)){
				p2y += 5*PLAYERSPEED;
				if(dx==0){
					dx=-1;
					dy=1;
				}
			}
			// co robit, ak KeyPressed
		});

		pg.setOnMouseDragged(event -> {
			System.out.println(event.getX() + ", " + event.getY() + event.getButton());
			if(event.getX() <= GAMEWIDTH * 0.5){
				p1y = (int) (event.getY());
				p1x = (int) (event.getX());
			}
			else {
				p2y = (int) (event.getY());
				p2x = (int) (event.getX());
			}
		});

		
		Scene scene = new Scene(new Pane(pg));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tenis");
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

	public void run() {
		System.out.println("started");
		while (!gameOver) {
			ballx += dx*BALLSPEED;
			bally += dy*BALLSPEED;
			if(bally<0 | bally>GAMEHEIGHT){
				dy*=-1;
			}


			if(ballx>GAMEWIDTH-OFFSET-BALLSIZE/2){
				System.out.println("Player 2 missed!");
				p1score += 1;
				dx = 0;
				dy = 0;
				bally = p2y+PLAYERWIDTH/2;
				ballx = p2x - (OFFSET+BALLSIZE/2);

			}
			if (bally>p2y && bally<p2y+PLAYERWIDTH && (Math.abs(ballx-p2x-BALLSIZE/2) < 10)){
				dx*=-1;
			}


			if(ballx<OFFSET+BALLSIZE/2){
				System.out.println("Player 1 missed!");
				p2score += 1;
				dx = 0;
				dy = 0;
				bally = p1y+PLAYERWIDTH/2;
				ballx = p1x + (OFFSET+BALLSIZE/2);

			}
			if (bally>p1y && bally<p1y+PLAYERWIDTH && (Math.abs(ballx-p1x+BALLSIZE/2) < 10)){
				dx*=-1;
			}
			// simulacia
			// sleep
			// vykreslenie
			try{
				Thread.sleep(16);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (pg != null){
				Platform.runLater(()->pg.paint());
			}
			//	pg.paint();
		}
	}

	class PlayGround extends Canvas {
		public PlayGround() {
			setWidth(GAMEWIDTH);
			setHeight(GAMEHEIGHT);
			setFocusTraversable(true);  // kvoli KeyPressed evetnu
			// inicializacia
		}

		public void paint() {
			// zisti aktualnu velkost
			double width = getWidth();
			double height = getHeight();
			
			GraphicsContext gc = getGraphicsContext2D();
			// kreslenie do gc
			
			// zafarbi antuku na antukovu...
			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, width, height);
			
			gc.setLineWidth(5);
			gc.strokeLine(p1x, p1y, p1x, p1y+100);
			gc.strokeLine(p2x, p2y, p2x, p2y+100);
			gc.strokeLine(GAMEWIDTH/2, 60, GAMEWIDTH/2, GAMEHEIGHT-60);


			gc.setFill(Color.BLACK);
			gc.fillOval(ballx-BALLSIZE/2, bally-BALLSIZE/2, BALLSIZE, BALLSIZE);

			gc.setFont(Font.font(50));
			gc.fillText(p1score + "", GAMEWIDTH/4, GAMEHEIGHT/8);
			gc.fillText(p2score + "", GAMEWIDTH - GAMEWIDTH/4, GAMEHEIGHT/8);
		}
	}
}
