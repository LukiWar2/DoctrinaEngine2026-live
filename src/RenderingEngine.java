import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderingEngine {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private JFrame mainFrame;
    private JPanel mainPanel;

    private BufferedImage bufferedImage;

    public RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    private void initializePanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setFocusable(true);
        mainPanel.setDoubleBuffered(true);
        mainFrame.add(mainPanel);
    }

    private void initializeFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setState(JFrame.NORMAL);
    }

    public void start() {
        mainFrame.setVisible(true);
    }

    public Graphics2D buildBuffer() {
        bufferedImage = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D bufferEngine = bufferedImage.createGraphics();
        bufferEngine.setRenderingHints(buildRenderingHints());
        return bufferEngine;
    }

    private RenderingHints buildRenderingHints() {
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        return hints;
    }

    public int getWidth() {
        return mainPanel.getWidth();
    }

    public int getHeight() {
        return mainPanel.getHeight();
    }

    public void drawOnScreen() {
        Graphics2D graphics = (Graphics2D) mainPanel.getGraphics();

        graphics.drawImage(bufferedImage, 0, 0, mainPanel);
        graphics.dispose();
    }


}
