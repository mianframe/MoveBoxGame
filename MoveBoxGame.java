import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MoveBoxGame extends JPanel implements KeyListener {
    private int boxX = 100;
    private int boxY = 100;
    private int boxSize = 50;

    private int coinX = 200;
    private int coinY = 200;
    private final int coinSize = 20;

    private final Random rand = new Random();

    public MoveBoxGame() {
        JFrame frame = new JFrame("Still Move Box Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        frame.addKeyListener(this);

        // Wait until panel is sized before placing first coin
        SwingUtilities.invokeLater(() -> {
            placeCoin();
            repaint();
        });
    }

    public void placeCoin() {
        int maxX = getWidth() - coinSize;
        int maxY = getHeight() - coinSize;
        if (maxX > 0 && maxY > 0) {
            coinX = rand.nextInt(maxX);
            coinY = rand.nextInt(maxY);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        // Draw player box
        g.setColor(Color.CYAN);
        g.fillRect(boxX, boxY, boxSize, boxSize);

        // Draw coin
        g.setColor(Color.YELLOW);
        g.fillOval(coinX, coinY, coinSize, coinSize);
    }

    public void keyPressed(KeyEvent e) {
        int moveAmount = 10;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> boxX -= moveAmount;
            case KeyEvent.VK_RIGHT -> boxX += moveAmount;
            case KeyEvent.VK_UP -> boxY -= moveAmount;
            case KeyEvent.VK_DOWN -> boxY += moveAmount;
        }

        // Keep the box inside window
        boxX = Math.max(0, Math.min(boxX, getWidth() - boxSize));
        boxY = Math.max(0, Math.min(boxY, getHeight() - boxSize));

        // Check collision
        if (isCollision()) {
            boxSize += 5;
            placeCoin();
        }

        repaint();
    }

    public boolean isCollision() {
        return boxX < coinX + coinSize &&
               boxX + boxSize > coinX &&
               boxY < coinY + coinSize &&
               boxY + boxSize > coinY;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new MoveBoxGame();
    }
}
