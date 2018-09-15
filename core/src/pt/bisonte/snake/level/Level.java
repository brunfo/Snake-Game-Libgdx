package pt.bisonte.snake.level;

import pt.bisonte.snake.entities.Wall;

import java.util.List;

public abstract class Level {
    int rows;
    int columns;
    int gridCell;

    int level;

    int fruitToNextLevel;

    protected List<Wall> walls;


    Level() {
        init();
    }

    public abstract void init();

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getGridCell() {
        return gridCell;
    }

    public int getLevel() {
        return level;
    }

    public int fruitToNextLevel() {
        return fruitToNextLevel;
    }


    public void up() {
        fruitToNextLevel += 10;
    }

    public List<Wall> getWalls(){ return walls;}
}
