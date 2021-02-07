import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class GameWindow {

    public static final int WIDTH = 768;
    public static final int HEIGHT = 1024;

    private Board board;
    private JFrame mainwindow;

    public GameWindow(){
        mainwindow = new JFrame("Tetris");
        mainwindow.setSize(WIDTH,HEIGHT);
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwindow.setResizable(false);
        mainwindow.setLocationRelativeTo(null);

        board = new Board();
        mainwindow.add(board);
        mainwindow.setVisible(true);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
