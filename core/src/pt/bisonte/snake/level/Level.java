package pt.bisonte.snake.level;


import pt.bisonte.snake.entities.Wall;

import java.util.List;

/**
 * Singleton Level Manager.
 */
public enum Level {

    MANAGER;

    private LevelData level;

    public LevelData getNextLevel() {
        if (level == null)
            level = new LevelData();
        return level;
    }


    public int getRows() {
        return level.getRows();
    }

    public int getColumns() {
        return level.getColumns();
    }

    public int getGrid() {
        return level.getGridCell();
    }

    public int getLevelID() {
        return level.getLevelID();
    }

    public int getFruitToNextLevel() {
        return level.getFruitToNextLevel();
    }

    public List<Wall> getWalls() {
        return level.getWalls();
    }


}
