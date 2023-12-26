package tile;

public class TileSet {

    private double progress = 0;
    final double duration;

    private int current = 0;

    final Tile[] tiles;

    public TileSet(Tile[] tiles, double duration) {
        this.tiles = tiles;
        this.duration = duration;
    }

    public void animate(double delta) {
        progress += delta;

        if (progress >= duration) {
            progress = 0;
        }

        current = (int) Math.round((tiles.length - 1) * progress);
    }

    public void set(int value) {
        this.current = value;
    }

    public void reset() {
        this.progress = 0;
        this.current = 0;
    }

    public Tile getCurrent() {
        return tiles[current];
    }
}
