package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileSet;

import java.awt.*;

enum PLAYER_DIRECTION {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3),
    NONE(4);

    private final int value;

    PLAYER_DIRECTION(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Player extends Entity {
    final GameSettings settings = new GameSettings();
    final int tileSize = settings.getTileSize();
    GamePanel gp;
    KeyHandler keyH;

    // player direction
    PLAYER_DIRECTION direction = PLAYER_DIRECTION.DOWN;
    // player movement (for choosing either moving animation or idle animation)
    PLAYER_DIRECTION movement = PLAYER_DIRECTION.NONE;

    TileSet idleSprites = new TileSet(new Tile[]{
            new Tile("/sprites/player/idle/0.png"),
            new Tile("/sprites/player/idle/1.png"),
            new Tile("/sprites/player/idle/2.png"),
            new Tile("/sprites/player/idle/3.png"),
    }, 0);
    TileSet[] walkingAnimations = new TileSet[]{
            new TileSet(new Tile[]{
                    new Tile("/sprites/player/walk_up/0.png"),
                    new Tile("/sprites/player/walk_up/1.png"),
            }, 1),
            new TileSet(new Tile[]{
                    new Tile("/sprites/player/walk_down/0.png"),
                    new Tile("/sprites/player/walk_down/1.png"),
            }, 1),
            new TileSet(new Tile[]{
                    new Tile("/sprites/player/walk_left/0.png"),
                    new Tile("/sprites/player/walk_left/1.png"),
            }, 1),
            new TileSet(new Tile[]{
                    new Tile("/sprites/player/walk_right/0.png"),
                    new Tile("/sprites/player/walk_right/1.png"),
            }, 1),
    };

    public Player(GamePanel gp, KeyHandler keyH) {
        super();

        this.keyH = keyH;
        this.gp = gp;
    }

    public void update(double delta) {
        if (this.keyH.upPressed) {
            this.moveY(-this.speed);
            direction = PLAYER_DIRECTION.UP;
            movement = PLAYER_DIRECTION.UP;
            walkingAnimations[PLAYER_DIRECTION.UP.getValue()].animate(delta);
        } else if (keyH.downPressed) {
            this.moveY(this.speed);
            direction = PLAYER_DIRECTION.DOWN;
            movement = PLAYER_DIRECTION.DOWN;
            walkingAnimations[PLAYER_DIRECTION.DOWN.getValue()].animate(delta);
        } else if (keyH.leftPressed) {
            this.moveX(-this.speed);
            direction = PLAYER_DIRECTION.LEFT;
            movement = PLAYER_DIRECTION.LEFT;
            walkingAnimations[PLAYER_DIRECTION.LEFT.getValue()].animate(delta);
        } else if (keyH.rightPressed) {
            this.moveX(this.speed);
            direction = PLAYER_DIRECTION.RIGHT;
            movement = PLAYER_DIRECTION.RIGHT;
            walkingAnimations[PLAYER_DIRECTION.RIGHT.getValue()].animate(delta);
        } else {
            // reset animation
            movement = PLAYER_DIRECTION.NONE;
            walkingAnimations[PLAYER_DIRECTION.UP.getValue()].reset();
            walkingAnimations[PLAYER_DIRECTION.DOWN.getValue()].reset();
            walkingAnimations[PLAYER_DIRECTION.LEFT.getValue()].reset();
            walkingAnimations[PLAYER_DIRECTION.RIGHT.getValue()].reset();
        }
    }

    public void repaint(Graphics2D g2) {
        Tile tile;
        if (movement == PLAYER_DIRECTION.NONE) {
            // idle sprite
            idleSprites.set(direction.getValue());
            tile = idleSprites.getCurrent();
        } else {
            // movement
            TileSet movementTiles = walkingAnimations[movement.getValue()];

            tile = movementTiles.getCurrent();
        }

        g2.drawImage(tile.image, this.x, this.y, tileSize, tileSize, null);
    }
}
