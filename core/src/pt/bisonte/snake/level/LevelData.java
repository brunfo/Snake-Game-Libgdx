package pt.bisonte.snake.level;

import pt.bisonte.snake.Game;
import static pt.bisonte.snake.entities.Player.Facing;
import pt.bisonte.snake.entities.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves to load data files of level games, draw new level or replace existing ones.
 * To replace, simply delete the file of the desired level and place the data in save method.
 * To add new level, place the data in save method, and play until reach the level that has being setup.
 */
class LevelData {

    private int rows;
    private int columns;
    private int gridCell;

    private int levelID;

    private int fruitToNextLevel;

    private List<Wall> walls;

    private float startX;
    private float startY;
    private Facing facing;

    /**
     * Method to save and replace level design.
     */
    void save() {

        // setup walls
        walls = new ArrayList<>();
        level10();

    }


    private void level1() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 1;
        fruitToNextLevel = 20;

        startX = 5;
        startY =5;
        facing=Facing.UP;

        init();

        line(0, 0, 19, 0);
        line(0, 19, 19, 19);
        line(0, 1, 0, 18);
        line(19, 1, 19, 18);
    }

    private void level2() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 2;
        fruitToNextLevel = 40;

        startX = 5;
        startY =5;
        facing=Facing.UP;

        init();

        line(0, 0, 6, 0);
        line(13, 0, 19, 0);
        line(0, 19, 6, 19);
        line(13, 19, 19, 19);

        line(0, 1, 0, 6);
        line(0, 13, 0, 18);
        line(19, 1, 19, 6);
        line(19, 13, 19, 18);
    }

    private void level3() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 3;
        fruitToNextLevel = 60;

        startX = 3;
        startY =5;
        facing=Facing.UP;

        init();

        line(0, 0, 6, 0);
        line(13, 0, 19, 0);
        line(0, 19, 6, 19);
        line(13, 19, 19, 19);

        line(0, 1, 0, 6);
        line(0, 13, 0, 18);
        line(19, 1, 19, 6);
        line(19, 13, 19, 18);

        line(5, 6, 14, 6);
        line(5, 13, 14, 13);
    }

    private void level4() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 4;
        fruitToNextLevel = 80;
        startX = 3;
        startY =13;
        facing=Facing.UP;

        init();

        line(0, 7, 10, 7);
        line(10, 13, 19, 13);
        line(7, 10, 7, 19);
        line(13, 0, 13, 10);

    }

    private void level5() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 5;
        fruitToNextLevel = 100;
        startX = 10;
        startY =10;
        facing=Facing.UP;
        init();

        line(0, 0, 0, 6);
        line(0, 13, 0, 19);
        line(1, 19, 19, 19);
        line(19, 13, 19, 18);
        line(19, 0, 19, 6);
        line(1, 0, 18, 0);

        line(6, 5, 6, 14);
        line(13, 5, 13, 14);


    }

    private void level6() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 6;
        fruitToNextLevel = 120;
        startX = 10;
        startY =8;
        facing=Facing.UP;
        init();

        line(0, 0, 0, 6);
        line(1, 6, 3, 6);
        line(3, 7, 3, 13);
        line(0, 13, 2, 13);
        line(0, 14, 0, 19);
        line(0, 14, 0, 19);
        line(1, 19, 7, 19);
        line(7, 15, 7, 18);
        line(8, 15, 12, 15);
        line(12, 16, 12, 19);
        line(13, 19, 19, 19);
        line(19, 13, 19, 18);
        line(17, 13, 18, 13);
        line(17, 6, 18, 6);
        line(19, 0, 19, 6);
        line(12, 0, 18, 0);
        line(12, 1, 12, 2);
        line(1, 0, 7, 0);
        line(7, 1, 7, 2);



    }

    private void level7() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 7;
        fruitToNextLevel = 140;
        startX = 15;
        startY =5;
        facing=Facing.DOWN;
        init();

        line(0, 10, 19, 10);
        line(10, 0, 10, 9);
        line(10, 11, 10, 19);
    }

    private void level8() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 8;
        fruitToNextLevel = 160;
        startX = 10;
        startY =15;
        facing=Facing.RIGHT;
        init();

        line(0, 10, 19, 10);
        line(6, 0, 6, 9);
        line(13, 0, 13, 9);
    }

    private void level9() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 9;
        fruitToNextLevel = 180;
        startX = 15;
        startY =15;
        facing=Facing.RIGHT;
        init();

        line(0, 10, 19, 10);
        line(6, 0, 6, 9);
        line(13, 0, 13, 9);
        line(10,11,10,19);
    }

    private void level10() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 10;
        fruitToNextLevel = 200;
        startX = 10;
        startY =9;
        facing=Facing.LEFT;
        init();

        line(0, 6, 19, 6);
        line(0, 12, 8, 12);
        line(12, 12, 19, 12);
        line(0, 19, 3, 19);
        line(7, 19, 16, 19);
        line(12, 0, 12, 5);
        line(8, 13, 8, 18);
        line(0, 16, 0, 18);

    }


    /**
     * Adds a sets of walls in a direction x (columns) or y (rows).
     * Only allows vertical or horizontal sequences.
     *
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


    float getStartx() {
        return startX;
    }

    float getStartY() {
        return startY;
    }

    Facing getFacing() {
        return facing;
    }
}
        