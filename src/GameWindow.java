import javax.swing.JFrame;

public class GameWindow {
    public static final int WIDTH = 1024, HEIGHT = 768;

    private Board board;
    private Title title;
    private JFrame window;

    public GameWindow() {
// Winow attributes
        window = new JFrame("TetrisAWT");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
// Game board
        board = new Board();
        title = new Title(this);

        window.addKeyListener(board);
        window.addKeyListener(title);

        window.add(title);

        window.setVisible(true);
    }
//game loop
    public void startTetris() {
        window.remove(title);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.startGame();
        window.revalidate();
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
