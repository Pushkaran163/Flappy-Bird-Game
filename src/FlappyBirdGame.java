import javax.swing.JFrame;

public class FlappyBirdGame {
    public static void main(String[] args){
        JFrame frame = new JFrame("Flappy Bird");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}