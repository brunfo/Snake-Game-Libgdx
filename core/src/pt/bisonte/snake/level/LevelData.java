package pt.bisonte.snake.level;

import pt.bisonte.snake.Game;
import pt.bisonte.snake.entities.Wall;

import java.util.ArrayList;
import java.util.List;

public class LevelData {


    private int rows = 15;
    private int columns = 15;
    private int gridCell = 15;

    private int levelID;

    private int fruitToNextLevel;

    private List<Wall> walls;

    {
        levelID = 1;
        fruitToNextLevel = 30;

        //setup board
        Game.WIDTH = columns * gridCell;
        Game.HEIGHT = rows * gridCell;

        Game.camera.position.set(Game.WIDTH / 2, Game.HEIGHT / 2, 0);
        Game.camera.update();

        // setup walls
        walls = new ArrayList<Wall>();
        for (int i = gridCell; i < Game.WIDTH - gridCell; i += gridCell) {
            walls.add(new Wall(0, i, gridCell));
            walls.add(new Wall(Game.WIDTH - gridCell, i, gridCell));
        }

        for (int i = 0; i < Game.HEIGHT; i += gridCell) {
            walls.add(new Wall(i, 0, gridCell));
            walls.add(new Wall(i, Game.HEIGHT - gridCell, gridCell));
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getGridCell() {
        return gridCell;
    }

    public int getLevelID() {
        return levelID;
    }

    public int getFruitToNextLevel() {
        return fruitToNextLevel;
    }

    public List<Wall> getWalls() {
        return walls;
    }
}
        