import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;    //scaling for 16x16 tile
    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 18;
    final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;



    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#4383FF"));
        this.setDoubleBuffered(true);


    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {

    }
}
