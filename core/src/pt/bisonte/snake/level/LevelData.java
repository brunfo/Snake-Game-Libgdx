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
class LevelData {

    private int rows;
    private int columns;
    private int gridCell;

    private int levelID;

    private int fruitToNextLevel;

    private List<Wall> walls;

    /**
     * Method to save and replace level design.
     */
    void save() {
        rows = 20;
        columns = 20;
        gridCell = 15;
        levelID = 3;
        fruitToNextLevel = 40;

        init();

        // setup walls
        walls = new ArrayList<>();
        for (int i = gridCell; i < Game.WIDTH - gridCell; i += gridCell) {
            walls.add(new Wall(0, i, gridCell));
            walls.add(new Wall(Game.WIDTH - gridCell, i, gridCell));
        }

        for (int i = 0; i < Game.HEIGHT; i += gridCell) {
            walls.add(new Wall(i, 0, gridCell));
            walls.add(new Wall(i, Game.HEIGHT - gridCell, gridCell));
        }

        for (int i = 4 * gridCell; i < Game.WIDTH - 4 * gridCell; i += gridCell) {
            walls.add(new Wall(i, 8 * gridCell, gridCell));
            walls.add(new Wall(i, 12 * gridCell, gridCell));
        }


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
        