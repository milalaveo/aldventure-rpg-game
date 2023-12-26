package entity;

public class GameSettings {
    private static GameSettings instance;
    final int originalTileSize = 16;
    final int scale = 3;    //scaling for 16x16 tile
    final int tileSize = originalTileSize * scale;

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public int getTileSize() {
        return tileSize;
    }
}
