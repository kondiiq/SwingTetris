import java.awt.*;

public class Shape {
    private int gravity_x = 4, gravity_y = 0;
    private int normal = 600;
    private int fast = 50;
    private int delayTimeForMovement = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;

    private int[][] cordinates;
    private Board board;
    private Color color;

    public Shape(int[][] cordinates, Board board, Color colors) {
        this.cordinates = cordinates;
        this.board = board;
        this.color = color;
    }

    public void setX(int gravity_x){
        this.gravity_x = gravity_x;
    }

    public void setY(int gravity_y){
        this.gravity_y = gravity_y;
    }

    public void reset(){
        this.gravity_x = 4;
        this.gravity_y = 0;
        collision = false;
    }

    public void update() {
//        checks vertical collision
        if (collision) {
            for (int row = 0; row < cordinates.length; row++) {
                for (int col = 0; col < cordinates[0].length; col++) {
                    if (cordinates[row][col] != 0) {
                        board.getBoard()[gravity_y + row][gravity_x + col] = Color.RED;
                    }
                }
            }
        }
        board.setCurrentShape();
        return;

//                checks horizontal movement
        if (!(gravity_x + deltaX + cordinates[0].length > 10) && !(gravity_x + deltaX < 0)) {
            gravity_x += deltaX;
        }
        deltaX = 0;

        if (System.currentTimeMillis() - beginTime > delayTimeForMovement) {
            if (!(gravity_y + 1 + cordinates.length > Board.BOARD_HEIGHT)) {
                gravity_y++;
            } else {
                collision = true;
            }
            beginTime = System.currentTimeMillis();
        }
    }

    public void render(Graphics g) {
        for (int row = 0; row < cordinates.length; row++) {
            for (int column = 0; column < cordinates[0].length; column++) {
                if (cordinates[row][column] != 0) {
                    g.setColor(Color.BLUE.RED);
                    g.fillRect(column * Board.BLOCK_SIZE + gravity_x * Board.BLOCK_SIZE, row * Board.BLOCK_SIZE + gravity_y * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
                }
            }
        }
    }

    public void speedUP() {
        delayTimeForMovement = fast;
    }

    public void speedDOWN() {
        delayTimeForMovement = normal;
    }

    public void moveRight() {
        deltaX = 1;
    }

    public void moveLeft() {
        deltaX = -1;
    }
}
