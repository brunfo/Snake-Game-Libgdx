package pt.bisonte.snake.level;

import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves to load data files of level games, draw new level or replace existing ones.
 * To replace, simply delete the file of the desired level and place the data in save method.
 * To add new level, place the data in save method, and play until reach the level that has being setup.
 */
public class LevelData {

    private int rows;
    private int columns;
    private int gridCell;

    private int levelID;

    private int fruitToNextLevel;

    private List<Wall> walls;

    LevelData(){}

    LevelData(int rows, int columns, int levelID, int fruitToNextLevel){
        this.rows= rows;
        this.columns=columns;
        this.levelID = levelID;
        this.fruitToNextLevel = fruitToNextLevel;
        init();
    }

    /**
     * Method to save and replace level design.
     */
    void save() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 6;
        fruitToNextLevel = 90;

        init();

        // setup walls
        walls = new ArrayList<>();
        line(0, 0, 0, 5);
        line(1, 5, 4, 5);
        line(4, 6, 4, 12);
        line(3, 12, 0, 12);
        //line(0, 13, 0, 19);





    }

    /**
     * Adds a sets of walls in a direction x (columns) or y (rows).
     * Only allows vertical or horizontal sequences.
     * @param x1 - Coordinate
     * @param y1 - Coordinate
     * @param x2 - Coordinate
     * @param y2 - Coordinate
     * @return true or false
     */
    private boolean line(int x1, int y1, int x2, int y2) {

        int dx = x1 - x2;
        int dy = y1 - y2;
        //distance between to points
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        //the distance between the two points must be different then zero, and dx or dy must be zero.
        if (distance == 0 || dx != 0 && dy != 0)
            return false;

        for (int i = (dx == 0 ? y1 : x1);
             i <= (dx == 0 ? y2 : x2);
             i++) {
            walls.add(new Wall(
                    (dx == 0 ? x1 : i) * gridCell,
                    (dy == 0 ? y1 : i) * gridCell,
                    gridCell));
        }

        return true;
    }

    /**
     * Setup board.
     */
    void init() {
        //setup board
        Game.WIDTH = columns * gridCell;
        Game.HEIGHT = rows * gridCell;

        Game.camera.position.set(Game.WIDTH / 2, Game.HEIGHT / 2, 0);
        Game.camera.update();
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    int getGridCell() {
        return gridCell;
    }

    int getLevelID() {
        return levelID;
    }

    int getFruitToNextLevel() {
        return fruitToNextLevel;
    }

    List<Wall> getWalls() {
        return walls;
    }


}
        