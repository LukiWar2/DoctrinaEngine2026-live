import java.awt.*;

public class Ball {
    private int positionX = 200;
    private int positionY = 150;
    private int velocityX = 5;
    private int velocityY = 3;
    private final int DIAMETER = 50;
    private int maxBallWidth;
    private int maxBallHeight;

    public Ball(int windowWidth, int windowHeight) {
        maxBallWidth = windowWidth - DIAMETER;
        maxBallHeight = windowHeight - DIAMETER;
    }

    public int update() {
        positionX += velocityX;
        positionY += velocityY;
        int bounceCount = 0;

        if (positionX >= maxBallWidth || positionX <= 0) {
            velocityX = -velocityX;
            bounceCount++;
        }

        if (positionY >= maxBallHeight || positionY <= 0) {
            velocityY = -velocityY;
            bounceCount++;
        }

        return bounceCount;
    }

    public void draw(Graphics2D graphics) {
        graphics.setPaint(Color.RED);
        graphics.fillOval(positionX, positionY, DIAMETER, DIAMETER);
    }
}
