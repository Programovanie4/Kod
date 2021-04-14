import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.text.html.parser.DTDConstants.NUMBER;

public class CoronaFx extends Application {
	final static int NUMBER = 100;
	static CoronaState cs = new CoronaState();
	final static Random rnd = new Random();

	@Override
	public void start(Stage primaryStage) {
		CoronaThreadPanel balls = new CoronaThreadPanel();

		Scene scene = new Scene(balls, 450, 450);// vytvor scenu

		scene.widthProperty().addListener(ov -> balls.setW((int) scene.getWidth()));
		scene.heightProperty().addListener(ov -> balls.setH((int) scene.getHeight()));

		primaryStage.setTitle("Corona 1"); // pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}
	public static void main(String[] args) {
		launch(args);
	}
}
class CoronaState {
	List<CoronaThread> threads;

	public CoronaState() {
		threads = new ArrayList<CoronaThread>();
	}

	synchronized
	public void addNew(CoronaThreadPanel cp, int w, int h) {
		//System.out.println("new " + threads.size());
		for(;;) {
			CoronaThread cth = new CoronaThread(cp,
					CoronaFx.rnd.nextInt(w), CoronaFx.rnd.nextInt(h),
					CoronaFx.rnd.nextInt(3) - 1, CoronaFx.rnd.nextInt(3) - 1, 5, w, h,
					// rnd.nextInt(2)  // positive
					CoronaFx.rnd.nextInt(CoronaFx.NUMBER+1) == 0 ,
					// positive 0..NUMBER -> 0,0, ... , 1 (1:NUMBER)
					30+CoronaFx.rnd.nextInt(20),  // sleep time
					200+CoronaFx.rnd.nextInt(200)		// recovery time
			);
			if ((cth.dx  | cth.dy) != 0) {
				cth.start();
				threads.add(cth);
				break;
			}
		}
	}
	synchronized
	public boolean checkCollisions(CoronaThread cth) {
		for (CoronaThread th : threads) {
			if (th != cth && !th.dead)
				if (Math.sqrt(Math.pow(th.x - cth.x,2) + Math.pow(th.y - cth.y,2)) < 2*th.size
					&& th.positive		 					// len ak je pozitivny
					&& CoronaFx.rnd.nextInt(4) < 1  // 0.1.2.3, teda len kazdeho 4teho
					)
					return true;
		}
		return false;
	}
}
class CoronaThreadPanel extends Pane {
	private int w = 450, h = 450;

	public CoronaThreadPanel() {
		Random rnd = new Random();
		for(int i = 0; i < CoronaFx.NUMBER; i++)
			CoronaFx.cs.addNew(this, w,h);
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setW(int w) {
		this.w = w;
	}

	protected void paint() {
		getChildren().clear();
		for(CoronaThread c : CoronaFx.cs.threads) {
			Circle circle = new Circle(c.x, c.y, c.size);
			Color fillColor = Color.BLUE;
			if (c.positive) {
				fillColor = Color.RED;
			}
			if (c.dead)
				fillColor = Color.BLACK;
			circle.setFill(fillColor);
			circle.setStroke(Color.BLACK);
			getChildren().add(circle);
		}
	}
}

class CoronaThread extends Thread {
	int x, y;
	int dx, dy;
	int size;
	int w, h;
	boolean positive;
	int sleepTime;
	int recoveryTime;
	int recovery;
	boolean dead = false;
	CoronaThreadPanel bp;

	public CoronaThread(CoronaThreadPanel bp, int x, int y, int dx, int dy, int size,
						int w, int h, boolean positive, int sleepTime, int recoveryTime) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.w = w;
		this.bp = bp;
		this.h = h;
		this.positive = positive;
		this.sleepTime = sleepTime;
		this.recoveryTime = recoveryTime;
		dead = false;
		if (positive) {
			System.out.println("positive");
			recovery = recoveryTime;
		}
	}

	public void update(int w, int h) {
		if (dead)
			return;
		x += dx;
		y += dy;
		if (!positive && CoronaFx.cs.checkCollisions(this)) {
			System.out.println("+positive");
			positive = true;
			recovery = recoveryTime;
		}
		if (positive) {
			recovery--;
			if (recovery <= 0)
				if (CoronaFx.rnd.nextDouble() < 0.03) {
					System.out.println("+dead");
					dead = true;
				} else {
					System.out.println("+recovered");
					positive = false;
				}
		}
		if (x < size)
			dx = -dx;
		if (y < size)
			dy = -dy;
		if (x > w - size)
			dx = -dx;
		if (y > h - size)
			dy = -dy;
	}

	@Override
	public void run() {
		while (!dead) {
			update(w, h);
			try {
				Thread.sleep(sleepTime);
				/*
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						bp.paintBallPane();
					}
				});
				*/
				Platform.runLater(() ->	bp.paint() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}