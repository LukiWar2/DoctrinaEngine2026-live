import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Game {

    private final int SLEEP = 25;
    private boolean playing = true;

    private long lastUpdate = System.currentTimeMillis();
    private RenderingEngine engine;

    public Game() {
        engine = new RenderingEngine();
    }

    public abstract void update();
    public abstract void drawOnBuffer(Graphics2D buffer);
    public abstract void initialize();

    public void start() {
        engine.start();

        initialize();

        while (playing) {
            update();
            drawOnBuffer(engine.getBuffer());
            engine.drawOnScreen();
            sleep();
        }
    }

    private void sleep() {
        long sleepTime = SLEEP - (System.currentTimeMillis() - lastUpdate);
        sleepTime = Math.max(sleepTime, 4);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lastUpdate = System.currentTimeMillis();
    }

    public int getWidth() {
        return engine.getWidth();
    }

    public int getHeight() {
        return engine.getHeight();
    }
}
