import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int SLEEP = 100;

    private int positionX = 200;
    private int positionY = 150;
    private final int DIAMETER = 50;

    private JPanel mainPanel;

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

        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();
        graphics.setPaint(Color.RED);
        graphics.fillOval(positionX, positionY, DIAMETER, DIAMETER);

        graphics.dispose();
    }
}
