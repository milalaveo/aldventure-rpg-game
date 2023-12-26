package main;

import tile.TileManger;

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

    //Frames Per Second
    int FPS = 60;



    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    //Set Player's default position;
    int setPlayerX = 100;
    int setPlayerY = 100;
    int setPlayerSpeed = 4;



    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#4383FF"));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        long timer = 0;



        while(gameThread != null) {

            // Update info about character position;
            update();

            // Draw the screen according to updated information;
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void update(){

        if(keyH.upPressed == true){
            setPlayerY -= setPlayerSpeed;
        }
        else if (keyH.downPressed == true) {
            setPlayerY += setPlayerSpeed;
        }
        else if (keyH.leftPressed == true) {
            setPlayerX -= setPlayerSpeed;
        }
        else if (keyH.rightPressed == true){
            setPlayerX += setPlayerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(setPlayerX, setPlayerY, tileSize, tileSize);
        g2.dispose();

    }
}
