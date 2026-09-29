import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JPanel mainPanel;

    public GameWindow() {
        setSize(800, 600);
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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();
        graphics.setPaint(Color.RED);
        graphics.fillOval(200, 150, 50, 50);

        graphics.dispose();
    }
}
