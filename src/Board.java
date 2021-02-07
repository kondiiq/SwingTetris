import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {
    private  static int FPS = 60; //FramePerSecond
    private static int delay = FPS / 1000;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 33;
    public static final int BLOCK_SIZE = 30;
    private Timer looper;// Timer
    private Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];

    private Color[][] shape = {
            {Color.RED, Color.RED, Color.RED},
            {null, Color.RED, null}
    };

    private int gravity_x = 4, gravity_y = 0;
    private int normal = 600;
    private int fast = 50;
    private int delayTimeForMovement = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;

    public Board() {
        looper = new Timer(delay, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
//               checks vertical collision
                if(collision){
                    return;
                }

//                checks horizpntal movement
                if(!(gravity_x + deltaX + shape[0].length > 10) && (gravity_x + deltaX < 0)){
                    gravity_x += deltaX;
                }
                deltaX = 0;

                if(System.currentTimeMillis() - beginTime > delayTimeForMovement){
                    if(!(gravity_y + 1 + shape.length > BOARD_HEIGHT)){
                        gravity_y++;
                    }
                    else {
                        collision = true;
                    }
                    beginTime = System.currentTimeMillis();
                }
                repaint();
            }
        });
        looper.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(10, 10, 200, 200);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

//        Color and draw shapes shape
        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[0].length; column++) {
                if (shape[row][column] != null) {
                    g.setColor(shape[row][column]);
                    g.fillRect(column * BLOCK_SIZE + gravity_x * BLOCK_SIZE, row * BLOCK_SIZE + gravity_y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            delayTimeForMovement = fast;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            deltaX = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            deltaX = - 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            delayTimeForMovement = normal;
        }

    }
}
