package Quadterm2;
import javafx.scene.paint.Color;

import java.util.*;

public class GameState {
    public boolean gameOver = false;
    public boolean paused = false;
    Random random = new Random();
    public Map<Integer, Color> colors = Map.of(  // TODO: add colors
            2, Color.GREEN, 4, Color.BLUE, 8, Color.RED, 16, Color.ORANGE, 32, Color.LIGHTBLUE,
            64, Color.WHITE, 128, Color.DARKRED, 256, Color.DARKCYAN, 512, Color.DARKGOLDENROD,
            1024, Color.GREY
    );

    Ball topBall;
    List<Ball> balls = new ArrayList<>();

    public GameState() {
        topBall = generateNewBall();
    }

    public class Ball {  // TODO
        public double x;
        public double y;
        public double radius;
        public int value;
        public double vx = 1;
        public double vy = 3;
        public double ax = 1;
        public boolean falling = false;
        public double bounce = Game2048.windowHeight - Game2048.windowHeight / 3;

        public Ball(double x, double y) {
            this.x = x;
            this.y = y;
            this.value = (int) Math.pow(2, 1 + random.nextInt(3));
            this.radius = 20 + value;
        }

        protected Ball clone() {
            Ball copy = new Ball(this.x, this.y);
            copy.radius = this.radius;
            copy.value = this.value;
            copy.falling = this.falling;
            return copy;
        }
    }

    public void updateGameState() {
        // TODO: smoother bouncing
        // falling
        for (Ball ball: balls) {
            if (ball.bounce < Game2048.windowHeight - ball.radius) {
                // System.out.println(ball.bounce);
                ball.y += ball.vy;
            }
            // ball.x += ball.vx;

            // window edges
            if (ball.falling && ball.y + ball.radius > Game2048.windowHeight) {
                ball.vy *= -1;
                ball.falling = false;
                ball.bounce *= 1.05;
                if (ball.bounce > Game2048.windowHeight) ball.bounce = Game2048.windowHeight - ball.radius;
            }
            if (ball.x + ball.radius > Game2048.windowWidth || ball.x - ball.radius < 0) {
                ball.vx *= -1;
                ball.vx *= 0.8;  // slower
            }

            if (!ball.falling && ball.y < ball.bounce) {
                ball.vy *= -1;
                ball.falling = true;
            }
        }

        for (int i = 0; i < balls.size() - 1; i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ballI = balls.get(i);
                Ball ballJ = balls.get(j);
                if ( Math.sqrt(Math.pow(ballI.x - ballJ.x, 2) + Math.pow(ballI.y - ballJ.y, 2)) < ballI.radius) {
                    // System.out.println("touch");
                    if (ballI.value == ballJ.value) {
                        // TODO: create new ball
                        balls.remove(i);
                        i--; j--;
                        ballJ.value = ballI.value + ballJ.value;
                    }
                }
            }
        }
    }

    public void updateTopBall(double x) {
        topBall.x = x;
    }

    public Ball generateNewBall() {
        return new Ball(Game2048.windowWidth / 2, 40);
    }

    public void fall() {
        topBall.falling = true;
        balls.add(topBall.clone());
        topBall = generateNewBall();
    }
}
