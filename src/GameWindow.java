import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int SLEEP = 100;

    private int positionX = 200;
    private int positionY = 150;
    private int velocityX = 5;
    private int velocityY = 3;
    private final int DIAMETER = 50;
    private int maxBallWidth;
    private int maxBallHeight;

    private JPanel mainPanel;
    private boolean playing = true;

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

        while (playing) {
            update();
            draw();

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        positionX += velocityX;
        positionY += velocityY;

        if (positionX >= maxBallWidth || positionX <= 0) {
            velocityX = -velocityX;
        }

        if (positionY >= maxBallHeight || positionY <= 0) {
            velocityY = -velocityY;
        }
    }

    public void draw() {
        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();

        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0,0, mainPanel.getWidth(), mainPanel.getHeight());

        graphics.setPaint(Color.RED);
        graphics.fillOval(positionX, positionY, DIAMETER, DIAMETER);

        graphics.dispose();
    }
}
