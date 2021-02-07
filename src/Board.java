import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {
    private static int FPS = 60; //FramePerSecond
    private static int delay = FPS / 1000;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 33;
    public static final int BLOCK_SIZE = 30;
    private Timer looper;// Timer
    private Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];

    private Color[] colors ={Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), Color.decode("#22b14c"),
            Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f4cc")};
    private Shape currentShape;

    private Shape[] shape_form = new Shape[7];

    public Board() {
        shape_form[0] = new Shape(new int[][]{
            {1, 1, 1, 1}  // I-shape
        }, this, colors[0]);

        shape_form[1] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, // T-shape
        }, this, colors[1]);

        shape_form[2] = new Shape(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, //L-Shape
        }, this, colors[2]);

        shape_form[3] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 0, 1},// J-Shape
        }, this, colors[3]);

        shape_form[4] = new Shape(new int[][]{
                {0, 1, 1},
                {1, 1, 0},//s-shape
        }, this, colors[4]);

        shape_form[5] = new Shape(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, //Z-shape
        }, this, colors[5]);

        shape_form[6] = new Shape(new int[][]{
                {1, 1},
                {1, 1}, // Square-Shape
        }, this, colors[6]);

        currentShape = shape_form[0];

        looper = new Timer(delay, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        looper.start();
    }

    private void update(){
        currentShape.update();
    }

    public void setCurrentShape(){
        currentShape = shape_form[1];
        currentShape.reset();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(10, 10, 200, 200);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        currentShape.render(g);

        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                if(board[row][col] != null){
                    g.setColor(board[row][col]);
                    g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }

            }
        }

//draw  board
        g.setColor(Color.WHITE);
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * row);
        }
//        vertical
        g.setColor(Color.WHITE);
        for (int col = 0; col < BOARD_WIDTH + 1; col++) {
            g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, BLOCK_SIZE * BOARD_HEIGHT);
        }


    }

    public  Color [][] getBoard(){
        return board;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            currentShape.speedUP();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            currentShape.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            currentShape.moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            currentShape.speedDOWN();
        }
    }
}
