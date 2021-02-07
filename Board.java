import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board  extends JPanel {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 33;
    public static final int BLOCK_SIZE = 30;
    private Timer looper;// Timer
    private Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];

    private Color[][] shape = {
            {Color.RED, Color.RED, Color.RED},
            {null, Color.RED, null}
    };
    public Board(){
        looper = new Timer(500, new ActionListener(){
            int n = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println(n++);
            }
        });
        looper.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(10,10,200,200);
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());

//        Color ma shape
        for(int row = 0; row < shape.length; row++){
            for(int column = 0; column < shape[0].length; column++){
                if(shape[row][column] != null){
                    g.setColor(shape[row][column]);
                    g.fillRect(column * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
//draw  board
        g.setColor(Color.WHITE);
        for(int row = 0; row < BOARD_HEIGHT; row++)
        {
            g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * row);
        }
//        vertical
        g.setColor(Color.WHITE);
        for(int col = 0; col < BOARD_WIDTH + 1; col++)
        {
            g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE , BLOCK_SIZE * BOARD_HEIGHT);
        }


    }
}
