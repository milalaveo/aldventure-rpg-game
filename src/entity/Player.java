package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    final GameSettings settings = new GameSettings();
    final int tileSize = settings.getTileSize();
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super();

        this.keyH = keyH;
        this.gp = gp;
    }

    public void update() {
        if (this.keyH.upPressed) {
            this.moveY(-this.speed);
        } else if (keyH.downPressed) {
            this.moveY(this.speed);
        } else if (keyH.leftPressed) {
            this.moveX(-this.speed);
        } else if (keyH.rightPressed) {
            this.moveX(this.speed);
        }
    }

    public void repaint(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(this.x, this.y, tileSize, tileSize);
    }
}
