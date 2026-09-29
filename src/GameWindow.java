import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int SCORE_INCREMENT = 10;
    private final int SLEEP = 25;

    private JPanel mainPanel;
    private boolean playing = true;
    private BufferedImage bufferedImage;
    private Graphics2D bufferEngine;
    private int score = 0;
    private Ball ball;

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

        setupBuffering();
        makeBall();

        while (playing) {
            update();
            drawOnBuffer();
            drawOnScreen();
            sleep();
        }

        disposeElements();
    }

    private void makeBall() {
        ball = new Ball(mainPanel.getWidth(), mainPanel.getHeight());
    }

    private void setupBuffering() {
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
    }

    private void disposeElements() {
        bufferEngine.dispose();
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        score += ball.update() * SCORE_INCREMENT;
    }

    private void drawOnBuffer() {
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        drawBackground(graphics);
        drawScore(graphics);

        ball.draw(graphics);

        graphics.dispose();
    }

    private void drawBackground(Graphics2D graphics) {
        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0,0, mainPanel.getWidth(), mainPanel.getHeight());
    }

    private void drawScore(Graphics2D graphics) {
        graphics.setPaint(Color.WHITE);
        graphics.drawString("Score: " + score, 10, 20);
    }

    private void drawOnScreen() {
        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();

        graphics.drawImage(bufferedImage, 0, 0, mainPanel);
        graphics.dispose();
    }
}
