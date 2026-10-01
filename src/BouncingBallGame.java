import java.awt.*;

public class BouncingBallGame extends Game {
    private final int SCORE_INCREMENT = 10;
    private int score;
    private Ball ball;

    @Override
    public void update() {
        score += ball.update() * SCORE_INCREMENT;
    }

    @Override
    public void drawOnBuffer(Graphics2D buffer) {
        drawBackground(buffer);
        drawScore(buffer);

        ball.draw(buffer);
    }

    @Override
    public void initialize() {
        ball = new Ball(getWidth(), getHeight());
        score = 0;
    }

    private void drawBackground(Graphics2D graphics) {
        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0,0, getWidth(), getHeight());
    }

    private void drawScore(Graphics2D graphics) {
        graphics.setPaint(Color.WHITE);
        graphics.drawString("Score: " + score, 10, 20);
    }
}
