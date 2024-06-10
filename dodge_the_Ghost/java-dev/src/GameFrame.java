import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        Board board = new Board();
        this.setTitle("Game Dev In Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 920);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        add(board);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // GameFrame frame = new GameFrame();
        GameFrame frame = new GameFrame();
    }
}