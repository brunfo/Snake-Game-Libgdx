package pt.bisonte.snake.level;

public abstract class Level {
    int rows;
    int columns;
    int gridCell;

    int level;


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

}
