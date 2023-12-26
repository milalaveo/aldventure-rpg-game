package entity;

public class Entity {
    protected int x, y;
    public int speed = 4;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity() {
        this.x = 100;
        this.y = 100;
    }

    public void moveX(int value) {
        this.x += value;
    }

    public void moveY(int value) {
        this.y += value;
    }
}
