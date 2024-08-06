import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Hurdle {
    private int x, y, width, height;

    public Hurdle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move() {
        x -= 5; // Move hurdle to the left
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean intersects(Bird bird) {
        return getBounds().intersects(bird.getBounds());
    }
}