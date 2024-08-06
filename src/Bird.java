import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Bird{
    private int x, y, velocity;
    private static final int GRAVITY = 1;
    private static final int FLAP_STRENGTH = - 10;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Bird(int x, int y){
        this.x = x;
        this.y = y;
        this.velocity = 0;
    }

    public void flap(){
        velocity = FLAP_STRENGTH;
    }

    public void move(){
        velocity += GRAVITY;
        y += velocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public int getY(){
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}