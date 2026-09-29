import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int SCORE_INCREMENT = 10;
    private final int SLEEP = 25;

    private int positionX = 200;
    private int positionY = 150;
    private int velocityX = 5;
    private int velocityY = 3;
    private final int DIAMETER = 50;
    private int maxBallWidth;
    private int maxBallHeight;

    private JPanel mainPanel;
    private boolean playing = true;
    private BufferedImage bufferedImage;
    private Graphics2D bufferEngine;
    private int score = 0;

    public GameWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setState(JFrame.NORMAL);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setFocusable(true);
        mainPanel.setDoubleBuffered(true);
        add(mainPanel);
    }

    public void start() {
        setVisible(true);

        maxBallWidth = mainPanel.getWidth() - DIAMETER;
        maxBallHeight = mainPanel.getHeight() - DIAMETER;

        bufferedImage = new BufferedImage(
                mainPanel.getWidth(),
                mainPanel.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        hints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        bufferEngine = bufferedImage.createGraphics();
        bufferEngine.setRenderingHints(hints);

        while (playing) {
            update();
            drawOnBuffer();
            drawOnScreen();

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        bufferEngine.dispose();
    }

    public void update() {
        positionX += velocityX;
        positionY += velocityY;

        if (positionX >= maxBallWidth || positionX <= 0) {
            velocityX = -velocityX;
            score += SCORE_INCREMENT;
        }

        if (positionY >= maxBallHeight || positionY <= 0) {
            velocityY = -velocityY;
            score += SCORE_INCREMENT;
        }
    }

    public void drawOnBuffer() {
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0,0, mainPanel.getWidth(), mainPanel.getHeight());

        graphics.setPaint(Color.RED);
        graphics.fillOval(positionX, positionY, DIAMETER, DIAMETER);

        graphics.setPaint(Color.WHITE);
        graphics.drawString("Score: " + score, 10, 20);

        graphics.dispose();
    }

    public void drawOnScreen() {
        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();

        graphics.drawImage(bufferedImage, 0, 0, mainPanel);
        graphics.dispose();
    }
}
