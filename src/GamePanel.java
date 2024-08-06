import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Bird bird;
    private ArrayList<Hurdle> hurdles;
    private Timer timer;
    private int score;
    private boolean gameOver;

    public GamePanel() {
        bird = new Bird(100, 300);
        hurdles = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
        score = 0;
        gameOver = false;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
                    bird.flap();
                }
                if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    restartGame();
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bird.draw(g);
        for (Hurdle hurdle : hurdles) {
            hurdle.draw(g);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 25);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", 250, 300);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Press Enter to Restart", 250, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.move();
            manageHurdles();
            checkCollisions();
        }
        repaint();
    }

    private void manageHurdles() {
        if (hurdles.isEmpty() || hurdles.get(hurdles.size() - 1).getX() < 600) {
            int gapHeight = 200;
            int topHurdleHeight = (int) (Math.random() * (400 - gapHeight));
            hurdles.add(new Hurdle(800, 0, 50, topHurdleHeight));
            hurdles.add(new Hurdle(800, topHurdleHeight + gapHeight, 50, 600 - topHurdleHeight - gapHeight));
        }

        ArrayList<Hurdle> toRemove = new ArrayList<>();
        for (Hurdle hurdle : hurdles) {
            hurdle.move();
            if (hurdle.getX() + hurdle.getWidth() < 0) {
                toRemove.add(hurdle);
                score++;
            }
        }
        hurdles.removeAll(toRemove);
    }

    private void checkCollisions() {
        for (Hurdle hurdle : hurdles) {
            if (hurdle.intersects(bird)) {
                gameOver = true;
                timer.stop();
            }
        }
        if (bird.getY() > getHeight() || bird.getY() < 0) {
            gameOver = true;
            timer.stop();
        }
    }

    private void restartGame() {
        bird = new Bird(100, 300);
        hurdles.clear();
        score = 0;
        gameOver = false;
        timer.start();
    }
}